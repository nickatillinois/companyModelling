package main.assemAssist;


import assemAssist.CarModel;
import assemAssist.CarOrder;
import assemAssist.Company;
import assemAssist.constraint.Inspector;
import assemAssist.exceptions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.*;

public class CarOrderTest {

    static Company company;
    static CarOrder carOrderA;
    static CarOrder carOrderB;
    static CarOrder carOrderC;
    static CarOrder carOrderD;
    static CarOrder carOrderE;
    static CarOrder carOrderF;

    @BeforeAll
    static void init() throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        company = new Company();
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red");
        legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4");
        legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual");
        legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        CarModel carModelA = new CarModel("A", legalAOptions, company.getCatalog().getWorkingMinutesWorkstation("A"));
        carOrderA = new CarOrder("Danny Smeets", carModelA);
        TreeMap<String, String> legalBOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalBOptions.put("color", "red");
        legalBOptions.put("body", "break");
        legalBOptions.put("engine", "v4");
        legalBOptions.put("seats", "leather white");
        legalBOptions.put("airco", "manual");
        legalBOptions.put("gearbox", "6 manual");
        legalBOptions.put("wheels", "winter");
        legalBOptions.put("spoiler", "low");
        CarModel carModelB = new CarModel("B", legalBOptions, company.getCatalog().getWorkingMinutesWorkstation("B"));
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
        CarModel carModelC = new CarModel("C", legalCOptions, company.getCatalog().getWorkingMinutesWorkstation("C"));
        carOrderC = new CarOrder("Kim Smeets", carModelC);
        //company.addOrderToProductionSchedule(carOrderA);
        carOrderD = new CarOrder("Tanya Smeets", carModelC);
        carOrderE = new CarOrder("Kimberly Smeets", carModelC);
        carOrderF = new CarOrder("Vanessa Smeets", carModelC);
        carOrderD.setEstCompletionTime(LocalDateTime.now().minusDays(1));
        carOrderE.setEstCompletionTime(LocalDateTime.now().minusDays(2));
        carOrderF.setEstCompletionTime(LocalDateTime.now().minusDays(3));
        assertNull(carOrderA.getEstCompletionTime());
        assertEquals(carOrderD.getEstCompletionTime().getDayOfYear(), LocalDateTime.now().minusDays(1).getDayOfYear());
        company.completeOrderingForm(legalAOptions,"Danny Smeets","B");
        company.completeOrderingForm(legalBOptions,"Sandy Smeets","B");
        company.completeOrderingForm(legalCOptions,"Kim Smeets","C");
        company.getProductionScheduler().advanceOrders(50);
        // add some completed orders
        carOrderD = new CarOrder("Kim Smeets", carModelC);
        carOrderE = new CarOrder("Kim Smeets", carModelC);
        carOrderF = new CarOrder("Kim Smeets", carModelC);



        carOrderD.setEstCompletionTime(LocalDateTime.now().plusDays(7));
        carOrderE.setEstCompletionTime(LocalDateTime.now().plusDays(9));
        carOrderF.setEstCompletionTime(LocalDateTime.now().plusDays(8));

