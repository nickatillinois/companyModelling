package useCases;

import assemAssist.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UC3_PerformAssemblyTasksTest {

    private Company company;
    private AssemblyLine assemblyLine;
    private ProductionScheduler productionScheduler;
    private Mechanic mechanic;

    @BeforeAll
    void init() throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        ByteArrayInputStream in = new ByteArrayInputStream("1\nw\n0\n0\nd\n60\n0\nd\n60\ns\n4".getBytes());
        System.setIn(in);
        company = new Company();
        productionScheduler = company.getProductionScheduler();
        assemblyLine = productionScheduler.getAssemblyLine();
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red"); legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4"); legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual"); legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        company.completeOrderingForm(legalAOptions,"Luna Van den Bergh","A");
        company.completeOrderingForm(legalAOptions,"Luna Van den Bergh","A");
        company.completeOrderingForm(legalAOptions,"Luna Van den Bergh","A");
        company.completeOrderingForm(legalAOptions,"Luna Van den Bergh","A");
        mechanic = new Mechanic(assemblyLine);
    }

    @Test
    public void performAssemblyTasks() throws IllegalCompletionDateException, IllegalModelException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {

        List<CarOrder> orderListPre = new ArrayList<>();
        for (WorkStation ws : assemblyLine.getWorkStations()) {
            orderListPre.add(ws.getCurrentOrder());
        }

        new UI( new GarageHolderUI( new GarageHolderController(company)),
                new ManagerUI(      new ManagerController(company)),
                new MechanicUI(     new MechanicController( mechanic )));

        List<CarOrder> orderListPost = new ArrayList<>();
        for (WorkStation ws : assemblyLine.getWorkStations()) {
            orderListPost.add(ws.getCurrentOrder());
        }
        assertNotEquals(orderListPost,orderListPre);
    }

}