package ui;

import java.util.List;
import java.util.Scanner;
import controller.GarageHolderController;

public class GarageHolderUI {
    private GarageHolderController garageHolderController;

    public GarageHolderUI(GarageHolderController garageHolderController) {
        this.garageHolderController = garageHolderController;
    }

    public void startUI(Scanner in) {

        System.out.println("Your previous orders are:");
        List<String> overViewOfOrders = garageHolderController.newLogin();
        for (int i = 0; i < overViewOfOrders.size(); i++) {
            System.out.println(overViewOfOrders.get(i));
        }

        System.out.println("-------------");

        List<String> availableModels;
        while (true) {
            System.out.println("Press \'n\' to order a new car, press \'c\' to leave the overview");
            char nextstep = in.next().charAt(0);
            if (nextstep == 'c') {
                System.out.println("Leaving the Garage Holder View");
                System.out.println("-------------");
                return;
            } else if (nextstep == 'n') {
                System.out.println("The available car models are:");
                availableModels = garageHolderController.wantsToOrder();
                for (int i = 0; i < availableModels.size(); i++) {
                    System.out.println(availableModels.get(i));
                }
                break;
            } else {
                System.out.println("This is not a valid option. Try again.");
            }
        }

        System.out.println("-------------");

        List<String> orderingForm;
        while (true) {
            System.out.print("Choose the car model you want to order:");
            String model = in.nextLine();
            if (availableModels.contains(model)) {
                System.out.println("The available options for this model are:");
                orderingForm = garageHolderController.selectModel(model);
                for (int i = 0; i < orderingForm.size(); i++) {
                    System.out.println(orderingForm.get(i));
                }
                break;
            } else {
                System.out.println("This is not a valid option. Try again.");
            }
        }

        System.out.println("Leaving the Garage Holder View");
        System.out.println("-------------");
    }
}
