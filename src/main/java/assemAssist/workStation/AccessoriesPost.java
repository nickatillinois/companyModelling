package assemAssist.workStation;
import assemAssist.AssemblyLine;
import assemAssist.AssemblyTask;
import assemAssist.carOrder.CarOrder;

/* Class representing an accessories post on an assembly line. */
public class AccessoriesPost extends WorkStation{

    /**
     * Creates an accessory post with the given order as current order, on the given assembly line.
     *
     * @param currentOrder the order currently in this post
     * @param assemblyLine the assembly line this post is part of
     *
     * @throws IllegalArgumentException | assemblyLine is null
     */
    public AccessoriesPost(CarOrder currentOrder, AssemblyLine assemblyLine) {
        super(currentOrder, assemblyLine);

        //add task for seats
        this.addTask(new AssemblyTask("seats", this,
                currentOrder.getCarModel().getCarModelSpecification().getSeats(),
                "install " + currentOrder.getCarModel().getCarModelSpecification().getSeats().toString()));

        //add task for airco
        this.addTask(new AssemblyTask("airco", this,
                currentOrder.getCarModel().getCarModelSpecification().getAirco(),
                "install " + currentOrder.getCarModel().getCarModelSpecification().getAirco().toString()));

        //add task for wheels
        this.addTask(new AssemblyTask("wheels", this,
                currentOrder.getCarModel().getCarModelSpecification().getWheels(),
                "mount " + currentOrder.getCarModel().getCarModelSpecification().getWheels().toString()));

    }
}
