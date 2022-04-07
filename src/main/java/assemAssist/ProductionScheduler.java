package assemAssist;

import assemAssist.carOrder.*;
import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.workStation.WorkStation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class representing a production schedule.
 *
 * @author SWOP Team 10
 */
public class ProductionScheduler {
private final AssemblyLine assemblyLine;
private final List<CarOrder> fifoProductionSchedule;
private List<CarOrder> productionSchedule;
private final ArrayList<String> schedulingAlgorithms;
private String algorithm;
private static final String FIRSTALGORITHM = "FIFO";
private static final String SECONDALGORITHM = "Specification Batch";

    /**
     * Create a new production schedule af a single assembly line that can be managed by the manager
     * @param assemblyLine | the assemblyline belonging to the production scheduler
     */
public ProductionScheduler( AssemblyLine assemblyLine){
    fifoProductionSchedule = new ArrayList<>();
    this.assemblyLine = assemblyLine;
     schedulingAlgorithms = new ArrayList<String>();
    schedulingAlgorithms.add(FIRSTALGORITHM);
    schedulingAlgorithms.add(SECONDALGORITHM);
    if (getSchedulingAlgorithms().contains(FIRSTALGORITHM))
        setAlgorithm(FIRSTALGORITHM);
    else
        throw new IllegalArgumentException("The algorithm you will be set is not a valid one!");
}
    public List<List<String>> selectSchedulingAlgorithm(String algorithm) throws IllegalArgumentException{
    if (getSchedulingAlgorithms().contains(algorithm)){
        setAlgorithm(algorithm);
        if (getAlgorithm().equals(FIRSTALGORITHM)){
            productionSchedule = fifoProductionSchedule;
        }
        else if (getAlgorithm().equals(SECONDALGORITHM)){
            List<List<String>> listOfBatchOptions = specificBatchAlgorithm();
            if (listOfBatchOptions == null) {
                productionSchedule = fifoProductionSchedule;
                setAlgorithm(FIRSTALGORITHM);
            }
            else
                return listOfBatchOptions;
        }
    else
        throw new IllegalArgumentException("The given algorithm is not valid!");
    }
        return null;
    }

    private List<List<String>> specificBatchAlgorithm(){
    //TODO gaat kijken welke carOrders allemaal dezelfde options hebben. Indien deze niet bestaan return null
    return null;
    }

    public void selectBatchSet(List<String> options){
        List<CarOrder> carOrderWithSameOptions = new ArrayList<>();
        List<CarOrder> otherCarOrders = new ArrayList<>();
        for (CarOrder carOrder : fifoProductionSchedule){
            if(true /*options == carOrder.getCarModel().getOptions()*/){
                //TODO deze conditie invullen
                carOrderWithSameOptions.add(carOrder);
            }
            else
                otherCarOrders.add(carOrder);
        }
        if (carOrderWithSameOptions.size() < 3)
            throw new IllegalCallerException("The methode was called without a valid options there are not 3 or more cars with this set of options");
        carOrderWithSameOptions.addAll(otherCarOrders);
        setAlgorithm(SECONDALGORITHM);
        productionSchedule = carOrderWithSameOptions;
    }

    /**
     * @return the list of possible scheduling algorithms.
     */
    public List<String> getSchedulingAlgorithms(){
        return schedulingAlgorithms;
}


    /**
     * This function returns the assembly line on whits this production schedule runs.
     * @return assemblyLine
     */
    public AssemblyLine getAssemblyLine(){
        return this.assemblyLine;
    }

    /**
     * This function gifs a list of the orders that wait to be produced in the order she get produced with the current
     * algorithm.
     * @return productionSchedule
     */
    public List<CarOrder> getProductionSchedule() {
        return productionSchedule;
    }

