package ui;

import java.util.Scanner;

public class UI {
    public UI(GarageHolderUI garageHolderUI, ManagerUI managerUI, MechanicUI mechanicUI){
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to AssemAssist.");
        while (true){
            System.out.println("You can give here the number corresponding with the " +
                    "option you are: 1 Car Mechanic, 2 Garage Holder or 3 Manager?");
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
