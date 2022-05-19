package assemAssist.workStation;

import assemAssist.AssemblyTask;
import assemAssist.Company;

/**
 *  Class representing a Drivetrain Post
 */
public class DrivetrainPost extends WorkStation{

    /**
     * Creates a drivetrain post.
     *
     * @param company the observer that will be watching this class
     * @throws IllegalArgumentException company == null
     */
    public DrivetrainPost(Company company) {
        super("Drivetrain Post");
        if( company == null ) { throw new IllegalArgumentException("The given observer cannot be null."); }
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
