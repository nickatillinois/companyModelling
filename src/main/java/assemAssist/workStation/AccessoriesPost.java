package assemAssist.workStation;

import assemAssist.AssemblyTask;
import assemAssist.Company;

/**
 *  Class representing an Accessories Post.
 */
public class AccessoriesPost extends WorkStation{

    /**
     * Creates an accessory post.
     *
     * @param company the observer that will be watching this class
     * @throws IllegalArgumentException company == null
     */
    public AccessoriesPost(Company company) throws IllegalArgumentException {
        super("Accessories Post");
        if( company == null ) { throw new IllegalArgumentException("The given observer cannot be null."); }
        addObserver(company);
    }

    /**
     *  Assigns the car options of the new car order to this work station
     *  and creates new tasks to be completed.
     * @throws RuntimeException | The adding of the assembly tasks goes wrong.
     */
    @Override
    public void newTasks() {
        try {
            //add task for seats
            this.addTask(new AssemblyTask("seats",
                    "install " + getCurrentOrder().getCarModel().getChosenOptions().get("Seats").toLowerCase() + " seats"));
            //add task for airco
            this.addTask(new AssemblyTask("airco",
                    "install " + getCurrentOrder().getCarModel().getChosenOptions().get("Airco").toLowerCase() + " airco"));
            //add task for wheels
            this.addTask(new AssemblyTask("wheels",
                    "mount " + getCurrentOrder().getCarModel().getChosenOptions().get("Wheels").toLowerCase() + " wheels"));
            //add task for spoiler, IF there needs to be one
            if (getCurrentOrder().getCarModel().getChosenOptions().get("spoiler") != null) {
                this.addTask(new AssemblyTask("spoiler",
                        "install " + getCurrentOrder().getCarModel().getChosenOptions().get("spoiler").toLowerCase() + " spoiler"));

            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Construction of the assembly tasks in "+ this.getName() +" went wrong.");
        }
    }

}
