package main.assemAssist.workStation;

import assemAssist.CarOrderData;
import assemAssist.workStation.WorkStationData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

public class WorkStationDataTest {

    @Test
    void gettersAndConstructorTest() {
        TreeMap<String, String> chosenOptionsA = new TreeMap<>();
        chosenOptionsA.put("Body", "Sedan");
        chosenOptionsA.put("Color", "blue");
        chosenOptionsA.put("Engine", "V4");
        chosenOptionsA.put("Gearbox", "5 manual");
        chosenOptionsA.put("Seats", "leather white");
        chosenOptionsA.put("Airco", "Manual");
        chosenOptionsA.put("Wheels", "comfort");

        LocalDateTime orderingTime = LocalDateTime.now();
        LocalDateTime estCompletionTime = LocalDateTime.now();
        LocalDateTime completionTime = LocalDateTime.now();

        CarOrderData carOrderData = new CarOrderData("A", chosenOptionsA, 2, "Raf Sablon",
                orderingTime, estCompletionTime, completionTime, false);

        Map tasksAndStatus = new TreeMap<String, Boolean>();
        tasksAndStatus.put("body", true);
        tasksAndStatus.put("color", false);

        WorkStationData workStationData = new WorkStationData("Car Body Post", tasksAndStatus, carOrderData);

        assertEquals("Car Body Post", workStationData.getNAME());
        assertEquals(tasksAndStatus, workStationData.getTASKSANDSTATUS());
        assertEquals(carOrderData, workStationData.getCURRENTORDER());
    }
}
