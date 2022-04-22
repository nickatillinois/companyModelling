package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;
import assemAssist.observer.StatisticsObserver;

import java.time.LocalDateTime;
import java.util.*;

public class WorkingDayStats extends Stats {

    private Map<String,Integer> lastTwoDays = new HashMap<String,Integer>();
    private int totalCars;
    private int carsToday;
    private int days;
    private List<Integer> carsProducedPerDay = new ArrayList<>();

    public WorkingDayStats(ArrayList<StatisticsObservable> subjects) {
        super(subjects);
        lastTwoDays.put("1 day ago",0);
        lastTwoDays.put("2 days ago",0);
    }

    @Override
    public Map<String, Double> getStatistics() {
        return null;
    }

    @Override
    public void update(String event, long delay) {
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
            double newAverage = totalCars / days; setAverage(newAverage);
            double newMedian = getMedian(carsProducedPerDay); setMedian(newMedian);
        }
    }

    public Map<String,Integer> getLastTwoDays() {
        return lastTwoDays;
    }

}
