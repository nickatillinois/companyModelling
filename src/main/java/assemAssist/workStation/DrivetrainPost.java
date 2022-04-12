package assemAssist.workStation;

import assemAssist.AssemblyTask;

/**
 *  Class representing a drivetrain post
 */
public class DrivetrainPost extends WorkStation{

    /**
     * Creates a drivetrain post.
     */
    public DrivetrainPost() {
        super("Drivetrain Post");
    }

    /**
     * Assigns the car options of the current car order to this work station.
     */
    @Override
    public void newTasks() {

        //add task for engine
        this.addTask(new AssemblyTask("engine",
                "insert " + getCurrentOrder().getCarModel().getSpecification().get("engine")));

        //add task for gearbox
        this.addTask(new AssemblyTask("gearbox",
                "install " + getCurrentOrder().getCarModel().getSpecification().get("gearbox")));

    }


}
