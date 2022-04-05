package ui;

import controller.ManagerController;

import java.util.List;
import java.util.Scanner;

public class ManagerUI {
    private ManagerController managerController;
    private Scanner in;

    public ManagerUI(ManagerController managerController) {
        this.managerController = managerController;
    }

    public void startUI(Scanner in) {
        this.in = in;
        /* List<List<String>> currentAndFutureStatus = managerController.newLogin();
        List<String> currentStatus = currentAndFutureStatus.get(0);
        List<String> futureStatus = currentAndFutureStatus.get(1);
        System.out.println("-------------");
        System.out.println("The current status of the assembly line is:");
        for (int i = 0; i < currentStatus.size(); i++) {
            String[] workstationStatus = currentStatus.get(i).split(" ; ");
            System.out.println(workstationStatus[0]);
            if (workstationStatus.length >= 3) {
                System.out.println("Model options:");
                String[] modelOptions = workstationStatus[1].split(", ");
                for (int j = 0; j < modelOptions.length; j++) {
                    System.out.println(modelOptions[j]);
                }

            }
            else
                System.out.println("No order at this work station.");
        }
        System.out.println("-------------");
        System.out.println("The future status of the assembly line is:");
        for (int i = 0; i < futureStatus.size(); i++) {
            String[] workstationStatus = futureStatus.get(i).split(" ; ");
            System.out.println(workstationStatus[0]);

            System.out.println("Model options:");
            String[] modelOptions = workstationStatus[1].split(", ");
            for (int j = 0; j < modelOptions.length; j++) {
                System.out.println(modelOptions[j]);
            }

        }

        List<String> statusAfterAdvance;
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
            }
        }
        */

        while (true) {
            System.out.println("Press \'s\' to check the production statistics, press \'a\' to change the scheduling algorithm.");
            String choice = in.next();
            if (choice.equals("s")) {
                statisticsUI();
                break;
            } else if (choice.equals("a")) {
                algorithmUI();
                break;
            } else {
                System.out.println("This is not a valid option. Try again.");
            }
        }

        System.out.println("-------------");
        System.out.println("Leaving the manager view.");
        System.out.println("-------------");

    }

    private void statisticsUI(){
        List<String> statistics = managerController.checkStatistics();
        for (String statistic : statistics) {
            System.out.println(statistic);
        }
    }

    private void algorithmUI() {
        List<String> schedulingAlgorithms = managerController.adaptSchedulingAlgorithm();
        System.out.println("The available scheduling algorithms are:");
        for (int i = 0; i < schedulingAlgorithms.size(); i++) {
            System.out.println(i + ". " + schedulingAlgorithms.get(i));
        }

        System.out.println("Select your chosen scheduling algorithm, by selecting a number. Press \'c\' to cancel.");
        while (true) {
            String chosenAlgorithm = in.next();
            if (chosenAlgorithm.equals("c")) {
                break;
            } else {
                try {
                    int chosenAlgorithmNumber = Integer.parseInt(chosenAlgorithm);
                    if (chosenAlgorithmNumber >= schedulingAlgorithms.size()) {
                        System.out.println("This is not a valid option. Try again.");
                    } else {
                        List<String> notification = managerController.selectSchedulingAlgorithm(schedulingAlgorithms.get(chosenAlgorithmNumber));
                        if (notification == null) {
                            System.out.println("The scheduling algorithm has been changed to " + schedulingAlgorithms.get(chosenAlgorithmNumber));
                        } else {
                            specificationBatchUI(notification);
                        }
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("This is not a valid option. Try again.");
                }
            }
        }
    }

    private void specificationBatchUI(List<String> batches) {
        System.out.println("The batches you can select are:");
        for (int i = 0; i < batches.size(); i++) {
            System.out.println(i + ". " + batches.get(i));
        }

        System.out.println("Select the set to batch process by typing the corresponding number.");
        while (true) {
            String chosenBatch = in.next();
            try {
                int chosenBatchNumber = Integer.parseInt(chosenBatch);
                if (chosenBatchNumber >= batches.size()) {
                    System.out.println("This is not a valid option. Try again.");
                } else {
                    managerController.selectBatchSet(batches.get(chosenBatchNumber));
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("This is not a valid option. Try again.");
            }
        }
    }
}
