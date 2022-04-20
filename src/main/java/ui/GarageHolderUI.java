package ui;

import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.exceptions.IllegalModelException;
import controller.GarageHolderController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GarageHolderUI {
    private final GarageHolderController garageHolderController;
    private Scanner in;

    public GarageHolderUI(GarageHolderController garageHolderController) {
        this.garageHolderController = garageHolderController;
    }

    /**
     * Starts the UI for the Garage Holder, once the user selected that they are a garage holder. It asks the name
     * of the user and gives its pending and finished orders. Then it asks whether the user wants to order a new car,
     * check the details of a specific order or leave the overview and starts the relevant sub-UI-method.
     * @param in Scanner used to ask input from the user. Gets initialised for the GarageHolderUI class.
     * @throws IllegalModelException
     * @throws IllegalChoiceException
     * @throws IllegalCompletionDateException
     */
    public void startUI(Scanner in) throws IllegalModelException, IllegalChoiceException, IllegalCompletionDateException {
        this.in = in;
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

        while (true) {
            System.out.println("Press \'n\' to order a new car, press \'d\' to check the details of an order press \'c\' to leave the overview");
            char nextstep = in.next().charAt(0);
            if (nextstep == 'c') {
                break;
            } else if (nextstep == 'n') {
                orderCarUI();
                break;
            } else if (nextstep == 'd'){
                checkDetailsUI(name);
                break;
            } else {
                System.out.println("This is not a valid option. Try again.");
            }
        }

        System.out.println("-------------");
        System.out.println("Leaving the Garage Holder View");
        System.out.println("-------------");
    }

    /**
     * Handles the case where the user wants to check the details of a certain order. It asks for the ID and returns the
     * details if possible.
     * @param name Name of the user (that is a garage holder).
     */
    private void checkDetailsUI(String name){
        while (true) {
            System.out.println("Give the ID of the car order you want to check the details of");
            String carIDString = in.next();
            try{
                int carID = Integer.parseInt(carIDString);
                try{
                    List<String> details = garageHolderController.viewDetails(carID, name);
                    for (String detail : details) {
                        System.out.println(detail);
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("This is not a valid ID. Try again");
                }
            } catch (NumberFormatException e) {
                System.out.println("This is not a valid ID. Try again");
            }
        }
    }

    /**
     * Handles the case when the user wants order a new car. Once they selected a model, the ordering form is completed
     * by a submethod for the UI.
     * @throws IllegalModelException
     * @throws IllegalChoiceException
     * @throws IllegalCompletionDateException
     */
    private void orderCarUI() throws IllegalModelException, IllegalChoiceException, IllegalCompletionDateException {
        List<String> availableModels;

        System.out.println("The available car models are:");
        availableModels = garageHolderController.wantsToOrder();
        for (String availableModel : availableModels) {
            System.out.println(availableModel);
        }

        System.out.println("-------------");

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
                completeOrderingFormUI(model);
                break;
            } else {
                System.out.println("This is not a valid model. Try again.");
            }
        }

    }

    /**
     * This method shows the available options for the chosen car model and then asks the user whether they want to select
     * the options. This is then handled by a specific subroutine.
     * @param model
     * @throws IllegalModelException
     * @throws IllegalChoiceException
     * @throws IllegalCompletionDateException
     */
    private void completeOrderingFormUI(String model) throws IllegalModelException, IllegalChoiceException, IllegalCompletionDateException {
        List<String> orderingForm = garageHolderController.selectModel(model);
        List<String[]> orderingFormArrays = new ArrayList<>();
        for (String s : orderingForm) {
            String[] optionsAndName = s.split(": ");
            String optionName = optionsAndName[0];
            String[] availableOptions = optionsAndName[1].split(", ");
            List<String> availableOptionsList = new ArrayList<>();
            availableOptionsList.add(optionName);
            for (String option : availableOptions) {
                availableOptionsList.add(option);
            }
            String[] optionsAndNameArray = new String[availableOptionsList.size()];
            optionsAndNameArray = availableOptionsList.toArray(optionsAndNameArray);
            orderingFormArrays.add(optionsAndNameArray);
        }
        System.out.println("The available options for this model are:");
        for (String[] option : orderingFormArrays){
            String toPrint = option[0] + ": ";
            for (int i = 1; i<option.length; i++){
                toPrint += i + ". " + option[i];
                if (i != option.length-1) {
                    toPrint += ", ";
                }
            }
            System.out.println(toPrint);
        }
        while(true) {
            System.out.println("-------------");
            System.out.println("Press \'q\' to cancel ordering, press \'s\' to select the options for your car order.");
            String choice = in.next();
            if (choice.equals("q")) {
                System.out.println("Canceling your order.");
                break;
            } else if (choice.equals("s")) {
                selectOptionsUI(orderingFormArrays);
                break;
            } else {
                System.out.println("This is not a valid option. Try again.");
            }
        }
    }

    /**
     * Lets the user select the options specified in the ordering form for the car model selected. Once the options are
     * selected, this car is ordered in the system.
     * @param orderingForm The ordering form for the car model that was selected. This gives all the options one can choose
     *                     for the car model.
     * @throws IllegalModelException
     * @throws IllegalChoiceException
     * @throws IllegalCompletionDateException
     */
    private void selectOptionsUI(List<String[]> orderingForm) throws IllegalModelException, IllegalChoiceException, IllegalCompletionDateException {
        System.out.println("Select the options for you car order:");
        List<String> carOptions = new ArrayList<>();
        for (String[] option : orderingForm) {
            while (true) {
                System.out.println("Type the number of your choice for the " + option[0].toLowerCase() + ":");
                String chosenOptionString = in.next();
                try {
                    int chosenOption = Integer.parseInt(chosenOptionString);
                    if (chosenOption >= option.length) {
                        System.out.println("This is not a valid option. Try again.");
                    } else {
                        carOptions.add(option[chosenOption]);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("This is not a valid option. Try again.");
                }
            }
        }

        String estimatedCompletionDate = garageHolderController.completeOrderingForm(carOptions);
        System.out.println("You have successfully ordered a car! The estimated completion date is " + estimatedCompletionDate);
    }
}
