package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DelayStats extends Stats{

    public DelayStats(ArrayList<StatisticsObservable> subjects) {
        super(subjects);
    }

    @Override
    public Map<String, Double> getStatistics(int fromXLastDays) {
        return null;
    }

    @Override
    public void update(double newDelay) {
        addStats(LocalDate.now().toString(),newDelay);
    }





}
