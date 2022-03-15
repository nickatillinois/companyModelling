import assemAssist.AssemblyLine;
import assemAssist.ProductionScheduler;
import assemAssist.carOrder.CarOrder;
import assemAssist.workStation.AccessoriesPost;
import assemAssist.workStation.CarBodyPost;
import assemAssist.workStation.DrivetrainPost;
import assemAssist.workStation.WorkStation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.testng.AssertJUnit.assertEquals;
public class productSchedulerTest {
    private static ProductionScheduler productionScheduler;
    private static AssemblyLine assemblyLine;
    private static AccessoriesPost accessoriesPost;
    private static CarBodyPost carBodyPost;
    private static DrivetrainPost drivetrainPost;
    @BeforeAll
    static void init(){
        accessoriesPost = new AccessoriesPost(null);
        carBodyPost = new CarBodyPost(null);
        drivetrainPost = new DrivetrainPost(null);
        ArrayList<WorkStation> workStations = new ArrayList<>();
        workStations.add(accessoriesPost);
        workStations.add(carBodyPost);
        workStations.add(drivetrainPost);
        assemblyLine = new AssemblyLine(workStations);
        productionScheduler = new ProductionScheduler("Nick",assemblyLine);
    }

    @Test
    public void getManagerTest(){
        assertEquals(productionScheduler.getManager(),"Nick");
    }

    @Test
    public void setManagerTest(){
        assertEquals(productionScheduler.getManager(),"Nick");
        productionScheduler.setManager("Raf");
        assertEquals(productionScheduler.getManager(),"Raf");
    }

    @Test
    public void getCurrentStateNoOrdersTest(){
        ArrayList<CarOrder> nullist = new ArrayList<>(3);
        nullist.add(null);
        nullist.add(null);
        nullist.add(null);
        assertEquals(productionScheduler.getCurrentState(),nullist);
    }
}
