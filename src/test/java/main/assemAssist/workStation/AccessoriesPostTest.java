package main.assemAssist.workStation;

import assemAssist.AssemblyTask;
import assemAssist.CarModel;
import assemAssist.CarOrder;
import assemAssist.Company;
import assemAssist.exceptions.*;
import assemAssist.workStation.AccessoriesPost;
import assemAssist.workStation.CarBodyPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.*;

class AccessoriesPostTest {

    static Company company;
    static AccessoriesPost accessoriesPost;
    static CarOrder orderA;
    static CarOrder orderC;

    @BeforeEach
    void init() throws IllegalModelException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        company = new Company();
        accessoriesPost = new AccessoriesPost(company);

        TreeMap<String, String> chosenOptionsA = new TreeMap<>();
        chosenOptionsA.put("Body", "Sedan");
        chosenOptionsA.put("Color", "blue");
        chosenOptionsA.put("Engine", "V4");
        chosenOptionsA.put("Gearbox", "5 manual");
        chosenOptionsA.put("Seats", "leather white");
        chosenOptionsA.put("Airco", "Manual");
        chosenOptionsA.put("Wheels", "comfort");
        orderA = new CarOrder("A", new CarModel("A", chosenOptionsA,company.getWorkingTimeWorkingStation("A")));

        TreeMap<String, String> chosenOptionsC = new TreeMap<>();
        chosenOptionsC.put("Body", "Sport");
        chosenOptionsC.put("Color", "black");
        chosenOptionsC.put("Engine", "V6");
        chosenOptionsC.put("Gearbox", "6 manual");
        chosenOptionsC.put("Seats", "leather black");
        chosenOptionsC.put("Airco", "Manual");
        chosenOptionsC.put("Wheels", "sports");
        chosenOptionsC.put("spoiler", "high");
        orderC = new CarOrder("C", new CarModel("C", chosenOptionsC,company.getWorkingTimeWorkingStation("C")));

        accessoriesPost.setCurrentOrder(orderC);
    }

    @Test
    void constructorTest() {
        accessoriesPost = new AccessoriesPost(company);
        assertEquals(accessoriesPost.getName(),"Accessories Post");
        assertTrue(accessoriesPost.getTasks().isEmpty());
        assertNull(accessoriesPost.getCurrentOrder());
    }

    @Test
    void constructorNullTest() {
        assertThrows(IllegalArgumentException.class, () -> accessoriesPost = new AccessoriesPost(null));
    }

    @Test
    void getCurrentOrderTest() {
        accessoriesPost.setCurrentOrder(orderA);
        assertEquals(orderA, accessoriesPost.getCurrentOrder());
        assertFalse(accessoriesPost.getTasks().isEmpty());
    }

    @Test
    void setCurrentOrderNullTest() {
        accessoriesPost.setCurrentOrder(null);
        assertNull(accessoriesPost.getCurrentOrder());
        assertTrue(accessoriesPost.getTasks().isEmpty());
    }

    @Test
    void getNameTest() {
        assertEquals("Accessories Post", accessoriesPost.getName());
    }

    @Test
    void getTasksTest() {
        List<AssemblyTask> accTasks = List.of(new AssemblyTask("seats", "install leather black seats"),
                                                  new AssemblyTask("airco", "install manual airco"),
                                                  new AssemblyTask("wheels", "mount sports wheels"),
                                                  new AssemblyTask("spoiler", "install high spoiler"));
        for (int i = 0; i < accTasks.size(); i++) {
            assertEquals(accTasks.get(i).getName(), accessoriesPost.getTasks().get(i).getName());
            assertEquals(accTasks.get(i).getTaskDefinition(), accessoriesPost.getTasks().get(i).getTaskDefinition());
        }
    }

    @Test
    void getTasksTestNoOrder() {
        accessoriesPost.setCurrentOrder(null);
        assertTrue(accessoriesPost.getTasks().isEmpty());
    }

    @Test
    void getIsFinished() {
        assertFalse(accessoriesPost.isFinished());
    }

    @Test
    void getIsFinishedNoOrder() {
        accessoriesPost.setCurrentOrder(null);
        assertTrue(accessoriesPost.isFinished());
    }

    @Test
    void getPendingTasksTest() {
        List<String> pendingCBP = List.of("seats", "airco", "wheels", "spoiler");
        assertEquals(accessoriesPost.getPendingTasks(), pendingCBP);
    }

    @Test
    void getPendingTasksNoOrderTest() {
        accessoriesPost.setCurrentOrder(null);
        List<String> pendingCBP = List.of();
        assertEquals(accessoriesPost.getPendingTasks(), pendingCBP);
    }

    @Test
    void getFinishedTasks() {
        List<String> finishedCBP = List.of();
        assertEquals(finishedCBP, accessoriesPost.getFinishedTasks());
    }

    @Test
    void getInformationFromTaskTest() {
        assertEquals("install leather black seats", accessoriesPost.getInformationFromTask("seats"));
        assertEquals("install manual airco", accessoriesPost.getInformationFromTask("airco"));

    }

    @Test
    void getInformationFromNull() {
        assertThrows(IllegalArgumentException.class, () -> accessoriesPost.getInformationFromTask(null));
    }

    @Test
    void getInformationFromEmpty() {
        assertThrows(IllegalArgumentException.class, () -> accessoriesPost.getInformationFromTask(""));
    }

    @Test
    void getInformationFromWrong() {
        assertThrows(IllegalArgumentException.class, () -> accessoriesPost.getInformationFromTask("body"));
    }

    @Test
    void performTaskTest() {
        accessoriesPost.setCurrentOrder(orderC);
        assertEquals(accessoriesPost.getFinishedTasks(),List.of());
        accessoriesPost.performAssemblyTask("wheels",60);
        assertEquals(accessoriesPost.getFinishedTasks(),List.of("wheels"));
    }

    @Test
    void performTaskTestNull() {
        assertThrows(IllegalArgumentException.class, () -> accessoriesPost.performAssemblyTask(null,60));
    }

    @Test
    void performTaskTestEmpty() {
        assertThrows(IllegalArgumentException.class, () -> accessoriesPost.performAssemblyTask("",60));
    }

    @Test
    void performTaskTestNeg() {
        assertThrows(IllegalArgumentException.class, () -> accessoriesPost.performAssemblyTask("seats",0));
    }

    @Test
    void performTaskTestWrongTask() {
        assertThrows(IllegalArgumentException.class, () -> accessoriesPost.performAssemblyTask("body",60));
    }

    @Test
    void getTasksAndStatusTest() {
        String statusCBP = accessoriesPost.getTasksAndStatus();
        assertEquals(statusCBP, "seats: pending, airco: pending, wheels: pending, spoiler: pending");
    }

    @Test
    void equalsTest() {
        CarBodyPost cbp1 = new CarBodyPost(company);
        CarBodyPost cbp2 = new CarBodyPost(company);
        assertEquals(cbp1,cbp2);
    }

}