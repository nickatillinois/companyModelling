package assemAssist;

import assemAssist.schedulingAlgorithm.Batch;
import assemAssist.schedulingAlgorithm.FIFO;
import assemAssist.workStation.WorkStation;
import assemAssist.schedulingAlgorithm.SchedulingAlgorithm;

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
    private SchedulingAlgorithm schedulingAlgorithm = new FIFO();
    private final ArrayList<SchedulingAlgorithm> schedulers = new ArrayList<>();

        /**
         * Create a new production schedule af a single assembly line that can be managed by the manager
         */
    public ProductionScheduler(Company company){
        schedulers.add(schedulingAlgorithm);
        schedulers.add(new Batch());
        this.assemblyLine = new AssemblyLine(company);
    }


    /**
     * This function wil set the scheduling algorithm
     * @param algorithm the algorithm you will set
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
     * This function will advance the assembly line if that is possible and then returns the nuw state
     * else it returns null. If the scheduling algorithm is "Specification Batch" then it will check of al the orders
     * with the same options set are produced if this is true the scheduling algorithm is changed to "FIFO"
     * @param timeBetweenTwoStates | time entered by the manager that was consumed
     */
    public CarOrder advanceOrders(int timeBetweenTwoStates){
        if (!assemblyLine.canMove())
            return null;

        int nrOfWorkStations = assemblyLine.getWorkStations().size();
        CarOrder finishedOrder = assemblyLine.getWorkStations().get(nrOfWorkStations-1).getCurrentOrder();

        if (getProductionSchedule().isEmpty())
            assemblyLine.move(null,timeBetweenTwoStates);
        else {
            CarOrder carOrder = schedulingAlgorithm.getNextCarOrder();
            assemblyLine.move(carOrder, timeBetweenTwoStates);
        }

        if (schedulingAlgorithm.getName().equals("Specification Batch"))
            if (schedulingAlgorithm.getProductionSchedule() == schedulers.get(0).getProductionSchedule())
                selectSchedulingAlgorithm("FIFO");
        return finishedOrder;
    }
    
    /**
     * This function return a list of car orders of the given garageHolder.
     * @param garageHolder | the garage holder of whom the car orders are to be found
     * @return list of car order
     */
    public List<CarOrder> getOrdersFromGarageHolder(String garageHolder){
        List<CarOrder> orderFromGarageHolder = schedulingAlgorithm.getProductionSchedule().stream().
                filter(p-> Objects.equals(p.getGarageHolder(), garageHolder)).collect(Collectors.toList());
        orderFromGarageHolder.addAll(getPendingOrdersFromGarageHolder(garageHolder));
        return orderFromGarageHolder;
    }

    /**
     * This function returns a list of pending car orders of the given garage holder.
     * @param garageHolder | the garage holder of whom the car orders are to be found
     * @return list of car orders
     */
    public List<CarOrder> getPendingOrdersFromGarageHolder(String garageHolder){
        List<CarOrder> pending = new ArrayList<>();
        for(WorkStation workStation : getAssemblyLine().getWorkStations()){
            if (workStation.getCurrentOrder() != null)
                if (Objects.equals(workStation.getCurrentOrder().getGarageHolder(), garageHolder))
                    pending.add( workStation.getCurrentOrder());
        }
        return pending;
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
     * Gives a list of pending orders in the work stations.
     * @return the pending orders in the workstations of the assembly line
     */
    public List<CarOrder> getPendingOrders(){
        ArrayList<CarOrder> pending = new ArrayList<>();
        for (WorkStation workStation : getAssemblyLine().getWorkStations())
            if (workStation.getCurrentOrder() != null)
                pending.add(workStation.getCurrentOrder());
        return pending;
    }


}
