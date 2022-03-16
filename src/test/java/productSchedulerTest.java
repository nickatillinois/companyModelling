import assemAssist.AssemblyLine;
import assemAssist.ProductionScheduler;
import assemAssist.carOrder.*;
import assemAssist.exceptions.IllegalChoiceException;
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
        accessoriesPost = new AccessoriesPost();
        carBodyPost = new CarBodyPost();
        drivetrainPost = new DrivetrainPost();
        ArrayList<WorkStation> workStations = new ArrayList<>();
        workStations.add(carBodyPost);
        workStations.add(drivetrainPost);
        workStations.add(accessoriesPost);
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
    @Test
    public void getCurrentStateOneOrderNoAdvanceTest() throws IllegalChoiceException {
        Body body = new Body();
        body.setChosenChoice("break");
        CarModelSpecification carModelSpecification = new CarModelSpecification(body,new Color(),new Engine(),new Gearbox(),new Seats(),new Airco(),new Wheels());
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
    public void getCurrentStateOneOrderAdvanceTest() throws IllegalChoiceException {
        Body body = new Body();
        body.setChosenChoice("break");
        CarModelSpecification carModelSpecification = new CarModelSpecification(body,new Color(),new Engine(),new Gearbox(),new Seats(),new Airco(),new Wheels());
        CarModel carModel = new CarModel("Jaguar",carModelSpecification);
        CarOrder carOrder1 = new CarOrder("Luna",carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder1);
        productionScheduler.advanceOrders(1);
        ArrayList<CarOrder> nullist = new ArrayList<>(3);
        nullist.add(carOrder1);
        nullist.add(null);
        nullist.add(null);
        System.out.println(productionScheduler.getCurrentState());
        assertEquals(productionScheduler.getCurrentState(),nullist);
    }

    @Test
    public void getCurrentStateTwoOrderAdvanceTest() throws IllegalChoiceException {
        Body body = new Body();
        body.setChosenChoice("break");
        CarModelSpecification carModelSpecification = new CarModelSpecification(body,new Color(),new Engine(),new Gearbox(),new Seats(),new Airco(),new Wheels());
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
        nullist2.add(carOrder2);
        nullist2.add(carOrder1);
        nullist2.add(null);
        assertEquals(productionScheduler.getCurrentState(),nullist2);
    }

    @Test
    public void getCurrentStateFourOrderAdvanceTest() throws IllegalChoiceException {
        Body body = new Body();
        body.setChosenChoice("break");
        CarModelSpecification carModelSpecification = new CarModelSpecification(body,new Color(),new Engine(),new Gearbox(),new Seats(),new Airco(),new Wheels());
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
        System.out.println(productionScheduler.getCurrentState());
        assertEquals(productionScheduler.getCurrentState(),nullist);
        productionScheduler.advanceOrders(1);
        ArrayList<CarOrder> nullist2 = new ArrayList<>(3);
        nullist2.add(carOrder2);
        nullist2.add(carOrder1);
        nullist2.add(null);
        System.out.println(productionScheduler.getCurrentState());
        assertEquals(productionScheduler.getCurrentState(),nullist2);
        CarOrder carOrder3 = new CarOrder("Luna",carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder3);
        CarOrder carOrder4 = new CarOrder("Luna",carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder4);
        System.out.println(productionScheduler.getCurrentState());
        productionScheduler.advanceOrders(1);
        ArrayList<CarOrder> nullist3 = new ArrayList<>(3);
        nullist.add(carOrder3);
        nullist.add(carOrder2);
        nullist.add(carOrder1);
        assertEquals(productionScheduler.getCurrentState(), nullist3);

    }

    @Test
    public void canMoveOneOrderTest() throws IllegalChoiceException {
        Body body = new Body();
        body.setChosenChoice("break");
        CarModelSpecification carModelSpecification = new CarModelSpecification(body,new Color(),new Engine(),new Gearbox(),new Seats(),new Airco(),new Wheels());
        CarModel carModel = new CarModel("Jaguar",carModelSpecification);
        CarOrder carOrder1 = new CarOrder("Luna",carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder1);
        assert(productionScheduler.getAssemblyLine().canMove());
        productionScheduler.advanceOrders(1);
        assert(!productionScheduler.getAssemblyLine().canMove());

    }

}
