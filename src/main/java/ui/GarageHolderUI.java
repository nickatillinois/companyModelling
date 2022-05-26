package ui;

import assemAssist.exceptions.*;
import controller.GarageHolderController;
import java.util.*;

/**
 * The UI for the GarageHolder.
 * @author Team 10
 */
public class GarageHolderUI {

    /**
     * The controller for the GarageHolder. This is used to interact with the
     * GarageHolder.
     */
    private final GarageHolderController garageHolderController;

    /**
     * The scanner used to read user input.
     */
    private Scanner in;

    /**
     * Constructor for the GarageHolderUI.
     *
     * @param garageHolderController The controller for the GarageHolder.
     *                               This is used to interact with the GarageHolder.
     *                               Gets initialised for the GarageHolderUI class.
     * @throws IllegalArgumentException if the controller is null.
     */
    public GarageHolderUI(GarageHolderController garageHolderController) {
        if (garageHolderController == null) {
            throw new IllegalArgumentException("GarageHolderController cannot be null");
        }
        this.garageHolderController = garageHolderController;
    }

    /**
     * Starts the UI for the Garage Holder, once the user selected that they are a garage holder. It asks the name
     * of the user and gives its pending and finished orders. Then it asks whether the user wants to order a new car,
     * check the details of a specific order or leave the overview and starts the relevant sub-UI-method.
     *
     * @param in Scanner used to ask input from the user. Gets initialised for the GarageHolderUI class.
     * @throws IllegalModelException          | if the chosen model is not offered by the company.
     * @throws IllegalCompletionDateException | if a completion date is given that is before the order date.
     */
    public void startUI(Scanner in) throws IllegalModelException, IllegalCompletionDateException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        this.in = in;
        System.out.println("Enter your first name and last name, e.g. 'Tom Smets'");
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
            System.out.println(" | " + "ID: " + order[0] + " | completion date: " + order[1] + " | ");
        }
        System.out.println("-------------");
        while (true) {
            System.out.println("""
                    Press the letter that matches your request:\s
                    \tn. order a new car;\s
                    \td. check order details;\s
                    \tc. leave the overview.""");
            String nextstep = "";
            try {
                nextstep = in.next();}
            catch (Exception e) {
                break;
                //System.out.println("The option you chose was not valid, please try again.");
                //throw e;
            }
                if (Objects.equals(nextstep, "c")) {
                    break;
                } else if (Objects.equals(nextstep, "n")) {
                    orderCarUI();
                } else if (Objects.equals(nextstep, "d")) {
                    checkDetailsUI(name);
                } else {
                    System.out.println("The option you chose was not valid, please try again.");
                }
        }

        System.out.println("-------------");
        System.out.println("Leaving the Garage Holder View");
        System.out.println("-------------");
    }

    /**
     * Handles the case where the user wants to check the details of a certain order. It asks for the ID and returns the
     * details if possible.
     *
     * @param name Name of the user (that is a garage holder).
     */
    private void checkDetailsUI(String name) {
        while (true) {
            System.out.println("Give the ID of the car order you want to check the details of");
            String carIDString = in.next();
            try {
                int carID = Integer.parseInt(carIDString);
                try {
                    List<String> details = garageHolderController.viewDetails(carID, name);
                    // Prints the details of the order in a nice way.
                    System.out.println("-------------");
                    System.out.println(" | " + details.get(0) + " | ");
                    System.out.println(" | " + details.get(1) + " | ");
                    System.out.println(" | " + details.get(2) + " | ");
                } catch (Exception e) {
                    if (e instanceof OrderNotFoundException) {
                        System.out.println("We could not find an order with this ID.");
                    } else if (e instanceof IllegalArgumentException) {
                        System.out.println("This is not a valid ID.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("This is not a valid ID. Try again");
            }
            // Asks the user if he wants to check the details of another order.
            System.out.println("Do you want to check the details of another order? (y/n)");
            char nextstep = in.next().charAt(0);
            // loop until the user enters a valid input.
            while (nextstep != 'y' && nextstep != 'n') {
                System.out.println("This is not a valid option. Try again.");
                nextstep = in.next().charAt(0);
            }
            if (nextstep == 'n') {
                break;
            }
        }
    }

    /**
     * Handles the case when the user wants order a new car. Once they selected a model, the ordering form is completed
     * by a sub method for the UI.
     *
     * @throws IllegalModelException          | if the chosen model is not offered by the company.
     * @throws IllegalCompletionDateException | if a completion date is given that is before the order date.
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
            for (String modelName : availableModels) {
                if (modelName.equalsIgnoreCase(model)) {
                    model = model.toUpperCase(Locale.ROOT);
                    contains = true;
                    break;
                }
            }
            if (contains) {
                completeOrderingFormUI(model, garageHolderController.getWorkingMinutesWorkingStation(model));
                break;
            } else {
                System.out.println("This is not a valid model. Try again.");
            }
        }

    }

    /**
     * This method shows the available options for the chosen car model and then asks the user whether they want to select
     * the options. This is then handled by a specific subroutine.
     *
     * @param model | The chosen car model.
     * @throws IllegalModelException          | if the chosen model is not offered by the company.
     * @throws IllegalCompletionDateException | if a completion date is given that is before the order date.
     */
    private void completeOrderingFormUI(String model, int workingMinutesWorkstation) throws IllegalModelException, IllegalCompletionDateException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        TreeMap<String, HashSet<String>> orderingForm = garageHolderController.selectModel(model);
        TreeMap<String, String[]> orderingFormList = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (String option : orderingForm.keySet()) {
            String[] options = orderingForm.get(option).toArray(new String[0]);
            orderingFormList.put(option, options);
        }
        System.out.println("The available options for this model are:");
        TreeMap<String, String> selectedOptions = new TreeMap<>();
        int i;
        in.nextLine();
        for (String option : orderingFormList.keySet()) {
            System.out.println(option + ":");
            i = 0;
            System.out.println("Press 'x' to skip this component, press 's' to select it, or press 'q' to cancel");
            String choice = in.next();
            if (choice.equalsIgnoreCase("s")) {
                for (String value : orderingFormList.get(option)) {
                    System.out.println("\t" + i + ": " + value);
                    i++;
                }
                System.out.println("Enter the number of the option you want to select:");
                while (true) {
                    int optionNumber;
                    try {
                        optionNumber = in.nextInt();
                        if (optionNumber >= 0 && optionNumber < orderingFormList.get(option).length) {
                            String optionValue = orderingFormList.get(option)[optionNumber];
                            selectedOptions.put(option.toLowerCase(), optionValue.toLowerCase());
                            break;
                        } else {
                            System.out.println("This is not a valid option number.\n  Enter the number of the option you want to select: ");

                        }
                    } catch (InputMismatchException e) {
                        System.out.println("This is not a valid option number.\n  Enter the number of the option you want to select: ");
                        in.nextLine();
                    }
                }
            } else if (choice.equalsIgnoreCase("q")) {
                System.out.println("Cancelling order");
                return;
            } else {
                System.out.println("This is not a valid option.");
            }
        }
        try {
            String estimatedCompletionDate = garageHolderController.completeOrderingForm(selectedOptions);
            System.out.println("You have successfully ordered a car! The estimated completion date is " + estimatedCompletionDate);
        } catch (IllegalModelException | OptionAThenOptionBException | OptionThenComponentException | RequiredComponentException e) {
            System.out.println(e.getMessage());
            System.out.println("No order was placed.");
        }
        System.out.println("Press 'q' to cancel ordering, press 's' to place a new order.");
        String nextstep = "";
        try {
            nextstep = in.next();
        } catch (Exception e) {
            //System.out.println("No order was placed.");
        }
        if (nextstep.equals("q")) {
            System.out.println("-------------");
            System.out.println("Leaving the Ordering Form");
            System.out.println("-------------");
        } else if (nextstep.equals("s")) {
            System.out.println("-------------");
            System.out.println("Ordering a new car");
            System.out.println("-------------");
            orderCarUI();
        }
    }
}
