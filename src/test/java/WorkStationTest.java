import assemAssist.carOrder.*;
import assemAssist.exceptions.IllegalChoiceException;
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
    static void init() throws IllegalModelException, IllegalChoiceException {

        carBodyPost = new CarBodyPost();
        drivetrainPost = new DrivetrainPost();
        accessoriesPost = new AccessoriesPost();

        Body body = new Body("sedan");
        CarModelSpecification specification = new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        CarOrder orderA = new CarOrder("A",new CarModel("Jaguar", specification));
        CarOrder orderB = new CarOrder("B",new CarModel("Jaguar", specification));

        carBodyPost.setCurrentOrder(orderA);
        drivetrainPost.setCurrentOrder(orderB);
    }

    @Test
    void getCurrentOrder() throws IllegalModelException, IllegalChoiceException {
        Body body = new Body("sedan");
        CarModelSpecification specification= new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        CarOrder orderA = new CarOrder("A",new CarModel("Jaguar", specification));
        CarOrder orderB = new CarOrder("B",new CarModel("Jaguar", specification));

        assertEquals(carBodyPost.getCurrentOrder(),orderA);
        assertEquals(drivetrainPost.getCurrentOrder(),orderB);
        assertNull(accessoriesPost.getCurrentOrder());
    }

    @Test
    void setCurrentOrder() throws IllegalModelException, IllegalChoiceException {
        Body body = new Body("sedan");
        CarModelSpecification specification = new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
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