package assemAssist.workStation;
import assemAssist.AssemblyLine;
import assemAssist.AssemblyTask;
import assemAssist.carOrder.CarOrder;

/* Class representing a car body post on an assembly line. */
public class CarBodyPost extends WorkStation{

    /**
     * Creates a car body post with the given order as current order, on the given assembly line.
     */
    public CarBodyPost() { super(); }


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
