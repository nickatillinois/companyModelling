package classTests;

import assemAssist.CarModel;
import assemAssist.CarOrder;
import assemAssist.Company;
import assemAssist.exceptions.IllegalConstraintException;
import assemAssist.exceptions.IllegalModelException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CarOrderTest {

    static Company company;
    static CarOrder carOrderA;
    static CarOrder carOrderB;
    static CarOrder carOrderC;




    @BeforeAll
    static void init() {
        company = new Company();
        // add some pending orders to the company
        Map<String, String> legalAOptions = Map.of("body", "sedan", "color", "red", "engine", "v4", "gearbox", " 6 manual", "seats", "leather white", "airco", "manual", "wheels", "winter");
        CarModel carModelA = new CarModel("A", legalAOptions);
        LocalDateTime estCompletionDateA = LocalDateTime.now().plusWeeks(1);
        carOrderA = new CarOrder("Danny Smeets", carModelA, estCompletionDateA);

        Map<String, String> legalBOptions = Map.of("body", "sedan", "color", "red", "engine", "v4", "gearbox", " 6 manual", "seats", "leather white", "airco", "manual", "wheels", "winter", "spoiler", "low");
        CarModel carModelB = new CarModel("B", legalBOptions);
        LocalDateTime estCompletionDateB = LocalDateTime.now().plusWeeks(2);
        carOrderB = new CarOrder("Sandy Smeets", carModelB, estCompletionDateB);

        Map<String, String> legalCOptions = Map.of("body", "sport", "color", "black", "engine", "v6", "gearbox", " 6 manual", "seats", "leather white", "airco", "manual", "wheels", "winter", "spoiler", "low");
        CarModel carModelC = new CarModel("C", legalCOptions);
        LocalDateTime estCompletionDateC = LocalDateTime.now().plusWeeks(3);
        carOrderC = new CarOrder("Kim Smeets", carModelC, estCompletionDateC);

        // complete order C
        carOrderC.setCompleted(true);

    }

    @Test
    void exceptionsTest() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new CarOrder(null, null, null));
    }
    @Test
    void testGettersCarOrder() throws IllegalModelException, IllegalConstraintException {
        assertEquals(carOrderA.getGarageholder(), "Danny Smeets");
        assertEquals(carOrderB.getGarageholder(), "Sandy Smeets");
        assertEquals(carOrderC.getGarageholder(), "Kim Smeets");
        assertEquals(3, carOrderC.getOrderID());
        assertFalse(carOrderA.isCompleted());
        assertFalse(carOrderB.isCompleted());
        assertTrue(carOrderC.isCompleted());
        assertEquals(carOrderA.getOrderingTime().getDayOfMonth(), LocalDateTime.now().getDayOfMonth());
        assertEquals("A", carOrderA.getCarModel().getModelName());
        assertEquals("B", carOrderB.getCarModel().getModelName());
        assertEquals("C", carOrderC.getCarModel().getModelName());
        assertEquals(50, company.getCatalog().getModel("A").getStandardWorkStationTime());
        assertEquals(1, carOrderA.getOrderID());
        assertEquals(2, carOrderB.getOrderID());
        assertTrue(carOrderA.isValidCarModel());
        assertTrue(carOrderB.isValidCarModel());
        assertTrue(carOrderC.isValidCarModel());
    }
}