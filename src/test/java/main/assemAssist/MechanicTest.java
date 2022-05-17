package main.assemAssist;

import assemAssist.*;
import assemAssist.exceptions.*;
import assemAssist.workStation.AccessoriesPost;
import assemAssist.workStation.CarBodyPost;
import assemAssist.workStation.DrivetrainPost;
import assemAssist.workStation.WorkStation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class MechanicTest {

    static Mechanic mechanic;
    static AssemblyLine assemblyLine;

    static CarOrder orderA;
    static CarOrder orderB;
    static CarOrder orderC;

    @BeforeEach
    void init() throws RequiredComponentException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, IllegalModelException {
        Company company = new Company();
        assemblyLine = new AssemblyLine(company);
        mechanic = new Mechanic(assemblyLine);

        mechanic.selectWorkStation("Car Body Post");

        TreeMap<String, String> chosenOptionsA = new TreeMap<>();
        chosenOptionsA.put("Body", "Sedan");
        chosenOptionsA.put("Color", "blue");
        chosenOptionsA.put("Engine", "V4");
        chosenOptionsA.put("Gearbox", "5 manual");
        chosenOptionsA.put("Seats", "leather white");
        chosenOptionsA.put("Airco", "Manual");
        chosenOptionsA.put("Wheels", "comfort");
        orderA = new CarOrder("A", new CarModel("A", chosenOptionsA),company.getWorkingTimeWorkingStation("A"));
        orderA.setEstCompletionTime(LocalDateTime.now());

        TreeMap<String, String> chosenOptionsB = new TreeMap<>();
        chosenOptionsB.put("Body", "Sport");
        chosenOptionsB.put("Color", "yellow");
        chosenOptionsB.put("Engine", "V8");
        chosenOptionsB.put("Gearbox", "6 manual");
        chosenOptionsB.put("Seats", "vinyl grey");
        chosenOptionsB.put("Airco", "Manual");
        chosenOptionsB.put("Wheels", "sports");
        chosenOptionsB.put("spoiler", "low");
        orderB = new CarOrder("B", new CarModel("B", chosenOptionsB),company.getWorkingTimeWorkingStation("B"));

        TreeMap<String, String> chosenOptionsC = new TreeMap<>();
        chosenOptionsC.put("Body", "Sport");
        chosenOptionsC.put("Color", "black");
        chosenOptionsC.put("Engine", "V6");
        chosenOptionsC.put("Gearbox", "6 manual");
        chosenOptionsC.put("Seats", "leather black");
        chosenOptionsC.put("Airco", "Manual");
        chosenOptionsC.put("Wheels", "sports");
        chosenOptionsC.put("spoiler", "high");
        orderC = new CarOrder("C", new CarModel("C", chosenOptionsC),company.getWorkingTimeWorkingStation("C"));
    }

    @Test
    void getAssemblyLineTest() {
        assertEquals(assemblyLine, mechanic.getAssemblyLine());
    }

    @Test
    void getAllWorkStationsTest() {
        List<String> expWorkStations = List.of("Car Body Post", "Drivetrain Post", "Accessories Post");
        assertEquals(expWorkStations, mechanic.getAllWorkStations());
    }

    @Test
    void selectWorkStationTest() { // also tests mechanic.getWorkStation()
        assemblyLine.getWorkStations().get(0).setCurrentOrder(orderA);
        assemblyLine.getWorkStations().get(1).setCurrentOrder(orderB);
        List<String> expPendingTasks = List.of("engine", "gearbox");
        assertEquals(expPendingTasks, mechanic.selectWorkStation("Drivetrain Post"));
        assertEquals("Drivetrain Post", mechanic.getWorkStation().getName());
        assertThrows(IllegalArgumentException.class, () -> mechanic.selectWorkStation(null));
        assertThrows(IllegalArgumentException.class, () -> mechanic.selectWorkStation("Motorcycle Post"));
    }

    @Test
    void selectTaskTest() {
        assemblyLine.move(orderA, 60);
        assertEquals("assemble sedan body", mechanic.selectTask("body"));
        assertThrows(IllegalArgumentException.class, () -> mechanic.selectTask(null));
        assertThrows(IllegalArgumentException.class, () -> mechanic.selectTask("seats"));
    }

    @Test
    void finishTaskTest() {
        assemblyLine.getWorkStations().get(0).setCurrentOrder(orderA);
        assemblyLine.getWorkStations().get(1).setCurrentOrder(orderB);
        List<String> expPendingTasks = List.of("body");
        assertEquals(expPendingTasks, mechanic.finishTask("paint", 60));
        assertThrows(IllegalArgumentException.class, () -> mechanic.finishTask(null, 60));
        assertThrows(IllegalArgumentException.class, () -> mechanic.finishTask("engine", 60));
    }

    @Test
    void getCurrentStateAssembly() {
        assemblyLine.getWorkStations().get(0).setCurrentOrder(orderA);
        assemblyLine.getWorkStations().get(1).setCurrentOrder(orderB);
        assemblyLine.getWorkStations().get(2).setCurrentOrder(orderC);

        List<String> expStateAssembly = new ArrayList<>();
        expStateAssembly.add("Car Body Post ; body: pending, paint: pending");
        expStateAssembly.add("Drivetrain Post ; engine: pending, gearbox: pending");
        expStateAssembly.add("Accessories Post ; seats: pending, airco: pending, wheels: pending, spoiler: pending");

        assertEquals(expStateAssembly, mechanic.getCurrentStateAssembly());
    }
}
