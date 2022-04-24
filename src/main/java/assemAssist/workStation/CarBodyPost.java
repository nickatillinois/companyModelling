package assemAssist.workStation;

import assemAssist.AssemblyTask;

/**
 *  Class representing a Car Body post
 */
public class CarBodyPost extends WorkStation{

    /**
     * Creates a car body post.
     */
    public CarBodyPost() {
        super("Car Body Post");
    }

    /**
     *  Assigns the car options of the current car order to this work station and creates new tasks to be completed.
     */
    @Override
    public void newTasks() {

        //add task for body
        this.addTask(new AssemblyTask("body",
                "assemble " + getCurrentOrder().getCarModel().getChosenOptions().get("body") + " body"));
        //add task for paint
        this.addTask(new AssemblyTask("paint",
                "paint the car " + getCurrentOrder().getCarModel().getChosenOptions().get("color")));


    }

}
