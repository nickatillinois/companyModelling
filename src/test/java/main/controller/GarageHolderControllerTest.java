package main.controller;

import assemAssist.CarModel;
import assemAssist.CarOrder;
import assemAssist.Company;
import assemAssist.exceptions.*;
import controller.GarageHolderController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GarageHolderControllerTest {

    static GarageHolderController controller;
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
        controller = new GarageHolderController(company);
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
    void testNewLogin() throws IllegalArgumentException {
        boolean got_error = false;
        try{
            controller.newLogin(null);
        }
        catch (IllegalArgumentException e){
            assertEquals("Garage holder cannot be null", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            controller.newLogin("");
        }
        catch (IllegalArgumentException e){
            assertEquals("Garage holder cannot be empty", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            controller.newLogin(" ");
        }
        catch (IllegalArgumentException e){
            assertEquals("Garage holder cannot be whitespace", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        System.out.println("---------------------New Login---------------------");
        // print the two lists returned by the newLogin method
        ArrayList<String[]>[] login = controller.newLogin("kim Smeets");
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


}
