package useCases;

import assemAssist.CarOrder;
import assemAssist.Company;
import assemAssist.Mechanic;
import assemAssist.exceptions.*;
import assemAssist.workStation.WorkStation;
import controller.GarageHolderController;
import controller.ManagerController;
import controller.MechanicController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
public class OrderNewCar2 {


    private Company NicksCompany;


    @BeforeEach
    void init() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException, IllegalCompletionDateException {
        NicksCompany = new Company();
        assertEquals(NicksCompany.getProductionScheduler().getPendingOrders().size(), 0);
        assertEquals(NicksCompany.getCompletedCarOrders().size(), 0);
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
        NicksCompany.completeOrderingForm(legal_AOptions,"Timo Smeets","A");
        NicksCompany.completeOrderingForm(legal_BOptions,"Timo Smeets","B");
        NicksCompany.completeOrderingForm(legal_AOptions,"Timo Smeets","A");
        List <CarOrder> orders = NicksCompany.getOrdersFromGarageHolder("Timo Smeets")[0];
        orders.get(0).setEstCompletionTime(LocalDateTime.now().minusDays(1));
        orders.get(1).setEstCompletionTime(LocalDateTime.now().minusDays(2));
        orders.get(2).setEstCompletionTime(LocalDateTime.now().minusDays(3));
        orders.get(0).setEstCompletionTime(LocalDateTime.now().minusDays(1));
        orders.get(1).setEstCompletionTime(LocalDateTime.now().minusDays(2));
        NicksCompany.completeOrderingForm(legal_AOptions,"Filip Smeets","B");
        NicksCompany.completeOrderingForm(legal_AOptions,"Filip Smeets","A");
    }

    @Test
    public void orderLegalA() throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException, CloneNotSupportedException {
        assertEquals(NicksCompany.getProductionScheduler().getPendingOrders().size(), 5);
        assertEquals(NicksCompany.getOrdersFromGarageHolder("Timo Smeets")[0].size(), 3);
        assertEquals(NicksCompany.getOrdersFromGarageHolder("Filip Smeets")[0].size(), 2);
        ByteArrayInputStream in = new ByteArrayInputStream("2\nTimo Smeets\nn\nA\ns\n0\ns\n0\ns\n0\ns\n0\ns\n0\ns\n0\ns\n0\nq\n4".getBytes());
        System.setIn(in);
        new UI(new GarageHolderUI(new GarageHolderController(NicksCompany)),new ManagerUI(new ManagerController(NicksCompany)),
                new MechanicUI(new MechanicController(new Mechanic(NicksCompany.getProductionScheduler().getAssemblyLine()))));
        assertEquals(NicksCompany.getOrdersFromGarageHolder("Timo Smeets")[0].size(), 4);
        assertEquals(NicksCompany.getOrdersFromGarageHolder("Filip Smeets")[0].size(), 2);
        int nmbrOrders = NicksCompany.getCompletedCarOrders().size() + NicksCompany.getProductionScheduler().getPendingOrders().size();
        assertEquals(6, nmbrOrders);
        completeAllOrders(nmbrOrders + 1);
        assertEquals(NicksCompany.getOrdersFromGarageHolder("Timo Smeets")[0].size(), 0);
        assertEquals(NicksCompany.getOrdersFromGarageHolder("Timo Smeets")[1].size(), 4);
    }


    private void completeAllOrders(int n) throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException, CloneNotSupportedException {
        for (int i = 0; i < n; i++) {
            completeAssemblyTask();
        }
    }
    private void completeAssemblyTask(){
        for(WorkStation workStation: NicksCompany.getProductionScheduler().getAssemblyLine().getWorkStations()){
            List<String> tasks = workStation.getPendingTasks();
            for(String task : tasks){
                workStation.performAssemblyTask(task, 60);
            }
        }
        System.out.println("------------------------------------------------------");
        // print each workstation and it's current order and its status
        for(WorkStation workStation: NicksCompany.getProductionScheduler().getAssemblyLine().getWorkStations()){
            if(workStation.getCurrentOrder() != null){
                System.out.println(workStation.getName() + ": " + "\n\t" + workStation.getCurrentOrder().getOrderID() + " " + workStation.isFinished());
            }
            else{
                System.out.println(workStation.getName() + ": " + "\n\t" + "No order");
            }
        }
        //Print the number of orders in completed orders in Nick's company
        System.out.println("------------------------------------------------------");
        System.out.println("Completed orders: " + NicksCompany.getCompletedCarOrders().size());

    }


}
