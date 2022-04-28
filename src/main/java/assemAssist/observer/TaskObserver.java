package assemAssist.observer;

public interface TaskObserver {

    /**
     * Updates the observer when a task is finished.
     */
    void update(int time);
}
