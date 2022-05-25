package assemAssist.observer;

/**
 * A class representing the observers for the Task class.
 *
 * @author SWOP team 10
 */
public interface TaskObserver {

    /**
     * Updates the observer when a task is finished.
     */
    void update(int time);
}
