package assemAssist;

import assemAssist.Comparator.CompletedCarOrderComparator;
import assemAssist.Comparator.PendingCarOrderComparator;
import assemAssist.exceptions.*;
import assemAssist.observer.TaskObserver;
import assemAssist.statistics.DelayStats;
import assemAssist.statistics.Stats;
import assemAssist.statistics.WorkingDayStats;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

public class Company implements TaskObserver {
    private final ProductionScheduler productionScheduler;
    private final Catalog catalog;
    private ArrayList<CarOrder> completedCarOrders;
    private final List<Stats> statistics = new ArrayList<>();

    public Company(){
        this.productionScheduler = new ProductionScheduler();
        this.catalog = new Catalog();
        this.completedCarOrders = new ArrayList<CarOrder>();
        //statistics aanmaken
        statistics.add(new WorkingDayStats());
        statistics.add(new DelayStats());
    }

    public ProductionScheduler getProductionScheduler() {
        return productionScheduler;
    }
    public Catalog getCatalog() {
        return catalog;
    }
    public ArrayList<CarOrder> getCompletedCarOrders() {
        return completedCarOrders;
    }
    public void setCompletedCarOrders(ArrayList<CarOrder> completedCarOrders) {
        this.completedCarOrders = completedCarOrders;
    }


    /**
     * Returns a set of CarOrders that are completed and ordered by the given garage holder.
     * The set is sorted by the completion date (most recent first).
     *
     * @return set of CarOrders that are completed and ordered by the given garage holder
     * @param name the garage holder to get the completed CarOrders for
     */
    private ArrayList<CarOrder> getCompletedOrdersFromGarageHolder(String name) {
        ArrayList<CarOrder> orders = new ArrayList<>();
        for (CarOrder carOrder : this.completedCarOrders) {
            if (carOrder.getGarageHolder().equalsIgnoreCase(name)) {
                orders.add(carOrder);
            }
        }
        CompletedCarOrderComparator comparator = new CompletedCarOrderComparator();
        // sort the orders by the comparator
        orders.sort(comparator);
        return orders;
    }

    /**
     * Returns a set of CarOrders that are pending and ordered by the given garage holder.
     * The set is ordered by the estimated completion date (most recent first).
     *
     * @return set of CarOrders that are pending and ordered by the given garage holder
     * @param name the garage holder to get the pending CarOrders for
     */
    private List<CarOrder> getPendingOrdersFromGarageHolder(String name) {
        List<CarOrder> orders = getProductionScheduler().getOrdersFromGarageHolder(name);
        orders.removeIf(CarOrder::isCompleted);
        PendingCarOrderComparator comparator = new PendingCarOrderComparator();
        orders.sort(comparator);
        return orders;
    }


