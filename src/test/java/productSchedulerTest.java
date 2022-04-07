import assemAssist.AssemblyLine;
import assemAssist.AssemblyTask;
import assemAssist.ProductionScheduler;
import assemAssist.carOrder.*;
import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.exceptions.IllegalModelException;
import assemAssist.workStation.AccessoriesPost;
import assemAssist.workStation.CarBodyPost;
import assemAssist.workStation.DrivetrainPost;
import assemAssist.workStation.WorkStation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
public class productSchedulerTest {
    private static ProductionScheduler productionScheduler;
    private static AssemblyLine assemblyLine;
    private static AccessoriesPost accessoriesPost;
    private static CarBodyPost carBodyPost;
    private static DrivetrainPost drivetrainPost;

    @BeforeEach
    public void init(){
        accessoriesPost = new AccessoriesPost();
        carBodyPost = new CarBodyPost();
        drivetrainPost = new DrivetrainPost();
        ArrayList<WorkStation> workStations = new ArrayList<>();
        assemblyLine = new AssemblyLine();
        productionScheduler = new ProductionScheduler(assemblyLine);
    }

    @Test
    public void getCurrentStateNoOrdersTest(){
        ArrayList<CarOrder> nullist = new ArrayList<>(3);
        nullist.add(null);
        nullist.add(null);
        nullist.add(null);
        assertEquals(productionScheduler.getCurrentState(),nullist);
    }

    @Test
    public void getCurrentStateOneOrderNoAdvanceTest() throws IllegalChoiceException, IllegalModelException, IllegalCompletionDateException {
        Body body = new Body("sedan");
        CarModelSpecification carModelSpecification = new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        CarModel carModel = new CarModel("Jaguar",carModelSpecification);
        CarOrder carOrder1 = new CarOrder("Luna",carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder1);
        ArrayList<CarOrder> nullist = new ArrayList<>(3);
        nullist.add(null);
        nullist.add(null);
        nullist.add(null);
        assertEquals(productionScheduler.getCurrentState(),nullist);
    }

    @Test
    public void OneOrderAdvanceTest() throws IllegalChoiceException, IllegalModelException, IllegalCompletionDateException {
        Body body = new Body("sedan");
        CarModelSpecification carModelSpecification = new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        CarModel carModel = new CarModel("Jaguar",carModelSpecification);
        CarOrder carOrder1 = new CarOrder("Luna",carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder1);
        productionScheduler.advanceOrders(1);
        ArrayList<CarOrder> nullist = new ArrayList<>(3);
        nullist.add(carOrder1);
        nullist.add(null);
        nullist.add(null);
        assertEquals(productionScheduler.getCurrentState(),nullist);
    }

    @Test
    public void TwoOrderAdvanceTest() throws IllegalChoiceException, IllegalModelException, IllegalCompletionDateException {
        Body body = new Body("sedan");
        CarModelSpecification carModelSpecification = new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        /*ProductionScheduler.addModel("Jaguar");*/
        CarModel carModel = new CarModel("Jaguar",carModelSpecification);
        CarOrder carOrder1 = new CarOrder("Luna",carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder1);
        CarOrder carOrder2 = new CarOrder("Luna",carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder2);
        productionScheduler.advanceOrders(1);
        ArrayList<CarOrder> nullist = new ArrayList<>(3);
        nullist.add(carOrder1);
        nullist.add(null);
        nullist.add(null);
        assertEquals(productionScheduler.getCurrentState(),nullist);
        productionScheduler.advanceOrders(1);
        ArrayList<CarOrder> nullist2 = new ArrayList<>(3);
        nullist2.add(carOrder1);
        nullist2.add(null);
        nullist2.add(null);
        assertEquals(productionScheduler.getCurrentState(),nullist2);
        WorkStation workStation  = productionScheduler.getAssemblyLine().getWorkStations().get(0);
        List<AssemblyTask> assemblyTasks = workStation.getTasks();
        for (AssemblyTask task : assemblyTasks){
            task.setIsCompleted(true);
        }
        assertEquals(productionScheduler.getCurrentState(),nullist2);
        productionScheduler.advanceOrders(1);
        ArrayList<CarOrder> nullist3 = new ArrayList<>(3);
        nullist3.add(carOrder2);
        nullist3.add(carOrder1);
        nullist3.add(null);
        assertEquals(productionScheduler.getCurrentState(),nullist3);


    }

