package assemAssist.comparator;

import assemAssist.CarOrder;

import java.util.Comparator;

/**
 * This class is used to compare two CarOrder objects.
 *
 * @author Team 10
 */
public class PendingCarOrderComparator implements Comparator<CarOrder> {


    /**
     * Constructor for PendingCarOrderComparator.
     */
    public PendingCarOrderComparator() {
    }


    /**
     * Compares two CarOrder objects.
     *
     * @param order1 The first CarOrder object to compare.
     * @param order2 The second CarOrder object to compare.
     * @return The result of the comparison.
     */
    @Override
    public int compare(CarOrder order1, CarOrder order2) {
        if (order1.getEstCompletionTime().isBefore(order2.getEstCompletionTime())) {
            return -1;}
        else if (order1.getEstCompletionTime().isAfter(order2.getEstCompletionTime())){
            return 1;}
        else
            return 0;
    }

}