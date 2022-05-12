package classTests;

import assemAssist.CarModel;
import assemAssist.CarModelSpecification;
import assemAssist.CarOrder;
import assemAssist.Company;
import assemAssist.exceptions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyCatalogEtcTest {


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
        CarModel carModelA = new CarModel("A", legalAOptions);
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
        //company.addOrderToProductionSchedule(carOrderA);
        carOrderD = new CarOrder("Tanya Smeets", carModelC);
        carOrderE = new CarOrder("Kimberly Smeets", carModelC);
        carOrderF = new CarOrder("Vanessa Smeets", carModelC);
        carOrderD.setEstCompletionTime(LocalDateTime.now().minusDays(1));
        carOrderE.setEstCompletionTime(LocalDateTime.now().minusDays(2));
        carOrderF.setEstCompletionTime(LocalDateTime.now().minusDays(3));
        System.out.println(carOrderA.getEstCompletionTime());
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
        carOrderD.setCompleted(true);
        carOrderE.setCompleted(true);
        carOrderF.setCompleted(true);
        completedOrders.add(carOrderD);
        completedOrders.add(carOrderE);
        completedOrders.add(carOrderF);
        company.setCompletedCarOrders(completedOrders);
    }

    @Test
    void testGettersCompany() throws OrderNotFoundException {
        assertEquals(3, company.getCompletedCarOrders().size());
        boolean got_error = false;
        try{
            company.getOrdersFromGarageHolder(null);
        }
        catch (IllegalArgumentException e){
            assertEquals("Garage holder name cannot be null.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            company.getOrdersFromGarageHolder("");
        }
        catch (IllegalArgumentException e){
            assertEquals("Garage holder name cannot be empty.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            company.getOrdersFromGarageHolder(" ");
        }
        catch (IllegalArgumentException e){
            assertEquals("Garage holder name cannot be whitespace.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        company.getOrdersFromGarageHolder("Kim Smeets");
        assertEquals(2, company.getOrdersFromGarageHolder("Kim Smeets").length);
        assertEquals(2, company.getOrdersFromGarageHolder("Sandy Smeets").length);
        assertEquals(2, company.getOrdersFromGarageHolder("Danny Smeets").length);
        assertEquals(0, company.getOrdersFromGarageHolder("Sandy Smeets")[1].size());
        assertEquals(0, company.getOrdersFromGarageHolder("Danny Smeets")[1].size());
        got_error = false;
        try{
            company.getOrderDetails(0, null);
        }
        catch (IllegalArgumentException e){
            assertEquals("ID must be strictly positive.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            company.getOrderDetails(1, "");
        }
        catch (IllegalArgumentException e){
            assertEquals("Garage holder name cannot be empty.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            company.getOrderDetails(1, null);
        }
        catch (IllegalArgumentException e){
            assertEquals("Garage holder name cannot be null.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            company.getOrderDetails(1, "      ");
        }
        catch (IllegalArgumentException e){
            assertEquals("Garage holder name cannot be whitespace.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            company.getOrderDetails(3, "Danny Smeets");
        }
        catch (OrderNotFoundException e){
            got_error = true;
        }
        assertTrue(got_error);
    }

    @Test
    void testSmallGettersCompany(){
        assertEquals(3, company.getAvailableModels().size());
        boolean got_error = false;
        try{
            company.selectModelString(null);
        }
        catch (IllegalArgumentException | IllegalModelException e){
            assertEquals("Model name cannot be null.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            company.selectModelString("");
        }
        catch (IllegalArgumentException | IllegalModelException e){
            assertEquals("Model name cannot be empty.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            company.selectModelString(" ");
        }
        catch (IllegalArgumentException | IllegalModelException e){
            assertEquals("Model name cannot be whitespace.", e.getMessage());
            got_error = true;
        }
        try{
            company.selectModel(null);
        }
        catch (IllegalArgumentException | IllegalModelException e){
            assertEquals("Model name cannot be null.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            company.selectModel("");
        }
        catch (IllegalArgumentException | IllegalModelException e){
            assertEquals("Model name cannot be empty.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            company.selectModel(" ");
        }
        catch (IllegalArgumentException | IllegalModelException e){
            assertEquals("Model name cannot be whitespace.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            company.getStatistics(-1);
        }
        catch (IllegalArgumentException e){
            assertEquals("fromXLastDays must be strictly positive.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            company.completeOrderingForm(null,null,null);
        }
        catch (IllegalArgumentException | IllegalCompletionDateException | IllegalConstraintException | IllegalModelException | OptionThenComponentException | OptionAThenOptionBException | RequiredComponentException e){
            assertEquals("chosenOptions cannot be null.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
    }

    @Test
    void testStatistics() throws OrderNotFoundException {
        List<CarOrder>[] orders = company.getOrdersFromGarageHolder("Danny Smeets");
        ArrayList<String> orderDetails = company.getOrderDetails(orders[0].get(0).getOrderID(), "Danny Smeets");
        System.out.println(orderDetails);
        List<String> statistics = company.getStatistics(0);
        System.out.println(statistics);
    }

    @Test
    void testNewLogin() throws IllegalArgumentException {
        boolean got_error = false;
        try{
            company.newLogin(null);
        }
        catch (IllegalArgumentException e){
            assertEquals("Garage holder name cannot be null.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            company.newLogin("");
        }
        catch (IllegalArgumentException e){
            assertEquals("Garage holder name cannot be empty.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            company.newLogin(" ");
        }
        catch (IllegalArgumentException e){
            assertEquals("Garage holder name cannot be whitespace.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        System.out.println("---------------------New Login---------------------");
        // print the two lists returned by the newLogin method
        ArrayList<String[]>[] login = company.newLogin("kim Smeets");
        // print the first list
        System.out.println("The first list is: ");
        for (String[] s : login[0]){
            System.out.println(s[0] + " " + s[1]);
        }
        // print the second list
        System.out.println("The second list is: ");
        for (String[] s : login[1]){
            System.out.println(s[0] + " " + s[1]);
        }
    }

    @Test
    void testConstructorCarModelSpecification(){
        TreeMap<String, HashSet<String>> D = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        D.put("Body", new HashSet<>() {{
            add("Sedan");
            add("Break");
        }});
        D.put("Color", new HashSet<>() {{
            add("red");
            add("blue");
            add("black");
            add("white");
        }});
        D.put("Engine", new HashSet<>() {{
            add("V4");
            add("V6");
        }});
        D.put("Gearbox", new HashSet<>() {{
            add("6 manual");
            add("5 manual");
            add("5 automatic");
        }});
        D.put("Seats", new HashSet<>() {{
            add("leather white");
            add("leather black");
            add("vinyl grey");
        }});
        D.put("Airco", new HashSet<>() {{
            add("Manual");
            add("Automatic");
        }});
        D.put("Wheels", new HashSet<>() {{
            add("winter");
            add("comfort");
            add("sports");
        }});
        boolean got_error = false;
        try{
            new CarModelSpecification(null, 80, D);
        }
        catch (IllegalArgumentException e){
            assertEquals("A modelName cannot be null.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            new CarModelSpecification("", 80, D);
        }
        catch (IllegalArgumentException e){
            assertEquals("A modelName cannot be the empty string.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            new CarModelSpecification("   ", 80, D);
        }
        catch (IllegalArgumentException e){
            assertEquals("A modelName cannot be whitespace.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            new CarModelSpecification("D", 0, D);
        }
        catch (IllegalArgumentException e){
            assertEquals("A standard task time must be greater than 0.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            new CarModelSpecification("D", 80, null);
        }
        catch (IllegalArgumentException e){
            assertEquals("A car model specification cannot be null.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
    }

    @Test
    void testSetModelName() {
        String oldName;
        TreeMap<String, HashSet<String>> oldOptions;
        boolean got_error;
        for (CarModelSpecification c : company.getCatalog().getAvailableModels()) {
            oldName = c.getModelName();
            got_error = false;
            try{
                c.setModelName(null);
            }
            catch (IllegalArgumentException e){
                assertEquals("A modelName cannot be null.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.setModelName("");
            }
            catch (IllegalArgumentException e){
                assertEquals("A modelName cannot be the empty string.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.setModelName("     ");
            }
            catch (IllegalArgumentException e){
                assertEquals("A modelName cannot be whitespace.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            c.setModelName("D");
            assertEquals("D", c.getModelName());
            c.setModelName(oldName);
            oldOptions = c.getAvailableOptions();
            c.setAvailableOptions(new TreeMap<>());
            c.setAvailableOptions(oldOptions);
        }
    }

    @Test
    void testToStringCarModelSpecification() {
        for (CarModelSpecification c : company.getCatalog().getAvailableModels()) {
            // print a nice box
            System.out.println("+" + String.join("", Collections.nCopies(c.toString().length() + 2, "-")) + "+");
            System.out.println("| " + c + " |");
            System.out.println("+" + String.join("", Collections.nCopies(c.toString().length() + 2, "-")) + "+");
        }
    }

    @Test
    void testAddOption() {
        boolean got_error;
        for (CarModelSpecification c : company.getCatalog().getAvailableModels()) {
            got_error = false;
            try{
                c.addOption(null, "D");
            }
            catch (IllegalArgumentException e){
                assertEquals("A component cannot be null.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.addOption("", "D");
            }
            catch (IllegalArgumentException e){
                assertEquals("A component cannot be the empty string.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.addOption("     ", "D");
            }
            catch (IllegalArgumentException e){
                assertEquals("A component cannot be whitespace.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.addOption("D", null);
            }
            catch (IllegalArgumentException e){
                assertEquals("An option cannot be null.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.addOption("D", "");
            }
            catch (IllegalArgumentException e){
                assertEquals("An option cannot be the empty string.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.addOption("D", "     ");
            }
            catch (IllegalArgumentException e){
                assertEquals("An option cannot be whitespace.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            c.addOption("Price", "28000");
            assertTrue(c.getAvailableOptions().get("price").contains("28000"));
            c.addOption("wheels", "4");
            assertTrue(c.getAvailableOptions().get("wheels").contains("4"));
        }
    }

    @Test
    void testAddComponent() {
        boolean got_error;
        HashSet<String> options = new HashSet<>();
        for (CarModelSpecification c : company.getCatalog().getAvailableModels()) {
            got_error = false;
            try{
                c.addComponent(null, options);
            }
            catch (IllegalArgumentException e){
                assertEquals("A component cannot be null.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.addComponent("", options);
            }
            catch (IllegalArgumentException e){
                assertEquals("A component cannot be the empty string.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.addComponent("    ", options);
            }
            catch (IllegalArgumentException e){
                assertEquals("A component cannot be whitespace.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.addComponent("D", null);
            }
            catch (IllegalArgumentException e){
                assertEquals("There must be at least one option for the given component.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            c.addComponent("beamGuns", options);
            assertTrue(c.getAvailableOptions().containsKey("beamGuns"));
        }
    }

    @Test
    void testRemoveComponent() {
        boolean got_error;
        for (CarModelSpecification c : company.getCatalog().getAvailableModels()) {
            got_error = false;
            try{
                c.removeComponent(null);
            }
            catch (IllegalArgumentException e){
                assertEquals("A component cannot be null.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.removeComponent("");
            }
            catch (IllegalArgumentException e){
                assertEquals("A component cannot be the empty string.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.removeComponent("    ");
            }
            catch (IllegalArgumentException e){
                assertEquals("A component cannot be whitespace.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.removeComponent("not_a_component");
            }
            catch (IllegalArgumentException e){
                assertEquals("The given component does not exist.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
        }
    }


}
