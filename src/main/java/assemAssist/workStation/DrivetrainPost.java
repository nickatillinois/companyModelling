package assemAssist.workStation;

import assemAssist.AssemblyTask;
import assemAssist.Company;

/**
 *  Class representing a Drivetrain Post
 */
public class DrivetrainPost extends WorkStation{

    /**
     * Creates a drivetrain post.
     */
    public DrivetrainPost(Company company) {
        super("Drivetrain Post");
        addObserver(company);
    }

    /**
     *  Assigns the car options of the current car order to this work station and creates new tasks to be completed.
     */
    @Override
    public void newTasks() {

        //add task for engine
        this.addTask(new AssemblyTask("engine",
                "insert " + getCurrentOrder().getCarModel().getChosenOptions().get("Engine")));

        //add task for gearbox
        this.addTask(new AssemblyTask("gearbox",
                "install " + getCurrentOrder().getCarModel().getChosenOptions().get("Gearbox").toLowerCase()));

    }


}
