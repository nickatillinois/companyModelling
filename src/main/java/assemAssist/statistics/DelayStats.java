package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DelayStats extends Stats{

    private double average;
    private double median;

    // TODO betere representatie zoeken voor deze data
    private double lastDelay;
    private LocalDateTime lastDelayDate;
    private double sLastDelay;
    private LocalDateTime sLastDelayDate;

    public DelayStats(ArrayList<StatisticsObservable> subjects) {
        super(subjects);
    }

    @Override
    public void update(String event) {

    }
}
