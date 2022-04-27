package assemAssist;

import assemAssist.etc.CompletedCarOrderComparator;
import assemAssist.etc.PendingCarOrderComparator;
import assemAssist.exceptions.IllegalModelException;
import assemAssist.exceptions.OrderNotFoundException;
import assemAssist.statistics.Stats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Company {
    private final ProductionScheduler productionScheduler;
    private Catalog catalog;
    private ArrayList<CarOrder> completedCarOrders;
    private List<Stats> statistics;

    public Company(){
        this.productionScheduler = new ProductionScheduler();
        this.catalog = new Catalog();
        this.completedCarOrders = new ArrayList<CarOrder>();
        //statistics aanmaken
        //statistics.add(new WorkingDayStats());
        //statistics.add(new DelayStats());
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
     * The set is ordered by the completion date (most recent first).
     *
     * @return set of CarOrders that are completed and ordered by the given garage holder
     * @param name the garage holder to get the completed CarOrders for
     */
    private ArrayList<CarOrder> getCompletedOrdersFromGarageHolder(String name) {
        ArrayList<CarOrder> orders = new ArrayList<>();
        for (CarOrder carOrder : this.completedCarOrders) {
            if (carOrder.getGarageholder().equalsIgnoreCase(name)) {
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
        List<CarOrder> orders = getProductionScheduler().getPendingOrdersFromGarageHolder(name);
        PendingCarOrderComparator comparator = new PendingCarOrderComparator();
        // sort the orders by the comparator
        orders.sort(comparator);
        return orders;
    }


    /**
     * Returns an array consisting of two elements. The first element is a set of CarOrders that are pending
     * , the second element is a set of CarOrders that are completed, both specific to the given garage holder
     *
     * @return set of CarOrders that are pending or completed and ordered by the given garage holder
     * @param name the garage holder to get the pending and completed CarOrders for
     */
    public List<CarOrder>[] getOrdersFromGarageHolder(String name) {
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
    public String selectModelString(String model) throws IllegalModelException {
        return catalog.getModelSpecification(model);
    }
    public Map<String, HashSet<String>> selectModel(String model) throws IllegalModelException {
        return catalog.getModelSpecifications(model);
    }

    public List<String> getStatistics(int fromXLastDays) {
        List<String> result = new ArrayList<>();
        for (Stats stat : statistics) {
            result.addAll(stat.getStatistics(fromXLastDays));
        }
        return result;
    }

    public ArrayList<String[]>[] newLogin(String garageHolder) {
        // create an array of 2 lists each containing a number of arrays of strings
        ArrayList<String[]>[] result = new ArrayList[2];
        ArrayList<String[]> pendingOrdersString = result[0];
        ArrayList<String[]> completedOrdersString = result[1];
        // get the pending and completed orders from this garage holder
        List<CarOrder>[] orders = this.getOrdersFromGarageHolder(garageHolder);
        List<CarOrder> pendingOrders = orders[0];
        List<CarOrder> completedOrders = orders[1];
        // take the ID and the estimated completition date from each pending order
        for (CarOrder carOrder : pendingOrders) {
            pendingOrdersString.add(new String[]{Integer.toString(carOrder.getOrderID()), carOrder.getEstCompletionTime().toString()});
        }
        // take the ID and the completion date from each completed order
        for (CarOrder carOrder : completedOrders) {
            completedOrdersString.add(new String[]{Integer.toString(carOrder.getOrderID()), carOrder.getCompletionTime().toString()});
        }
        // return the result
        return result;

    }

    public String completeOrderingForm(Map<String, String> chosenOptions, String garageHolder, String chosenModel) {
        // create a new car model
        CarModel carModel = new CarModel(chosenModel, chosenOptions);
        // create a new car order
        CarOrder carOrder = new CarOrder(garageHolder, carModel, null);
        return garageHolder;
    }

    // TODO implement
    public LocalDate addOrderToProductionSchedule(CarOrder order) {
        // stuur order naar production schedule
        return LocalDate.now();
    }

}
