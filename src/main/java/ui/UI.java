package ui;

import java.util.Scanner;

public class UI {
    public UI(GarageHolderUI garageHolderUI){
    //public UI(GarageHolderUI garageHolderUI, ManagerUI managerUI, MechanicUI mechanicUI){
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to AssemAssist.");
        while (true){
            System.out.println("You can give here the number corresponding with the " +
                    "option you are: 1 Car Mechanic, 2 Garage Holder or 3 Manager?");
            String option = in.next();
            if (option.equals("1"))
                carMechanic();
            else if (option.equals("2"))
                garageHolderUI.startUI(in);
            else if (option.equals("3"))
                manager();
            else
                System.out.println("The option you chose was not valid, please try again.");

        }
    }
    private void carMechanic(){
        // hier bepaalde routine van de controller oproepen en bepaalde vragen stellen aan de user
        System.out.println("You are logged out.");

    }

    private void garageHolder(){
        // hier bepaalde routine van de controller oproepen en bepaalde vragen stellen aan de user
        System.out.println("You are logged out.");

    }

    private void manager(){
        // hier bepaalde routine van de controller oproepen en bepaalde vragen stellen aan de user
        System.out.println("You are logged out.");

    }
}
