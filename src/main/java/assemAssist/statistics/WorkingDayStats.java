package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;
import java.time.LocalDate;
import java.util.*;

public class WorkingDayStats extends Stats {

    public WorkingDayStats(ArrayList<StatisticsObservable> subjects) {
        super(subjects);
        addStats(LocalDate.now().toString(),0.0);
    }

    @Override
    public Map<String, Double> getStatistics(int fromXLastDays) {


        return null;
    }

    // TODO check of dit nt gewoon een copy aanpast
    @Override
    public void update(double delay) {
        Map<String,Double> stats = getStatsPerDay();
        if (stats.containsKey(LocalDate.now().toString())) {
            stats.replace(LocalDate.now().toString(), stats.get(LocalDate.now().toString()) + 1);
        } else {
            stats.put(LocalDate.now().toString(),1.0);
        }
    }

}
