package main.assemAssist;


import assemAssist.CarModel;
import assemAssist.CarOrder;
import assemAssist.CarOrderData;
import assemAssist.Company;
import assemAssist.exceptions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;


public class CompanyTest {

    static Company company;
    static CarOrder carOrderA;
    static CarOrder carOrderB;
    static CarOrder carOrderC;
    static CarOrder carOrderD;
    static CarOrder carOrderE;
    static CarOrder carOrderF;

    @BeforeAll
    static void init() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException, IllegalCompletionDateException {
        company = new Company();
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red");
        legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4");
        legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual");
        legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        CarModel carModelA = new CarModel("A", legalAOptions,company.getWorkingTimeWorkingStation("A"));
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
        CarModel carModelB = new CarModel("B", legalBOptions,company.getWorkingTimeWorkingStation("B"));
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
        CarModel carModelC = new CarModel("C", legalCOptions,company.getWorkingTimeWorkingStation("C"));
        carOrderC = new CarOrder("Kim Smeets", carModelC);
        //company.addOrderToProductionSchedule(carOrderA);
        carOrderD = new CarOrder("Tanya Smeets", carModelC);
        carOrderE = new CarOrder("Kimberly Smeets", carModelC);
        carOrderF = new CarOrder("Vanessa Smeets", carModelC);
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
        carOrderD.setCompleted();
        carOrderE.setCompleted();
        carOrderF.setCompleted();
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
        CarOrderData orderDetails = company.getOrderDetails(orders[0].get(0).getOrderID(), "Danny Smeets");
        System.out.println(orderDetails);
        List<String> statistics = company.getStatistics(0);
        System.out.println(statistics);
    }
}
