package ui;

import assemAssist.exceptions.*;
import java.util.Scanner;


/**
 * The general UI initially used for all users.
 * It reads the user input and calls the corresponding methods in the corresponding controller.
 * @author Team 10
 */
public class UI {
    public UI(GarageHolderUI garageHolderUI, ManagerUI managerUI, MechanicUI mechanicUI) throws IllegalModelException, IllegalCompletionDateException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        Scanner in = new Scanner(System.in);
        // print welcome message in a nice box
        System.out.println("==============================================================");
        System.out.println("||                 Welcome to AssemAssist.                   ||");
        System.out.println("==============================================================");
        while (true){
            System.out.println("""
                    Please enter the number corresponding with your role:\s
                    \t1. Car Mechanic;\s
                    \t2. Garage Holder;\s
                    \t3. Manager;\s
                    \t4. STOP.""");
            String option = in.next();
            switch (option) {
                case "1" -> mechanicUI.startUI(in);
                case "2" -> garageHolderUI.startUI(in);
                case "3" -> managerUI.startUI(in);
                case "4" -> {System.out.println("Goodbye!");
                            in.close();
                            return;}
                default -> System.out.println("The option you chose was not valid, please try again.");
            }
        }
    }
}