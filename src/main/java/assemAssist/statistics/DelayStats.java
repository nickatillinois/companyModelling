package assemAssist.statistics;

import assemAssist.observer.StatisticsObservable;
import java.time.LocalDate;
import java.util.*;

public class DelayStats extends Stats{

    public DelayStats(List<StatisticsObservable> subjects) {
        super(subjects);
    }


    @Override
    public List<String> getStatistics(int fromXLastDays) {
        List<String> statistics = new ArrayList<>();
        statistics.add("the average delay is " + getAverage());
        statistics.add("the median delay is " + getMedian());


        for (int i = 1; i<fromXLastDays; i++) {
            String text;
            if (i == 1) {
                text = "last delay on ";
            } else if (i == 2) {
                text = "2nd last delay on ";
            } else if (i == 3) {
                text = "3rd last delay on ";
            } else { text = i + "th last delay on "; }

            Set<String> delayDates = getStatsPerDay().keySet(); List<String> dates = delayDates.stream().toList();

            int day = 0;
            int last = i;
            while (last > 0) {
                if (getStatsPerDay().get(dates.get(day)).size() >= last) {
                    statistics.add(text + dates.get(day) + ": " + getStatsPerDay().get(dates.get(day)) + " minutes");
                    last = 0;
                } else {
                    last -= getStatsPerDay().get(dates.get(day)).size();
                    day++;
                }
            }

        }
        return statistics;
    }

    @Override
    public void update(double newDelay) {
        if (getStatsPerDay().containsKey(LocalDate.now().toString())) {
            List<Double> newValues = new ArrayList<>(getStatsPerDay().get(LocalDate.now().toString()));
            newValues.add(newDelay);
            getStatsPerDay().replace(LocalDate.now().toString(),newValues);
        } else {
            addStats(LocalDate.now().toString(), List.of(newDelay));
        }
    }





}