    /**
     * This function calculate a simulation of an advanced assembly line without knowing of this is possible.
     * @return simulation
     */
    public List<CarOrder> simulateAdvanceAssemblyLine() {
        List<CarOrder> currentState = getCurrentState();
        ArrayList<CarOrder> simulatedState = new ArrayList<>(3);
        if (getProductionSchedule().size() == 0)
            simulatedState.add(null);
        else if(assemblyLine.remainWorkingTime() >=3 )
            simulatedState.add( getProductionSchedule().get(0));
        else
            simulatedState.add(null);
        simulatedState.add( currentState.get(0));
        simulatedState.add( currentState.get(1));
        return simulatedState;
    }

    /**
     * This function will advance the assembly line if that is possible and then returns the nuw state
     * else it return null.
     * @param timeBetweenTwoStates | time entered by the manager that was consumed
     */
    public void advanceOrders(int timeBetweenTwoStates){
        if (!assemblyLine.canMove())
            return;
        if (productionSchedule.equals(fifoProductionSchedule))
            selectSchedulingAlgorithm(FIRSTALGORITHM);
        if (productionSchedule.isEmpty())
            assemblyLine.move(null,timeBetweenTwoStates);
        else {
            CarOrder carOrder= productionSchedule.remove(0);
            fifoProductionSchedule.remove(carOrder);
            assemblyLine.move(carOrder, timeBetweenTwoStates);
        }
    }

/*    *//**
     * This function returns a list of carOrders that have pending task in his workstation.
     * @return list of car orders
     *//*
    public List<String> canNotMove(){
        if (assemblyLine.canMove())
            throw new IllegalCallerException("The caller can move!");
        return assemblyLine.canNotMove();
    }*/

    /**
     * This function returns a list whit car orders, the car order at index i is in progress in the i'th workstation.
     * @return list of car orders
     */
    public List<CarOrder> getCurrentState(){
        return  assemblyLine.getCurrentState();
    }

    /**
     * This function will add a real car order to the production schedule.
     * @param carOrder | the car order added to the proction schedule
     * @throws NullPointerException | car order is null
     */
    public LocalDate addOrderToProductionSchedule(CarOrder carOrder) throws NullPointerException, IllegalCompletionDateException {
        if (carOrder != null) {
            productionSchedule.add(carOrder);
            int carsToProduceToday = Math.min(assemblyLine.remainWorkingTime() - 2,0);
            float carsToProduceLater = productionSchedule.size() -carsToProduceToday;
            int daysNeeded = (int)Math.ceil(carsToProduceLater/14);
            return LocalDate.now().plusDays(daysNeeded);
        }
        else
            throw new NullPointerException("You can not add null to the production schedule.");

    }

    /**
     * This function return a list of carorders of the given garageHolder.
     * @param garageHolder | the garageholder of whom the carorders are to be found
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
        orderFromGarageHolder.addAll(getPendingOrdersFromGarageHolder(garageHolder));
        return orderFromGarageHolder;
    }

    /**
     * This function returns a list of pending car orders of the given garage holder.
     * @param garageHolder | the garage holder of whom the car orders are to be found
     * @return list of car orders
     */
    private List<CarOrder> getPendingOrdersFromGarageHolder(String garageHolder){
        List<CarOrder> pending = productionSchedule.stream().filter(p-> Objects.equals(p.getGarageholder(), garageHolder)).collect(Collectors.toList());
        for(WorkStation workStation : getAssemblyLine().getWorkStations()){
            if (workStation.getCurrentOrder() != null)
                if (Objects.equals(workStation.getCurrentOrder().getGarageholder(), garageHolder))
                    pending.add( workStation.getCurrentOrder());
        }
        return pending;
    }


    /**
     * @param algorithm the selected algorithm
     * @throws IllegalArgumentException if the algorithm is not valid!
     */
    public void setAlgorithm(String algorithm) throws IllegalArgumentException {
        if (getSchedulingAlgorithms().contains(algorithm))
            this.algorithm = algorithm;
        else
            throw new IllegalArgumentException("The algorithm you will be set is not a valid one!");

    }

    /**
     * @return The current algorithm
     */
    public String getAlgorithm() {
        return algorithm;
    }
}