    @Test
    public void FourOrderAdvanceTest() throws IllegalChoiceException, IllegalModelException, IllegalCompletionDateException {
        Body body = new Body("sedan");
        CarModelSpecification carModelSpecification = new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        CarModel carModel = new CarModel("Jaguar",carModelSpecification);
        CarOrder carOrder1 = new CarOrder("Luna",carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder1);
        CarOrder carOrder2 = new CarOrder("Luna",carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder2);
        productionScheduler.advanceOrders(1);
        ArrayList<CarOrder> nullist = new ArrayList<>(3);
        nullist.add(carOrder1);
        nullist.add(null);
        nullist.add(null);
        assertEquals(productionScheduler.getCurrentState(),nullist);
        int hoursWorkedToday1 = productionScheduler.getAssemblyLine().getMinutesWorkedToday();
        productionScheduler.advanceOrders(1);
        int hoursWorkedToday2 = productionScheduler.getAssemblyLine().getMinutesWorkedToday();
        assertEquals(hoursWorkedToday1,hoursWorkedToday2);
        assertEquals(productionScheduler.getCurrentState(),nullist);
        ArrayList<CarOrder> nullist2 = new ArrayList<>(3);
        nullist2.add(carOrder2);
        nullist2.add(carOrder1);
        nullist2.add(null);
        WorkStation workStation  = productionScheduler.getAssemblyLine().getWorkStations().get(0);
        List<AssemblyTask> assemblyTasks = workStation.getTasks();
        for (AssemblyTask task : assemblyTasks){
            task.setIsCompleted(true);
        }
        assertEquals(productionScheduler.getCurrentState(),nullist);
        productionScheduler.advanceOrders(1);
        assertEquals(nullist2,productionScheduler.getCurrentState());
        CarOrder carOrder3 = new CarOrder("Luna",carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder3);
        CarOrder carOrder4 = new CarOrder("Luna",carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder4);
        WorkStation workStation1  = productionScheduler.getAssemblyLine().getWorkStations().get(0);
        List<AssemblyTask> assemblyTasks1 = workStation1.getTasks();
        for (AssemblyTask task : assemblyTasks1){
            task.setIsCompleted(true);
        }
        WorkStation workStation2  = productionScheduler.getAssemblyLine().getWorkStations().get(1);
        List<AssemblyTask> assemblyTasks2 = workStation2.getTasks();
        for (AssemblyTask task : assemblyTasks2){
            task.setIsCompleted(true);
        }
        productionScheduler.advanceOrders(1);
        ArrayList<CarOrder> nullist3 = new ArrayList<>(3);
        nullist3.add(carOrder3);
        nullist3.add(carOrder2);
        nullist3.add(carOrder1);
        assertEquals(productionScheduler.getCurrentState(), nullist3);
        WorkStation workStation12  = productionScheduler.getAssemblyLine().getWorkStations().get(0);
        List<AssemblyTask> assemblyTasks12 = workStation12.getTasks();
        for (AssemblyTask task : assemblyTasks12){
            task.setIsCompleted(true);
        }
        WorkStation workStation22  = productionScheduler.getAssemblyLine().getWorkStations().get(1);
        List<AssemblyTask> assemblyTasks22 = workStation22.getTasks();
        for (AssemblyTask task : assemblyTasks22){
            task.setIsCompleted(true);
        }
        WorkStation workStation32  = productionScheduler.getAssemblyLine().getWorkStations().get(2);
        List<AssemblyTask> assemblyTasks32 = workStation32.getTasks();
        for (AssemblyTask task : assemblyTasks32){
            task.setIsCompleted(true);
        }
        assertEquals(nullist3,productionScheduler.getCurrentState());
        ArrayList<CarOrder> nullist4 = new ArrayList<>(3);
        nullist4.add(carOrder4);
        nullist4.add(carOrder3);
        nullist4.add(carOrder2);
        productionScheduler.advanceOrders(1);
        assertEquals(nullist4,productionScheduler.getCurrentState());
        assert(carOrder1.isCompleted());
    }

    @Test
    public void canMoveOneOrderTest() throws IllegalChoiceException, IllegalModelException, IllegalCompletionDateException {
        Body body = new Body("sedan");
        CarModelSpecification carModelSpecification = new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        CarModel carModel = new CarModel("Jaguar",carModelSpecification);
        CarOrder carOrder1 = new CarOrder("Luna",carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder1);
        assert(productionScheduler.getAssemblyLine().canMove());
        productionScheduler.advanceOrders(1);
        assert(!productionScheduler.getAssemblyLine().canMove());

    }

