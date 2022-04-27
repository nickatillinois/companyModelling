package classTests;

import assemAssist.CarModel;
import assemAssist.CarOrder;
import assemAssist.Company;
import assemAssist.exceptions.IllegalConstraintException;
import assemAssist.exceptions.IllegalModelException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

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
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red");
        legalAOptions.put("body", "sedan");
        legalAOptions.put("engine", "v4");
        legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual");
        legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        CarModel carModelA = new CarModel("A", legalAOptions);
        carOrderA = new CarOrder("Danny Smeets", carModelA);
        TreeMap<String, String> legalBOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalBOptions.put("color", "red");
        legalBOptions.put("body", "sedan");
        legalBOptions.put("engine", "v4");
        legalBOptions.put("seats", "leather white");
        legalBOptions.put("airco", "manual");
        legalBOptions.put("gearbox", "6 manual");
        legalBOptions.put("wheels", "winter");
        legalBOptions.put("spoiler", "low");
        CarModel carModelB = new CarModel("B", legalBOptions);
        carOrderB = new CarOrder("Sandy Smeets", carModelB);
        TreeMap<String, String> legalCOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalCOptions.put("color", "black");
        legalCOptions.put("body", "sport");
        legalCOptions.put("engine", "v6");
        legalCOptions.put("seats", "leather white");
        legalCOptions.put("airco", "manual");
        legalCOptions.put("gearbox", "6 manual");
        legalCOptions.put("wheels", "winter");
        legalCOptions.put("spoiler", "low");
        CarModel carModelC = new CarModel("C", legalCOptions);
        carOrderC = new CarOrder("Kim Smeets", carModelC);

        // complete order C

    }

    @Test
    void exceptionsTest() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new CarOrder(null, null));
    }
    @Test
    void testGettersCarOrder() throws IllegalModelException, IllegalConstraintException {
        assertEquals(carOrderA.getGarageholder(), "Danny Smeets");
        assertEquals(carOrderB.getGarageholder(), "Sandy Smeets");
        assertEquals(carOrderC.getGarageholder(), "Kim Smeets");
        assertEquals(3, carOrderC.getOrderID());
        assertFalse(carOrderA.isCompleted());
        assertFalse(carOrderB.isCompleted());
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