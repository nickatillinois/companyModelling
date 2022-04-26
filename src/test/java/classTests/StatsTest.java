package classTests;

import assemAssist.observer.StatisticsObservable;
import assemAssist.statistics.DelayStats;
import assemAssist.statistics.Stats;
import assemAssist.statistics.WorkingDayStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StatsTest {

    WorkingDayStats workingDayStats;
    DelayStats delayStats;

    @BeforeEach
    void init() {

        List<StatisticsObservable> observables = new ArrayList<>();
        workingDayStats = new WorkingDayStats(observables);
        delayStats = new DelayStats(observables);

        workingDayStats.addStats(LocalDate.of(2022,4,24).toString(), Collections.singletonList(10.0));
        workingDayStats.addStats(LocalDate.of(2022,4,23).toString(), Collections.singletonList(13.0));
        workingDayStats.addStats(LocalDate.of(2022,4,22).toString(), Collections.singletonList(16.0));
        workingDayStats.addStats(LocalDate.of(2022,4,21).toString(), Collections.singletonList(18.0));
        workingDayStats.addStats(LocalDate.of(2022,4,20).toString(), Collections.singletonList(0.0));
        workingDayStats.addStats(LocalDate.of(2022,4,19).toString(), Collections.singletonList(9.0));

        delayStats.addStats(LocalDate.of(2022,4,24).toString(), Collections.singletonList(12.75));
        delayStats.addStats(LocalDate.of(2022,4,22).toString(), Collections.singletonList(0.0));
        delayStats.addStats(LocalDate.of(2022,4,20).toString(), Collections.singletonList(60.0));

    }

    @Test
    void getAverage() {
        assertEquals(workingDayStats.getAverage(),11.0);
        workingDayStats.addStats(LocalDate.of(2022,4,25).toString(), Collections.singletonList(4.0));
        assertEquals(workingDayStats.getAverage(),10.0);

        assertEquals(delayStats.getAverage(),24.25);
        delayStats.addStats(LocalDate.of(2022,4,25).toString(), Collections.singletonList(31.25));
        assertEquals(delayStats.getAverage(),26);
    }

    @Test
    void getMedian() {
        assertEquals(workingDayStats.getMedian(),11.5);
        workingDayStats.addStats(LocalDate.of(2022,4,25).toString(), Collections.singletonList(4.0));
        assertEquals(workingDayStats.getMedian(),10.0);

        assertEquals(delayStats.getMedian(),12.75);
        delayStats.addStats(LocalDate.of(2022,4,25).toString(), Collections.singletonList(31.25));
        assertEquals(delayStats.getMedian(),22);
    }

    @Test
    void getStatistics() {

    }

}