package assemAssist.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the observables for the Task class.
 *
 * @author SWOP team 10
 */
public interface TaskObservable {

    /**
     * A list of observers that need to be notified when the observables call for it.
     */
    List<TaskObserver> observers = new ArrayList<>();

    /**
     * Adds the given observer to the list of observers.
     *
     * @param observer the observer to be added
     * @throws IllegalArgumentException | observer is null
     */
    default void addObserver(TaskObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("An observer cannot be null.");
        }
        observers.add(observer);
    }

    /**
     * Removes the given observer to the list of observers.
     *
     * @param observer the observer to be removed
     * @throws IllegalArgumentException | observer is null
     *                                  | observer is not in the list of observers
     */
    default void removeObserver(TaskObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("An observer cannot be null.");
        }
        observers.remove(observer);
    }

    /**
     * Notifies the observers for the tasks that a task has been finished.
     */
    default void notifyObservers(int time) {
        for (TaskObserver observer : observers) {
            observer.update(time);
        }
    }
}
