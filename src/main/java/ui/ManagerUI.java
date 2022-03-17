package ui;

import controller.ManagerController;

import java.util.List;
import java.util.Scanner;

public class ManagerUI {
    private ManagerController managerController;

    public ManagerUI(ManagerController managerController) {
        this.managerController = managerController;
    }

    public void startUI(Scanner in) {

        List<List<String>> currentAndFutureStatus = managerController.newLogin();
        List<String> currentStatus = currentAndFutureStatus.get(0);
        List<String> futureStatus = currentAndFutureStatus.get(1);
        System.out.println("-------------");
        System.out.println("The current status of the assembly line is:");
        for (int i = 0; i < currentStatus.size(); i++) {
            String[] workstationStatus = currentStatus.get(i).split(" ; ");
            System.out.println(workstationStatus[0].split("\\.")[2].split("@")[0]);
            if (workstationStatus.length >= 3) {
                System.out.println("Model options:");
                String[] modelOptions = workstationStatus[1].split(", ");
                for (int j = 0; j < modelOptions.length; j++) {
                    System.out.println(modelOptions[j]);
                }

                System.out.println("Tasks:");
                String[] tasksStatus = workstationStatus[1].split(", ");
                for (int j = 0; j < tasksStatus.length; j++) {
                    System.out.println(tasksStatus[j]);
                }
            }
            else
                System.out.println("No order at this workpost.");
        }
        System.out.println("-------------");
        System.out.println("The future status of the assembly line is:");
        for (int i = 0; i < futureStatus.size(); i++) {
            String[] workstationStatus = futureStatus.get(i).split(" ; ");
            System.out.println(workstationStatus[0].split("\\.")[2].split("@")[0]);

//            System.out.println("Model options:");
            String[] modelOptions = workstationStatus[1].split(", ");
            for (int j = 0; j < modelOptions.length; j++) {
                System.out.println(modelOptions[j]);
            }

        }

        List<String> statusAfterAdvance;
        while (true) {
            System.out.println("Press \'a\' to advance the assembly line.");
            String confirmation = in.next();
            if (confirmation.equals("a")) {
                while (true) {
                    System.out.println("Enter the time spent during the current phase in hours:");
                    try {
                        int currentPhaseTime = Integer.parseInt(in.next());
                        statusAfterAdvance = managerController.confirmAdvance(currentPhaseTime);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("This is not a valid time. Try again.");
                    }
                }
                break;
            } else {
                System.out.println("This is not a valid option. Try again.");
            }
        }

        if (statusAfterAdvance.get(0).equals("Blocked")) {
            System.out.println("The blocking posts are:");
            for (int i = 1; i < statusAfterAdvance.size(); i++) {
                System.out.println(statusAfterAdvance.get(i));
            }
        } else {
            System.out.println("The new status of the assembly line is:");
            for (int i = 0; i < statusAfterAdvance.size(); i++) {
                String[] workstationStatus = statusAfterAdvance.get(i).split(" ; ");
                System.out.println(workstationStatus[0]);

                System.out.println("Model options:");
                String[] modelOptions = workstationStatus[1].split(", ");
                for (int j = 0; j < modelOptions.length; j++) {
                    System.out.println(modelOptions[j]);
                }

                System.out.println("Tasks:");
                String[] tasksStatus = workstationStatus[1].split(", ");
                for (int j = 0; j < tasksStatus.length; j++) {
                    System.out.println(tasksStatus[j]);
                }
            }
        }

        while (true) {
            System.out.println("Press \'d\' when you are done viewing the status.");
            String doneViewing = in.next();
            if (doneViewing.equals("d")) {
                System.out.println("Leaving the manager view.");
                System.out.println("-------------");
                return;
            } else {
                System.out.println("This is not a valid option. Try again.");
            }
        }
    }
}