    /**
     * Returns an array specific to the given garage holder consisting of two elements.
     * The first element is a list of CarOrders that are pending.
     * The second element is a list of CarOrders that are completed.
     * The first element is sorted by the estimated completion date (most recent first).
     * The second element is sorted by the completion date (most recent first).
     *
     * @return set of CarOrders that are pending or completed and ordered by the given garage holder
     * @param name the garage holder to get the pending and completed CarOrders for
     */
    public List<CarOrder>[] getOrdersFromGarageHolder(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Garage holder name cannot be null.");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Garage holder name cannot be empty.");
        }
        if(name.trim().length() <= 0) {
            throw new IllegalArgumentException("Garage holder name cannot be whitespace.");
        }
        List<CarOrder>[] orders = new java.util.List[2];
        orders[0] = getPendingOrdersFromGarageHolder(name);
        orders[1] = getCompletedOrdersFromGarageHolder(name);
       return orders;
    }


    /**
     * Returns a list consisting of strings. For a pending CarOrder, these are the specifications, the timestamp of ordering and the
     * estimated production time. For completed orders, these are the specifications, the timestamp
     * of ordering and the timestamp of completion.
     *
     * @return set of CarOrders that are pending or completed and ordered by the given garage holder
     * @param ID the id of the order of which the details will be returned
     * @param garageHolder the garage holder who ordered the order belonging to the given ID
     */
    public ArrayList<String> getOrderDetails(int ID, String garageHolder) throws OrderNotFoundException {
        if(ID <= 0) {
            throw new IllegalArgumentException("ID must be strictly positive.");
        }
        if (garageHolder == null) {
            throw new IllegalArgumentException("Garage holder name cannot be null.");
        }
        if (garageHolder.isEmpty()) {
            throw new IllegalArgumentException("Garage holder name cannot be empty.");
        }
        if(garageHolder.trim().length() <= 0) {
            throw new IllegalArgumentException("Garage holder name cannot be whitespace.");
        }
        ArrayList<CarOrder> pendingOrders = (ArrayList<CarOrder>) this.getOrdersFromGarageHolder(garageHolder)[0];
        ArrayList<CarOrder> completedOrders = (ArrayList<CarOrder>) this.getOrdersFromGarageHolder(garageHolder)[1];
        pendingOrders.addAll(completedOrders);
        for (CarOrder carOrder : pendingOrders) {
            if (carOrder.getOrderID() == ID ) {
                return carOrder.getOrderDetails();
            }
        }
        throw new OrderNotFoundException("ID: " + ID + " was not found in pending or completed orders from this garage holder.");
    }

    public HashSet<String> getAvailableModels() {
        return catalog.getAvailableModelNames();
    }
    public String selectModelString(String model) throws IllegalModelException, IllegalArgumentException {
        if (model == null) {
            throw new IllegalArgumentException("Model name cannot be null.");
        }
        if (model.isEmpty()) {
            throw new IllegalArgumentException("Model name cannot be empty.");
        }
        if(model.trim().length() <= 0) {
            throw new IllegalArgumentException("Model name cannot be whitespace.");
        }
        return catalog.getModelSpecification(model);
    }
    public TreeMap<String, HashSet<String>> selectModel(String model) throws IllegalModelException {
        if (model == null) {
            throw new IllegalArgumentException("Model name cannot be null.");
        }
        if (model.isEmpty()) {
            throw new IllegalArgumentException("Model name cannot be empty.");
        }
        if(model.trim().length() <= 0) {
            throw new IllegalArgumentException("Model name cannot be whitespace.");
        }
        return catalog.getModelSpecifications(model);
    }

    public List<String> getStatistics(int fromXLastDays) {
        if(fromXLastDays < 0) {
            throw new IllegalArgumentException("fromXLastDays must be strictly positive.");
        }
        List<String> result = new ArrayList<>();
        for (Stats stat : statistics) {
            result.addAll(stat.getStatistics(fromXLastDays, LocalDate.now()));
        }
        return result;
    }

    public ArrayList<String[]>[] newLogin(String garageHolder) {
        if (garageHolder == null) {
            throw new IllegalArgumentException("Garage holder name cannot be null.");
        }
        if (garageHolder.isEmpty()) {
            throw new IllegalArgumentException("Garage holder name cannot be empty.");
        }
        if(garageHolder.trim().length() <= 0) {
            throw new IllegalArgumentException("Garage holder name cannot be whitespace.");
        }
        // create an array of 2 lists each containing a number of arrays of strings
        ArrayList<String[]>[] result = new ArrayList[2];
        ArrayList<String[]> pendingOrdersString = new ArrayList<>();
        ArrayList<String[]> completedOrdersString = new ArrayList<>();
        // get the pending and completed orders from this garage holder
        List<CarOrder>[] orders = this.getOrdersFromGarageHolder(garageHolder);
        List<CarOrder> pendingOrders = orders[0];
        List<CarOrder> completedOrders = orders[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // take the ID and the estimated completion date from each pending order
        for (CarOrder carOrder : pendingOrders) {
            pendingOrdersString.add(new String[]{Integer.toString(carOrder.getOrderID()), carOrder.getEstCompletionTime().format(formatter)});
        }
        // take the ID and the completion date from each completed order
        for (CarOrder carOrder : completedOrders) {
            completedOrdersString.add(new String[]{Integer.toString(carOrder.getOrderID()), carOrder.getCompletionTime().format(formatter)});
        }
        result[0] = pendingOrdersString;
        result[1] = completedOrdersString;
        // return the result
        return result;
    }


    public String completeOrderingForm(TreeMap<String, String> chosenOptions, String garageHolder, String chosenModel) throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        if(chosenOptions == null) {
            throw new IllegalArgumentException("chosenOptions cannot be null.");
        }
        if(garageHolder == null) {
            throw new IllegalArgumentException("garageHolder cannot be null.");
        }
        if(garageHolder.isEmpty()) {
            throw new IllegalArgumentException("garageHolder cannot be empty.");
        }
        if(garageHolder.trim().length() <= 0) {
            throw new IllegalArgumentException("garageHolder cannot be whitespace.");
        }
        if(chosenModel == null) {
            throw new IllegalArgumentException("chosenModel cannot be null.");
        }
        if(chosenModel.isEmpty()) {
            throw new IllegalArgumentException("chosenModel cannot be empty.");
        }
        if(chosenModel.trim().length() <= 0) {
            throw new IllegalArgumentException("chosenModel cannot be whitespace.");
        }
        CarModel carModel = new CarModel(chosenModel, chosenOptions);
        CarOrder carOrder = new CarOrder(garageHolder, carModel);
        this.productionScheduler.getSchedulingAlgorithm().addOrderToProductionSchedule(carOrder);
        for (Stats stat : statistics) {
            carOrder.addObserver(stat);
        }
        // format carOrder.getEstCompletionTime() in a nice way
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return carOrder.getEstCompletionTime().format(formatter);
    }

    /**
     * Updates the observer when a task is finished.
     */
    public void update(int time) {
        CarOrder finishedOrder = productionScheduler.advanceOrders(time);
        if (finishedOrder != null) {
            completedCarOrders.add(finishedOrder);
        }
    }
}
