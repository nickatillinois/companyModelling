package assemAssist.comparator;

import assemAssist.CarOrder;

import java.util.Comparator;

/**
 * This class is used to compare two CarOrder objects.
 *
 * @author Team 10
 */
public class PendingCarOrderComparator implements Comparator {


    /**
     * Constructor for PendingCarOrderComparator.
     */
    public PendingCarOrderComparator() {
    }


    /**
     * Compares two CarOrder objects.
     *
     * @param o1 The first CarOrder object to compare.
     * @param o2 The second CarOrder object to compare.
     * @return The result of the comparison.
     */
    @Override
    public int compare(Object o1, Object o2) {
        CarOrder c1 = (CarOrder) o1;
        CarOrder c2 = (CarOrder) o2;
        if (c1.getEstCompletionTime().isBefore(c2.getEstCompletionTime())) {
            return 1;}
        else if (c1.getEstCompletionTime().isAfter(c2.getEstCompletionTime())){
            return -1;}
        else
            return 0;
    }

}
