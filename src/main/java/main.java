import assemAssist.AssemblyLine;
import assemAssist.AssemblyTask;
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
        ProductionScheduler.addModel("Jaguar");


        //DEMO
        CarModelSpecification cmf = new CarModelSpecification(new Body("sedan"),new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        String modelName = "jaguar";
        CarModel carModel = new CarModel(modelName, cmf);
        String garageHolder = "Nick Degelin";
        CarOrder carOrder1 = new CarOrder(garageHolder, carModel);

        CarModelSpecification cmf2 = new CarModelSpecification(new Body("sedan"),new Color("blue"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        CarModel carModel2 = new CarModel(modelName, cmf2);
        CarOrder carOrder2 = new CarOrder(garageHolder, carModel2);

        String garageHolder2 = "Raf Sablon";
        CarModelSpecification cmf3 = new CarModelSpecification(new Body("sedan"),new Color("blue"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        CarModel carModel3 = new CarModel(modelName, cmf3);
        CarOrder carOrder3 = new CarOrder(garageHolder2, carModel3);
        productionScheduler.getAssemblyLine().getWorkStations().get(0).setCurrentOrder(carOrder3);
        productionScheduler.getAssemblyLine().getWorkStations().get(1).setCurrentOrder(carOrder2);
        productionScheduler.getAssemblyLine().getWorkStations().get(2).setCurrentOrder(carOrder1);
        for (AssemblyTask task :productionScheduler.getAssemblyLine().getWorkStations().get(2).getTasks()){
            task.setIsCompleted(true);
        }
        for (AssemblyTask task :productionScheduler.getAssemblyLine().getWorkStations().get(1).getTasks()){
            task.setIsCompleted(true);
        }
        // Tot hier DEMO


        ManagerController managerController = new ManagerController(productionScheduler);
        ManagerUI managerUI = new ManagerUI(managerController);

        MechanicController mechanicController = new MechanicController(assemblyLine);
        MechanicUI mechanicUI = new MechanicUI(mechanicController);

        UI ui = new UI(garageHolderUI, managerUI, mechanicUI);
    }
}
