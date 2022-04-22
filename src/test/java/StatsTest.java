import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

class StatsTest {

    @Test
    void getStatistics() {
        LocalDateTime estCompletionTime = LocalDateTime.of(2022,4,23,1,6);
        LocalDateTime completionTime = LocalDateTime.of(2022,4,22,23,45);
        long minutes = ChronoUnit.MINUTES.between(estCompletionTime,completionTime);

    }

}