package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;
import java.time.LocalDate;
import java.util.*;

public class WorkingDayStats extends Stats {

    public WorkingDayStats(List<StatisticsObservable> subjects) {
        super(subjects);
        addStats(LocalDate.now().toString(),0.0);
    }

    @Override
    public List<String> getStatistics(int fromXLastDays) {
        List<String> statistics = new ArrayList<>();
        statistics.add("average" + getAverage());
        statistics.add("median" + getMedian());

        for (int i = 1; i<fromXLastDays; i++) {
            statistics.add("cars produced " + i + " day(s) ago" +
                    getStatsPerDay().get(LocalDate.now().minusDays(i).toString()));
        }
        return statistics;
    }

    // TODO check of dit nt gewoon een copy aanpast
    @Override
    public void update(double delay) {
        Map<String,Double> stats = getStatsPerDay();
        if (stats.containsKey(LocalDate.now().toString())) {
            stats.replace(LocalDate.now().toString(), stats.get(LocalDate.now().toString()) + 1);
        } else {
            stats.put(LocalDate.now().toString(),1.0);
        }
    }

}
