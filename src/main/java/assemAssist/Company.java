package assemAssist;

import assemAssist.etc.CompletedCarOrderComparator;
import assemAssist.etc.PendingCarOrderComparator;
import assemAssist.exceptions.IllegalModelException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class Company {
    private final ProductionScheduler productionScheduler;
    private Catalog catalog;
    private ArrayList<CarOrder> completedCarOrders;

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
            if (carOrder.getGarageholder().equals(name)) {
                orders.add(carOrder);
            }
        }
        CompletedCarOrderComparator comparator = new CompletedCarOrderComparator();
        // sort the orders by the comparator
        orders.sort(comparator);
        return orders;
    }
    // return the pending orders of a given garage holder
    private ArrayList<CarOrder> getPendingOrdersFromGaragHolder(String name) {
        ArrayList<CarOrder> orders = new ArrayList<CarOrder>();
        for (CarOrder carOrder : this.productionScheduler.getPendingOrders()) {
            if (carOrder.getGarageholder().equals(name)) {
                orders.add(carOrder);
            }
        }
        PendingCarOrderComparator comparator = new PendingCarOrderComparator();
        // sort the orders by the comparator
        orders.sort(comparator);
        return orders;
    }

    public ArrayList<CarOrder>[] getOrdersFromGarageHolder(String name) {
        ArrayList<CarOrder>[] orders = new ArrayList[2];
        orders[0] = getPendingOrdersFromGaragHolder(name);
        orders[1] = getCompletedOrdersFromGaragHolder(name);
       return orders;
    }


    // return order details
    public ArrayList<String> getPendingOrderDetails(int ID) {
        ArrayList<CarOrder> pendingOrders = productionScheduler.getPendingOrders();
        for (CarOrder carOrder : pendingOrders) {
            // verwijder isCompleted check als werkt
            if (carOrder.getOrderID() == ID && !carOrder.isCompleted()) {
                return carOrder.getPendingOrderDetails();
            }
        }
        return null;
    }
    public ArrayList<String> getCompletedOrderDetails(int ID) {
        ArrayList<CarOrder> completedOrders = getCompletedCarOrders();
        for (CarOrder carOrder : completedOrders) {
            // verwijder isCompleted check als werkt
            if (carOrder.getOrderID() == ID && carOrder.isCompleted()) {
                return carOrder.getPendingOrderDetails();
            }
        }
        return null;
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
