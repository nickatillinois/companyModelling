package classTests;

import assemAssist.CarModel;
import assemAssist.CarOrder;
import assemAssist.Company;
import assemAssist.exceptions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
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
        try {
            carOrderA.setGarageHolder(null);
        }
        catch (IllegalArgumentException e) {
            assertEquals("A garage holder cannot be null.", e.getMessage());
        }
        try {
            carOrderA.setGarageHolder("");
        }
        catch (IllegalArgumentException e) {
            assertEquals("A garage holder cannot be the empty string.", e.getMessage());
        }
        carOrderA.setGarageHolder("Irma Smeets");
        assertEquals("Irma Smeets", carOrderA.getGarageHolder());
        carOrderA.setGarageHolder("Danny Smeets");
        try {
            carOrderA.setCarModel(null);
        }
        catch (IllegalArgumentException e) {
            assertEquals("A car model cannot be null.", e.getMessage());
        }
        try {
            carOrderA.setCarModel(new CarModel("", null));
        }
        catch (IllegalArgumentException e) {
            assertEquals("A car model cannot be the empty string.", e.getMessage());
        }
        try {
            carOrderA.setCompletionTime(null);
        }
        catch (IllegalArgumentException e) {
            assertEquals("A completion time cannot be null.", e.getMessage());
        }
        try {
            carOrderA.setEstCompletionTime(null);
        }
        catch (IllegalArgumentException e) {
            assertEquals("An estimated completion time cannot be null.", e.getMessage());
        }
        assertEquals(carOrderA, carOrderA);
        assertNotEquals(null, carOrderA);

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
        assertNotEquals(carOrderA, carOrderB);
    }

    @Test
    void exceptionsTest() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new CarOrder(null, null));
    }
    @Test
    void testGettersCarOrder() throws IllegalModelException {
        assertEquals(carOrderA.getGarageHolder(), "Danny Smeets");
        assertEquals(carOrderB.getGarageHolder(), "Sandy Smeets");
        assertEquals(carOrderC.getGarageHolder(), "Kim Smeets");
        assertEquals(3, carOrderC.getOrderID());
        assertFalse(carOrderA.isCompleted());
        assertFalse(carOrderB.isCompleted());
        assertEquals("A", carOrderA.getCarModel().getModelName());
        assertEquals("B", carOrderB.getCarModel().getModelName());
        assertEquals("C", carOrderC.getCarModel().getModelName());
        assertEquals(50, company.getCatalog().getModel("A").getStandardWorkStationTime());
        assertEquals(70, company.getCatalog().getModel("B").getStandardWorkStationTime());
        assertEquals(60, company.getCatalog().getModel("C").getStandardWorkStationTime());
        assertEquals(1, carOrderA.getOrderID());
        assertEquals(2, carOrderB.getOrderID());
    }

    @Test
    void testLegalModels() throws IllegalModelException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        assertTrue(carOrderA.isValidCarModel());
        assertTrue(carOrderB.isValidCarModel());
        assertTrue(carOrderC.isValidCarModel());
    }

    @Test
    void testIllegalName() throws IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        TreeMap<String, String> illegalOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        illegalOptions.put("color", "red");
        illegalOptions.put("body", "sedan");
        illegalOptions.put("engine", "v4");
        illegalOptions.put("seats", "leather white");
        illegalOptions.put("airco", "manual");
        illegalOptions.put("gearbox", "6 manual");
        illegalOptions.put("wheels", "winter");
        illegalOptions.put("spoiler", "low");
        CarModel illegalModel = new CarModel("illegal", illegalOptions);
        CarOrder illegalOrder = new CarOrder("illegal", illegalModel);
        // test if it throws an IllegalModelException, if so catch it and test passes
        try {
            illegalOrder.isValidCarModel();
        }
        catch (IllegalModelException e) {
            assertEquals("IllegalModelException", e.getClass().getSimpleName());
        }
    }

    @Test
    void testNoBody() throws IllegalModelException {
        TreeMap<String, String> noBodyOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        noBodyOptions.put("color", "red");
        noBodyOptions.put("engine", "v4");
        noBodyOptions.put("seats", "leather white");
        noBodyOptions.put("airco", "manual");
        noBodyOptions.put("gearbox", "6 manual");
        noBodyOptions.put("wheels", "winter");
        CarModel carModelA = new CarModel("A", noBodyOptions);
        CarOrder carOrderNoBody = new CarOrder("Danny Smeets", carModelA);
        // test if it throws an IllegalConstraintException, if so catch it and test passes
        try {
            carOrderNoBody.isValidCarModel();
        }
        catch (IllegalConstraintException | OptionThenComponentException | OptionAThenOptionBException | RequiredComponentException e) {
            assertEquals("RequiredComponentException", e.getClass().getSimpleName());
        }
    }

    @Test
    void testOptionAthenOptionB() throws IllegalModelException {
        TreeMap<String, String> illegalOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        illegalOptions.put("color", "red");
        illegalOptions.put("body", "sport");
        illegalOptions.put("engine", "v4");
        illegalOptions.put("seats", "leather white");
        illegalOptions.put("airco", "manual");
        illegalOptions.put("gearbox", "6 manual");
        illegalOptions.put("wheels", "winter");
        illegalOptions.put("spoiler", "low");
        CarModel illegalModel = new CarModel("B", illegalOptions);
        CarOrder illegalOrder = new CarOrder("A", illegalModel);
        // test if it throws an IllegalConstraintException, if so catch it and test passes
        try {
            illegalOrder.isValidCarModel();
        }
        catch (IllegalConstraintException | OptionThenComponentException | OptionAThenOptionBException | RequiredComponentException e) {
            assertEquals("OptionAThenOptionBException", e.getClass().getSimpleName());
        }
    }

    @Test
    void testOptionThenComponent() throws IllegalModelException {
        TreeMap<String, String> illegalOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        illegalOptions.put("color", "red");
        illegalOptions.put("body", "sport");
        illegalOptions.put("engine", "v6");
        illegalOptions.put("seats", "leather white");
        illegalOptions.put("airco", "manual");
        illegalOptions.put("gearbox", "6 manual");
        illegalOptions.put("wheels", "winter");
        CarModel illegalModel = new CarModel("B", illegalOptions);
        CarOrder illegalOrder = new CarOrder("B", illegalModel);
        // test if it throws an IllegalConstraintException, if so catch it and test passes
        try {
            illegalOrder.isValidCarModel();
        }
        catch (IllegalConstraintException | OptionThenComponentException | OptionAThenOptionBException | RequiredComponentException e) {
            assertEquals("OptionThenComponentException", e.getClass().getSimpleName());
        }
    }

    @Test
    void testRequiredComponent() throws IllegalModelException {
        TreeMap<String, String> illegalOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        illegalOptions.put("color", "red");
        illegalOptions.put("body", "sedan");
        illegalOptions.put("seats", "leather white");
        illegalOptions.put("airco", "manual");
        illegalOptions.put("gearbox", "6 manual");
        illegalOptions.put("wheels", "winter");
        CarModel illegalModel = new CarModel("A", illegalOptions);
        CarOrder illegalOrder = new CarOrder("Ai", illegalModel);
        try {
            illegalOrder.isValidCarModel();
        } catch (IllegalConstraintException | OptionThenComponentException | OptionAThenOptionBException | RequiredComponentException e) {
            assertEquals("RequiredComponentException", e.getClass().getSimpleName());
        }
    }

        @Test
        void testCarModelAndOptions(){
            TreeMap<String, String> options = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
            options.put("color", "red");
            options.put("body", "sedan");
            options.put("engine", "v6");
            options.put("seats", "leather white");
            options.put("airco", "manual");
            options.put("gearbox", "6 manual");
            options.put("wheels", "winter");
            CarModel model = new CarModel("A", options);
            CarOrder order = new CarOrder("A", model);
            order.setCarModel(model);
            System.out.println(order.getCarModelAndOptions());
            try {
                order.getOrderDetails();
            }
            catch(IllegalArgumentException e){
                assertEquals("IllegalArgumentException", e.getClass().getSimpleName());
            }
            order.setEstCompletionTime(LocalDateTime.now());
            System.out.println(order.getOrderDetails());
            order.setCompletionTime(LocalDateTime.now());
            assertEquals(order.getCompletionTime().getDayOfYear(), LocalDateTime.now().getDayOfYear());
            assertEquals(order.getEstCompletionTime().getDayOfYear(), LocalDateTime.now().getDayOfYear());
            assertEquals(order.getOrderingTime().getDayOfYear(), LocalDateTime.now().getDayOfYear());
            try {
                order.addObserver(null);
            }
            catch(IllegalArgumentException e){
                assertEquals("IllegalArgumentException", e.getClass().getSimpleName());
            }
            try {
                order.removeObserver(null);
            }
            catch(IllegalArgumentException e){
                assertEquals("IllegalArgumentException", e.getClass().getSimpleName());
            }
            order.setCompleted(true);
            assertTrue(order.isCompleted());
        }
}