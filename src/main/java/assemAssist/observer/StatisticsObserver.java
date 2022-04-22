package assemAssist.observer;

import java.time.LocalDateTime;

public interface StatisticsObserver {

    public void update(String event, long delay);

}
