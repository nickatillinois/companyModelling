package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;
import java.time.LocalDate;
import java.util.*;

/**
 * Class representing working day statistics.
 */
public class WorkingDayStats extends Stats {

    /**
     * Creates working day statistics.
     *
     * @param subjects list of observables
     */
    public WorkingDayStats(List<StatisticsObservable> subjects) {
        super(subjects);
    }

    /**
     * Returns a list of strings, containing the average and median for this statistic as well as statistics for
     * the last number of days, specified by the given value.
     *
     * @param fromXLastDays the number of days this method will return statistics from
     * @return a list of statistics in string form
     */
    @Override
    public List<String> getStatistics(int fromXLastDays) {
        List<String> statistics = new ArrayList<>();
        statistics.add("average" + getAverage());
        statistics.add("median" + getMedian());

        for (int i = 1; i<fromXLastDays; i++) {
            statistics.add("cars produced " + i + " day(s) ago" +
                    getStatsPerDay().get(LocalDate.now().minusDays(i).toString()).get(0));
        }
        return statistics;
    }

    /**
     * Updates this statistic.
     *
     * @param delay the delay of a completed order
     */
    @Override
    public void update(double delay) {
        Map<String,List<Double>> stats = getStatsPerDay();
        if (stats.containsKey(LocalDate.now().toString())) {
            stats.replace(LocalDate.now().toString(), List.of(stats.get(LocalDate.now().toString()).get(0) + 1));
        } else {
            stats.put(LocalDate.now().toString(),List.of(1.0));
        }
    }

}
