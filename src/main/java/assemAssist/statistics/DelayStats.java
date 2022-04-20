package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DelayStats extends Stats{

    // TODO betere representatie zoeken voor deze data
    private int delay;
    private int ordersDelayed;
    private Set<Double> delays;
    private LocalDateTime lastDelay;
    private LocalDate lastDelayDate;
    private LocalDateTime sLastDelay;
    private LocalDate sLastDelayDate;

    public DelayStats(ArrayList<StatisticsObservable> subjects) {
        super(subjects);
    }

    @Override
    public void update(String event) {
        if ( event.equals("order completed" ) ) {

        }
    }
    // TODO moet die carorder meegegeven worden? anders kan je niet aan de waarden
}
