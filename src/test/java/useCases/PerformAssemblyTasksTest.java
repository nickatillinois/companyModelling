package useCases;

import assemAssist.AssemblyLine;
import assemAssist.ProductionScheduler;
import assemAssist.carOrder.*;
import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.exceptions.IllegalModelException;
import assemAssist.workStation.AccessoriesPost;
import assemAssist.workStation.CarBodyPost;
import assemAssist.workStation.DrivetrainPost;
import assemAssist.workStation.WorkStation;
import controller.GarageHolderController;
import controller.ManagerController;
import controller.MechanicController;
import org.junit.jupiter.api.Test;
import ui.GarageHolderUI;
import ui.ManagerUI;
import ui.MechanicUI;
import ui.UI;

import java.util.List;

public class PerformAssemblyTasksTest {

    @Test
    public void performTasksTest() throws IllegalChoiceException, IllegalModelException, IllegalCompletionDateException {

        Body body = new Body("sedan");
        CarModelSpecification cmf = new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        AccessoriesPost accessoriesPost = new AccessoriesPost();
        CarBodyPost carBodyPost = new CarBodyPost();
        DrivetrainPost drivetrainPost = new DrivetrainPost();
        List<WorkStation> workStationList = List.of(accessoriesPost,carBodyPost,drivetrainPost);
        AssemblyLine assemblyLine = new AssemblyLine(workStationList);
        ProductionScheduler productionScheduler = new ProductionScheduler("frank", assemblyLine);
        ProductionScheduler.addModel("Jaguar");
        String modelName = "jaguar";
        CarModel carModel = new CarModel(modelName, cmf);
        String garageHolder = "john doe";
        CarOrder carOrder = new CarOrder(garageHolder, carModel);

        UI ui = new UI(new GarageHolderUI(new GarageHolderController(productionScheduler)),
                       new ManagerUI(new ManagerController(productionScheduler)),
                       new MechanicUI(new MechanicController(assemblyLine)));


    }
}
