package assemAssist.observer;

/**
 * An interface representing the observer for the Statistics class.
 *
 * @author SWOP team 10
 */
public interface StatisticsObserver {

    /**
     * Updates the observers with the given value.
     *
     * @param delay value that's being passed
     */
    void update(double delay);

}