    @Test
    public void simulateAdvanceAssemblyLineTest() throws IllegalModelException, IllegalChoiceException, IllegalCompletionDateException {
        Body body = new Body("sedan");
        CarModelSpecification carModelSpecification = new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        /*ProductionScheduler.addModel("Jaguar");*/
        CarModel carModel = new CarModel("Jaguar",carModelSpecification);
        CarOrder carOrder1 = new CarOrder("Luna",carModel);
        CarOrder carOrder2 = new CarOrder("Raf", carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder1);
        productionScheduler.advanceOrders(1);
        ArrayList<CarOrder> test = new ArrayList<>();
        test.add(carOrder2);
        test.add(carOrder1);
        test.add(null);
        productionScheduler.addOrderToProductionSchedule(carOrder2);
        List<CarOrder> simulation = productionScheduler.simulateAdvanceAssemblyLine();
        assertEquals(test,simulation );
    }

    @Test
    public void getOrdersFromGarageHolderTest() throws IllegalModelException, IllegalChoiceException, IllegalCompletionDateException {
        Body body = new Body("sedan");
        CarModelSpecification carModelSpecification = new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
       /* ProductionScheduler.addModel("Jaguar");*/
        CarModel carModel = new CarModel("Jaguar",carModelSpecification);
        CarOrder carOrder1 = new CarOrder("Luna",carModel);
        CarOrder carOrder2 = new CarOrder("Raf", carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder1);
        productionScheduler.addOrderToProductionSchedule(carOrder2);
        assert(productionScheduler.getOrdersFromGarageHolder("Luna").contains(carOrder1));
        assert(!productionScheduler.getOrdersFromGarageHolder("Luna").contains(carOrder2));
        assert(!productionScheduler.getOrdersFromGarageHolder("Raf").contains(carOrder1));
        assert(productionScheduler.getOrdersFromGarageHolder("Raf").contains(carOrder2));
        productionScheduler.advanceOrders(1);
        assert(productionScheduler.getOrdersFromGarageHolder("Raf").contains(carOrder2));
        assert(productionScheduler.getOrdersFromGarageHolder("Luna").contains(carOrder1));

    }

    @Test
    public void getOrdersFromGarageHolderTest2() throws IllegalModelException, IllegalChoiceException, IllegalCompletionDateException {
        Body body = new Body("sedan");
        CarModelSpecification carModelSpecification = new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        /*ProductionScheduler.addModel("Jaguar");*/
        CarModel carModel = new CarModel("Jaguar", carModelSpecification);
        CarOrder carOrder1 = new CarOrder("Luna", carModel);
        CarOrder carOrder2 = new CarOrder("Luna", carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder1);
        //productionScheduler.completedCarOrder(carOrder2);
        assert(productionScheduler.getOrdersFromGarageHolder("Luna").contains(carOrder1));
        assert(!productionScheduler.getOrdersFromGarageHolder("Luna").contains(carOrder2));

    }

/*    @Test
    public void getCompletedOrdersFromGarageHolderTest() throws IllegalModelException, IllegalChoiceException, IllegalCompletionDateException {
        Body body = new Body("sedan");
        CarModelSpecification carModelSpecification = new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        ProductionScheduler.addModel("Jaguar");
        CarModel carModel = new CarModel("Jaguar", carModelSpecification);
        CarOrder carOrder1 = new CarOrder("Luna", carModel);
        CarOrder carOrder2 = new CarOrder("Luna", carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder1);
        productionScheduler.completedCarOrder(carOrder2);
        assert(!productionScheduler.getCompletedOrdersFromGarageHolder("Luna").contains(carOrder1));
        assert(productionScheduler.getCompletedOrdersFromGarageHolder("Luna").contains(carOrder2));
    }*/

/*    @Test
    public void getCompletedOrdersFromGarageHolderTest2() throws IllegalModelException, IllegalChoiceException, IllegalCompletionDateException {
        Body body = new Body("sedan");
        CarModelSpecification carModelSpecification = new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        ProductionScheduler.addModel("Jaguar");
        CarModel carModel = new CarModel("Jaguar", carModelSpecification);
        CarOrder carOrder1 = new CarOrder("Luna", carModel);
        CarOrder carOrder2 = new CarOrder("Luna", carModel);
        CarOrder carOrder3 = new CarOrder("Raf", carModel);
        CarOrder carOrder4 = new CarOrder("Raf", carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder1);
        productionScheduler.addOrderToProductionSchedule(carOrder3);
        productionScheduler.completedCarOrder(carOrder2);
        productionScheduler.completedCarOrder(carOrder4);
        assert(!productionScheduler.getCompletedOrdersFromGarageHolder("Luna").contains(carOrder1));
        assert(productionScheduler.getCompletedOrdersFromGarageHolder("Luna").contains(carOrder2));
        assert(!productionScheduler.getCompletedOrdersFromGarageHolder("Raf").contains(carOrder3));
        assert(productionScheduler.getCompletedOrdersFromGarageHolder("Raf").contains(carOrder4));
    }*/
}
