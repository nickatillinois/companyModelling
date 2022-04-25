package assemAssist.schedulingAlgorithm;

import assemAssist.CarOrder;
import assemAssist.exceptions.IllegalConstraintException;
import assemAssist.exceptions.IllegalModelException;

import java.util.ArrayList;
import java.util.List;

public abstract class SchedulingAlgorithm {
    /**
     * This is the list af al car orders that are waiting to be produced.
     */
    List<CarOrder> carOrderList = new ArrayList<>();

    /**
     * This function gives a list of possible batch of options where for there are more than three car order with that
     * set of options
     * @return List with a batch of options where for there are three car Orders or null
     */
    public abstract List<String> possibleBatch();

    /**
     * Set the batch to the new batch
     * @param selectBatch the batch that you will select
     */
    public abstract void selectBatch(String selectBatch);

    /**
     * This function gives the car order that will be the next for the assembly line
     * @return car order to produce next
     */
    public abstract CarOrder getNextCarOrder();

    /**
     * This function returns the name of the scheduling algorithm
     * @return the name
     */
    public abstract String getName();

    /**
     * This function return the list of how the car orders will be produced.
     * @return the list of car orders in the order they would be produced.
     */
    public abstract List<CarOrder> getProductionSchedule();

    /**
     * This function add a new car order to the car order list.
     * @param order new carOrder
     * @throws IllegalConstraintException If the car model is not a valid confirmation.
     * @throws IllegalModelException
     */
    public  void addOrderToProductionSchedule(CarOrder order) throws IllegalConstraintException, IllegalModelException {
        if (order.isValidCarModel())
            carOrderList.add(order);
        //TODO set the estimate complition time
        else
            throw new IllegalConstraintException("This car order has not a valid car model!");
    }
}
