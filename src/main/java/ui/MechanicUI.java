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
        System.out.println("Press 'w' to select a workstation, press 'o' to get an overview of the assembly line.");
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
        System.out.println("-------------");
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
                    List<String> pendingTasks = mechanicController.selectWorkStation(workStations.get(workStationNumber));
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
        for (String status : currentStatus) {
            String[] workstationStatus = status.split(" ; ");

            System.out.println(workstationStatus[0]);
            System.out.print("-> ");
            if (workstationStatus.length >= 2) {
                System.out.println(workstationStatus[1]);
            } else
                System.out.println("No order at this work station.");
        }
        System.out.println("-------------");
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
                    System.out.println("Select the task you want to perform. Press 's' to stop performing tasks.");
                    String task = in.next();
                    if (task.equals("s")) {
                        return;
                    } else {
                        try {
                            int taskNumber = Integer.parseInt(task);
                            if (taskNumber >= pendingTasks.size()) {
                                System.out.println("This is not a valid option. Try again.");
                            } else {
                                pendingTasks = performSpecificTaskUI(pendingTasks.get(taskNumber));
                                break;
                            }
                        } catch (Exception e) {
                            if (e instanceof NumberFormatException) {
                                System.out.println("This is not a valid option. Try again.");
                            } else if (e instanceof IllegalArgumentException) {
                                System.out.println("Something unexpected went wrong. Try again.");
                                break;
                            }
                        }
                    }
                }
                if (pendingTasks.isEmpty()) {
                    System.out.println("There are no pending tasks anymore");
                }
            }
        }
    }

    private List<String> performSpecificTaskUI(String taskName) throws IllegalArgumentException{

        String taskDefinition;
        try {
            taskDefinition = mechanicController.selectTask(taskName);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        System.out.println("This task consists of: " + taskDefinition);
        List<String> pendingTasks;

        while (true) {
            System.out.println("Press 'd' to indicate the task is finished.");
            String finished = in.next();
            if (finished.equals("d")) {
                System.out.println("Enter the time you spent on this task in minutes:");
                while (true) {
                    String timeSpent = in.next();
                    try {
                        int timeSpentNumber = Integer.parseInt(timeSpent);
                        pendingTasks = mechanicController.finishTask(taskName, timeSpentNumber);
                        break;
                    } catch (Exception e) {
                        if (e instanceof NumberFormatException) {
                            System.out.println("This is not a valid time. Try again.");
                        } else if (e instanceof IllegalArgumentException) {
                            System.out.println(e.getMessage() + " Try again.");
                        }
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
