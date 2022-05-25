package useCases;

import assemAssist.Company;
import assemAssist.Mechanic;
import assemAssist.exceptions.*;
import controller.GarageHolderController;
import controller.ManagerController;
import controller.MechanicController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.GarageHolderUI;
import ui.ManagerUI;
import ui.MechanicUI;
import ui.UI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckOrderDetailsTest {

    private Company NicksSecondCompany;


    @BeforeEach
    void init() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException, IllegalCompletionDateException {
        NicksSecondCompany = new Company();
        assertEquals(NicksSecondCompany.getProductionScheduler().getPendingOrders().size(), 0);
        assertEquals(NicksSecondCompany.getCompletedCarOrders().size(), 0);
        TreeMap<String, String> legal_AOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legal_AOptions.put("color", "red");
        legal_AOptions.put("body", "break");
        legal_AOptions.put("engine", "v4");
        legal_AOptions.put("seats", "leather white");
        legal_AOptions.put("airco", "manual");
        legal_AOptions.put("gearbox", "6 manual");
        legal_AOptions.put("wheels", "winter");
        TreeMap<String, String> legal_BOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legal_BOptions.put("color", "red");
        legal_BOptions.put("body", "break");
        legal_BOptions.put("engine", "v4");
        legal_BOptions.put("seats", "leather white");
        legal_BOptions.put("airco", "manual");
        legal_BOptions.put("gearbox", "6 manual");
        legal_BOptions.put("wheels", "winter");
        legal_BOptions.put("spoiler", "low");
        NicksSecondCompany.completeOrderingForm(legal_AOptions,"Timo Smeets","A");
        NicksSecondCompany.completeOrderingForm(legal_BOptions,"Timo Smeets","B");
        NicksSecondCompany.completeOrderingForm(legal_AOptions,"Timo Smeets","A");
        NicksSecondCompany.completeOrderingForm(legal_AOptions,"Filip Smeets","B");
        NicksSecondCompany.completeOrderingForm(legal_AOptions,"Filip Smeets","A");
    }

    @Test
    public void testRequestDetails() {
        assertEquals(NicksSecondCompany.getProductionScheduler().getPendingOrders().size(), 5);
        assertEquals(NicksSecondCompany.getOrdersFromGarageHolder("Filip Smeets")[0].size(), 2);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        ByteArrayInputStream in = new ByteArrayInputStream("2\nTimo Smeets\nn\nA\ns\n0\ns\n0\ns\n0\ns\n0\ns\n0\ns\n0\ns\n0\nq\n2\nTimo Smeets\nd\n1".getBytes());
        System.setIn(in);
        try{
            new UI(new GarageHolderUI(new GarageHolderController(NicksSecondCompany)),new ManagerUI(new ManagerController(NicksSecondCompany)),
                    new MechanicUI(new MechanicController(new Mechanic(NicksSecondCompany.getProductionScheduler().getAssemblyLine()))));
        } catch (Exception ignored) {}
        assertEquals(NicksSecondCompany.getOrdersFromGarageHolder("Timo Smeets")[0].size(), 4);
        assertEquals(NicksSecondCompany.getOrdersFromGarageHolder("Filip Smeets")[0].size(), 2);
        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened
        boolean containsString = out.toString().contains("Specifications: model: A, {airco=manual, body=break, color=red, engine=v4, gearbox=6 manual, seats=leather white, wheels=winter}");
        assertTrue(containsString);
        System.out.println("Here: " + out);
    }

    @Test
    public void testRequestIllegalDetails(){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        ByteArrayInputStream in = new ByteArrayInputStream("2\nTimo Smeets\nd\n4".getBytes());
        System.setIn(in);
        try{
            new UI(new GarageHolderUI(new GarageHolderController(NicksSecondCompany)),new ManagerUI(new ManagerController(NicksSecondCompany)),
                    new MechanicUI(new MechanicController(new Mechanic(NicksSecondCompany.getProductionScheduler().getAssemblyLine()))));
        } catch (Exception ignored) {}
        assertEquals(NicksSecondCompany.getOrdersFromGarageHolder("Timo Smeets")[0].size(), 3);
        assertEquals(NicksSecondCompany.getOrdersFromGarageHolder("Filip Smeets")[0].size(), 2);
        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened
        boolean containsString = out.toString().contains("We could not find an order with this ID.");
        assertTrue(containsString);
        System.out.println("Here: " + out);
    }
}
