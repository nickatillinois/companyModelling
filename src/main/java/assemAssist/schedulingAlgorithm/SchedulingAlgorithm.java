package assemAssist.schedulingAlgorithm;

import assemAssist.CarOrder;
import assemAssist.Catalog;
import assemAssist.exceptions.*;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.List;


/**
 * A class representing an abstract scheduling algorithm.
 *
 * @author SWOP team 10
 */
public abstract class SchedulingAlgorithm {
    /**
     * This is the list af al car orders that are waiting to be produced.
     */
    static List<CarOrder> carOrderList =  new ArrayList<>();

    /**
     * This function gives a list of possible batch of options where for there are more than three car order with that
     * set of options
     *
     * @return List with a batch of options where for there are three car Orders or null
     */
    public abstract List<String> possibleBatch();

    /**
     * Set the batch to the new batch
     *
     * @param selectBatch the batch that you will select
     * @return
     */
    public abstract Executable selectBatch(String selectBatch);

    /**
     * This function gives the car order that will be the next for the assembly line
     *
     * @return car order to produce next
     */
    public abstract CarOrder getNextCarOrder();

    /**
     * This function returns the name of the scheduling algorithm
     *
     * @return the name
     */
    public abstract String getName();

    /**
     * This function return the list of how the car orders will be produced that is different for each type(Batch,Fifo).
     *
     * @return the list of car orders in the order they would be produced.
     */
    public abstract List<CarOrder> getProductionSchedule();


    /**
     * This function add a new car order to the car order list.
     *
     * @param order new carOrder
     * @throws IllegalConstraintException If the car model is not a valid confirmation.
     */

    public void addOrderToProductionSchedule(CarOrder order) throws IllegalConstraintException, IllegalCompletionDateException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        try{
            carOrderList.add(order);
            int workingTime = order.getWorkingMinutesWorkStation()* 3;
            boolean before = true;
            if (carOrderList != null) {
                for (CarOrder order1 : getProductionSchedule()) {
                    if (order == order1)
                        before = false;
                    if (before)
                        workingTime = workingTime + order1.getWorkingMinutesWorkStation();
                    else
                        break;
                }
            }
            order.setEstCompletionTime(order.getOrderingTime().plusMinutes(workingTime));

        }
        catch (Exception e) {
            throw e;
        }
    }

    /**
     * This function will reset the CarOrderList.
     */
    public void clearWaitingList(){
        carOrderList.clear();
    }
}