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

class AssemblyLineTest {

    static AssemblyLine assemblyLine;
    static CarOrder orderA;
    static CarOrder orderB;
    static CarOrder orderC;

    @BeforeEach
    void init() throws IllegalModelException, IllegalConstraintException, OptionAThenOptionBException, OptionThenComponentException, RequiredComponentException {
        Company company = new Company();
        assemblyLine = new AssemblyLine(company);

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
        orderB.setEstCompletionTime(LocalDateTime.now());

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
        orderC.setEstCompletionTime(LocalDateTime.now());
    }

    @Test
    void getWorkStationsTest() {
        ArrayList<WorkStation> workStationstest = new ArrayList<>();
        workStationstest.add(new CarBodyPost(null));
        workStationstest.add(new DrivetrainPost(null));
        workStationstest.add(new AccessoriesPost(null));
        assertEquals(workStationstest,assemblyLine.getWorkStations());
    }

    @Test
    void getWorkStationNamesTest() {
        List<String> workStationNames = new ArrayList<>();
        workStationNames.add("Car Body Post");
        workStationNames.add("Drivetrain Post");
        workStationNames.add("Accessories Post");
        assertEquals(workStationNames, assemblyLine.getWorkStationNames());
    }

    @Test
    void getMinutesWorkedTodayTest() {

    }

    @Test
    void nextDayTest() {
        assemblyLine.nextDay();
        assertEquals(0,assemblyLine.getMinutesWorkedToday());
        assemblyLine.move(orderA,120);
        assertEquals(120,assemblyLine.getMinutesWorkedToday());
        for(AssemblyTask assemblyTask : assemblyLine.getWorkStations().get(0).getTasks())
            assemblyTask.setIsCompleted(true);
        assemblyLine.move(null, 16*60);
        assertEquals(18*60,assemblyLine.getMinutesWorkedToday());
        for(AssemblyTask assemblyTask : assemblyLine.getWorkStations().get(1).getTasks())
            assemblyTask.setIsCompleted(true);
        assemblyLine.move(null, 2*60);
        assertEquals(20*60, assemblyLine.getMinutesWorkedToday());
        for(AssemblyTask assemblyTask : assemblyLine.getWorkStations().get(2).getTasks())
            assemblyTask.setIsCompleted(true);
        assemblyLine.move(null,2*60);
        assertEquals(22*60, assemblyLine.getMinutesWorkedToday());
        assemblyLine.nextDay();
        assertEquals(((22-6)-(22-(22-6)))*60,assemblyLine.remainingWorkingTime());
    }

    @Test
    void getCurrentStateTest() {
        ArrayList<CarOrder> test = new ArrayList<>();
        test.add(null);
        test.add(null);
        test.add(null);
        assertEquals(test, assemblyLine.getCurrentState());
        ArrayList<CarOrder> test2 = new ArrayList<>();
        test2.add(orderA);
        test2.add(null);
        test2.add(null);
        assemblyLine.getWorkStations().get(0).setCurrentOrder(orderA);
        assertEquals(test2,assemblyLine.getCurrentState());

    }

    @Test
    void getCurrentStateStringTest() {
        ArrayList<String> test = new ArrayList<>();
        test.add("Car Body Post ; ");
        test.add("Drivetrain Post ; ");
        test.add("Accessories Post ; ");
        assertEquals(test, assemblyLine.getCurrentStateString());

        ArrayList<String> test2 = new ArrayList<>();
        test2.add("Car Body Post ; body: pending, paint: pending");
        test2.add("Drivetrain Post ; ");
        test2.add("Accessories Post ; ");
        assemblyLine.getWorkStations().get(0).setCurrentOrder(orderA);
        assertEquals(test2,assemblyLine.getCurrentStateString());

        ArrayList<String> test3 = new ArrayList<>();
        test3.add("Car Body Post ; body: done, paint: pending");
        test3.add("Drivetrain Post ; ");
        test3.add("Accessories Post ; ");
        assemblyLine.getWorkStations().get(0).performAssemblyTask("body", 60);
        assertEquals(test3,assemblyLine.getCurrentStateString());
    }

    @Test
    void canMoveTest() {
        assemblyLine.getWorkStations().get(0).setCurrentOrder(orderA);
        assert(!assemblyLine.canMove());
        for(AssemblyTask assemblyTask : assemblyLine.getWorkStations().get(0).getTasks())
            assemblyTask.setIsCompleted(true);
        assert(assemblyLine.canMove());
    }

    @Test
    void moveTest() { // also tests the method assemblyline.remainingWorkingTime()
        assemblyLine.getWorkStations().get(1).setCurrentOrder(orderA);
        assemblyLine.getWorkStations().get(0).setCurrentOrder(orderB);
        for(AssemblyTask assemblyTask : assemblyLine.getWorkStations().get(0).getTasks())
            assemblyTask.setIsCompleted(true);
        for(AssemblyTask assemblyTask : assemblyLine.getWorkStations().get(1).getTasks())
            assemblyTask.setIsCompleted(true);
        assemblyLine.move(null, 60);
        assertEquals((22-6-1)*60,assemblyLine.remainingWorkingTime());
        assertEquals(orderA, assemblyLine.getWorkStations().get(2).getCurrentOrder());
        for(AssemblyTask assemblyTask : assemblyLine.getWorkStations().get(1).getTasks())
            assemblyTask.setIsCompleted(true);
        for(AssemblyTask assemblyTask : assemblyLine.getWorkStations().get(2).getTasks())
            assemblyTask.setIsCompleted(true);
        assemblyLine.move(null,1*60);
        assertEquals((22-6-2)*60,assemblyLine.remainingWorkingTime());
        assertEquals(orderB, assemblyLine.getWorkStations().get(2).getCurrentOrder());
        assert(orderA.isCompleted());

    }

    @Test
    void findWorkStationTest() {
        assertEquals(new DrivetrainPost(null), assemblyLine.findWorkStation("Drivetrain Post"));
        assertEquals(new CarBodyPost(null), assemblyLine.findWorkStation("Car Body Post"));
        assertEquals(new AccessoriesPost(null), assemblyLine.findWorkStation("Accessories Post"));
        assertThrows(IllegalArgumentException.class, () -> assemblyLine.findWorkStation("foo"));
    }

}