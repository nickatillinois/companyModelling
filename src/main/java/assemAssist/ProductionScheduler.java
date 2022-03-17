package assemAssist;

import assemAssist.carOrder.CarOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Class representing a production schedule.
 *
 * @author SWOP Team 10
 */
public class ProductionScheduler {
private AssemblyLine assemblyLine;
private List<CarOrder>  productionSchedule;
private List<CarOrder> completedOrders;
private String manager;

    /**
     * Create a new production schedule af a single assembly line that can be managed by the manager
     * @param manager
     * @param assemblyLine
     */
public ProductionScheduler(String manager, AssemblyLine assemblyLine){
    productionSchedule = new ArrayList<>();
    completedOrders = new ArrayList<>();
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

    /**
     * This function gifs a list of the orders that wait to be produced in FCFS order.
     * @return productionSchedule
     */
    public List<CarOrder> getProductionSchedule() {
        return productionSchedule;
    }

    /**
     * This function returns a list of the completed orders!
     * @return completedOrders
     */
    public List<CarOrder> getCompletedOrders() {
        return completedOrders;
    }

    /**
     * This function will set the car order as completed and add the car order to the complitedCarorder list.
     * @param carOrder
     */
    public void completedCarOrder(CarOrder carOrder) throws NullPointerException{
        if (carOrder != null) {
            carOrder.setCompleted(true);
            productionSchedule.remove(carOrder);
            addCompletedOrder(carOrder);
        }
        else
            throw new NullPointerException("The given carOrder was null!");


    }

    /**
     * This function will add a carOrder that already is completed to a List of completed Orders.
     * @param carOrder
     * @throws NullPointerException
     */
    private void addCompletedOrder(CarOrder carOrder) throws NullPointerException {
        if (carOrder == null)
            throw new NullPointerException("The carOrder is null");
        if (carOrder.isCompleted())
            completedOrders.add(carOrder);
        else
            throw new IllegalArgumentException("The current carOrder can't be add to the Completed list because he is not completed!");

    }

    /**
     * This function calculate a simulation of an advanced assembly line without knowing of this is possible.
     * @return simulation
     */
    public List<CarOrder> simulateAdvanceAssemblyLine() {
        List<CarOrder> currentState = getCurrentState();
        List<CarOrder> nullList = new ArrayList<>(3);
        nullList.add(null);
        nullList.add(null);
        nullList.add(null);
        ArrayList<CarOrder> simulatedState = new ArrayList<>(3);
        if (getProductionSchedule().size() == 0)
            simulatedState.add(null);
        else if(assemblyLine.remainWorkingTime() >=3 )
            simulatedState.add( getProductionSchedule().get(0));
        else
            simulatedState.add(null);
        simulatedState.add( currentState.get(0));
        simulatedState.add( currentState.get(1));
        if (simulatedState == nullList )
            simulatedState.set(0, getProductionSchedule().get(0));
            assemblyLine.nextDay();
        return simulatedState;
    }

    /**
     * This function will advance the assembly line if that is possible and then returns the nuw state
     * else it return null.
     * @param timeBetweenToStates
     * @return new state or null
     */
    public List<CarOrder> advanceOrders(int timeBetweenToStates){
        if (!assemblyLine.canMove())
            return null;
        return assemblyLine.move(productionSchedule.remove(0),timeBetweenToStates).subList(0,2) ;
    }

    /**
     * This function returns a list of carOrders that have pending task in his workstation.
     * @return list of car orders
     */
    public List<CarOrder> canNotMove(){
        if (assemblyLine.canMove())
            throw new IllegalCallerException("The caller can move!");
        return assemblyLine.canNotMove();
    }

    /**
     * This function returns a list whit car orders, the car order at index i is in progress in the i'th workstation.
     * @return list of car orders
     */
    public List<CarOrder> getCurrentState(){
        return  assemblyLine.getCurrentState();
    }

    /**
     * This function will add a real car order to the production schedule.
     * @param carOrder
     * @throws NullPointerException
     */
    public void addOrderToProductionSchedule(CarOrder carOrder) throws NullPointerException{
        if (carOrder != null)
            productionSchedule.add(carOrder);
        else
            throw new NullPointerException("You can not null to the production schedule.");

    }

    /**
     * This function return a list of carorders of the given garageHolder.
     * @param garageHolder
     * @return list of car order
     */
    public List<CarOrder> getOrdersFromGarageHolder(String garageHolder){
        List<CarOrder> orderFromGarageHolder = productionSchedule.stream().
                filter(p-> Objects.equals(p.getGarageholder(), garageHolder)).collect(Collectors.toList());
        List<CarOrder> orderInWorkstations = getCurrentState();
        for (CarOrder order : orderInWorkstations){
            if (order != null)
                orderFromGarageHolder.add(order);
        }
        return orderFromGarageHolder;
    }

    /**
     * This function returns a list of completed car orders of the given garage holder.
     * @param garageHolder
     * @return list of car orders
     */
    public List<CarOrder> getCompletedOrdersFromGarageHolder(String garageHolder){
        return completedOrders.stream().filter(p-> Objects.equals(p.getGarageholder(), garageHolder)).collect(Collectors.toList());
    }

    public List getCurrentAndFutureStatusAndTaskStatus(){
        List currentAndFutureStatus = new ArrayList<>();
        List<CarOrder> curuntStatus  = getCurrentState();
        List<CarOrder> futureStatus  = simulateAdvanceAssemblyLine();
        List<String> curuntStatusAndTasks = new ArrayList<>();
        for(int i = 0 ; i < getCurrentState().size() ; i++){
            CarOrder carOrder = getCurrentState().get(i);
            String s = getAssemblyLine().getWorkStations().get(i).toString() + "; ";
            if(carOrder != null) {
                s += "Model: " + carOrder.getCarModel();
                getAssemblyLine().getWorkStations().get(i).getTasksAndStatus();
            }
            else
                s += "No Order in this workstation";
            curuntStatusAndTasks.add(s);
        }
        currentAndFutureStatus.add(curuntStatusAndTasks);
        return currentAndFutureStatus;
    }
}
