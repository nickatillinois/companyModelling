import ui.GarageHolderUI;
import ui.UI;
import controller.GarageHolderController;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        GarageHolderController garageHolderController = new GarageHolderController();
        GarageHolderUI garageHolderUI = new GarageHolderUI(garageHolderController);
        UI ui = new UI(garageHolderUI);
    }
}
