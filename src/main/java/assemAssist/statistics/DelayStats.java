package assemAssist.statistics;

import java.time.LocalDate;
import java.util.*;

/**
 * Class representing delay statistics.
 */
public class DelayStats extends Stats{

    /**
     * Creates a delay statistic.
     */
    public DelayStats() {
        super();
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
        if (statsPerDay.size() < fromXLastDays) {
            fromXLastDays = statsPerDay.size();
        }
        statistics.add("- the average delay on a car is " + getAverage());
        statistics.add("- the median delay on a car is " + getMedian());

        Map<String,List<Double>> statsOnlyDelays = onlyDelays();

        for (int i = 0; i<fromXLastDays; i++) {
            int daysAgo = i + 1;
            String text;
            if (daysAgo == 1) {
                text = "- the last delay was on ";
            } else if (daysAgo == 2) {
                text = "- the 2nd to last delay was on ";
            } else if (daysAgo == 3) {
                text = "- the 3rd last delay was on ";
            } else { text = "- the " + daysAgo + "th last delay was on "; }

            Set<String> delayDates = statsPerDay.keySet(); List<String> dates = delayDates.stream().toList();

            int day = 0;
            int last = i+1;
            while (last > 0) {
                if (statsOnlyDelays.get(dates.get(day)).size() >= last) {
                    statistics.add(text + dates.get(day) + ": " + statsOnlyDelays.get(dates.get(day)).get(last-1) + " minutes");
                    last = 0;
                } else {
                    last -= statsOnlyDelays.get(dates.get(day)).size();
                    day++;
                }
            }
        }
        return statistics;
    }

    /**
     * Removes the statistics for completed car orders that have no delay.
     *
     * @return values for this statistics that are not zero
     */
    private Map<String, List<Double>> onlyDelays() {
        Map<String,List<Double>> result = new HashMap<>();
        for (Map.Entry<String,List<Double>> entry : statsPerDay.entrySet()) {
            result.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }

        for ( String date : result.keySet() ) {
            List<Double> numbers = new ArrayList<>(List.copyOf(result.get(date)));
            while (numbers.contains(0.0)) {
                numbers.remove(0.0);
            }
            result.put(date,numbers);
        }
        return result;
    }

    /**
     * Updates this statistic.
     *
     * @param newDelay the delay of a completed order
     */
    @Override
    public void update(double newDelay) {
        if (statsPerDay.containsKey(LocalDate.now().toString())) {
            List<Double> newValues = new ArrayList<>(statsPerDay.get(LocalDate.now().toString()));
            newValues.add(newDelay);
            statsPerDay.replace(LocalDate.now().toString(),newValues);
        } else {
            addStats(LocalDate.now().toString(), List.of(newDelay));
        }
    }

}
