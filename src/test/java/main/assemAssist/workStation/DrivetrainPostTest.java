package main.assemAssist.workStation;

import assemAssist.AssemblyTask;
import assemAssist.CarModel;
import assemAssist.CarOrder;
import assemAssist.Company;
import assemAssist.exceptions.*;
import assemAssist.workStation.DrivetrainPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.*;

class DrivetrainPostTest {

    static Company company;
    static DrivetrainPost drivetrainPost;
    static CarOrder orderA;
    static CarOrder orderB;

    @BeforeEach
    void init() throws IllegalModelException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        company = new Company();
        drivetrainPost = new DrivetrainPost(company);

        TreeMap<String, String> chosenOptionsA = new TreeMap<>();
        chosenOptionsA.put("Body", "Sedan");
        chosenOptionsA.put("Color", "blue");
        chosenOptionsA.put("Engine", "V4");
        chosenOptionsA.put("Gearbox", "5 manual");
        chosenOptionsA.put("Seats", "leather white");
        chosenOptionsA.put("Airco", "Manual");
        chosenOptionsA.put("Wheels", "comfort");
        orderA = new CarOrder("A", new CarModel("A", chosenOptionsA,company.getWorkingTimeWorkingStation("A")));

        TreeMap<String, String> chosenOptionsB = new TreeMap<>();
        chosenOptionsB.put("Body", "Sport");
        chosenOptionsB.put("Color", "yellow");
        chosenOptionsB.put("Engine", "V8");
        chosenOptionsB.put("Gearbox", "6 manual");
        chosenOptionsB.put("Seats", "vinyl grey");
        chosenOptionsB.put("Airco", "Manual");
        chosenOptionsB.put("Wheels", "sports");
        chosenOptionsB.put("spoiler", "low");
        orderB = new CarOrder("B", new CarModel("B", chosenOptionsB,company.getWorkingTimeWorkingStation("B")));

        drivetrainPost.setCurrentOrder(orderB);
    }

    @Test
    void constructorTest() {
        drivetrainPost = new DrivetrainPost(company);
        assertEquals(drivetrainPost.getName(),"Drivetrain Post");
        assertTrue(drivetrainPost.getTasks().isEmpty());
        assertNull(drivetrainPost.getCurrentOrder());
    }

    @Test
    void constructorNullTest() {
        assertThrows(IllegalArgumentException.class, () -> drivetrainPost = new DrivetrainPost(null));
    }

    @Test
    void getCurrentOrderTest() {
        drivetrainPost.setCurrentOrder(orderA);
        assertEquals(orderA, drivetrainPost.getCurrentOrder());
        assertFalse(drivetrainPost.getTasks().isEmpty());
    }

    @Test
    void setCurrentOrderNullTest() {
        drivetrainPost.setCurrentOrder(null);
        assertNull(drivetrainPost.getCurrentOrder());
        assertTrue(drivetrainPost.getTasks().isEmpty());
    }

    @Test
    void getNameTest() {
        assertEquals("Drivetrain Post", drivetrainPost.getName());
    }

    @Test
    void getTasksTest() {
        List<AssemblyTask> dTTasks = List.of(new AssemblyTask("engine", "insert V8"), new AssemblyTask("gearbox", "install 6 manual"));
        for (int i = 0; i < dTTasks.size(); i++) {
            assertEquals(dTTasks.get(i).getName(), drivetrainPost.getTasks().get(i).getName());
            assertEquals(dTTasks.get(i).getTaskDefinition(), drivetrainPost.getTasks().get(i).getTaskDefinition());
        }
    }

    @Test
    void getTasksTestNoOrder() {
        drivetrainPost.setCurrentOrder(null);
        assertTrue(drivetrainPost.getTasks().isEmpty());
    }

    @Test
    void getIsFinished() {
        assertFalse(drivetrainPost.isFinished());
    }

    @Test
    void getIsFinishedNoOrder() {
        drivetrainPost.setCurrentOrder(null);
        assertTrue(drivetrainPost.isFinished());
    }

    @Test
    void getPendingTasksTest() {
        List<String> pendingCBP = List.of("engine", "gearbox");
        assertEquals(drivetrainPost.getPendingTasks(), pendingCBP);
    }

    @Test
    void getPendingTasksNoOrderTest() {
        drivetrainPost.setCurrentOrder(null);
        List<String> pendingCBP = List.of();
        assertEquals(drivetrainPost.getPendingTasks(), pendingCBP);
    }

    @Test
    void getFinishedTasks() {
        List<String> finishedCBP = List.of();
        assertEquals(finishedCBP, drivetrainPost.getFinishedTasks());
    }

    @Test
    void getInformationFromTaskTest() {
        assertEquals("insert V8", drivetrainPost.getInformationFromTask("engine"));
        assertEquals("install 6 manual", drivetrainPost.getInformationFromTask("gearbox"));

    }

    @Test
    void getInformationFromNull() {
        assertThrows(IllegalArgumentException.class, () -> drivetrainPost.getInformationFromTask(null));
    }

    @Test
    void getInformationFromEmpty() {
        assertThrows(IllegalArgumentException.class, () -> drivetrainPost.getInformationFromTask(""));
    }

    @Test
    void getInformationFromWrong() {
        assertThrows(IllegalArgumentException.class, () -> drivetrainPost.getInformationFromTask("spoiler"));
    }

    @Test
    void performTaskTest() {
        drivetrainPost.setCurrentOrder(orderA);
        assertEquals(drivetrainPost.getFinishedTasks(),List.of());
        drivetrainPost.performAssemblyTask("engine",60);
        assertEquals(drivetrainPost.getFinishedTasks(),List.of("engine"));
    }

    @Test
    void performTaskTestNull() {
        assertThrows(IllegalArgumentException.class, () -> drivetrainPost.performAssemblyTask(null,60));
    }

    @Test
    void performTaskTestEmpty() {
        assertThrows(IllegalArgumentException.class, () -> drivetrainPost.performAssemblyTask("",60));
    }

    @Test
    void performTaskTestNeg() {
        assertThrows(IllegalArgumentException.class, () -> drivetrainPost.performAssemblyTask("engine",0));
    }

    @Test
    void performTaskTestWrongTask() {
        assertThrows(IllegalArgumentException.class, () -> drivetrainPost.performAssemblyTask("airco",60));
    }

    @Test
    void equalsTest() {
        DrivetrainPost dtp1 = new DrivetrainPost(company);
        DrivetrainPost dtp2 = new DrivetrainPost(company);
        assertEquals(dtp1,dtp2);
    }

}