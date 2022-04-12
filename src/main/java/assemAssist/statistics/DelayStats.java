package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;

import java.util.ArrayList;

public class DelayStats extends Stats{

    /**
     * Average delay of a car order.
     */
    private int average;

    /**
     * Median delay of a car order.
     */
    private int median;

    public DelayStats(ArrayList<StatisticsObservable> subjects) {
        super(subjects);
    }

    @Override
    public void update() {
    }
}
