package assemAssist.workStation;
import assemAssist.AssemblyLine;
import assemAssist.AssemblyTask;
import assemAssist.carOrder.CarOrder;

/* Class representing a drivetrain post on an assembly line. */
public class DrivetrainPost extends WorkStation{

    /**
     * Creates a drivetrain post with the given order as current order, on the given assembly line.
     *
     * @param currentOrder the order currently in this post
     */
    public DrivetrainPost(CarOrder currentOrder) {
        super(currentOrder);
            //add task for engine
            this.addTask(new AssemblyTask("engine", this,
                    currentOrder.getCarModel().getCarModelSpecification().getEngine(),
                    "insert " + currentOrder.getCarModel().getCarModelSpecification().getEngine().toString()));

            //add task for gearbox
            this.addTask(new AssemblyTask("gearbox", this,
                    currentOrder.getCarModel().getCarModelSpecification().getGearbox(),
                    "insert " + currentOrder.getCarModel().getCarModelSpecification().getGearbox().toString()));

    }
}
