package assemAssist.workStation;

import assemAssist.AssemblyLine;
import assemAssist.AssemblyTask;
import assemAssist.carOrder.CarOrder;

public class CarBodyPost extends WorkStation{

    public CarBodyPost(CarOrder currentOrder, AssemblyLine assemblyLine) {
        super(currentOrder, assemblyLine);

        //add task for body
        this.addTask(new AssemblyTask(this,
                currentOrder.getCarModel().getCarModelSpecification().getBody(),
                new String("assemble " + currentOrder.getCarModel().getCarModelSpecification().getBody().toString())));

        //add task for paint
        this.addTask(new AssemblyTask(this,
                currentOrder.getCarModel().getCarModelSpecification().getColor(),
                new String("paint the car " + currentOrder.getCarModel().getCarModelSpecification().getColor().toString())));

    }



}
