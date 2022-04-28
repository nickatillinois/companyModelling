package classTests;

import assemAssist.observer.StatisticsObservable;
import assemAssist.statistics.DelayStats;
import assemAssist.statistics.WorkingDayStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StatsTest {

    WorkingDayStats workingDayStats;
    DelayStats delayStats;

    @BeforeEach
    void init() {
        List<StatisticsObservable> observables = new ArrayList<>();
        workingDayStats = new WorkingDayStats(observables);
        delayStats = new DelayStats(observables);

        workingDayStats.addStats(LocalDate.of(2022,4,24).toString(),List.of(10.0));
        workingDayStats.addStats(LocalDate.of(2022,4,23).toString(),List.of(13.0));
        workingDayStats.addStats(LocalDate.of(2022,4,22).toString(),List.of(16.0));
        workingDayStats.addStats(LocalDate.of(2022,4,21).toString(),List.of(18.0));
        workingDayStats.addStats(LocalDate.of(2022,4,20).toString(),List.of(0.0));
        workingDayStats.addStats(LocalDate.of(2022,4,19).toString(),List.of(9.0));

        delayStats.addStats(LocalDate.of(2022,4,24).toString(),List.of(12.0,3.0,0.0));
        delayStats.addStats(LocalDate.of(2022,4,22).toString(),List.of(6.0,0.0,0.0));
        delayStats.addStats(LocalDate.of(2022,4,20).toString(),List.of(7.0));
        delayStats.addStats(LocalDate.of(2022,4,19).toString(),List.of(0.0));
    }

    @Test
    void getAverageWorkingDayStats() {
        assertEquals(workingDayStats.getAverage(),11.0);
        workingDayStats.addStats(LocalDate.of(2022,4,25).toString(),List.of(4.0));
        assertEquals(workingDayStats.getAverage(),10.0);
    }

    @Test
    void getAverageDelayStats() {
        assertEquals(delayStats.getAverage(),3.5);
        delayStats.addStats(LocalDate.of(2022,4,25).toString(),List.of(8.0));
        assertEquals(delayStats.getAverage(),4.0);
    }

    @Test
    void getMedianWorkingDayStats() {
        assertEquals(workingDayStats.getMedian(),11.5);
        workingDayStats.addStats(LocalDate.of(2022,4,25).toString(),List.of(4.0));
        assertEquals(workingDayStats.getMedian(),10.0);
    }

    @Test
    void getMedianDelayStats() {
        assertEquals(delayStats.getMedian(),1.5);
        delayStats.addStats(LocalDate.of(2022,4,25).toString(),List.of(10.0));
        assertEquals(delayStats.getMedian(),3.0);
    }

    @Test
    void getStatisticsWorkingDayStats() {
        List<String> statistics = new ArrayList<>();
        statistics.add("the average of completed cars in a day is 11.0");
        statistics.add("the median of completed cars in a day is 11.5");
        assertEquals(workingDayStats.getStatistics(0,LocalDate.of(2022,4,25)),statistics);
        statistics.add("cars produced 1 day(s) ago: 10.0");
        assertEquals(workingDayStats.getStatistics(1,LocalDate.of(2022,4,25)),statistics);
        statistics.add("cars produced 2 day(s) ago: 13.0");
        assertEquals(workingDayStats.getStatistics(2,LocalDate.of(2022,4,25)),statistics);
    }

    @Test
    void getStatisticsDelayStats() {
        List<String> statistics = new ArrayList<>();
        statistics.add("the average delay on a car is 3.5");
        statistics.add("the median delay on a car is 1.5");
        assertEquals(delayStats.getStatistics(0,LocalDate.of(2022,4,25)),statistics);
        statistics.add("the last delay was on 2022-04-24: 12.0 minutes");
        assertEquals(delayStats.getStatistics(1,LocalDate.of(2022,4,25)),statistics);
        statistics.add("the 2nd to last delay was on 2022-04-24: 3.0 minutes");
        assertEquals(delayStats.getStatistics(2,LocalDate.of(2022,4,25)),statistics);
        statistics.add("the 3rd last delay was on 2022-04-22: 6.0 minutes");
        assertEquals(delayStats.getStatistics(3,LocalDate.of(2022,4,25)),statistics);
        statistics.add("the 4th last delay was on 2022-04-20: 7.0 minutes");
        assertEquals(delayStats.getStatistics(4,LocalDate.of(2022,4,25)),statistics);
    }

    @Test
    void getStatsPerDayAndAddStats() {
        Map<String,List<Double>> stats1 = new HashMap<>();
        stats1.put(LocalDate.of(2022,4,24).toString(),List.of(10.0));
        stats1.put(LocalDate.of(2022,4,23).toString(),List.of(13.0));
        stats1.put(LocalDate.of(2022,4,22).toString(),List.of(16.0));
        stats1.put(LocalDate.of(2022,4,21).toString(),List.of(18.0));
        stats1.put(LocalDate.of(2022,4,20).toString(),List.of(0.0));
        stats1.put(LocalDate.of(2022,4,19).toString(),List.of(9.0));
        assertEquals(workingDayStats.getStatsPerDay(),stats1);
        workingDayStats.addStats(LocalDate.of(2022,4,25).toString(),List.of(5.0));
        stats1.put(LocalDate.of(2022,4,25).toString(),List.of(5.0));
        assertEquals(workingDayStats.getStatsPerDay(),stats1);

        Map<String,List<Double>> stats2 = new HashMap<>();
        stats2.put(LocalDate.of(2022,4,24).toString(),List.of(12.0,3.0,0.0));
        stats2.put(LocalDate.of(2022,4,22).toString(),List.of(6.0,0.0,0.0));
        stats2.put(LocalDate.of(2022,4,20).toString(),List.of(7.0));
        stats2.put(LocalDate.of(2022,4,19).toString(),List.of(0.0));
        assertEquals(delayStats.getStatsPerDay(),stats2);
        delayStats.addStats(LocalDate.of(2022,4,25).toString(),List.of(5.0));
        stats2.put(LocalDate.of(2022,4,25).toString(),List.of(5.0));
        assertEquals(delayStats.getStatsPerDay(),stats2);
    }

    @Test
    void updateWorkingDayStats() {
        Map<String,List<Double>> statistics = new HashMap<>();
        statistics.put(LocalDate.of(2022,4,24).toString(),List.of(10.0));
        statistics.put(LocalDate.of(2022,4,23).toString(),List.of(13.0));
        statistics.put(LocalDate.of(2022,4,22).toString(),List.of(16.0));
        statistics.put(LocalDate.of(2022,4,21).toString(),List.of(18.0));
        statistics.put(LocalDate.of(2022,4,20).toString(),List.of(0.0));
        statistics.put(LocalDate.of(2022,4,19).toString(),List.of(9.0));
        assertEquals(workingDayStats.getStatsPerDay(),statistics);
        workingDayStats.update(0.0);
        statistics.put(LocalDate.now().toString(),List.of(1.0));
        assertEquals(workingDayStats.getStatsPerDay(),statistics);
        workingDayStats.update(0.0);
        statistics.replace(LocalDate.now().toString(),List.of(2.0));
        assertEquals(workingDayStats.getStatsPerDay(),statistics);
    }

    @Test
    void updateDelayStats() {
        Map<String,List<Double>> statistics = new HashMap<>();
        statistics.put(LocalDate.of(2022,4,24).toString(),List.of(12.0,3.0,0.0));
        statistics.put(LocalDate.of(2022,4,22).toString(),List.of(6.0,0.0,0.0));
        statistics.put(LocalDate.of(2022,4,20).toString(),List.of(7.0));
        statistics.put(LocalDate.of(2022,4,19).toString(),List.of(0.0));
        assertEquals(delayStats.getStatsPerDay(),statistics);
        delayStats.update(15.0);
        statistics.put(LocalDate.now().toString(),List.of(15.0));
        assertEquals(delayStats.getStatsPerDay(),statistics);
        delayStats.update(10.0);
        statistics.replace(LocalDate.now().toString(),List.of(15.0,10.0));
        assertEquals(delayStats.getStatsPerDay(),statistics);
    }

}