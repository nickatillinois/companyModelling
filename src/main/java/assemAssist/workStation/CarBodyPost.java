package assemAssist.workStation;
import assemAssist.AssemblyLine;
import assemAssist.AssemblyTask;
import assemAssist.carOrder.CarOrder;

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
     * Assigns the car options of the current car order to this work station.
     */
    @Override
    public void newTasks() {

        //add task for body
        this.addTask(new AssemblyTask("body",
                getCurrentOrder().getCarModel().getCarModelSpecification().getBody(),
                "assemble " + getCurrentOrder().getCarModel().getCarModelSpecification().getBody().toString()));

        //add task for paint
        this.addTask(new AssemblyTask("paint",
                getCurrentOrder().getCarModel().getCarModelSpecification().getColor(),
                "paint the car " + getCurrentOrder().getCarModel().getCarModelSpecification().getColor().toString()));

    }

}
