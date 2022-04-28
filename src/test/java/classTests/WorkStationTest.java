package classTests;

import assemAssist.*;
import assemAssist.exceptions.*;
import assemAssist.workStation.AccessoriesPost;
import assemAssist.workStation.CarBodyPost;
import assemAssist.workStation.DrivetrainPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
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

        carBodyPost = new CarBodyPost();
        drivetrainPost = new DrivetrainPost();
        accessoriesPost = new AccessoriesPost();

        Catalog catalog = new Catalog();

        TreeMap<String, String> chosenOptionsA = new TreeMap<>();
        chosenOptionsA.put("Body", "Sedan");
        chosenOptionsA.put("Color", "blue");
        chosenOptionsA.put("Engine", "V4");
        chosenOptionsA.put("Gearbox", "5 manual");
        chosenOptionsA.put("Seats", "leather white");
        chosenOptionsA.put("Airco", "Manual");
        chosenOptionsA.put("Wheels", "comfort");
        orderA = new CarOrder("A", new CarModel("A", chosenOptionsA));

        TreeMap<String, String> chosenOptionsB = new TreeMap<>();
        chosenOptionsB.put("Body", "Sport");
        chosenOptionsB.put("Color", "yellow");
        chosenOptionsB.put("Engine", "V8");
        chosenOptionsB.put("Gearbox", "6 manual");
        chosenOptionsB.put("Seats", "vinyl grey");
        chosenOptionsB.put("Airco", "Manual");
        chosenOptionsB.put("Wheels", "sports");
        chosenOptionsB.put("spoiler", "low");
        orderB = new CarOrder("B", new CarModel("B", chosenOptionsB));

        TreeMap<String, String> chosenOptionsC = new TreeMap<>();
        chosenOptionsC.put("Body", "Sport");
        chosenOptionsC.put("Color", "black");
        chosenOptionsC.put("Engine", "V6");
        chosenOptionsC.put("Gearbox", "6 manual");
        chosenOptionsC.put("Seats", "leather black");
        chosenOptionsC.put("Airco", "Manual");
        chosenOptionsC.put("Wheels", "sports");
        chosenOptionsC.put("spoiler", "high");
        orderB = new CarOrder("C", new CarModel("C", chosenOptionsC));

        carBodyPost.setCurrentOrder(orderA);
        drivetrainPost.setCurrentOrder(orderB);
        accessoriesPost.setCurrentOrder(orderC);
    }

    @Test
    void getAndSetCurrentOrderTest() {
        carBodyPost.setCurrentOrder(orderA);
        assertEquals(orderA, carBodyPost.getCurrentOrder());
    }

    @Test
    void getNameTest() {
        assertEquals("Car Body Post", carBodyPost.getName());
        assertEquals("Drivetrain Post", drivetrainPost.getName());
        assertEquals("Accessories Post", accessoriesPost.getName());
    }

    @Test
    void getTasksTest() {
        for (AssemblyTask task : carBodyPost.getTasks() ) {
            assert(task.getName().equals("body")
                    || task.getName().equals("paint"));
        }
        for (AssemblyTask task : drivetrainPost.getTasks() ) {
            assert(task.getName().equals("engine")
                    || task.getName().equals("gearbox"));
        }
        for (AssemblyTask task : accessoriesPost.getTasks() ) {
            assert(task.getName().equals("seats")
                    || task.getName().equals("airco")
                    || task.getName().equals("wheels") );
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
    void addAndRemoveMechanicTest() {
        carBodyPost.addMechanic("Bart");
        drivetrainPost.addMechanic("Jos");
        drivetrainPost.addMechanic("Wim");

        List<String> mechanicsCBP = List.of("Bart");
        assertEquals(carBodyPost.getMechanics(),mechanicsCBP);
        List<String> mechanicsDP = List.of("Jos","Wim");
        assertEquals(drivetrainPost.getMechanics(),mechanicsDP);
        List<String> mechanicsAP = List.of();
        assertEquals(accessoriesPost.getMechanics(),mechanicsAP);

        carBodyPost.removeMechanic("Bart");
        List<String> mechanicsCBPAfterRemove = List.of();
        assertEquals(mechanicsCBPAfterRemove, carBodyPost.getMechanics());
        drivetrainPost.removeMechanic("Wim");
        List<String> mechanicsDPAfterRemove = List.of("Jos");
        assertEquals(mechanicsDPAfterRemove,drivetrainPost.getMechanics());

        assertThrows(IllegalArgumentException.class, () -> drivetrainPost.removeMechanic(null));
        assertThrows(IllegalArgumentException.class, () -> carBodyPost.removeMechanic(""));
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