package assemAssist.workStation;

import assemAssist.AssemblyTask;
import assemAssist.Company;

/**
 *  Class representing a Drivetrain Post.
 *
 *  @author SWOP team 10
 */
public class DrivetrainPost extends WorkStation{

    /**
     * Creates a drivetrain post.
     *
     * @param company the observer that will be watching this class
     * @throws IllegalArgumentException company is null
     */
    public DrivetrainPost(Company company) throws IllegalArgumentException {
        super("Drivetrain Post");
        if( company == null ) { throw new IllegalArgumentException("The given observer cannot be null."); }
        addObserver(company);
    }

    /**
     * Assigns the car options of the current car order to this work station and creates new tasks to be completed.
     * @throws RuntimeException the adding of the assembly tasks goes wrong
     */
    @Override
    public void newTasks() throws RuntimeException{

        try {
            //add task for engine
            this.addTask(new AssemblyTask("engine",
                    "insert " + getCurrentOrder().getCarModel().getChosenOptions().get("Engine")));

            //add task for gearbox
            this.addTask(new AssemblyTask("gearbox",
                    "install " + getCurrentOrder().getCarModel().getChosenOptions().get("Gearbox").toLowerCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Construction of the assembly tasks in "+ this.getName() +" went wrong.");
        }
    }


}
