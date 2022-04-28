package ui;

import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.exceptions.IllegalModelException;

import java.util.Scanner;

public class UI {
    public UI(GarageHolderUI garageHolderUI, ManagerUI managerUI, MechanicUI mechanicUI) throws IllegalModelException, IllegalCompletionDateException {
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to AssemAssist.");
        while (true){
            System.out.println("Please enter the number corresponding with your role:" +
                    "1 Car Mechanic, 2 Garage Holder or 3 Manager?");
            String option = in.next();
            switch (option) {
                case "1" -> mechanicUI.startUI(in);
                case "2" -> garageHolderUI.startUI(in);
                case "3" -> managerUI.startUI(in);
                default -> System.out.println("The option you chose was not valid, please try again.");
            }

        }
    }


}
