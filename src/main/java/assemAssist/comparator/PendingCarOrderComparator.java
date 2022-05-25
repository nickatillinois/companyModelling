package assemAssist.comparator;

import assemAssist.CarOrder;

import java.util.Comparator;

/**
 *
 * Class representing a comparator for pending car orders that sorts them by
 * their estimated completion time (the earliest first).
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
     * Compares two pending CarOrder objects.
     *
     * @param order1 The first pending CarOrder object to compare.
     * @param order2 The second pending CarOrder object to compare.
     * @return A negative integer, zero, or a positive integer as the first
     * CarOrder object is less than, equal to, or greater than the second
     * CarOrder object.
     * @throws IllegalArgumentException | if order1 or order2 is null.
     *                                  | if order1.getEstCompletionTime() or order2.getEstCompletionTime() is null.
     *                                  | if order1.isCompleted() or order2.isCompleted() is true.
     */
    @Override
    public int compare(CarOrder order1, CarOrder order2) {
        if(order1 == null || order2 == null) {
            throw new IllegalArgumentException("Cannot compare null objects.");
        }
        if(order1.getEstCompletionTime() == null || order2.getEstCompletionTime() == null) {
            throw new IllegalArgumentException("Estimated completion time must be set.");
        }
        if(order1.isCompleted() || order2.isCompleted()) {
            throw new IllegalArgumentException("This comparator is intended for pending CarOrders.");
        }
        if (order1.getEstCompletionTime().isBefore(order2.getEstCompletionTime())) {
            return -1;}
        else if (order1.getEstCompletionTime().isAfter(order2.getEstCompletionTime())){
            return 1;}
        else
            return 0;
    }

}