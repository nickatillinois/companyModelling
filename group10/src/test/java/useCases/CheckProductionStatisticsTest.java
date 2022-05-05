package useCases;

import assemAssist.*;
import assemAssist.exceptions.*;
import controller.GarageHolderController;
import controller.ManagerController;
import controller.MechanicController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ui.GarageHolderUI;
import ui.ManagerUI;
import ui.MechanicUI;
import ui.UI;
import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.TreeMap;

public class CheckProductionStatisticsTest {

    @BeforeAll
    static void init() {
        ByteArrayInputStream in = new ByteArrayInputStream("3\ns".getBytes());
        System.setIn(in);
    }

    @Test
    public void checkProductionStatistics() throws IllegalCompletionDateException, IllegalModelException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {

        Company company = new Company();
        ProductionScheduler productionScheduler = company.getProductionScheduler();
        AssemblyLine assemblyLine = productionScheduler.getAssemblyLine();
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red"); legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4"); legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual"); legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        company.completeOrderingForm(legalAOptions,"Luna Van den Bergh","A");
        company.completeOrderingForm(legalAOptions,"Luna Van den Bergh","A");
        company.completeOrderingForm(legalAOptions,"Luna Van den Bergh","A");
        company.completeOrderingForm(legalAOptions,"Luna Van den Bergh","A");

        // TODO complete alle tasks telkens


        productionScheduler.advanceOrders(60);
        productionScheduler.advanceOrders(60);
        productionScheduler.advanceOrders(60); // first one done: delay = 0
        productionScheduler.advanceOrders(90); // second one done: delay = 30
        productionScheduler.advanceOrders(70); // third one done: delay = 40
        new UI( new GarageHolderUI( new GarageHolderController(company)),
                new ManagerUI(      new ManagerController(company)),
                new MechanicUI(     new MechanicController( new Mechanic(assemblyLine) )));

    }
}
