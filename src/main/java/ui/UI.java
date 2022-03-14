package ui;

import java.util.Scanner;

public class UI {
    public UI(){
        Scanner in = new Scanner(System.in);
        int option;

        System.out.println("Welcome to AssemAssist.");
        while (true){
            System.out.println("You can give here the number corresponding with the " +
                    "option you are: 1 Car Mechanic, 2 Garage Holder or 3 Manager?");
            option = Integer.parseInt(in.next());
            if (option == 1)
                carMechanic();
            else if (option == 2)
                garageHolder();
            else if (option == 3)
                manager();
            else
                System.out.println("The option you choose was not valid, please try again.");

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
