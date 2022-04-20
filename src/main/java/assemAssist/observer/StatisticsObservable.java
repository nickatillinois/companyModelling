package assemAssist.observer;

import java.util.ArrayList;
import java.util.List;

public interface StatisticsObservable {

    List<StatisticsObserver> observers = new ArrayList<>();

    public void addObserver(StatisticsObserver observer);

    public void removeObserver(StatisticsObserver observer);

    public void notifyObservers(String event);
}
