package assemAssist.schedulingAlgorithm;

import assemAssist.CarModel;
import assemAssist.CarOrder;
import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.exceptions.IllegalConstraintException;
import assemAssist.exceptions.IllegalModelException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Batch extends SchedulingAlgorithm{
    /**
     * The name of this scheduling algorithm.
     */
    String name = "Specification Batch";
    private String batch = null;
    private List<CarOrder> productionSchedule = null;

    /**
     * This function returns the first order in the production schedule.
     * @return the first car order in production schedule
     * @throws IllegalArgumentException if the production schedule is null
     */
    @Override
    public CarOrder getNextCarOrder() throws IllegalArgumentException{
        if (productionSchedule == null)
            throw new IllegalArgumentException("There is no production schedule because there is no batch selected!");
        if (productionSchedule.isEmpty())
            return null;
        return getProductionSchedule().get(0);
    }

    /**
     * This function returns the name of this scheduling algorithm
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * This function will return the order in which car order will be produced
     * @return the list of orders in order to produce
     * @throws IllegalArgumentException if the batch is current null
     */
    @Override
    public List<CarOrder> getProductionSchedule() throws IllegalArgumentException {
        if (productionSchedule == null)
            throw new IllegalArgumentException("There is no production schedule because there is no batch selected!");
        return productionSchedule;
    }

    /**
     * This function will set the batch to the new value en the production schedule will be updated.
     * @param batch the new batch
     */
    public void selectBatch(String batch){
        this.batch = batch;
        productionSchedule  =new ArrayList<>();
        List<CarOrder> fifo = new ArrayList<>();
        for(CarOrder order: carOrderList) {
            if (Objects.equals(order.getCarModel().getChosenOptionsString(), getBatch()))
                productionSchedule.add(order);
            else
                fifo.add(order);
        }
        productionSchedule.addAll(fifo);

    }

    /**
     * This function returns the current selected batch.
     * @return the current batch
     */
    public String getBatch(){
        return batch;
    }

    /**
     * This function will search all possible batch of option where for there are more than  or three car orders.
     * @return a list of possible batches
     */
    public  List<String> possibleBatch(){
        List<String> batchs = new ArrayList<>();
        for (CarOrder order: carOrderList) {
            String specification = order.getCarModel().getChosenOptionsString();
            int counter = 1;
            for (CarOrder order1: carOrderList){
                if (order != order1)
                    if(!batchs.contains(specification))
                        if(Objects.equals(specification, order1.getCarModel().getChosenOptionsString()))
                            counter++;
                if(counter >= 3 ){
                    batchs.add(specification);
                    break;
                }

            }
        }

        return null;
    }
}
