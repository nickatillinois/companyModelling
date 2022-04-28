package ui;

import assemAssist.exceptions.*;
import controller.GarageHolderController;

import javax.swing.text.html.Option;
import java.util.*;

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
     * @throws IllegalCompletionDateException
     */
    public void startUI(Scanner in) throws IllegalModelException, IllegalCompletionDateException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        this.in = in;
        System.out.println("Enter your first name lastname, e.g. 'Tom Smets'");
        String name = in.next() + " " + in.next();
        ArrayList<String[]>[] overViewOfOrders = garageHolderController.newLogin(name);
        System.out.println("Your pending orders are:");
        for (String[] order : overViewOfOrders[0]) {
            // Prints the elements of the order in a nice way.
            System.out.println(" | " + "ID: " + order[0] + " | est. completion date: " + order[1] + " | ");
        }

        System.out.println("Your finished orders are:");
        for (String[] order : overViewOfOrders[1]) {
            // Prints the elements of the order in a nice way.
            System.out.println(" | " + "ID: " + order[0] + " | est. completion date: " + order[1] + " | ");
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
     * @throws IllegalCompletionDateException
     */
    private void orderCarUI() throws IllegalModelException, IllegalCompletionDateException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        HashSet<String> availableModels = garageHolderController.wantsToOrder();

        System.out.println("The available car models are:");
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
     * @throws IllegalCompletionDateException
     */
    private void completeOrderingFormUI(String model) throws IllegalModelException, IllegalCompletionDateException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        TreeMap<String, HashSet<String>> orderingForm = garageHolderController.selectModel(model);
        TreeMap<String, String[]> orderingFormList = new TreeMap<String, String[]>(String.CASE_INSENSITIVE_ORDER);
        for (String option : orderingForm.keySet()) {
            String[] options = orderingForm.get(option).toArray(new String[0]);
            orderingFormList.put(option, options);
        }
        System.out.println("The available options for this model are:");
        TreeMap<String, String> selectedOptions = new TreeMap<>();
        int i;
        for (String option : orderingFormList.keySet()) {
            System.out.println(option + ":");
            i = 0;
            System.out.println("Press \'q\' to skip this component, press \'s\' to select it.");
            String choice = in.next();
            if (choice.equalsIgnoreCase("q")) {
                continue;
            } else if (choice.equalsIgnoreCase("s")) {
                for(String value : orderingFormList.get(option)){
                    System.out.println("\t" + i + ": " + value);
                    i++;
                }
                System.out.println("Enter the number of the option you want to select:");
                int optionNumber = in.nextInt();
                if (optionNumber >= 0 && optionNumber < orderingFormList.get(option).length) {
                    String optionValue = orderingFormList.get(option)[optionNumber];
                    selectedOptions.put(option, optionValue);
                }
            }else {
                System.out.println("This is not a valid option.");
            }
        }
        try {
            String estimatedCompletionDate = garageHolderController.completeOrderingForm(selectedOptions);
            System.out.println("You have successfully ordered a car! The estimated completion date is " + estimatedCompletionDate);
        }
        catch (IllegalModelException | OptionAThenOptionBException | OptionThenComponentException | RequiredComponentException e){
            System.out.println(e.getMessage());
            System.out.println("No order was placed.");
        }
        System.out.println("Press \'q\' to cancel ordering, press \'s\' to place a new order.");
        String nextstep = in.next();
        if (nextstep.equals("q")) {
            System.out.println("-------------");
            System.out.println("Leaving the Ordering Form");
            System.out.println("-------------");
            return;
        } else if (nextstep.equals("s")) {
            System.out.println("-------------");
            System.out.println("Ordering a new car");
            System.out.println("-------------");
            orderCarUI();
        }
    }
}
