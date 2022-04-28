package useCases;

import assemAssist.AssemblyLine;
import assemAssist.Company;
import assemAssist.ProductionScheduler;
import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.exceptions.IllegalModelException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class PerformAssemblyTasksTest {

    @BeforeAll
    static void init() {
        ByteArrayInputStream in = new ByteArrayInputStream("3\ns".getBytes());
        System.setIn(in);
    }

    @Test
    public void checkProductionStatistics() throws IllegalCompletionDateException, IllegalModelException {
        ProductionScheduler productionScheduler = new ProductionScheduler();
        Company company = new Company();
        //CarModel model = new CarModel();
        //CarOrder orderA = new CarOrder();
        //company.setCompletedCarOrders();
        AssemblyLine assemblyLine = new AssemblyLine();
/*        new UI( new GarageHolderUI( new GarageHolderController(company)),
                new ManagerUI(      new ManagerController(company)),
                new MechanicUI(     new MechanicController( new Mechanic(productionScheduler,assemblyLine) )));
*/
    }

}
