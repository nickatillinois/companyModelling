package ui;

import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.exceptions.IllegalModelException;

import java.util.Scanner;

public class UI {
    public UI(GarageHolderUI garageHolderUI, ManagerUI managerUI, MechanicUI mechanicUI) throws IllegalModelException, IllegalChoiceException, IllegalCompletionDateException {
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to AssemAssist.");
        while (true){
            System.out.println("Please enter the number corresponding with your role:" +
                    "1 Car Mechanic, 2 Garage Holder or 3 Manager?");
            String option = in.next();
            if (option.equals("1"))
                mechanicUI.startUI(in);
            else if (option.equals("2"))
                garageHolderUI.startUI(in);
            else if (option.equals("3"))
                managerUI.startUI(in);
            else
                System.out.println("The option you chose was not valid, please try again.");

        }
    }


}
