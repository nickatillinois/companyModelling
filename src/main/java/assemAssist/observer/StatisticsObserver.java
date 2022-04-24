package assemAssist.observer;

/**
 * An interface representing the observer for the Statistics class.
 */
public interface StatisticsObserver {

    /**
     * Updates the observers with the given value.
     *
     * @param delay value that's being passed
     */
    void update(double delay);

}
