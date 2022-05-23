package main.assemAssist.statistics;

import assemAssist.statistics.WorkingDayStatistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class WorkingDayStatisticsTest {

    WorkingDayStatistics workingDayStats;

    @BeforeEach
    void init() {
        workingDayStats = new WorkingDayStatistics();
        workingDayStats.addStats(LocalDate.of(2022,4,24).toString(), List.of(10.0));
        workingDayStats.addStats(LocalDate.of(2022,4,23).toString(),List.of(13.0));
        workingDayStats.addStats(LocalDate.of(2022,4,22).toString(),List.of(16.0));
        workingDayStats.addStats(LocalDate.of(2022,4,21).toString(),List.of(18.0));
        workingDayStats.addStats(LocalDate.of(2022,4,20).toString(),List.of(0.0));
        workingDayStats.addStats(LocalDate.of(2022,4,19).toString(),List.of(9.0));
    }

    @Test
    void constructorTest() {
        workingDayStats = new WorkingDayStatistics();
        assertTrue(workingDayStats.getStatsPerDay().isEmpty());
    }

    @Test
    void getStatsPerDayTest() {
        Map<String,List<Double>> stats = new HashMap<>();
        stats.put(LocalDate.of(2022,4,24).toString(),List.of(10.0));
        stats.put(LocalDate.of(2022,4,23).toString(),List.of(13.0));
        stats.put(LocalDate.of(2022,4,22).toString(),List.of(16.0));
        stats.put(LocalDate.of(2022,4,21).toString(),List.of(18.0));
        stats.put(LocalDate.of(2022,4,20).toString(),List.of(0.0));
        stats.put(LocalDate.of(2022,4,19).toString(),List.of(9.0));
        assertEquals(workingDayStats.getStatsPerDay(),stats);
    }

    @Test
    void getStatisticsTest() {
        List<String> statistics = new ArrayList<>();
        statistics.add("- the average of completed cars in a day is 11.0");
        statistics.add("- the median of completed cars in a day is 11.5");
        assertEquals(workingDayStats.getStatistics(0,LocalDate.of(2022,4,25)),statistics);
        statistics.add("- cars produced 1 day(s) ago: 10.0");
        assertEquals(workingDayStats.getStatistics(1,LocalDate.of(2022,4,25)),statistics);
        statistics.add("- cars produced 2 day(s) ago: 13.0");
        assertEquals(workingDayStats.getStatistics(2,LocalDate.of(2022,4,25)),statistics);
    }

    @Test
    void getStatisticsTestNegValues() {
        assertThrows(IllegalArgumentException.class, () -> workingDayStats.getStatistics(-1,LocalDate.now()));
    }

    @Test
    void getStatisticsTestNullValues() {
        assertThrows(IllegalArgumentException.class, () -> workingDayStats.getStatistics(1,null));
    }

    @Test
    void addStatsTest() {
        Map<String,List<Double>> stats = new HashMap<>();
        stats.put(LocalDate.of(2022,4,24).toString(),List.of(10.0));
        stats.put(LocalDate.of(2022,4,23).toString(),List.of(13.0));
        stats.put(LocalDate.of(2022,4,22).toString(),List.of(16.0));
        stats.put(LocalDate.of(2022,4,21).toString(),List.of(18.0));
        stats.put(LocalDate.of(2022,4,20).toString(),List.of(0.0));
        stats.put(LocalDate.of(2022,4,19).toString(),List.of(9.0));
        workingDayStats.addStats(LocalDate.of(2022,4,25).toString(),List.of(5.0));
        stats.put(LocalDate.of(2022,4,25).toString(),List.of(5.0));
        assertEquals(workingDayStats.getStatsPerDay(),stats);
    }

    @Test
    void addStatsTestNullKey() {
        assertThrows(IllegalArgumentException.class, () -> workingDayStats.addStats(null, List.of(6.0)));
    }

    @Test
    void addStatsTestInvalidKey() {
        assertThrows(IllegalArgumentException.class, () -> workingDayStats.addStats("monday", List.of(6.0)));
    }

    @Test
    void addStatsTestNullValues() {
        assertThrows(IllegalArgumentException.class, () -> workingDayStats.addStats("2022-04-04", null));
    }

    @Test
    void addStatsTestNegValues() {
        assertThrows(IllegalArgumentException.class, () -> workingDayStats.addStats("2022-04-04", List.of(-5.0)));
    }

    @Test
    void updateTest() {
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
    void updateTestNegValues() {
        assertThrows(IllegalArgumentException.class, () -> workingDayStats.update(-1));
    }

}