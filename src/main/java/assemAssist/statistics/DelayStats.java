package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DelayStats extends Stats{

    private long delay;
    private int ordersDelayed;
    private Set<Long> delays;
    private long lastDelay;
    private LocalDate lastDelayDate;
    private long sLastDelay;
    private LocalDate sLastDelayDate;

    public DelayStats(ArrayList<StatisticsObservable> subjects) {
        super(subjects);
    }

    @Override
    public Map<String, Double> getStatistics() {
        return null;
    }

    @Override
    public void update(String event, long newDelay) {
        if ( event.equals("order completed") ) {
            // add newDelay to delay
            delay += newDelay;
            // add 1 to ordersdelayed
            ordersDelayed += 1;
            // add new delay to set of all delays
            delays.add(newDelay);
            // move last delay to second to last delay and update lastdelay
            sLastDelay = lastDelay;
            sLastDelayDate = lastDelayDate;
            lastDelay = newDelay;
            lastDelayDate = LocalDate.now();
            // adjust median and average
            setAverage(delay / ordersDelayed);
            setMedian(0);
        }
    }





}
