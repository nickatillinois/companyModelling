package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class DelayStats extends Stats{

    public DelayStats(List<StatisticsObservable> subjects) {
        super(subjects);
    }

    @Override
    public List<String> getStatistics(int fromXLastDays) {
        List<String> statistics = new ArrayList<>();
        statistics.add("average" + getAverage());
        statistics.add("median" + getMedian());

        for (int i = 1; i<fromXLastDays; i++) {
            statistics.add("last delay" +
                    getStatsPerDay().get(LocalDate.now().minusDays(i).toString()));
        }
        return statistics;
    }

    @Override
    public void update(double newDelay) {
        addStats(LocalDate.now().toString(),newDelay);
    }





}
