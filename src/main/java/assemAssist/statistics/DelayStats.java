package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;
import java.time.LocalDate;
import java.util.*;

/**
 * Class representing delay statistics.
 */
public class DelayStats extends Stats{

    /**
     * Creates a delay statistic.
     *
     * @param subjects list of observables
     */
    public DelayStats(List<StatisticsObservable> subjects) {
        super(subjects);
    }

    /**
     * Returns a list of strings, containing the average and median for this statistic as well as statistics for
     * the last number of days, specified by the given value.
     *
     * @param fromXLastDays the number of days this method will return statistics from
     * @param date the date statistics need to be calculated on
     * @return a list of statistics in string form
     */
    @Override
    public List<String> getStatistics(int fromXLastDays, LocalDate date) {
        List<String> statistics = new ArrayList<>();
        statistics.add("the average delay on a car is " + getAverage());
        statistics.add("the median delay on a car is " + getMedian());


        for (int i = 0; i<fromXLastDays; i++) {
            int daysAgo = i + 1;
            String text;
            if (daysAgo == 1) {
                text = "the last delay was on ";
            } else if (daysAgo == 2) {
                text = "the 2nd to last delay was on ";
            } else if (daysAgo == 3) {
                text = "the 3rd last delay was on ";
            } else { text = "the " + daysAgo + "th last delay was on "; }

            Set<String> delayDates = getStatsPerDay().keySet(); List<String> dates = delayDates.stream().toList();

            int day = 0;
            int last = i+1;
            while (last > 0) {
                if (getStatsPerDay().get(dates.get(day)).size() >= last) {
                    statistics.add(text + dates.get(day) + ": " + getStatsPerDay().get(dates.get(day)).get(last-1) + " minutes");
                    last = 0;
                } else {
                    last -= getStatsPerDay().get(dates.get(day)).size();
                    day++;
                }
            }

        }
        return statistics;
    }

    /**
     * Updates this statistic.
     *
     * @param newDelay the delay of a completed order
     */
    @Override
    public void update(double newDelay) {
        if (getStatsPerDay().containsKey(LocalDate.now().toString())) {
            List<Double> newValues = new ArrayList<>(getStatsPerDay().get(LocalDate.now().toString()));
            newValues.add(newDelay);
            getStatsPerDay().replace(LocalDate.now().toString(),newValues);
        } else {
            addStats(LocalDate.now().toString(), List.of(newDelay));
        }
    }





}
