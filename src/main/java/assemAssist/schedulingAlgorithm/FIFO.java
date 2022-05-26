package assemAssist.schedulingAlgorithm;

import assemAssist.CarOrder;
import java.lang.reflect.Executable;

import java.util.List;

/**
 * A class representing the FIFO scheduling algorithm.
 *
 * @author SWOP team 10
 */
public class FIFO extends SchedulingAlgorithm {
    /**
     * The name of the scheduling Algorithm
     */
    String name = "FIFO";



    /**
     * @return null
     * @throws IllegalCallerException can not be operated in this algorithm
     *
     */
    @Override
    public List<String> possibleBatch() throws IllegalCallerException {
        return null;

    }

    /**
     * This function can not execute in this scheduling algorithm en throws an error.
     * @param selectBatch the batch that you will select
     * @throws IllegalCallerException this methode can not be called in this algorithm
     */
    @Override
    public Executable selectBatch(String selectBatch) throws IllegalCallerException{
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

}
