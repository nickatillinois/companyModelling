package main.assemAssist;

import assemAssist.CarOrderData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.TreeMap;

public class CarOrderDataTest {

    @Test
    void constructorAndGettersTest() {
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

        assertEquals("A", carOrderData.getMODELNAME());
        assertEquals(chosenOptionsA, carOrderData.getCAROPTIONS());
        assertEquals(2, carOrderData.getORDERID());
        assertEquals("Raf Sablon", carOrderData.getGARAGEHOLDER());
        assertEquals(orderingTime, carOrderData.getORDERINGTIME());
        assertEquals(estCompletionTime, carOrderData.getESTCOMPLETIONTIME());
        assertEquals(completionTime, carOrderData.getCOMPLETIONTIME());
        assertFalse(carOrderData.isCOMPLETED());
    }
}
