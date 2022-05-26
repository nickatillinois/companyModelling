package main.assemAssist.workStation;

import assemAssist.AssemblyTask;
import assemAssist.CarModel;
import assemAssist.CarOrder;
import assemAssist.Company;
import assemAssist.exceptions.*;
import assemAssist.workStation.CarBodyPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.*;

class CarBodyPostTest {

    static Company company;
    static CarBodyPost carBodyPost;
    static CarOrder orderA;

    @BeforeEach
    void init() throws IllegalModelException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        company = new Company();
        carBodyPost = new CarBodyPost(company);

        TreeMap<String, String> chosenOptionsA = new TreeMap<>();
        chosenOptionsA.put("Body", "Sedan");
        chosenOptionsA.put("Color", "blue");
        chosenOptionsA.put("Engine", "V4");
        chosenOptionsA.put("Gearbox", "5 manual");
        chosenOptionsA.put("Seats", "leather white");
        chosenOptionsA.put("Airco", "Manual");
        chosenOptionsA.put("Wheels", "comfort");
        orderA = new CarOrder("A", new CarModel("A", chosenOptionsA,company.getWorkingTimeWorkingStation("A")));

        carBodyPost.setCurrentOrder(orderA);
    }

    @Test
    void constructorTest() {
        carBodyPost = new CarBodyPost(company);
        assertEquals(carBodyPost.getName(),"Car Body Post");
        assertTrue(carBodyPost.getTasks().isEmpty());
        assertNull(carBodyPost.getCurrentOrder());
    }

    @Test
    void constructorNullTest() {
        assertThrows(IllegalArgumentException.class, () -> carBodyPost = new CarBodyPost(null));
    }

    @Test
    void getCurrentOrderTest() {
        carBodyPost.setCurrentOrder(orderA);
        assertEquals(orderA, carBodyPost.getCurrentOrder());
        assertFalse(carBodyPost.getTasks().isEmpty());
    }

    @Test
    void setCurrentOrderNullTest() {
        carBodyPost.setCurrentOrder(null);
        assertNull(carBodyPost.getCurrentOrder());
        assertTrue(carBodyPost.getTasks().isEmpty());
    }

    @Test
    void getNameTest() {
        assertEquals("Car Body Post", carBodyPost.getName());
    }

    @Test
    void getTasksTest() {
        List<AssemblyTask> carBodyTasks = List.of(new AssemblyTask("body", "assemble sedan body"), new AssemblyTask("paint", "paint the car blue"));
        for (int i = 0; i < carBodyTasks.size(); i++) {
            assertEquals(carBodyTasks.get(i).getName(), carBodyPost.getTasks().get(i).getName());
            assertEquals(carBodyTasks.get(i).getTaskDefinition(), carBodyPost.getTasks().get(i).getTaskDefinition());
        }
    }

    @Test
    void getTasksTestNoOrder() {
        carBodyPost.setCurrentOrder(null);
        assertTrue(carBodyPost.getTasks().isEmpty());
    }

    @Test
    void getIsFinished() {
        assertFalse(carBodyPost.isFinished());
    }

    @Test
    void getIsFinishedNoOrder() {
        carBodyPost.setCurrentOrder(null);
        assertTrue(carBodyPost.isFinished());
    }

    @Test
    void getPendingTasksTest() {
        List<String> pendingCBP = List.of("body", "paint");
        assertEquals(carBodyPost.getPendingTasks(), pendingCBP);
    }

    @Test
    void getPendingTasksNoOrderTest() {
        carBodyPost.setCurrentOrder(null);
        List<String> pendingCBP = List.of();
        assertEquals(carBodyPost.getPendingTasks(), pendingCBP);
    }

    @Test
    void getFinishedTasks() {
        List<String> finishedCBP = List.of();
        assertEquals(finishedCBP, carBodyPost.getFinishedTasks());
    }

    @Test
    void getInformationFromTaskTest() {
        assertEquals("assemble sedan body", carBodyPost.getInformationFromTask("body"));
        assertEquals("paint the car blue", carBodyPost.getInformationFromTask("paint"));

    }

    @Test
    void getInformationFromNull() {
        assertThrows(IllegalArgumentException.class, () -> carBodyPost.getInformationFromTask(null));
    }

    @Test
    void getInformationFromEmpty() {
        assertThrows(IllegalArgumentException.class, () -> carBodyPost.getInformationFromTask(""));
    }

    @Test
    void getInformationFromWrong() {
        assertThrows(IllegalArgumentException.class, () -> carBodyPost.getInformationFromTask("spoiler"));
    }

    @Test
    void performTaskTest() {
        carBodyPost.setCurrentOrder(orderA);
        assertEquals(carBodyPost.getFinishedTasks(),List.of());
        carBodyPost.performAssemblyTask("body",60);
        assertEquals(carBodyPost.getFinishedTasks(),List.of("body"));
    }

    @Test
    void performTaskTestNull() {
        assertThrows(IllegalArgumentException.class, () -> carBodyPost.performAssemblyTask(null,60));
    }

    @Test
    void performTaskTestEmpty() {
        assertThrows(IllegalArgumentException.class, () -> carBodyPost.performAssemblyTask("",60));
    }

    @Test
    void performTaskTestNeg() {
        assertThrows(IllegalArgumentException.class, () -> carBodyPost.performAssemblyTask("body",0));
    }

    @Test
    void performTaskTestWrongTask() {
        assertThrows(IllegalArgumentException.class, () -> carBodyPost.performAssemblyTask("airco",60));
    }

    @Test
    void getTasksAndStatusTest() {
        String statusCBP = carBodyPost.getTasksAndStatus();
        assertEquals(statusCBP, "body: pending, paint: pending");
    }

    @Test
    void equalsTest() {
        CarBodyPost cbp1 = new CarBodyPost(company);
        CarBodyPost cbp2 = new CarBodyPost(company);
        assertEquals(cbp1,cbp2);
    }

}