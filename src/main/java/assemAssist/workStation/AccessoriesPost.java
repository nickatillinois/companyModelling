package assemAssist.workStation;

import assemAssist.AssemblyTask;

/* Class representing an accessories post on an assembly line. */
public class AccessoriesPost extends WorkStation{

    /**
     * Creates an accessory post with the given order as current order, on the given assembly line.
     */
    public AccessoriesPost() { super(); }


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
