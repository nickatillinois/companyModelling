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
import controller.ManagerController;
import controller.MechanicController;
import ui.GarageHolderUI;
import ui.ManagerUI;
import ui.MechanicUI;
import ui.UI;
import controller.GarageHolderController;

import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) throws IllegalModelException, IllegalChoiceException, IllegalCompletionDateException {
        CarBodyPost carBodyPost = new CarBodyPost();
        DrivetrainPost drivetrainPost = new DrivetrainPost();
        List<WorkStation> workStations = new ArrayList<>();
        workStations.add(carBodyPost);
        workStations.add(drivetrainPost);
        AccessoriesPost accessoriesPost = new AccessoriesPost();
        workStations.add(accessoriesPost);
        AssemblyLine assemblyLine = new AssemblyLine(workStations);
        ProductionScheduler productionScheduler = new ProductionScheduler("Nick",assemblyLine);
        GarageHolderController garageHolderController = new GarageHolderController(productionScheduler);
        GarageHolderUI garageHolderUI = new GarageHolderUI(garageHolderController);
        CarModel.addModel("Jaguar");


        Body body = new Body("sedan");
        CarModelSpecification cmf = new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        String modelName = "jaguar";
        CarModel carModel = new CarModel(modelName, cmf);
        String garageHolder = "john doe";
        CarOrder carOrder1 = new CarOrder(garageHolder, carModel);
        Body body2 = new Body("sedan");
        CarModelSpecification cmf2 = new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        CarModel carModel2 = new CarModel(modelName, cmf);
        CarOrder carOrder2 = new CarOrder(garageHolder, carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder1);
        productionScheduler.addOrderToProductionSchedule(carOrder2);


        ManagerController managerController = new ManagerController(productionScheduler);
        ManagerUI managerUI = new ManagerUI(managerController);

        MechanicController mechanicController = new MechanicController(assemblyLine);
        MechanicUI mechanicUI = new MechanicUI(mechanicController);

        UI ui = new UI(garageHolderUI, managerUI, mechanicUI);
    }
}
