package assemAssist.workStation;

import assemAssist.AssemblyTask;

/**
 *  Class representing a Drivetrain Post
 */
public class DrivetrainPost extends WorkStation{

    /**
     * Creates a drivetrain post.
     */
    public DrivetrainPost() {
        super("Drivetrain Post");
    }

    /**
     *  Assigns the car options of the current car order to this work station and creates new tasks to be completed.
     */
    @Override
    public void newTasks() {

        //add task for engine
        this.addTask(new AssemblyTask("engine",
                "insert " + getCurrentOrder().getCarModel().getChosenOptions().get("engine").toLowerCase()));

        //add task for gearbox
        this.addTask(new AssemblyTask("gearbox",
                "install " + getCurrentOrder().getCarModel().getChosenOptions().get("gearbox").toLowerCase()));

    }


}
