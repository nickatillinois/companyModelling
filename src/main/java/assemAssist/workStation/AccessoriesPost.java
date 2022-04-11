package assemAssist.workStation;

import assemAssist.AssemblyTask;

/**
 *  Class representing a accessories post
 */
public class AccessoriesPost extends WorkStation{

    /**
     * Creates an accessory post.
     */
    public AccessoriesPost() { super("Accessories Post"); }

    /**
     * Assigns the car options of the current car order to this work station.
     */
    @Override
    public void newTasks() {

        //add task for seats
        this.addTask(new AssemblyTask("seats",
                "install " + getCurrentOrder().getCarModel().getSpecification().get("seats") + " seats"));

        //add task for airco
        this.addTask(new AssemblyTask("airco",
                "install " + getCurrentOrder().getCarModel().getSpecification().get("airco") + " airco"));
        //add task for wheels
        this.addTask(new AssemblyTask("wheels",
                "wheels " + getCurrentOrder().getCarModel().getSpecification().get("wheels") + " wheels"));
    }

}
