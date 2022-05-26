package useCases;

import assemAssist.*;
import assemAssist.exceptions.*;
import assemAssist.workStation.WorkStation;
import controller.GarageHolderController;
import controller.ManagerController;
import controller.MechanicController;
import org.junit.jupiter.api.*;
import ui.GarageHolderUI;
import ui.ManagerUI;
import ui.MechanicUI;
import ui.UI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UC4_CheckAssemblyLineStatusTest {

    private Company company;
    private AssemblyLine assemblyLine;
    private ProductionScheduler productionScheduler;
    private Mechanic mechanic;

    @BeforeEach
    void init() throws IllegalConstraintException {
        company = new Company();
        productionScheduler = company.getProductionScheduler();
        assemblyLine = productionScheduler.getAssemblyLine();
        mechanic = new Mechanic(assemblyLine);
    }

    @Test
    void assemblyLineNoStationTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        ByteArrayInputStream in = new ByteArrayInputStream("1\no".getBytes());
        System.setIn(in);
        try{
            new UI(new GarageHolderUI(new GarageHolderController(company)),new ManagerUI(new ManagerController(company)),
                    new MechanicUI(new MechanicController(mechanic)));
        } catch (Exception ignored) {}
        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened

        boolean expectedOutput = out.toString().contains("The current status of the assembly line is:\r\n" +
                "Car Body Post\r\n" +
                "-> No order at this work station.\r\n" +
                "Drivetrain Post\r\n" +
                "-> No order at this work station.\r\n" +
                "Accessories Post\r\n" +
                "-> No order at this work station.");

        assertTrue(expectedOutput);

    }

    @Test
    void assemblyLineOnlyOneStationTest() {
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red"); legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4"); legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual"); legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        try{
            assemblyLine.getWorkStations().get(0).setCurrentOrder(new CarOrder("Raf Sablon", new CarModel("A", legalAOptions, 60)));
        } catch (Exception ignored){}

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        ByteArrayInputStream in = new ByteArrayInputStream("1\no".getBytes());
        System.setIn(in);
        try{
            new UI(new GarageHolderUI(new GarageHolderController(company)),new ManagerUI(new ManagerController(company)),
                    new MechanicUI(new MechanicController(mechanic)));
        } catch (Exception ignored) {}
        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened

        boolean expectedOutput = out.toString().contains("The current status of the assembly line is:\r\n" +
                "Car Body Post\r\n" +
                "-> body: pending, paint: pending\r\n" +
                "Drivetrain Post\r\n" +
                "-> No order at this work station.\r\n" +
                "Accessories Post\r\n" +
                "-> No order at this work station.");

        assertTrue(expectedOutput);
    }

    @Test
    void assemblyLineTwoStationsTest() {
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red"); legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4"); legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual"); legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        try {
            assemblyLine.getWorkStations().get(0).setCurrentOrder(new CarOrder("Raf Sablon", new CarModel("A", legalAOptions, 60)));
            assemblyLine.getWorkStations().get(2).setCurrentOrder(new CarOrder("Raf Sablon", new CarModel("A", legalAOptions, 60)));
        } catch (Exception ignored){}

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        ByteArrayInputStream in = new ByteArrayInputStream("1\no".getBytes());
        System.setIn(in);
        try{
            new UI(new GarageHolderUI(new GarageHolderController(company)),new ManagerUI(new ManagerController(company)),
                    new MechanicUI(new MechanicController(mechanic)));
        } catch (Exception ignored) {}
        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened

        boolean expectedOutput = out.toString().contains("The current status of the assembly line is:\r\n" +
                "Car Body Post\r\n" +
                "-> body: pending, paint: pending\r\n" +
                "Drivetrain Post\r\n" +
                "-> No order at this work station.\r\n" +
                "Accessories Post\r\n" +
                "-> seats: pending, airco: pending, wheels: pending");

        assertTrue(expectedOutput);
    }

    @Test
    void assemblyLineAllStationsTest() {
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red"); legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4"); legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual"); legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        try {
            assemblyLine.getWorkStations().get(0).setCurrentOrder(new CarOrder("Raf Sablon", new CarModel("A", legalAOptions, 60)));
            assemblyLine.getWorkStations().get(1).setCurrentOrder(new CarOrder("Raf Sablon", new CarModel("A", legalAOptions, 60)));
            assemblyLine.getWorkStations().get(2).setCurrentOrder(new CarOrder("Raf Sablon", new CarModel("A", legalAOptions, 60)));
        } catch (Exception ignored){}

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        ByteArrayInputStream in = new ByteArrayInputStream("1\no".getBytes());
        System.setIn(in);
        try{
            new UI(new GarageHolderUI(new GarageHolderController(company)),new ManagerUI(new ManagerController(company)),
                    new MechanicUI(new MechanicController(mechanic)));
        } catch (Exception ignored) {}
        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened

        boolean expectedOutput = out.toString().contains("The current status of the assembly line is:\r\n" +
                "Car Body Post\r\n" +
                "-> body: pending, paint: pending\r\n" +
                "Drivetrain Post\r\n" +
                "-> engine: pending, gearbox: pending\r\n" +
                "Accessories Post\r\n" +
                "-> seats: pending, airco: pending, wheels: pending");

        assertTrue(expectedOutput);
    }

}


