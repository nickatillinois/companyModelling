import assemAssist.AssemblyLine;
import assemAssist.ProductionScheduler;
import assemAssist.carOrder.CarModel;
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
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        GarageHolderController garageHolderController = new GarageHolderController();
        GarageHolderUI garageHolderUI = new GarageHolderUI(garageHolderController);

        ManagerController managerController = new ManagerController();
        ManagerUI managerUI = new ManagerUI(managerController);

        MechanicController mechanicController = new MechanicController();
        MechanicUI mechanicUI = new MechanicUI(mechanicController);

        UI ui = new UI(garageHolderUI, managerUI, mechanicUI);

        AccessoriesPost accessoriesPost = new AccessoriesPost();
        CarBodyPost carBodyPost = new CarBodyPost();
        DrivetrainPost drivetrainPost = new DrivetrainPost();
        List<WorkStation> workStations = new ArrayList<>();
        workStations.add(carBodyPost);
        workStations.add(drivetrainPost);
        workStations.add(accessoriesPost);

        AssemblyLine assemblyLine = new AssemblyLine(workStations);

        ProductionScheduler productionScheduler = new ProductionScheduler("Nick",assemblyLine);

        CarModel.addModel("Jaguar");

    }
}
