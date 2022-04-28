package useCases;

import assemAssist.AssemblyLine;
import assemAssist.Company;
import assemAssist.Mechanic;
import assemAssist.ProductionScheduler;
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

public class CheckProductionStatisticsTest {

    @BeforeAll
    static void init() {
        ByteArrayInputStream in = new ByteArrayInputStream("3\ns".getBytes());
        System.setIn(in);
    }

    @Test
    public void checkProductionStatistics() throws IllegalCompletionDateException, IllegalModelException{
        ProductionScheduler productionScheduler = new ProductionScheduler();
        Company company = new Company();
        AssemblyLine assemblyLine = new AssemblyLine();
/*        new UI( new GarageHolderUI( new GarageHolderController(company)),
                new ManagerUI(      new ManagerController(company)),
                new MechanicUI(     new MechanicController( new Mechanic(productionScheduler,assemblyLine) )));
*/
    }
}
