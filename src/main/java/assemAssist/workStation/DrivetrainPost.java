package assemAssist.workStation;
import assemAssist.AssemblyLine;
import assemAssist.AssemblyTask;
import assemAssist.carOrder.CarOrder;

import java.util.List;

/* Class representing a drivetrain post on an assembly line. */
public class DrivetrainPost extends WorkStation{

    /**
     * Creates a drivetrain post with the given order as current order, on the given assembly line.
     */
    public DrivetrainPost() {
        super();
    }

    @Override
    public void newTasks() {

        //add task for engine
        this.addTask(new AssemblyTask("engine",
                getCurrentOrder().getCarModel().getCarModelSpecification().getEngine(),
                "insert " + getCurrentOrder().getCarModel().getCarModelSpecification().getEngine()));
        //add task for gearbox
        this.addTask(new AssemblyTask("gearbox",
                getCurrentOrder().getCarModel().getCarModelSpecification().getGearbox(),
                "insert " + getCurrentOrder().getCarModel().getCarModelSpecification().getGearbox()));

    }
}
