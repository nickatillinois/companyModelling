package assemAssist.statistics;

import java.time.LocalDate;
import java.util.*;

/**
 * Class representing working day statistics.
 */
public class WorkingDayStats extends Stats {

    /**
     * Creates working day statistics.
     */
    public WorkingDayStats() {
        super();
    }

    /**
     * Returns a list of strings, containing the average and median for this statistic as well as statistics for
     * the last number of days, specified by the given value.
     *
     * @param fromXLastDays the number of days this method will return statistics from
     * @param date the date statistics need to be calculated on
     * @return a list of statistics in string form
     * @throws IllegalArgumentException if the given integer is higher than the amount of statistics in the system
     */
    @Override
    public List<String> getStatistics(int fromXLastDays, LocalDate date) {
        if (statsPerDay.size() < fromXLastDays) {
            throw new IllegalArgumentException("The system only contains data for the past " + statsPerDay.size() + " days!");
        }
        List<String> statistics = new ArrayList<>();
        statistics.add("the average of completed cars in a day is " + getAverage());
        statistics.add("the median of completed cars in a day is " + getMedian());

        for (int i = 0; i<fromXLastDays; i++) {
            int daysAgo = i + 1;
            statistics.add("cars produced " + daysAgo + " day(s) ago: " +
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
