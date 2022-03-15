package assemAssist;

import assemAssist.carOrder.CarOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Class representing a production schedule.
 */
public class ProductionScheduler {
private AssemblyLine assemblyLine;
private List<CarOrder>  productionSchedule;
private PriorityQueue<CarOrder> completedOrders;
private String manager;

    /**
     * Create a new production schedule af a single assembly line that can be managed by the manager
     * @param manager
     * @param assemblyLine
     */
public ProductionScheduler(String manager, AssemblyLine assemblyLine){
    productionSchedule = new ArrayList<>();
    completedOrders = new PriorityQueue<>();
    this.assemblyLine = assemblyLine;
    setManager(manager);
}

    /**
     * This function return the manager that can manage the assembly line.
     * @return manager
     */
    public String getManager() {
        return manager;
    }

    /**
     * Set the manager of the product schedule to the parameter.
     * @param manager
     */
    public void setManager(String manager) {
        this.manager = manager;
    }

    /**
     * This function returns the assembly line on whits this production schedule runs.
     * @return assemblyLine
     */
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

    public List<CarOrder> getOrdersFromGarageHolder(String garageHolder){
        return productionSchedule.stream().filter(p->p.getGarageholder() == garageHolder).collect(Collectors.toList());
    }
    public List<CarOrder> getCompletedOrdersFromGarageHolder(String garageHolder){
        return completedOrders.stream().filter(p->p.getGarageholder() == garageHolder).collect(Collectors.toList());
    }

}
