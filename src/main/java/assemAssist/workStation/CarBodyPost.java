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
     * @param assemblyLine the assembly line this post is part of
     *
     * @throws IllegalArgumentException | assemblyLine is null
     */
    public CarBodyPost(CarOrder currentOrder, AssemblyLine assemblyLine) {
        super(currentOrder, assemblyLine);

        //add task for body
        this.addTask(new AssemblyTask(this,
                currentOrder.getCarModel().getCarModelSpecification().getBody(),
                "assemble " + currentOrder.getCarModel().getCarModelSpecification().getBody().toString()));

        //add task for paint
        this.addTask(new AssemblyTask(this,
                currentOrder.getCarModel().getCarModelSpecification().getColor(),
                "paint the car " + currentOrder.getCarModel().getCarModelSpecification().getColor().toString()));

    }



}
