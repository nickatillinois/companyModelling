package assemAssist.Comparator;

import assemAssist.CarOrder;

import java.util.Comparator;


/**
 * Class representing a comparator for completed car orders.
 */
public class CompletedCarOrderComparator implements Comparator {


    /**
     * Constructor for CompletedCarOrderComparator.
     */
    public CompletedCarOrderComparator() {
    }

    /**
     * Compares two car orders.
     *
     * @param o1 the first car order to compare
     * @param o2 the second car order to compare
     * @return a negative integer, zero, or a positive integer as the first
     * car order is less than, equal to, or greater than the second car order
     */
    @Override
    public int compare(Object o1, Object o2) {
        CarOrder c1 = (CarOrder) o1;
        CarOrder c2 = (CarOrder) o2;
        if (c1.getCompletionTime().isBefore(c2.getCompletionTime())) {
            return 1;}
        else if (c1.getCompletionTime().isAfter(c2.getCompletionTime())){
            return -1;}
        else
            return 0;
    }

}
