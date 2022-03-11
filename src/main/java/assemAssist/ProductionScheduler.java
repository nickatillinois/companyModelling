package assemAssist;

import assemAssist.carOrder.CarOrder;
import assemAssist.user.GarageHolder;
import assemAssist.workStation.WorkStation;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class ProductionScheduler {
private AssemblyLine assemblyLine;
private List<CarOrder>  productionSchedule;
private PriorityQueue<CarOrder> completedOrders;

public ProductionScheduler(ArrayList<WorkStation> workStations,CarManufacturingCompany carManufacturingCompany){
    productionSchedule = new ArrayList<>();
    completedOrders = new PriorityQueue<>();
    this.assemblyLine = new AssemblyLine(workStations,carManufacturingCompany);
}

    public AssemblyLine getAssemblyLine(){
        return this.assemblyLine;
    }

    public List<CarOrder> getProductionSchedule() {
        return productionSchedule;
    }

    public PriorityQueue<CarOrder> getCompletedOrders() {
        return completedOrders;
    }

    public void completedCarOrder(CarOrder carOrder){
        if (productionSchedule.contains(carOrder)) {
            carOrder.setCompleted(true);
            productionSchedule.remove(carOrder);
            addCompletedOrder(carOrder);
        }
        else
            throw new IllegalArgumentException("The carOrder is not a part of the productionSchedule");

    }

    private void addCompletedOrder(CarOrder carOrder) throws NullPointerException {
        if (carOrder == null)
            throw new NullPointerException("The carOrder is null");
        if (carOrder.isCompleted())
            completedOrders.add(carOrder);
        else
            throw new IllegalArgumentException("The current carOrder can't be add to the Completed list because he is not completed!");

    }

    public List<CarOrder> advanceAssemblyLine() {
        //TODO
        return null;
    }

    public List<CarOrder> advanceOrders(List<CarOrder> advanceAssemblyScheme){
        //TODO
        return getProductionSchedule();
    }

    public void addOrderToProductionSchedule(CarOrder carOrder){
        productionSchedule.add(carOrder);

    }
    public List<CarOrder> getOrdersFromGarageHolder(GarageHolder garageHolder){
        return productionSchedule.stream().filter(p->p.getGarageholder() == garageHolder).collect(Collectors.toList());
    }
}
