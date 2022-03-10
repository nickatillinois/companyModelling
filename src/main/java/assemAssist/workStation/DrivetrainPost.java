package assemAssist.workStation;

import assemAssist.AssemblyLine;
import assemAssist.AssemblyTask;
import assemAssist.carOrder.CarOrder;

public class DrivetrainPost extends WorkStation{

    public DrivetrainPost(CarOrder currentOrder, AssemblyLine assemblyLine) {
        super(currentOrder, assemblyLine);

        //add task for engine
        this.addTask(new AssemblyTask(this,
                currentOrder.getCarModel().getCarModelSpecification().getEngine(),
                new String("insert " + currentOrder.getCarModel().getCarModelSpecification().getEngine().toString())));

        //add task for gearbox
        this.addTask(new AssemblyTask(this,
                currentOrder.getCarModel().getCarModelSpecification().getGearbox(),
                new String("insert " + currentOrder.getCarModel().getCarModelSpecification().getGearbox().toString())));

    }
}
