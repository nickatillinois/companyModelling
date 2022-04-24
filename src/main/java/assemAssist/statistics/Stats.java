package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;
import assemAssist.observer.StatisticsObserver;

import java.util.*;

public abstract class Stats implements StatisticsObserver {

    private Map<String,List<Double>> statsPerDay = new HashMap<>();


    public Stats(List<StatisticsObservable> subjects) {
        for (StatisticsObservable subject : subjects) {
            subject.addObserver(this);
        }
    }

    public Map<String, List<Double>> getStatsPerDay() {
        return statsPerDay;
    }

    public void addStats(String key, List<Double> values) {
        statsPerDay.put(key,values);
    }

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

    public double getMedian() {
        List<Double> numbers = new ArrayList<>();
        for (List<Double> doubles : statsPerDay.values()) {
            numbers.addAll(doubles);
        }
        return calculateMedian(numbers);
    }

    private double calculateMedian(List<Double> numbers) {
        Collections.sort(numbers);
        int n = numbers.size();
        if (n % 2 == 0) {
            return (numbers.get(n/2) + numbers.get(n/2 + 1)) / 2 - 1;
        } else {
            return numbers.get((n + 1)/2 - 1);
        }
    }

    public abstract List<String> getStatistics(int fromXLastDays);


}
