package assemAssist.schedulingAlgorithm;

import assemAssist.CarOrder;
import assemAssist.Catalog;
import assemAssist.exceptions.IllegalConstraintException;
import assemAssist.exceptions.IllegalModelException;

import java.time.LocalDate;
import java.util.List;

public class FIFO extends SchedulingAlgorithm {
    /**
     * The name of the scheduling Algorithm
     */
    String name = "FIFO";



    /**
     * @return nothing
     * @throws IllegalCallerException can not be operated in this algorithm
     */
    @Override
    public List<String> possibleBatch() throws IllegalCallerException {
        throw new IllegalCallerException("This algorithm can not return possible batches");

    }

    /**
     * @param selectBatch the batch that you will select
     * @throws IllegalCallerException this methode can not be called in this algorithm
     */
    @Override
    public void selectBatch(String selectBatch) throws IllegalCallerException{
        throw new  IllegalCallerException("This algoritme contains no batches.");
    }

    /**
     * This function returns the car order that is first on the production schedule.
     * @return the car order that will be produced next
     */
    @Override
    public CarOrder getNextCarOrder() {
        if (carOrderList.isEmpty())
                return null;
        return carOrderList.remove(0);
    }

    /**
     * Return the name of the scheduling algorithm.
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * This function will return a list of car orders in FIFO.
     * @return list of car order in FIFO
     */
    @Override
    public List<CarOrder> getProductionSchedule() {
        return carOrderList;
    }

    /**
     * This function add a new car order to the car order list.
     *
     * @param order new carOrder
     * @throws IllegalConstraintException If the car model is not a valid confirmation.
     * @throws IllegalModelException
     */
    @Override
    public void addOrderToProductionSchedule(CarOrder order) throws IllegalConstraintException, IllegalModelException {
        if (order.isValidCarModel())
            carOrderList.add(order);
            //TODO set the estimate complition time

        else
            throw new IllegalConstraintException("This car order has not a valid car model!");

    }


}
