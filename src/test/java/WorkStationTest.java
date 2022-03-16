import assemAssist.carOrder.*;
import assemAssist.exceptions.IllegalModelException;
import assemAssist.workStation.AccessoriesPost;
import assemAssist.workStation.CarBodyPost;
import assemAssist.workStation.DrivetrainPost;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

class WorkStationTest {

    static CarBodyPost carBodyPost;
    static DrivetrainPost drivetrainPost;
    static AccessoriesPost accessoriesPost;

    @BeforeAll
    static void init() throws IllegalModelException {

        carBodyPost = new CarBodyPost();
        drivetrainPost = new DrivetrainPost();
        accessoriesPost = new AccessoriesPost();

        CarModelSpecification specification = new CarModelSpecification(new Body(),new Color(),new Engine(),new Gearbox(),new Seats(),new Airco(),new Wheels());
        CarOrder orderA = new CarOrder("A",new CarModel("Jaguar", specification));
        CarOrder orderB = new CarOrder("B",new CarModel("Jaguar", specification));

        carBodyPost.setCurrentOrder(orderA);
        drivetrainPost.setCurrentOrder(orderB);
    }

    @Test
    void getCurrentOrder() throws IllegalModelException {
        CarModelSpecification specification = new CarModelSpecification(new Body(),new Color(),new Engine(),new Gearbox(),new Seats(),new Airco(),new Wheels());
        CarOrder orderA = new CarOrder("A",new CarModel("Jaguar", specification));
        CarOrder orderB = new CarOrder("B",new CarModel("Jaguar", specification));

        assertEquals(carBodyPost.getCurrentOrder(),orderA);
        assertEquals(drivetrainPost.getCurrentOrder(),orderB);
        assertNull(accessoriesPost.getCurrentOrder());
    }

    @Test
    void setCurrentOrder() throws IllegalModelException {
        CarModelSpecification specification = new CarModelSpecification(new Body(),new Color(),new Engine(),new Gearbox(),new Seats(),new Airco(),new Wheels());
        CarOrder orderC = new CarOrder("C",new CarModel("Jaguar", specification));

        accessoriesPost.setCurrentOrder(orderC);
        assertEquals(accessoriesPost.getCurrentOrder(),orderC);
    }

    @Test
    void getTasks() {
    }

    @Test
    void addTask() {
    }

    @Test
    void isFinished() {
    }

    @Test
    void addMechanic() {
    }

    @Test
    void removeMechanic() {
    }

    @Test
    void getMechanics() {
    }

    @Test
    void getPendingTasks() {
    }

    @Test
    void getInformationFromTask() {
    }

    @Test
    void performAssemblyTask() {
    }

    @Test
    void getTasksAndStatus() {
    }
}