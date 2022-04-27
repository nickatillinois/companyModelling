package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;
import assemAssist.observer.StatisticsObserver;

import java.util.*;

/**
 * A class representing statistics of this car manufacturing company.
 */
public abstract class Stats implements StatisticsObserver {

    /**
     * A map representing the statistics day by day.
     */
    private Map<String,List<Double>> statsPerDay = new HashMap<>();


    /**
     * Creates a statistic with the given observables.
     *
     * @param subjects a list of StatisticsObservables
     */
    public Stats(List<StatisticsObservable> subjects) {
        for (StatisticsObservable subject : subjects) {
            subject.addObserver(this);
        }
    }

    /**
     * Returns the map representing the statistics day by day.
     *
     * @return statistics per day
     */
    public Map<String, List<Double>> getStatsPerDay() {
        return statsPerDay;
    }

    /**
     * Adds the given statistic, with the given key and value, to the map of stats per day.
     *
     * @param key the key for the map
     * @param values the value belonging to the given key
     */
    public void addStats(String key, List<Double> values) {
        statsPerDay.put(key,values);
    }

    /**
     * Competes and returns the average of this statistic, based upon all data stored in this class.
     *
     * @return average of this statistic
     */
    public double getAverage() {
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
    public double getMedian() {
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
     */
    private double calculateMedian(List<Double> numbers) {
        Collections.sort(numbers);
        int n = numbers.size();
        if (n == 0) { return 0; }
        if (n % 2 == 0) {
            return (numbers.get(n/2) + numbers.get(n/2 + 1)) / 2 - 1;
        } else {
            return numbers.get((n + 1)/2 - 1);
        }
    }

    /**
     * Returns a list of strings, containing the average and median for this statistic as well as statistics for
     * the last number of days, specified by the given value.
     *
     * @param fromXLastDays the number of days this method will return statistics from
     * @return a list of statistics in string form
     */
    public abstract List<String> getStatistics(int fromXLastDays);


}
