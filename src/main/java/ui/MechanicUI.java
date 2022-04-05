package ui;

import controller.MechanicController;

import java.util.List;
import java.util.Scanner;

public class MechanicUI {

    private MechanicController mechanicController;
    private Scanner in;

    public MechanicUI(MechanicController mechanicController){
        this.mechanicController = mechanicController;
    }

    public void startUI(Scanner in) {
        this.in = in;
        System.out.println("-------------");
        System.out.println("Press \'w\' to select a workstation, press \'o\' to get an overview of the assembly line.");
        while (true) {
            String selection = in.next();
            if (selection.equals("w")) {
                workStationUI();
                break;
            } else if (selection.equals("o")) {
                overviewUI();
                break;
            } else {
                System.out.println("This is not a valid option. Try again.");
            }
        }

        System.out.println("Leaving the Car Mechanic View.");
    }

    private void workStationUI() {
        List<String> workStations = mechanicController.getAllWorkStations();
        System.out.println("The work stations at this assembly line are:");
        for (int i = 0; i < workStations.size(); i++) {
            System.out.println(i + ". " + workStations.get(i));
        }
        while (true) {
            System.out.println("Which work station do you want to work at?");
            String workStation = in.next();
            try {
                int workStationNumber = Integer.parseInt(workStation);
                if (workStationNumber >= workStations.size()) {
                    System.out.println("This is not a valid option. Try again.");
                } else {
                    List<String> pendingTasks = mechanicController.selectWorkStation(workStation);
                    performTasksUI(pendingTasks);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("This is not a valid option. Try again.");
            }
        }

    }

    private void overviewUI() {
        List<String> currentStatus = mechanicController.getCurrentStateAssembly();
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

    }

    private void performTasksUI(List<String> pendingTasks) {
        if (pendingTasks.isEmpty()) {
            System.out.println("There are no pending tasks anymore");
        } else {
            while (!pendingTasks.isEmpty()) {
                System.out.println("The pending tasks are:");
                for (int i = 0; i < pendingTasks.size(); i++) {
                    System.out.println(i + ". " + pendingTasks.get(i));
                }

                while (true) {
                    System.out.println("Select the task you want to perform. Press \'s\' to stop performing tasks.");
                    String task = in.next();
                    if (task.equals("s")) {
                        return;
                    } else {
                        try {
                            int taskNumber = Integer.parseInt(task);
                            if (taskNumber >= pendingTasks.size()) {
                                System.out.println("This is not a valid option. Try again.");
                            } else {
                                performSpecificTaskUI(pendingTasks.get(taskNumber));
                                break;
                            }
                        } catch (NumberFormatException e) {
                           System.out.println("This is not a valid option. Try again.");
                        }
                    }
                }
                if (pendingTasks.isEmpty()) {
                    System.out.println("There are no pending tasks anymore");
                }
            }
        }
    }

    private List<String> performSpecificTaskUI(String taskName) {

        String taskDefinition = mechanicController.selectTask(taskName);
        System.out.println("This task consists of: " + taskDefinition);
        List<String> pendingTasks;

        while (true) {
            System.out.println("Press \'d\' to indicate the task is finished.");
            String finished = in.next();
            if (finished.equals("d")) {
                System.out.println("Enter the time you spent on this task in minutes:");
                while (true) {
                    String timeSpent = in.next();
                    try {
                        int timeSpentNumber = Integer.parseInt(timeSpent);
                        pendingTasks = mechanicController.finishTask(taskName, timeSpentNumber);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("This is not a valid option. Try again.");
                    }
                }
                break;
            } else {
                System.out.println("This is not a valid option. Try again.");
            }
        }
        return pendingTasks;
    }
}
