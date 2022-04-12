package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;
import assemAssist.observer.StatisticsObserver;

import java.util.ArrayList;

public abstract class Stats implements StatisticsObserver {

    public Stats(ArrayList<StatisticsObservable> subjects) {
        for (StatisticsObservable subject : subjects) {
            subject.addObserver(this);
        }
    }
}
