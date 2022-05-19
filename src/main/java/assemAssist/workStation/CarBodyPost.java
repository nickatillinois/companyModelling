package assemAssist.workStation;

import assemAssist.AssemblyTask;
import assemAssist.Company;

/**
 *  Class representing a Car Body post
 */
public class CarBodyPost extends WorkStation{

    /**
     * Creates a car body post.
     *
     * @param company the observer that will be watching this class
     * @throws IllegalArgumentException company == null
     */
    public CarBodyPost(Company company) {
        super("Car Body Post");
        if( company == null ) { throw new IllegalArgumentException("The given observer cannot be null."); }
        addObserver(company);
    }

    /**
     *  Assigns the car options of the current car order to this work station and creates new tasks to be completed.
     */
    @Override
    public void newTasks() {

        //add task for body
        this.addTask(new AssemblyTask("body",
                "assemble " + getCurrentOrder().getCarModel().getChosenOptions().get("Body").toLowerCase() + " body"));
        //add task for paint
        this.addTask(new AssemblyTask("paint",
                "paint the car " + getCurrentOrder().getCarModel().getChosenOptions().get("Color").toLowerCase()));

    }

}
