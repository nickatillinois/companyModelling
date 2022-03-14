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

public ProductionScheduler(ArrayList<WorkStation> workStations){
    productionSchedule = new ArrayList<>();
    completedOrders = new PriorityQueue<>();
    this.assemblyLine = new AssemblyLine(workStations);
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
    //maakt een simulatie
    public List<CarOrder> simulateAdvanceAssemblyLine() {
        List<CarOrder> currentState = getCurrentState();
        List<CarOrder> nullList = new ArrayList<>(3);
        nullList.set(0,null);
        nullList.set(1,null);
        nullList.set(2,null);
        ArrayList<CarOrder> simulatedState = new ArrayList<>(3);
        simulatedState.set(2, currentState.get(1));
        simulatedState.set(1, currentState.get(0));
        if (getProductionSchedule().size() == 0)
            simulatedState.set(0,null);
        else if(assemblyLine.remainWorkingTime() >=3 )
            simulatedState.set(0, getProductionSchedule().get(0));
        else
            simulatedState.set(0,null);
        if (simulatedState == nullList )
            simulatedState.set(0, getProductionSchedule().get(0));
            assemblyLine.nextDay();
        return simulatedState;
    }

    //gaat de advance uitvoeren als het kan
    public List<CarOrder> advanceOrders(int timeBetweenToStates){
        if (!assemblyLine.canMove())
            return null;
        return assemblyLine.move(productionSchedule.remove(0),timeBetweenToStates).subList(0,2) ;
    }

    public List<CarOrder> canNotMove(){
        if (assemblyLine.canMove())
            throw new IllegalCallerException("The caller can move!");
        return assemblyLine.canNotMove();
    }

    public List<CarOrder> getCurrentState(){
        return  assemblyLine.getCurrentState();
    }

    public void addOrderToProductionSchedule(CarOrder carOrder){
        productionSchedule.add(carOrder);

    }

    public List<CarOrder> getOrdersFromGarageHolder(GarageHolder garageHolder){
        return productionSchedule.stream().filter(p->p.getGarageholder() == garageHolder).collect(Collectors.toList());
    }

}
