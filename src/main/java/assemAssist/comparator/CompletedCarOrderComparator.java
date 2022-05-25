package assemAssist.comparator;

import assemAssist.CarOrder;

import java.util.Comparator;


/**
 * Class representing a comparator for completed car orders that sorts them by
 * their completion time (the earliest first).
 *
 * @author Team 10
 */
public class CompletedCarOrderComparator implements Comparator<CarOrder> {


    /**
     * Constructor for CompletedCarOrderComparator.
     */
    public CompletedCarOrderComparator() {
    }

    /**
     * Compares two completed CarOrder objects.
     *
     * @param order1 The first completed CarOrder object to compare.
     * @param order2 The second completed CarOrder object to compare.
     * @return A negative integer, zero, or a positive integer as the first
     * CarOrder object is less than, equal to, or greater than the second
     * CarOrder object.
     * @throws IllegalArgumentException | if order1 or order2 is null.
     *                                  | if order1.getCompletionTime() or order2.getCompletionTime() is null.
     *                                  | if order1.isCompleted() or order2.isCompleted() is false.
     */
    @Override
    public int compare(CarOrder order1, CarOrder order2) {
        if(order1 == null || order2 == null) {
            throw new IllegalArgumentException("Cannot compare null objects.");
        }
        if(order1.getCompletionTime() == null || order2.getCompletionTime() == null) {
            throw new IllegalArgumentException("Completion time must be set.");
        }
        if(!order1.isCompleted() || !order2.isCompleted()) {
            throw new IllegalArgumentException("This comparator is intended for completed CarOrders.");
        }
        if (order1.getCompletionTime().isBefore(order2.getCompletionTime())) {
            return -1;}
        else if (order1.getCompletionTime().isAfter(order2.getCompletionTime())){
            return 1;}
        else
            return 0;
    }

}
