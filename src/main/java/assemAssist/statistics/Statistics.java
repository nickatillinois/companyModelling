package assemAssist.statistics;

import assemAssist.observer.StatisticsObserver;
import java.time.LocalDate;
import java.util.*;

/**
 * A class representing statistics of this car manufacturing company.
 *
 * @author SWOP team 10
 */
public abstract class Statistics implements StatisticsObserver {

    /**
     * A map representing the statistics day by day.
     */
    Map<String,List<Double>> statsPerDay = new HashMap<>();

    /**
     * Creates a statistic.
     */
    public Statistics() {}

    /**
     * Returns the map representing the statistics day by day.
     *
     * @return statistics per day
     */
    public Map<String, List<Double>> getStatsPerDay() {
        Map<String,List<Double>> copy = new HashMap<>();
        for (Map.Entry<String,List<Double>> entry : statsPerDay.entrySet()) {
            copy.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return copy;
    }

    /**
     * Adds the given statistic, with the given key and value, to the map of stats per day.
     *
     * @param key the key for the map
     * @param values the value belonging to the given key
     * @throws IllegalArgumentException | key == null
     *                                  | !isDate(key)
     *                                  | values == null
     */
    public void addStats(String key, List<Double> values) {
        if (key == null) throw new IllegalArgumentException("The given key cannot be null.");
        if ( !isDate(key) ) { throw new IllegalArgumentException("The given key must be a date in the YYYY-DD-MM format."); }
        if ( values == null ) { throw new IllegalArgumentException("The given values cannot be null."); }

        statsPerDay.put(key,values);
    }

    /**
     * Competes and returns the average of this statistic, based upon all data stored in this class.
     *
     * @return average of this statistic
     */
    double getAverage() {
        if ( statsPerDay.size() == 0 ) { return 0.0; }

        List<Double> numbers = new ArrayList<>();
        for (List<Double> doubles : statsPerDay.values()) {
            numbers.addAll(doubles);
        }
        double average = 0;
        for (double num : numbers) {
            average += num;
        }
        return average / numbers.size();
    }

    /**
     * Competes and returns the median of this statistic, based upon all data stored in this class.
     *
     * @return median of this statistic
     */
    double getMedian() {
        List<Double> numbers = new ArrayList<>();
        for (List<Double> doubles : statsPerDay.values()) {
            numbers.addAll(doubles);
        }
        return calculateMedian(numbers);
    }

    /**
     * Calculates the median of a given list of doubles.
     *
     * @param numbers the list of doubles to calculate the median
     * @return the median of the given list of doubles
     * @throws IllegalArgumentException | numbers == null
     */
    private double calculateMedian(List<Double> numbers) {
        if (numbers == null) throw new IllegalArgumentException("The given values cannot be null.");
        Collections.sort(numbers);
        int n = numbers.size();
        if (n == 0) { return 0; }
        if (n % 2 == 0) {
            return (numbers.get(n/2 - 1) + numbers.get(n/2)) / 2;
        } else {
            return numbers.get((n + 1)/2 - 1);
        }
    }

    /**
     * Returns a list of strings, containing the average and median for this statistic as well as statistics for
     * the last number of days, specified by the given value.
     *
     * @param fromXLastDays the number of days this method will return statistics from
     * @param date the date statistics need to be calculated on
     * @return a list of statistics in string form
     * @throws IllegalArgumentException | fromXLastDays < 0
     *                                  | date == null
     */
    public abstract List<String> getStatistics(int fromXLastDays, LocalDate date);

    /**
     * Returns true if the given string represents a date in the YYYY-MM-DD format.
     * @param date the given string that needs to be checked out
     * @return true if the given string is a date in the right format
     */
    private boolean isDate(String date) {
        if (date.length() != 10) return false;
        if (date.indexOf("-") != 4) return false;
        return date.lastIndexOf("-") == 7;
    }

}
