package useCases;

import assemAssist.AssemblyLine;
import assemAssist.Company;
import assemAssist.Mechanic;
import assemAssist.ProductionScheduler;
import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.exceptions.IllegalModelException;
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
import java.io.InputStream;

public class CheckProductionStatisticsTest {

    @BeforeAll
    static void init() {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("3".getBytes());
        System.setIn(in);
    }

    @Test
    public void checkProductionStatistics() throws IllegalCompletionDateException, IllegalModelException, IllegalChoiceException {
        ProductionScheduler productionScheduler = new ProductionScheduler();
        Company company = new Company();
        AssemblyLine assemblyLine = new AssemblyLine();
        new UI( new GarageHolderUI( new GarageHolderController(productionScheduler)),
                new ManagerUI(      new ManagerController(company)),
                new MechanicUI(     new MechanicController( new Mechanic(productionScheduler,assemblyLine) )));
    }

}