package assemAssist;

import assemAssist.schedulingAlgorithm.Batch;
import assemAssist.schedulingAlgorithm.FIFO;
import assemAssist.schedulingAlgorithm.SchedulingAlgorithm;
import assemAssist.workStation.WorkStation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class representing a production schedule.
 *
 * @author SWOP Team 10
 */
public class ProductionScheduler {
    /**
     * The assembly line of the production scheduler.
     */
    private final AssemblyLine assemblyLine;

    /**
     * The current scheduling algorithm, with default FIFO.
     */
    private SchedulingAlgorithm schedulingAlgorithm = new FIFO();

    /**
     * The list with al the different scheduling algorithms.
     */
    private final ArrayList<SchedulingAlgorithm> schedulers = new ArrayList<>();

    /**
     * Create a new production schedule af a single assembly line that can be managed by the company.
     * @param company The company that has this assembly line.
     */
    public ProductionScheduler(Company company){

        schedulers.add(schedulingAlgorithm);
        schedulers.add(new Batch());
        this.assemblyLine = new AssemblyLine(company);
        this.clearWaitingList();
    }


    /**
     * This function wil set the scheduling algorithm to another of the list of schedulers.
     * @param algorithm the name of the algorithm you will set
     * @throws IllegalArgumentException if the algorithm is not valid.
     */
    public void selectSchedulingAlgorithm(String algorithm) throws IllegalArgumentException{
        for (SchedulingAlgorithm algorithm1 : schedulers )
            if (algorithm.equals(algorithm1.getName()))
                setSchedulingAlgorithm(algorithm1);
        if (!Objects.equals(getSchedulingAlgorithm().getName(), algorithm))
            throw new IllegalArgumentException("This is not a valid argument!");
    }


    /**
     * This function returns a list of the possible scheduling algorithms.
     * @return the list of possible scheduling algorithms.
     */
    public List<String> getSchedulingAlgorithms(){
        List<String> algorithms = new ArrayList<>();
        for( SchedulingAlgorithm schedulingAlgorithm : schedulers)
            algorithms.add(schedulingAlgorithm.getName());
        return algorithms;
}

    /**
     * This function return a list of al the scheduling algorithm where this production scheduler can choose to schedule.
     * @return All the possible scheduling algorithms.
     */
    public List<SchedulingAlgorithm> getSchedulers(){
        return schedulers;
    }

    /**
     * This function returns the assembly line on which this production schedule runs.
     * @return assemblyLine
     */
    public AssemblyLine getAssemblyLine(){
        return this.assemblyLine;
    }

    /**
     * This function gives a list of the orders that wait to be produced in the order they are to be produced in the
     * current algorithm.
     * @return productionSchedule
     */
     List<CarOrder> getProductionSchedule() {
        return schedulingAlgorithm.getProductionSchedule();
    }

    /**
     * This function will advance the assembly line if that is possible and then returns the new state
     * else it returns null. If the scheduling algorithm is "Specification Batch" then it will check of al the orders
     * with the same options set are produced if this is true the scheduling algorithm is changed to "FIFO"
     * @param timeBetweenTwoStates | time entered by the manager that was consumed
     * @throws RuntimeException | something goes wrong when moving the assemblyline.
     */
    public CarOrder advanceOrders(int timeBetweenTwoStates) throws RuntimeException {
        if (!assemblyLine.canMove())
            return null;

        int nrOfWorkStations = assemblyLine.getWorkStations().size();
        CarOrder finishedOrder = assemblyLine.getWorkStations().get(nrOfWorkStations-1).getCurrentOrder();

        if (getProductionSchedule().isEmpty())
            try {
                assemblyLine.move(null, timeBetweenTwoStates);
            } catch (RuntimeException e) {
                throw new RuntimeException(e.getMessage());
            }
        else {
            CarOrder carOrder = schedulingAlgorithm.getNextCarOrder();
            try {
                assemblyLine.move(carOrder, timeBetweenTwoStates);
            } catch (RuntimeException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        //Check of all the car orders from the batch are produced and if that is it set the scheduling algorithm back to FIFO
        if (schedulingAlgorithm.getName().equals("Specification Batch"))
            if (schedulingAlgorithm.getProductionSchedule() == schedulers.get(0).getProductionSchedule())
                selectSchedulingAlgorithm("FIFO");
        return finishedOrder;
    }
    
    /**
     * This function return a list of car orders of the given garageHolder.
     * @return list of car order
     */
    private List<CarOrder> getWaitingOrders(){
        return new ArrayList<>(schedulingAlgorithm.getProductionSchedule());
    }

    /**
     * This function will give a list of al the orders that not are completed in the system.
     * @return Get al the orders.
     */
    public List<CarOrder> getPendingOrders(){
        List<CarOrder> allOrders = new ArrayList<>();
        allOrders.addAll(getOrdersInProduction());
        allOrders.addAll(getWaitingOrders());
        return allOrders;
    }

    /**
     * This function returns a list of pending car orders
     * @return list of car orders
     */
    private List<CarOrder> getOrdersInProduction(){
        List<CarOrder> inProductionOrders = new ArrayList<>();
        for(WorkStation workStation : getAssemblyLine().getWorkStations()){
            if (workStation.getCurrentOrder() != null)
                    inProductionOrders.add( workStation.getCurrentOrder());
        }
        return inProductionOrders;
    }

    /**
     * @return The current scheduling algorithm
     */
    public SchedulingAlgorithm getSchedulingAlgorithm() {
        return schedulingAlgorithm;
    }

    /**
     * @param schedulingAlgorithm selected algorithm
     * @throws IllegalArgumentException if the algorithm is not valid!
     */
    public void setSchedulingAlgorithm(SchedulingAlgorithm schedulingAlgorithm) throws IllegalArgumentException {
        if (!schedulers.contains(schedulingAlgorithm))
            throw new IllegalArgumentException("This is not a valid scheduling algorithm");
        this.schedulingAlgorithm = schedulingAlgorithm;
    }

    /**
     * This function reset the carOrderList of the schedulers.
     */
    private void clearWaitingList(){
        this.getSchedulingAlgorithm().clearWaitingList();
    }
}
