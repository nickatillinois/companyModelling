package assemAssist.comparator;

import assemAssist.CarOrder;

import java.util.Comparator;


/**
 * Class representing a comparator for completed car orders.
 */
public class CompletedCarOrderComparator implements Comparator<CarOrder> {


    /**
     * Constructor for CompletedCarOrderComparator.
     */
    public CompletedCarOrderComparator() {
    }

    /**
     * Compares two car orders.
     *
     * @param order1 the first car order comparing
     * @param order2 the second car order comparing
     * @return a negative integer, zero, or a positive integer as the first
     * car order is less than, equal to, or greater than the second car order
     */
    public int compare(CarOrder order1, CarOrder order2) {
        if (order1.getCompletionTime().isBefore(order2.getCompletionTime())) {
            return -1;}
        else if (order1.getCompletionTime().isAfter(order2.getCompletionTime())){
            return 1;}
        else
            return 0;
    }

}
