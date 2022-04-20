package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;
import assemAssist.observer.StatisticsObserver;

import java.util.*;

public class WorkingDayStats extends Stats {

    Map<String,Integer> lastTwoDays = new HashMap<String,Integer>();
    int totalCars;
    int carsToday;
    int days;
    double average;
    double median;
    List<Integer> carsProducedPerDay = new ArrayList<>();

    public WorkingDayStats(ArrayList<StatisticsObservable> subjects) {
        super(subjects);
        lastTwoDays.put("1 day ago",0);
        lastTwoDays.put("2 days ago",0);
    }

    @Override
    public void update(String event) {
        //wanneer het een nieuwe dag is
        if ( event.equals("next day") ) {
            days += 1;
            lastTwoDays.replace("2 days ago",lastTwoDays.get("1 day ago"));
            lastTwoDays.replace("1 day ago",carsToday);
            carsToday = 0;
        }
        //wanneer er een nieuwe order is gefinished
        if ( event.equals("order completed") ) {
            totalCars += 1;
            carsToday += 1;
            average = totalCars / days;
            median = getMedian(carsProducedPerDay);
        }
    }

    private double getMedian(List<Integer> numbers) {
        Collections.sort(numbers);
        int n = numbers.size();
        if (n % 2 == 0) {
            return (numbers.get(n/2) + numbers.get(n/2 + 1)) / 2 - 1;
        } else {
            return numbers.get((n + 1)/2 - 1);
        }
    }
}
