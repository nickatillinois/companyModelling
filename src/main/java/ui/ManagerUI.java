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

        while (true) {
            System.out.println("Press 's' to check the production statistics, press 'a' to change the scheduling algorithm.");
            String choice = in.next();
            if (choice.equals("s")) {
                statisticsUI();
                System.out.println("Press 'd' to indicate you are done viewing the production statistics.");
                if (in.next().equals("d")) {
                    break;
                }
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
        List<String> statistics = managerController.checkStatistics(2);
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

        System.out.println("Select your chosen scheduling algorithm, by selecting the corresponding number. Press 'c' to cancel.");
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
        if (!batches.isEmpty()){
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
        }}
    }
}
