package assemAssist.workStation;

import assemAssist.AssemblyTask;

/**
 *  Class representing an Accessories Post
 */
public class AccessoriesPost extends WorkStation{

    /**
     * Creates an accessory post.
     */
    public AccessoriesPost() { super("Accessories Post"); }


    /**
     *  Assigns the car options of the new car order to this work station
     *  and creates new tasks to be completed.
     */
    @Override
    public void newTasks() {
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
        if (getCurrentOrder().getCarModel().getChosenOptions().get("Spoiler") != null) {
            this.addTask(new AssemblyTask("spoiler",
                    "install " + getCurrentOrder().getCarModel().getChosenOptions().get("spoiler").toLowerCase() + " spoiler"));
        }
    }

}
