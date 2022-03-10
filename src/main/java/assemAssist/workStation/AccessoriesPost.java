package assemAssist.workStation;

import assemAssist.AssemblyLine;
import assemAssist.AssemblyTask;
import assemAssist.carOrder.CarOrder;

public class AccessoriesPost extends WorkStation{

    public AccessoriesPost(CarOrder currentOrder, AssemblyLine assemblyLine) {

        super(currentOrder, assemblyLine);

        //add task for seats
        this.addTask(new AssemblyTask(this,
                currentOrder.getCarModel().getCarModelSpecification().getSeats(),
                new String("install " + currentOrder.getCarModel().getCarModelSpecification().getSeats().toString())));

        //add task for airco
        this.addTask(new AssemblyTask(this,
                currentOrder.getCarModel().getCarModelSpecification().getAirco(),
                new String("install " + currentOrder.getCarModel().getCarModelSpecification().getAirco().toString())));

        //add task for wheels
        this.addTask(new AssemblyTask(this,
                currentOrder.getCarModel().getCarModelSpecification().getWheels(),
                new String("mount " + currentOrder.getCarModel().getCarModelSpecification().getWheels().toString())));

    }
}
