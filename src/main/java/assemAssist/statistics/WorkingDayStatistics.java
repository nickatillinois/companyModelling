package assemAssist.statistics;

import java.time.LocalDate;
import java.util.*;

/**
 * Class representing working day statistics.
 *
 * @author SWOP team 10
 */
public class WorkingDayStatistics extends Statistics {

    /**
     * Creates working day statistics.
     */
    public WorkingDayStatistics() {
        super();
    }

    /**
     * Returns a list of strings, containing the average and median for this statistic as well as statistics for
     * the last number of days, specified by the given value.
     *
     * @param fromXLastDays the number of days this method will return statistics from
     * @param date the date statistics need to be calculated on
     * @return a list of statistics in string form
     * @throws IllegalArgumentException fromXLastDays is less than zero
     * @throws IllegalArgumentException date is null
     */
    @Override
    public List<String> getStatistics(int fromXLastDays, LocalDate date) {
        if (fromXLastDays < 0) throw new IllegalArgumentException();
        if (date == null) throw new IllegalArgumentException();

        List<String> statistics = new ArrayList<>();
        if (statsPerDay.size() < fromXLastDays) {
            statistics.add("There are no statistics that go back " + fromXLastDays +
                    " days, here are the statistics for the last " + statsPerDay.size() + " days:");
            fromXLastDays = statsPerDay.size();
        }
        statistics.add("- the average of completed cars in a day is " + getAverage());
        statistics.add("- the median of completed cars in a day is " + getMedian());

        if (statsPerDay.size() == 1 && statsPerDay.containsKey(LocalDate.now().toString())) return statistics;

        for (int i = 0; i<fromXLastDays; i++) {
            int daysAgo = i + 1;
            statistics.add("- cars produced " + daysAgo + " day(s) ago: " +
                    statsPerDay.get(date.minusDays(i+1).toString()).get(0));
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
        Map<String,List<Double>> stats = statsPerDay;
        if (stats.containsKey(LocalDate.now().toString())) {
            stats.replace(LocalDate.now().toString(), List.of(stats.get(LocalDate.now().toString()).get(0) + 1));
        } else {
            stats.put(LocalDate.now().toString(),List.of(1.0));
        }
    }
}
