import assemAssist.AssemblyTask;
import assemAssist.CarModel;
import assemAssist.CarModelSpecification;
import assemAssist.CarOrder;
import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalModelException;
import assemAssist.workStation.AccessoriesPost;
import assemAssist.workStation.CarBodyPost;
import assemAssist.workStation.DrivetrainPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WorkStationTest {

    static CarBodyPost carBodyPost;
    static DrivetrainPost drivetrainPost;
    static AccessoriesPost accessoriesPost;

    @BeforeEach
    void init() throws IllegalModelException, IllegalChoiceException {

        carBodyPost = new CarBodyPost();
        drivetrainPost = new DrivetrainPost();
        accessoriesPost = new AccessoriesPost();


        CarOrder orderA = new CarOrder("A",new CarModel("Jaguar", specification));
        CarOrder orderB = new CarOrder("B",new CarModel("Jaguar", specification));

        carBodyPost.setCurrentOrder(orderA);
        drivetrainPost.setCurrentOrder(orderB);
    }

    @Test
    void getCurrentOrder() throws IllegalModelException, IllegalChoiceException {
        CarModelSpecification specification= new CarModelSpecification(new Body("sedan"),new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        CarOrder orderA = new CarOrder("A",new CarModel("Jaguar", specification));
        CarOrder orderB = new CarOrder("B",new CarModel("Jaguar", specification));

        assert(equals(carBodyPost.getCurrentOrder(),orderA));
        assert(equals(drivetrainPost.getCurrentOrder(),orderB));
        assertNull(accessoriesPost.getCurrentOrder());
    }

    @Test
    void setCurrentOrder() throws IllegalModelException, IllegalChoiceException {
        CarModelSpecification specification = new CarModelSpecification(new Body("sedan"),new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        CarOrder orderC = new CarOrder("C",new CarModel("Jaguar", specification));

        accessoriesPost.setCurrentOrder(orderC);
        assert(equals(accessoriesPost.getCurrentOrder(),orderC));
    }

    @Test
    void getTasks() {
        for (AssemblyTask task : carBodyPost.getTasks() ) {
            assert(task.getName().equals("body")
                    || task.getName().equals("paint"));
        }
        for (AssemblyTask task : drivetrainPost.getTasks() ) {
            assert(task.getName().equals("engine")
                    || task.getName().equals("gearbox"));
        }
        for (AssemblyTask task : accessoriesPost.getTasks() ) {
            assert(task.getName().equals("seats")
                    || task.getName().equals("airco")
                    || task.getName().equals("wheels") );
        }
    }

    @Test
    void newTaskDefinitionAfterChangingOrder() throws IllegalChoiceException, IllegalModelException {
        CarModelSpecification specification= new CarModelSpecification(new Body("sedan"),new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        CarOrder orderA = new CarOrder("A",new CarModel("Jaguar", specification));
        accessoriesPost.setCurrentOrder(orderA);

        assert(accessoriesPost.getTasks().get(0).getTaskDefinition().equals("install leather white seats"));
        assert(accessoriesPost.getTasks().get(1).getTaskDefinition().equals("install manual airco"));
        assert(accessoriesPost.getTasks().get(2).getTaskDefinition().equals("mount comfort wheels"));

    }

    @Test
    void isFinished() {

        assertFalse(carBodyPost.isFinished());
        assertFalse(drivetrainPost.isFinished());

        carBodyPost.performAssemblyTask("body");
        carBodyPost.performAssemblyTask("paint");
        assert(carBodyPost.isFinished());
        assertFalse(drivetrainPost.isFinished());

    }

    @Test
    void addOrRemoveMechanic() {
        carBodyPost.addMechanic("Bart");
        drivetrainPost.addMechanic("Jos");
        drivetrainPost.addMechanic("Wim");

        List<String> mechanicsCBP = List.of("Bart");
        assert(carBodyPost.getMechanics().equals(mechanicsCBP));
        List<String> mechanicsDP = List.of("Jos","Wim");
        assert(drivetrainPost.getMechanics().equals(mechanicsDP));
        List<String> mechanicsAP = List.of();
        assert(accessoriesPost.getMechanics().equals(mechanicsAP));
    }

    @Test
    void getPendingTasks() {
        List<String> pendingCBP = List.of("body","paint");
        assert(carBodyPost.getPendingTasks().equals(pendingCBP));
        List<String> pendingDP = List.of("engine","gearbox");
        assert(drivetrainPost.getPendingTasks().equals(pendingDP));
    }

    @Test
    void performAssemblyTask() {
        List<String> pendingCBP = List.of("body","paint");
        assert(carBodyPost.getPendingTasks().equals(pendingCBP));
        List<String> pendingDP = List.of("engine","gearbox");
        assert(drivetrainPost.getPendingTasks().equals(pendingDP));

        carBodyPost.performAssemblyTask("body");
        List<String> pendingCBPupdated = List.of("paint");
        assert(carBodyPost.getPendingTasks().equals(pendingCBPupdated));
        carBodyPost.performAssemblyTask("paint");
        pendingCBPupdated = List.of();
        assert(carBodyPost.getPendingTasks().equals(pendingCBPupdated));
    }

    @Test
    void getTasksAndStatus() {
        String statusCBP = carBodyPost.getTasksAndStatus();
        assert(statusCBP.equals("body: pending, paint: pending"));
        String statusDP = drivetrainPost.getTasksAndStatus();
        assert(statusDP.equals("engine: pending, gearbox: pending"));

        carBodyPost.performAssemblyTask("body");
        statusCBP = carBodyPost.getTasksAndStatus();
        assert(statusCBP.equals("body: done, paint: pending"));
    }
}