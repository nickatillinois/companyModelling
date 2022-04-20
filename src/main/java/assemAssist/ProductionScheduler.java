package assemAssist;

import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.exceptions.IllegalConstraintException;
import assemAssist.exceptions.IllegalModelException;
import assemAssist.observer.StatisticsObservable;
import assemAssist.observer.StatisticsObserver;
import assemAssist.schedulingAlgorithm.Batch;
import assemAssist.schedulingAlgorithm.FIFO;
import assemAssist.workStation.WorkStation;
import assemAssist.schedulingAlgorithm.SchedulingAlgorithm;

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
private SchedulingAlgorithm schedulingAlgorithm = new FIFO();
private final ArrayList<SchedulingAlgorithm> schedulers = new ArrayList<>();
    /**
     * Create a new production schedule af a single assembly line that can be managed by the manager
     */
public ProductionScheduler(){
    schedulers.add(new FIFO());
    schedulers.add(new Batch());
    this.assemblyLine = new AssemblyLine();
}


    public void selectSchedulingAlgorithm(String algorithm) throws IllegalArgumentException{
        for (SchedulingAlgorithm algorithm1 : schedulers )
            if (Objects.equals(algorithm, algorithm1.getName()))
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
    private List<CarOrder> getProductionSchedule() {
        return schedulingAlgorithm.getProductionSchedule();
    }

    /**
     * This function will advance the assembly line if that is possible and then returns the nuw state
     * else it returns null. If the scheduling algorithm is "Specification Batch" than it will check of al the orders
     * with the same options set are produced if this is true the scheduling algorithm is changed to "FIFO"
     * @param timeBetweenTwoStates | time entered by the manager that was consumed
     */
    public void advanceOrders(int timeBetweenTwoStates){
        if (!assemblyLine.canMove())
            return;
        if (getProductionSchedule().isEmpty())
            assemblyLine.move(null,timeBetweenTwoStates);
        else {
            CarOrder carOrder = schedulingAlgorithm.getNextCarOrder();
            assemblyLine.move(carOrder, timeBetweenTwoStates);
        }
    }


    /**
     * This function returns a list whit car orders, the car order at index i is in progress in the workstation.
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
    public LocalDate addOrderToProductionSchedule(CarOrder carOrder) throws NullPointerException, IllegalCompletionDateException, IllegalConstraintException, IllegalModelException {
        if (carOrder != null) {
            schedulingAlgorithm.addOrderToProductionSchedule(carOrder);
            int carsToProduceToday = Math.min(assemblyLine.remainWorkingTime() - 2,0);
            float carsToProduceLater = getProductionSchedule().size() -carsToProduceToday;
            int daysNeeded = 0;
            if (carsToProduceLater != 0 )
                daysNeeded = (int)Math.ceil(carsToProduceLater/14);
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
        List<CarOrder> orderFromGarageHolder = schedulingAlgorithm.getProductionSchedule().stream().
                filter(p-> Objects.equals(p.getGarageholder(), garageHolder)).collect(Collectors.toList());
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
                if (Objects.equals(workStation.getCurrentOrder().getGarageholder(), garageHolder))
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

    public List<CarOrder> getPendingOrders(){
        ArrayList<CarOrder> pending = new ArrayList<>();
        for (WorkStation workStation : getAssemblyLine().getWorkStations())
            if (workStation.getCurrentOrder() != null)
                pending.add(workStation.getCurrentOrder());
        return pending;
    }

}
