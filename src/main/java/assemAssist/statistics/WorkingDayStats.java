package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;
import assemAssist.observer.StatisticsObserver;

import java.util.ArrayList;
import java.util.HashSet;

public class WorkingDayStats extends Stats {

    // Average number of cars produced in a working day
    private double average;
    // Median number of cars produced in a working day
    private double median;

    public WorkingDayStats(ArrayList<StatisticsObservable> subjects) {
        super(subjects);
    }

    // updates average and median
    @Override
    public void update() {

    }
}
