package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;
import assemAssist.observer.StatisticsObserver;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Copy;

import java.util.*;

public abstract class Stats implements StatisticsObserver {

    private Map<String,Double> statsPerDay = new HashMap<String,Double>();


    public Stats(List<StatisticsObservable> subjects) {
        for (StatisticsObservable subject : subjects) {
            subject.addObserver(this);
        }
    }

    public Map<String, Double> getStatsPerDay() {
        return statsPerDay;
    }

    public void addStats(String key, Double value) {
        statsPerDay.put(key,value);
    }

    public double getAverage() {
        List<Double> numbers = (List<Double>) statsPerDay.values();
        double average = 0;

        for (double num : numbers) {
            average += num;
        }
        return average / numbers.size();
    }

    public double getMedian() {
        List<Double> numbers = (List<Double>) statsPerDay.values();
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
