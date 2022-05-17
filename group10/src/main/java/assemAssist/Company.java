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

/**
 * The Company class represents the company that offers products in the Catalogue, and manages the
 * orders of the customers in the production scheduler, keeps track of the statistics of the
 * company, and notifies the observers of the tasks that are completed. It has a list of all the
 * completed orders.
 *
 * @author Team 10
 */
public class Company implements TaskObserver {

    /**
     * The prodiction scheduler of the company.
     */
    private final ProductionScheduler productionScheduler;

    /**
     * The catalogue of the company.
     */
    private final Catalog catalog;

    /**
     * The list of completed orders.
     */
    private ArrayList<CarOrder> completedCarOrders;

    /**
     * The list of Statistics of the company.
     */
    private final List<Stats> statistics = new ArrayList<>();

    /**
     * Constructs a new Company, initializing the production scheduler, the catalogue, and the
     * list of completed orders.
     * It also creates the statistics of the company.
     */
    public Company(){
        this.productionScheduler = new ProductionScheduler();
        this.catalog = new Catalog();
        this.completedCarOrders = new ArrayList<CarOrder>();
        //statistics aanmaken
        statistics.add(new WorkingDayStats());
        statistics.add(new DelayStats());
    }

    /**
     * Returns the production scheduler of the company.
     * @return the production scheduler of the company.
     */
    public ProductionScheduler getProductionScheduler() {
        return productionScheduler;
    }

    /**
     * Returns the catalogue of the company.
     * @return the catalogue of the company.
     */
    public Catalog getCatalog() {
        return catalog;
    }

    /**
     * Returns the list of completed orders.
     * @return the list of completed orders.
     */
    public ArrayList<CarOrder> getCompletedCarOrders() {
        return completedCarOrders;
    }

    /**
     * Sets the list of completed orders.
     * @param completedCarOrders the list of completed orders.
     * @throws IllegalArgumentException if the given list is null.
     */
    public void setCompletedCarOrders(ArrayList<CarOrder> completedCarOrders) {
        if(completedCarOrders == null) {
            throw new IllegalArgumentException("completedCarOrders must not be null.");
        }
        this.completedCarOrders = completedCarOrders;
    }


    /**
     * Returns a set of CarOrders that are completed and ordered by the given garage holder.
     * The set is sorted by the completion date (most recent first).
     *
     * @return set of CarOrders that are completed and ordered by the given garage holder
     * @param name the garage holder to get the completed CarOrders for
     * @throws IllegalArgumentException if the given name is null
     *                                  or empty
     *                                  or if only whitespace is given
     */
    private ArrayList<CarOrder> getCompletedOrdersFromGarageHolder(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Garage holder name cannot be null.");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Garage holder name cannot be empty.");
        }
        if(name.trim().length() <= 0) {
            throw new IllegalArgumentException("Garage holder name cannot be whitespace.");
        }
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
     * @throws IllegalArgumentException if the given name is null
     *                                  or empty
     *                                  or if only whitespace is given
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
     * @throws IllegalArgumentException if the given ID is not strictly positive
     *                                  or if the given garage holder is null
     *                                  or if the given garage holder is empty
     *                                  or if only whitespace is given
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

    /**
     * Returns a set of model names that are offered by the company.
     * @return set of model names that are offered by the company
     */
    public HashSet<String> getAvailableModels() {
        return catalog.getAvailableModelNames();
    }

    /**
     * Returns a string representation of the model with the given name.
     * @param model the name of the model to get the string representation of
     * @throws IllegalArgumentException if the given model is null
     *                                  or if the given model is empty
     *                                  or if only whitespace is given
     * @throws IllegalModelException if the given model is not offered by the company
     * @return set of model names that are offered by the company
     */
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
        return catalog.getModelSpecificationString(model);
    }

    /**
     * Returns a map of components and their respective offerd options for the given modelname.
     * @param model the name of the model to get the map of components and their respective offerd options for
     *              from the catalog
     * @throws IllegalArgumentException if the given model is null
     *                                  or if the given model is empty
     *                                  or if only whitespace is given
     * @throws IllegalModelException if the given model is not offered by the company
     * @return map of components and their respective offerd options for the given modelname
     */
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
        return catalog.getOptions(model);
    }

    /**
     * Returns the statistics of the company from the last time it was updated following the given number of days.
     * @param fromXLastDays the number of days to get the statistics from
     *                      (the statistics are updated every day)
     * @throws IllegalArgumentException if the given number of days is negative
     * @return statistics of the company from the last time it was updated following the given number of days
     */
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

    /**
     * Returns an array consisting of two elements:
     * the first element is a list of pending orders that were not completed.
     * the second element is a list of completed orders.
     * @return an array consisting of two elements.
     *         the first element is a list of pending orders that were not completed.
     *         the second element is a list of completed orders.
     *         if there are no pending orders, the first element is empty.
     *         if there are no completed orders, the second element is empty.
     *         if there are no pending or completed orders, both elements are empty.
     * @param garageHolder the garage holder to get the orders from
     * @throws IllegalArgumentException if the given garage holder is null
     *                                  or if the given string is empty
     *                                  or if only whitespace is given
     */
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

    /**
     * Function that accepts a map of specifications, a garage holder name, and a chosen car type.
     * The function generates a new order for the garage holder with the given specifications and
     * adds it to the production queue.
     * @param chosenOptions the specifications of the car to order
     *                       (the specifications must be valid)
     * @param garageHolder the garage holder to order the car for
     *                     (the garage holder must be valid)
     * @param chosenModel the type of car to order
     *                     (the chosen model must be valid)
     * The function returns the estimated completion time of the order in the format
     *                     "yyyy-MM-dd HH:mm:ss".
     *
     * @return the estimated completion time of the order in the format
     *                     "yyyy-MM-dd HH:mm:ss".
     * @throws IllegalCompletionDateException if the completion date is invalid
     * @throws IllegalConstraintException if there exist constraints not following the correct syntax.
     * @throws IllegalModelException if the chosen model is not offered by the car manufacturer.
     * @throws OptionThenComponentException if there is an option specified but not the implied component.
     * @throws OptionAThenOptionBException if there is an option specified but not the implied option(s).
     * @throws RequiredComponentException if there is a required component not specified.
     */
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

        // When an order is added to the production schedule, it is possible it can already be added to the assembly line.
        update(0);

        // format carOrder.getEstCompletionTime() in a nice way
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return carOrder.getEstCompletionTime().format(formatter);
    }

    /**
     * Updates the observer when a task is finished.
     * @param time the time the task was finished.
     *             (the time must be valid)
     * @throws IllegalArgumentException if the given time is negative
     */
    public void update(int time) {
        if(time < 0) {
            throw new IllegalArgumentException("time cannot be negative.");
        }
        CarOrder finishedOrder = productionScheduler.advanceOrders(time);
        if (finishedOrder != null) {
            completedCarOrders.add(finishedOrder);
        }
    }
}