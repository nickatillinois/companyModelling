package useCases2;

import assemAssist.CarOrder;
import assemAssist.Company;
import assemAssist.Mechanic;
import assemAssist.exceptions.*;
import assemAssist.workStation.WorkStation;
import controller.GarageHolderController;
import controller.ManagerController;
import controller.MechanicController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ui.GarageHolderUI;
import ui.ManagerUI;
import ui.MechanicUI;
import ui.UI;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderNewCar {


    private Company angeloMotors2;


    @BeforeAll
    public void init() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException, IllegalCompletionDateException {
        angeloMotors2 = new Company();
        angeloMotors2.getOrdersFromGarageHolder("Timo Smeets")[0].size();
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
        angeloMotors2.completeOrderingForm(legal_AOptions,"Timo Smeets","A");
        angeloMotors2.completeOrderingForm(legal_BOptions,"Timo Smeets","B");
        angeloMotors2.completeOrderingForm(legal_AOptions,"Timo Smeets","A");
        List <CarOrder> orders = angeloMotors2.getOrdersFromGarageHolder("Timo Smeets")[0];
        orders.get(0).setEstCompletionTime(LocalDateTime.now().minusDays(1));
        orders.get(1).setEstCompletionTime(LocalDateTime.now().minusDays(2));
        orders.get(2).setEstCompletionTime(LocalDateTime.now().minusDays(3));
        orders.get(0).setEstCompletionTime(LocalDateTime.now().minusDays(1));
        orders.get(1).setEstCompletionTime(LocalDateTime.now().minusDays(2));
        angeloMotors2.completeOrderingForm(legal_AOptions,"Filip Smeets","B");
        angeloMotors2.completeOrderingForm(legal_AOptions,"Filip Smeets","A");
    }

    @Test
    public void orderLegalA() throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException, CloneNotSupportedException {
        assertEquals(angeloMotors2.getOrdersFromGarageHolder("Timo Smeets")[0].size(), 3);
        ByteArrayInputStream in = new ByteArrayInputStream("2\nTimo Smeets\nn\nA\ns\n0\ns\n0\ns\n0\ns\n0\ns\n0\ns\n0\ns\n0\nq\n4".getBytes());
        System.setIn(in);
        GarageHolderUI gui = new GarageHolderUI(new GarageHolderController(angeloMotors2));
        ManagerUI mui = new ManagerUI(new ManagerController(angeloMotors2));
        MechanicUI meui = new MechanicUI(new MechanicController(new Mechanic(angeloMotors2.getProductionScheduler().getAssemblyLine())));
        new UI(gui,mui,meui);
        assertEquals(angeloMotors2.getOrdersFromGarageHolder("Timo Smeets")[0].size(), 4);
        int nmbrOrders = angeloMotors2.getCompletedCarOrders().size() + angeloMotors2.getProductionScheduler().getPendingOrders().size();
        completeAllOrders(nmbrOrders + 500);
        completeAssemblyTask();
        completeAssemblyTask();
        completeAssemblyTask();
        completeAssemblyTask();
        completeAssemblyTask();
        completeAssemblyTask();
        completeAssemblyTask();
        completeAssemblyTask();
        completeAssemblyTask();
        completeAssemblyTask();
        assertEquals(angeloMotors2.getOrdersFromGarageHolder("Timo Smeets")[0].size(), 0);
        assertEquals(angeloMotors2.getOrdersFromGarageHolder("Timo Smeets")[1].size(), 4);
    }


    private void completeAllOrders(int n) throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException, CloneNotSupportedException {
        for (int i = 0; i < n; i++) {
            completeAssemblyTask();
        }
    }
    private void completeAssemblyTask(){
        for(WorkStation workStation: angeloMotors2.getProductionScheduler().getAssemblyLine().getWorkStations()){
            List<String> tasks = workStation.getPendingTasks();
            for(String task : tasks){
                workStation.performAssemblyTask(task, 60);
            }
        }
    }

}
