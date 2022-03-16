package ui;

import controller.MechanicController;

import java.util.List;
import java.util.Scanner;

public class MechanicUI {

    private MechanicController mechanicController;

    public MechanicUI(MechanicController mechanicController){
        this.mechanicController = mechanicController;
    }

    public void startUI(Scanner in) {
        System.out.println("-------------");
        List<String> workPosts = mechanicController.getAllWorkPosts();
        System.out.println("The workposts at this assembly line are:");
        System.out.println(workPosts);
        List<String> pendingTasks;
        while (true) {
            System.out.println("Which workpost do you work at?");
            String workPost = in.nextLine();
            if (workPosts.contains(workPost)) {
                pendingTasks = mechanicController.selectWorkPost(workPost);
                break;
            } else {
                System.out.println("This is not a valid workpost. Try again.");
            }
        }

        if (pendingTasks.isEmpty()) {
            System.out.println("There are no pending tasks anymore");
        } else {
            while (!pendingTasks.isEmpty()) {
                System.out.println("The pending tasks are:");
                for (int i = 0; i < pendingTasks.size(); i++) {
                    System.out.println(pendingTasks.get(i));
                }
                String taskDefinition;
                String task;
                while (true) {
                    System.out.println("Select the task you want to perform. Press \'s\' to stop performing tasks.");
                    task = in.nextLine();
                    if (task.equals("d")) {
                        System.out.println("Leaving the Car Mechanic View.");
                        return;
                    }
                    else if (pendingTasks.contains(task)) {
                        taskDefinition = mechanicController.selectTask(task);
                        break;
                    } else {
                        System.out.println("This is not a valid task. Try again.");
                    }
                }
                System.out.println("This task consists of: " + taskDefinition);

                while (true) {
                    System.out.println("Press \'d\' to indicate the task is finished.");
                    String finished = in.next();
                    if (finished.equals("d")) {
                        pendingTasks = mechanicController.finishTask(task);
                        break;
                    } else {
                        System.out.println("This is not a valid option. Try again.");
                    }
                }
                if (pendingTasks.isEmpty()) {
                    System.out.println("There are no pending tasks anymore");
                }
            }
        }

        System.out.println("Leaving the Car Mechanic View.");
    }
}
