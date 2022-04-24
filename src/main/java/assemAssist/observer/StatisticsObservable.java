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
    void addObserver(StatisticsObserver observer);

    /**
     * Removes the given observer to the list of observers.
     *
     * @param observer the observer to be removed
     * @throws IllegalArgumentException | observer is null
     *                                  | observer is not in the list of observers
     */
    void removeObserver(StatisticsObserver observer);

    /**
     * Notifies the observers for the statistics class with the given value.
     *
     * @param delay the given value
     */
    void notifyObservers(double delay);
}
