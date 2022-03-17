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
                getCurrentOrder().getCarModel().getCarModelSpecification().getSeats(),
                "install " + getCurrentOrder().getCarModel().getCarModelSpecification().getSeats().toString()));

        //add task for airco
        this.addTask(new AssemblyTask("airco",
                getCurrentOrder().getCarModel().getCarModelSpecification().getAirco(),
                "install " + getCurrentOrder().getCarModel().getCarModelSpecification().getAirco().toString()));

        //add task for wheels
        this.addTask(new AssemblyTask("wheels",
                getCurrentOrder().getCarModel().getCarModelSpecification().getWheels(),
                "mount " + getCurrentOrder().getCarModel().getCarModelSpecification().getWheels().toString()));

    }

}
