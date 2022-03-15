package assemAssist.workStation;
import assemAssist.AssemblyLine;
import assemAssist.AssemblyTask;
import assemAssist.carOrder.CarOrder;

/* Class representing a car body post on an assembly line. */
public class CarBodyPost extends WorkStation{

    /**
     * Creates a car body post with the given order as current order, on the given assembly line.
     *
     * @param currentOrder the order currently in this post
     */
    public CarBodyPost(CarOrder currentOrder) {
        super(currentOrder);
        if(getCurrentOrder() != null) {
            //add task for body
            this.addTask(new AssemblyTask("body", this,
                    currentOrder.getCarModel().getCarModelSpecification().getBody(),
                    "assemble " + currentOrder.getCarModel().getCarModelSpecification().getBody().toString()));

            //add task for paint
            this.addTask(new AssemblyTask("paint", this,
                    currentOrder.getCarModel().getCarModelSpecification().getColor(),
                    "paint the car " + currentOrder.getCarModel().getCarModelSpecification().getColor().toString()));
        }
    }



}
