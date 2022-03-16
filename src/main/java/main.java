import controller.ManagerController;
import controller.MechanicController;
import ui.GarageHolderUI;
import ui.ManagerUI;
import ui.MechanicUI;
import ui.UI;
import controller.GarageHolderController;
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
    }
}
