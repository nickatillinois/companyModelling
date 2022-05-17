package classTests;

import assemAssist.*;
import assemAssist.exceptions.*;
import assemAssist.workStation.AccessoriesPost;
import assemAssist.workStation.CarBodyPost;
import assemAssist.workStation.DrivetrainPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class WorkStationTest {

    static CarBodyPost carBodyPost;
    static DrivetrainPost drivetrainPost;
    static AccessoriesPost accessoriesPost;
    static CarOrder orderA;
    static CarOrder orderB;
    static CarOrder orderC;

    @BeforeEach
    void init() throws IllegalModelException, IllegalConstraintException, OptionAThenOptionBException, OptionThenComponentException, RequiredComponentException {

        Company company = new Company();
        carBodyPost = new CarBodyPost(company);
        drivetrainPost = new DrivetrainPost(company);
        accessoriesPost = new AccessoriesPost(company);

        TreeMap<String, String> chosenOptionsA = new TreeMap<>();
        chosenOptionsA.put("Body", "Sedan");
        chosenOptionsA.put("Color", "blue");
        chosenOptionsA.put("Engine", "V4");
        chosenOptionsA.put("Gearbox", "5 manual");
        chosenOptionsA.put("Seats", "leather white");
        chosenOptionsA.put("Airco", "Manual");
        chosenOptionsA.put("Wheels", "comfort");
        orderA = new CarOrder("A", new CarModel("A", chosenOptionsA),company.getWorkingTimeWorkingStation("A"));

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

        carBodyPost.setCurrentOrder(orderA);
        drivetrainPost.setCurrentOrder(orderB);
        accessoriesPost.setCurrentOrder(orderC);
    }

    @Test
    void getAndSetCurrentOrderTest() {
        drivetrainPost.setCurrentOrder(orderA);
        assertEquals(orderA, drivetrainPost.getCurrentOrder());
    }

    @Test
    void getNameTest() {
        assertEquals("Car Body Post", carBodyPost.getName());
        assertEquals("Drivetrain Post", drivetrainPost.getName());
        assertEquals("Accessories Post", accessoriesPost.getName());
    }

    @Test
    void getCarBodyTasksTest() {
        List<AssemblyTask> carBodyTasks = List.of(new AssemblyTask("body", "assemble sedan body"), new AssemblyTask("paint", "paint the car blue"));
        for (int i = 0; i < carBodyTasks.size(); i++) {
            assertEquals(carBodyTasks.get(i).getName(), carBodyPost.getTasks().get(i).getName());
            assertEquals(carBodyTasks.get(i).getTaskDefinition(), carBodyPost.getTasks().get(i).getTaskDefinition());
        }
    }

    @Test
    void getDrivetrainTasksTest() {

        List<AssemblyTask> drivetrainTasks = new ArrayList<>();
        drivetrainTasks.add(new AssemblyTask("engine", "insert V8"));
        drivetrainTasks.add(new AssemblyTask("gearbox", "install 6 manual"));
        for (int i = 0; i < drivetrainTasks.size(); i++) {
            assertEquals(drivetrainTasks.get(i).getName(), drivetrainPost.getTasks().get(i).getName());
            assertEquals(drivetrainTasks.get(i).getTaskDefinition(), drivetrainPost.getTasks().get(i).getTaskDefinition());
        }
    }

    @Test
    void getAccessoriesTasksTest() {

        List<AssemblyTask> accessoriesTasks = new ArrayList<>();
        accessoriesTasks.add(new AssemblyTask("seats", "install leather black seats"));
        accessoriesTasks.add(new AssemblyTask("airco", "install manual airco"));
        accessoriesTasks.add(new AssemblyTask("wheels", "mount sports wheels"));
        accessoriesTasks.add(new AssemblyTask("spoiler", "install high spoiler"));
        for (int i = 0; i < accessoriesTasks.size(); i++) {
            assertEquals(accessoriesTasks.get(i).getName(), accessoriesPost.getTasks().get(i).getName());
            assertEquals(accessoriesTasks.get(i).getTaskDefinition(), accessoriesPost.getTasks().get(i).getTaskDefinition());
        }
    }

    @Test
    void isFinishedTest() {

        assertFalse(carBodyPost.isFinished());
        assertFalse(drivetrainPost.isFinished());

        carBodyPost.performAssemblyTask("body",60);
        carBodyPost.performAssemblyTask("paint",60);
        assertTrue(carBodyPost.isFinished());
        assertFalse(drivetrainPost.isFinished());

    }

    @Test
    void getPendingTasksTest() {
        List<String> pendingCBP = List.of("body", "paint");
        assertEquals(carBodyPost.getPendingTasks(), pendingCBP);
        List<String> pendingDP = List.of("engine", "gearbox");
        assertEquals(drivetrainPost.getPendingTasks(), pendingDP);
    }

    @Test
    void getFinishedTasksTest() {
        carBodyPost.performAssemblyTask("body", 60);
        List<String> finishedCBP = List.of("body");
        assertEquals(finishedCBP, carBodyPost.getFinishedTasks());
    }

    @Test
    void getInformationFromTaskTest() {
        assertEquals("assemble sedan body", carBodyPost.getInformationFromTask("body"));
        assertEquals("paint the car blue", carBodyPost.getInformationFromTask("paint"));
        assertEquals("install leather black seats", accessoriesPost.getInformationFromTask("seats"));
        assertEquals("install manual airco", accessoriesPost.getInformationFromTask("airco"));
        assertEquals("mount sports wheels", accessoriesPost.getInformationFromTask("wheels"));
        assertEquals("install high spoiler", accessoriesPost.getInformationFromTask("spoiler"));
        assertEquals("insert V8", drivetrainPost.getInformationFromTask("engine"));
        assertEquals("install 6 manual", drivetrainPost.getInformationFromTask("gearbox"));

        carBodyPost.setCurrentOrder(orderB);
        assertEquals("assemble sport body", carBodyPost.getInformationFromTask("body"));

    }

    @Test
    void performAssemblyTaskTest() {
        List<String> pendingCBP = List.of("body","paint");
        assertEquals(carBodyPost.getPendingTasks(), pendingCBP);
        List<String> pendingDP = List.of("engine","gearbox");
        assertEquals(drivetrainPost.getPendingTasks(), pendingDP);

        carBodyPost.performAssemblyTask("body",60);
        List<String> pendingCBPupdated = List.of("paint");
        assertEquals(carBodyPost.getPendingTasks(), pendingCBPupdated);
        carBodyPost.performAssemblyTask("paint",60);
        pendingCBPupdated = List.of();
        assertEquals(carBodyPost.getPendingTasks(), pendingCBPupdated);
    }

    @Test
    void getTasksAndStatusTest() {
        String statusCBP = carBodyPost.getTasksAndStatus();
        assertEquals(statusCBP, "body: pending, paint: pending");
        String statusDP = drivetrainPost.getTasksAndStatus();
        assertEquals(statusDP, "engine: pending, gearbox: pending");

        carBodyPost.performAssemblyTask("body",60);
        statusCBP = carBodyPost.getTasksAndStatus();
        assertEquals(statusCBP, "body: done, paint: pending");
    }
}