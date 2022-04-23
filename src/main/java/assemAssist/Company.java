package assemAssist;

import assemAssist.etc.CompletedCarOrderComparator;
import assemAssist.etc.PendingCarOrderComparator;
import assemAssist.exceptions.IllegalModelException;
import assemAssist.exceptions.OrderNotFoundException;
import assemAssist.statistics.Stats;
import assemAssist.statistics.WorkingDayStats;

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
     * Returns a set of CarOrders that are completed and ordered by the given garage holder
     *
     * @return a set of CarOrders that are completed and ordered by the given garage holder
     * @param name the garage holder to get the completed CarOrders for
     */
    private ArrayList<CarOrder> getCompletedOrdersFromGaragHolder(String name) {
        ArrayList<CarOrder> orders = new ArrayList<CarOrder>();
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
    // return the pending orders of a given garage holder
    private List<CarOrder> getPendingOrdersFromGaragHolder(String name) {
        List<CarOrder> orders = getProductionScheduler().getPendingOrdersFromGarageHolder(name);
        PendingCarOrderComparator comparator = new PendingCarOrderComparator();
        // sort the orders by the comparator
        orders.sort(comparator);
        return orders;
    }

    public List<CarOrder>[] getOrdersFromGarageHolder(String name) {
        List<CarOrder>[] orders = new java.util.List[2];
        orders[0] = getPendingOrdersFromGaragHolder(name);
        orders[1] = getCompletedOrdersFromGaragHolder(name);
       return orders;
    }


    public ArrayList<String> getOrderDetails(int ID, String garageHolder) throws OrderNotFoundException {
        ArrayList<CarOrder> pendingOrders = (ArrayList<CarOrder>) this.getOrdersFromGarageHolder(garageHolder)[0];
        ArrayList<CarOrder> completedOrders = (ArrayList<CarOrder>) this.getOrdersFromGarageHolder(garageHolder)[1];
        pendingOrders.addAll(completedOrders);
        for (CarOrder carOrder : pendingOrders) {
            // verwijder isCompleted check als werkt
            if (carOrder.getOrderID() == ID ) {
                return carOrder.getOrderDetails();
            }
        }
        throw new OrderNotFoundException("ID: " + Integer.toString(ID) + " was not found in pending or completed orders from this garageholder.");
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
}
