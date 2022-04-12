package assemAssist.observer;

public interface StatisticsObservable {

    public void addObserver(StatisticsObserver observer);

    public void removeObserver(StatisticsObserver observer);

    public void notifyObservers();
}