        ArrayList<CarOrder> completedOrders = new ArrayList<>();
        carOrderD.setCompleted();
        carOrderE.setCompleted();
        carOrderF.setCompleted();
        completedOrders.add(carOrderD);
        completedOrders.add(carOrderE);
        completedOrders.add(carOrderF);
        company.setCompletedCarOrders(completedOrders);
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
            carOrderA.setCarModel(new CarModel("", null, 0));
        }
        catch (IllegalArgumentException e) {
            assertEquals("modelName must not be empty.", e.getMessage());
        }
        try {
            carOrderA.setCompletionTime(null);
        }
        catch (IllegalArgumentException e) {
            assertEquals("A completion time cannot be null.", e.getMessage());
        } catch (IllegalCompletionDateException e) {
            e.printStackTrace();
        }
        try {
            carOrderA.setEstCompletionTime(null);
        }
        catch (IllegalArgumentException e) {
            assertEquals("An estimated completion time cannot be null.", e.getMessage());
        }
        assertEquals(carOrderA, carOrderA);
        assertNotEquals(null, carOrderA);
        assertNotEquals(carOrderA, carOrderB);

    }

    @Test
    public void testGetCarOrders() {
        assertEquals(carOrderA.getWorkingMinutesWorkStation(), 50);
    }

    @Test
    void testGettersCarOrder() throws IllegalModelException {
        assertEquals(carOrderA.getGarageHolder(), "Danny Smeets");
        assertEquals(carOrderB.getGarageHolder(), "Sandy Smeets");
        assertEquals(carOrderC.getGarageHolder(), "Kim Smeets");
        assertFalse(carOrderA.isCompleted());
        assertFalse(carOrderB.isCompleted());
        assertEquals("A", carOrderA.getCarModel().getModelName());
        assertEquals("B", carOrderB.getCarModel().getModelName());
        assertEquals("C", carOrderC.getCarModel().getModelName());
        assertEquals(50, company.getCatalog().getModel("A").getStandardWorkStationTime());
        assertEquals(70, company.getCatalog().getModel("B").getStandardWorkStationTime());
        assertEquals(60, company.getCatalog().getModel("C").getStandardWorkStationTime());
    }

    @Test
    void testIllegalName() throws IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        TreeMap<String, String> illegalOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        illegalOptions.put("color", "red");
        illegalOptions.put("body", "break");
        illegalOptions.put("engine", "v4");
        illegalOptions.put("seats", "leather white");
        illegalOptions.put("airco", "manual");
        illegalOptions.put("gearbox", "6 manual");
        illegalOptions.put("wheels", "winter");
        illegalOptions.put("spoiler", "low");
        // test if it throws an IllegalModelException, if so catch it and test passes
        boolean caught = false;
        try {
            new CarModel("illegal", illegalOptions,0);
        }
        catch (IllegalModelException e) {
            assertEquals("IllegalModelException", e.getClass().getSimpleName());
            caught = true;
        }
        assertTrue(caught);

    }

    @Test
    void testNoWheels() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException {
        TreeMap<String, String> noBodyOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        noBodyOptions.put("Color", "red");
        noBodyOptions.put("Body", "sedan");
        noBodyOptions.put("Engine", "v4");
        noBodyOptions.put("Seats", "leather white");
        noBodyOptions.put("Airco", "manual");
        noBodyOptions.put("Gearbox", "6 manual");
        boolean caught = false;
        try {
            new CarModel("B", noBodyOptions,company.getWorkingTimeWorkingStation("B"));
        }
        catch (RequiredComponentException e) {
            assertEquals("RequiredComponentException", e.getClass().getSimpleName());
            caught = true;
        }
        assertTrue(caught);
    }

    @Test
    void testOptionAthenOptionB() throws IllegalModelException, IllegalConstraintException, OptionThenComponentException, RequiredComponentException {
        TreeMap<String, String> illegalOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        illegalOptions.put("color", "red");
        illegalOptions.put("body", "sport");
        illegalOptions.put("engine", "v4");
        illegalOptions.put("seats", "leather white");
        illegalOptions.put("airco", "manual");
        illegalOptions.put("gearbox", "6 manual");
        illegalOptions.put("wheels", "winter");
        illegalOptions.put("spoiler", "low");
        // test if it throws an IllegalModelException, if so catch it and test passes
        boolean caught = false;
        try {
            new CarModel("B", illegalOptions, company.getCatalog().getWorkingMinutesWorkstation("B"));
        }
        catch (OptionAThenOptionBException e) {
            assertEquals("OptionAThenOptionBException", e.getClass().getSimpleName());
            caught = true;
        }
        assertTrue(caught);
    }

    @Test
    void testOptionThenComponent() throws IllegalModelException, IllegalConstraintException, OptionAThenOptionBException, RequiredComponentException {
        TreeMap<String, String> illegalOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        illegalOptions.put("color", "red");
        illegalOptions.put("body", "sport");
        illegalOptions.put("engine", "v6");
        illegalOptions.put("seats", "leather white");
        illegalOptions.put("airco", "manual");
        illegalOptions.put("gearbox", "6 manual");
        illegalOptions.put("wheels", "winter");
        boolean caught = false;
        try {
            new CarModel("B", illegalOptions, company.getCatalog().getWorkingMinutesWorkstation("B"));
        }
        catch (OptionThenComponentException e) {
            assertEquals("OptionThenComponentException", e.getClass().getSimpleName());
            caught = true;
        }
        assertTrue(caught);
    }

    @Test
    void testRequiredComponent() throws IllegalModelException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException {
        TreeMap<String, String> illegalOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        illegalOptions.put("color", "red");
        illegalOptions.put("body", "sedan");
        illegalOptions.put("seats", "leather white");
        illegalOptions.put("airco", "manual");
        illegalOptions.put("gearbox", "6 manual");
        illegalOptions.put("wheels", "winter");
        boolean thrown = false;
        try {
            new CarModel("A", illegalOptions, company.getCatalog().getWorkingMinutesWorkstation("A"));
        }
        catch (RequiredComponentException e) {
            assertEquals(e.getMessage(), "You are missing an essential component: body, color, engine, gearbox, seats or/and wheels.");
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void testCarModelAndOptions() throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        TreeMap<String, String> options = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        options.put("color", "red");
        options.put("body", "sedan");
        options.put("engine", "v6");
        options.put("seats", "leather white");
        options.put("airco", "manual");
        options.put("gearbox", "6 manual");
        options.put("wheels", "winter");
        CarModel model = new CarModel("A", options, company.getCatalog().getWorkingMinutesWorkstation("A"));
        CarOrder order = new CarOrder("A", model);
        order.setCarModel(model);
        System.out.println(order.getCarModelAndOptions());
        boolean gotException = false;
        try {
            order.getOrderDetails();
        }
        catch(IllegalArgumentException | NullPointerException e){
            assertEquals("NullPointerException", e.getClass().getSimpleName());
            gotException = true;
        }
        assertTrue(gotException);
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
        order.setCompleted();
        assertTrue(order.isCompleted());
        System.out.println(order.getCarModel().getChosenOptionsString());
    }

    @Test
    void testAddOptionAAndBPair() throws IllegalConstraintException, IllegalModelException {
        TreeMap<String, String> options = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        options.put("color", "red");
        options.put("body", "sedan");
        options.put("engine", "v6");
        options.put("seats", "leather white");
        options.put("airco", "manual");
        options.put("gearbox", "6 manual");
        options.put("wheels", "winter");
        new Inspector(carOrderA.getCarModel()).reset();
        ArrayList<String> AAndB = new ArrayList<>();
        AAndB.add("green");
        AAndB.add("sedan");
        new Inspector(carOrderA.getCarModel()).addOptionAAndOptionBPair(AAndB);
        boolean gotError = false;
        try{
            new CarModel("B", options,company.getWorkingTimeWorkingStation("B"));
        }
        catch(Exception e){
            assertEquals("OptionAThenOptionBException", e.getClass().getSimpleName());
            gotError = true;
        }
        assertTrue(gotError);
        new Inspector(carOrderA.getCarModel()).reset();
    }

    @Test
    void testAddOptionThenComponentPair() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        TreeMap<String, String> options = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        options.put("color", "green");
        options.put("body", "sedan");
        options.put("engine", "v6");
        options.put("seats", "leather white");
        options.put("airco", "manual");
        options.put("gearbox", "6 manual");
        options.put("wheels", "winter");
        ArrayList<String> AAndB = new ArrayList<>();
        AAndB.add("green");
        AAndB.add("spoiler");
        new Inspector(new CarModel("B", options, company.getCatalog().getWorkingMinutesWorkstation("B"))).addOptionThenComponentPair(AAndB);
        boolean gotError = false;
        try{
            new CarModel("B", options, company.getCatalog().getWorkingMinutesWorkstation("B"));
        }
        catch(Exception e){
            assertEquals("OptionThenComponentException", e.getClass().getSimpleName());
            gotError = true;
        }
        assertTrue(gotError);
        new Inspector(carOrderA.getCarModel()).reset();
    }

    @Test
    void addIllegalConstraintTest() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        TreeMap<String, String> options = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        options.put("color", "green");
        options.put("body", "sedan");
        options.put("engine", "v6");
        options.put("seats", "leather white");
        options.put("airco", "manual");
        options.put("gearbox", "6 manual");
        options.put("wheels", "winter");
        ArrayList<String> AAndB = new ArrayList<>();
        AAndB.add("green");
        new Inspector(carOrderA.getCarModel()).reset();
        try{
            new Inspector(new CarModel("B", options, company.getCatalog().getWorkingMinutesWorkstation("B"))).addOptionAThenOptionBPair(AAndB);
        }
        catch(Exception e){
            assertEquals("IllegalConstraintException", e.getClass().getSimpleName());
        }
        boolean gotError;
        try{
            new CarModel("B", options, company.getCatalog().getWorkingMinutesWorkstation("B"));
        }
        catch(Exception e){
            assertEquals("OptionThenComponentException", e.getClass().getSimpleName());
        }
        new Inspector(carOrderA.getCarModel()).reset();
        TreeMap<String, String> options2 = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        options2.put("color", "red");
        options2.put("body", "sedan");
        options2.put("engine", "v6");
        options2.put("seats", "leather white");
        options2.put("airco", "manual");
        options2.put("gearbox", "6 manual");
        options2.put("wheels", "winter");
        CarModel model2 = new CarModel("A", options2,company.getCatalog().getWorkingMinutesWorkstation("A"));
        CarOrder order = new CarOrder("A", model2);
        gotError = false;
        try{
            order.setCompletionTime(LocalDateTime.now().minusDays(1));
        }
        catch(Exception e){
            assertEquals("IllegalCompletionDateException", e.getClass().getSimpleName());
            gotError = true;
        }
        assertTrue(gotError);

    }

}
