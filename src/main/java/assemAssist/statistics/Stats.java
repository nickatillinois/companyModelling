package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;
import assemAssist.observer.StatisticsObserver;

import java.util.*;

public abstract class Stats implements StatisticsObserver {

    private double average;
    private double median;

    public Stats(ArrayList<StatisticsObservable> subjects) {
        for (StatisticsObservable subject : subjects) {
            subject.addObserver(this);
        }
    }

    public double getAverage() {
        return average;
    }

    public double getMedian() {
        return median;
    }

    void setAverage(double average) {
        this.average = average;
    }

    void setMedian(double median) {
        this.median = median;
    }

    double getMedian(List<Integer> numbers) {
        Collections.sort(numbers);
        int n = numbers.size();
        if (n % 2 == 0) {
            return (numbers.get(n/2) + numbers.get(n/2 + 1)) / 2 - 1;
        } else {
            return numbers.get((n + 1)/2 - 1);
        }
    }

    public abstract Map<String,Double> getStatistics();

}
