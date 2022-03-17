package assemAssist.workStation;
import assemAssist.AssemblyLine;
import assemAssist.AssemblyTask;
import assemAssist.carOrder.CarOrder;

import java.util.List;

/**
 *  Class representing a drivetrain post
 */
public class DrivetrainPost extends WorkStation{

    /**
     * Creates a drivetrain post.
     */
    public DrivetrainPost() {
        super();
    }

    /**
     * Returns the name of the work station.
     */
    @Override
    public String getName() {
        return "Drivetrain Post";
    }

    /**
     * Assigns the car options of the current car order to this work station.
     */
    @Override
    public void newTasks() {

        //add task for engine
        this.addTask(new AssemblyTask("engine",
                getCurrentOrder().getCarModel().getCarModelSpecification().getEngine(),
                "insert " + getCurrentOrder().getCarModel().getCarModelSpecification().getEngine()));
        //add task for gearbox
        this.addTask(new AssemblyTask("gearbox",
                getCurrentOrder().getCarModel().getCarModelSpecification().getGearbox(),
                "insert " + getCurrentOrder().getCarModel().getCarModelSpecification().getGearbox()));

    }


}
