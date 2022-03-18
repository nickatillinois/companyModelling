package ui;

import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.exceptions.IllegalModelException;
import controller.GarageHolderController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GarageHolderUI {
    private final GarageHolderController garageHolderController;

    public GarageHolderUI(GarageHolderController garageHolderController) {
        this.garageHolderController = garageHolderController;
    }

    public void startUI(Scanner in) throws IllegalModelException, IllegalChoiceException, IllegalCompletionDateException {
        System.out.println("Enter your first name lastname, e.g. 'Tom Smets'");
        String name = in.next() + " " + in.next();
        List<List<String>> overViewOfOrders = garageHolderController.newLogin(name);
        System.out.println("Your pending orders are:");
        for (String pending: overViewOfOrders.get(0)) {
            System.out.println(pending);
        }
        System.out.println("Your finished orders are:");
        for (String finished: overViewOfOrders.get(1)) {
            System.out.println(finished);
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
                for (String availableModel : availableModels) {
                    System.out.println(availableModel);
                }
                break;
            } else {
                System.out.println("This is not a valid option. Try again.");
            }
        }

        System.out.println("-------------");

        List<String> orderingForm;
        while (true) {
            System.out.println("Enter the car model name you want to order:");
            String model = in.next();
            boolean contains = false;
            for (String modelName: availableModels) {
                if(modelName.equalsIgnoreCase(model)){
                    contains = true;
                    break;
                }
            }
            if (contains) {
                System.out.println("The available options for this model are:");
                orderingForm = garageHolderController.selectModel(model);
                for (String s : orderingForm) {
                    System.out.println(s);
                }
                break;
            } else {
                System.out.println("This is not a valid model. Try again.");
            }
        }
        while(true) {
            System.out.println("-------------");
            System.out.println("Press \'q\' to cancel ordering, press \'s\' to select the options for your car order");
            String choice = in.next();
            if (choice.equals("q")) {
                System.out.println("Canceling your order.");
                break;
            } else if (choice.equals("s")) {
                System.out.println("Select the options for you car order:");
                List<String> carOptions = new ArrayList<>();
                for (String s : orderingForm) {
                    boolean firstTime = false;
                    while (true) {

                        String[] optionsAndName = s.split(": ");
                        String optionName = optionsAndName[0];
                        String[] availableOptions = optionsAndName[1].split(", ");
                        if (!firstTime) {
                            System.out.println("Your choice for the " + optionName.toLowerCase() + " is:");
                        }
                        String optionString = "";
                        String option;
                        boolean loop = true;
                        while (loop) {
                            option = in.nextLine();
                            if (option.equals("")) {
                                break;
                            } else {
                                optionString += option;
                            }
                        }
                        if (Arrays.asList(availableOptions).contains(optionString)) {
                            carOptions.add(optionString);
                            break;
                        } else {
                            System.out.println("This is not a valid option for the " + optionName.toLowerCase() + ". Try again.");
                        }
                    }
                }

                String estimatedCompletionDate = garageHolderController.completeOrderingForm(carOptions);
                System.out.println("You have successfully ordered a car! The estimated completion date is " + estimatedCompletionDate);
                break;
            } else {
                System.out.println("This is not a valid option. Try again.");
            }
        }

        System.out.println("-------------");
        System.out.println("Leaving the Garage Holder View");
        System.out.println("-------------");
    }
}
