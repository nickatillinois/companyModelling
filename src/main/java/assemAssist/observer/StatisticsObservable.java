package assemAssist.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the observables for the Statistics class.
 */
public interface StatisticsObservable {

    /**
     * A list of observers that need to be notified when the observables call for it.
     */
    List<StatisticsObserver> observers = new ArrayList<>();

    /**
     * Adds the given observer to the list of observers.
     *
     * @param observer the observer to be added
     * @throws IllegalArgumentException | observer is null
     */
    default void addObserver(StatisticsObserver observer) {
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
    default void removeObserver(StatisticsObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("An observer cannot be null.");
        }
        observers.remove(observer);
    }

    /**
     * Notifies the observers for the statistics class with the given value.
     *
     * @param delay the given value
     */
    default void notifyObservers(double delay) {
        for (StatisticsObserver observer : observers) {
            observer.update(delay);
        }
    }
}
