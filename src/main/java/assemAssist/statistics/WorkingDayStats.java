package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;

import java.util.ArrayList;

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
