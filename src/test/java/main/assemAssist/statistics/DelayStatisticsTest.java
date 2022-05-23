package main.assemAssist.statistics;

import assemAssist.statistics.DelayStatistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class DelayStatisticsTest {

    DelayStatistics delayStats;

    @BeforeEach
    void init() {
        delayStats = new DelayStatistics();
        delayStats.addStats(LocalDate.of(2022,4,24).toString(), List.of(12.0,3.0,0.0));
        delayStats.addStats(LocalDate.of(2022,4,22).toString(),List.of(6.0,0.0,0.0));
        delayStats.addStats(LocalDate.of(2022,4,20).toString(),List.of(7.0));
        delayStats.addStats(LocalDate.of(2022,4,19).toString(),List.of(0.0));
    }

    @Test
    void testConstructors() {
        delayStats = new DelayStatistics();
        assertTrue(delayStats.getStatsPerDay().isEmpty());
    }

    @Test
    void getStatsPerDayTest() {
        Map<String,List<Double>> stats = new HashMap<>();
        stats.put(LocalDate.of(2022,4,24).toString(),List.of(12.0,3.0,0.0));
        stats.put(LocalDate.of(2022,4,22).toString(),List.of(6.0,0.0,0.0));
        stats.put(LocalDate.of(2022,4,20).toString(),List.of(7.0));
        stats.put(LocalDate.of(2022,4,19).toString(),List.of(0.0));
        assertEquals(delayStats.getStatsPerDay(),stats);
    }

    @Test
    void addStatsTest() {
        Map<String,List<Double>> stats = new HashMap<>();
        stats.put(LocalDate.of(2022,4,24).toString(),List.of(12.0,3.0,0.0));
        stats.put(LocalDate.of(2022,4,22).toString(),List.of(6.0,0.0,0.0));
        stats.put(LocalDate.of(2022,4,20).toString(),List.of(7.0));
        stats.put(LocalDate.of(2022,4,19).toString(),List.of(0.0));
        delayStats.addStats(LocalDate.of(2022,4,25).toString(),List.of(5.0));
        stats.put(LocalDate.of(2022,4,25).toString(),List.of(5.0));
        assertEquals(delayStats.getStatsPerDay(),stats);
    }

    @Test
    void addStatsTestNullKey() {
        assertThrows(IllegalArgumentException.class, () -> delayStats.addStats(null, List.of(6.0)));
    }

    @Test
    void addStatsTestInvalidKey() {
        assertThrows(IllegalArgumentException.class, () -> delayStats.addStats("monday", List.of(6.0)));
    }

    @Test
    void addStatsTestNullValues() {
        assertThrows(IllegalArgumentException.class, () -> delayStats.addStats("2022-04-04", null));
    }

    @Test
    void addStatsTestNegValues() {
        assertThrows(IllegalArgumentException.class, () -> delayStats.addStats("2022-04-04", List.of(-5.0)));
    }

    @Test
    void getStatisticsTest() {
        List<String> statistics = new ArrayList<>();
        statistics.add("- the average delay on a car is 3.5");
        statistics.add("- the median delay on a car is 1.5");
        assertEquals(delayStats.getStatistics(0,LocalDate.of(2022,4,25)),statistics);
        statistics.add("- the last delay was on 2022-04-24: 12.0 minutes");
        assertEquals(delayStats.getStatistics(1,LocalDate.of(2022,4,25)),statistics);
        statistics.add("- the 2nd to last delay was on 2022-04-24: 3.0 minutes");
        assertEquals(delayStats.getStatistics(2,LocalDate.of(2022,4,25)),statistics);
        statistics.add("- the 3rd last delay was on 2022-04-22: 6.0 minutes");
        assertEquals(delayStats.getStatistics(3,LocalDate.of(2022,4,25)),statistics);
        statistics.add("- the 4th last delay was on 2022-04-20: 7.0 minutes");
        assertEquals(delayStats.getStatistics(4,LocalDate.of(2022,4,25)),statistics);
    }

    @Test
    void getStatisticsTestNegValues() {
        assertThrows(IllegalArgumentException.class, () -> delayStats.getStatistics(-1,LocalDate.now()));
    }

    @Test
    void getStatisticsTestNullValues() {
        assertThrows(IllegalArgumentException.class, () -> delayStats.getStatistics(1,null));
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

    @Test
    void updateTestNegValues() {
        assertThrows(IllegalArgumentException.class, () -> delayStats.update(-1));
    }

}