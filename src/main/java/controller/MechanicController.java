package controller;

import java.util.ArrayList;
import java.util.List;

public class MechanicController {

    public MechanicController() {}

    public List<String> getAllWorkPosts() {
        List<String> workPosts = new ArrayList<>();
        workPosts.add("Car Body Post");
        workPosts.add("Drivetrain Post");
        workPosts.add("Accessories Post");

        return workPosts;
    }

    public List<String> selectWorkPost(String workPost) {
        List<String> pendingTasks = new ArrayList<>();
        if (workPost.equals("Car Body Post")) {
            pendingTasks.add("Assembly Car Body");
        } else if (workPost.equals("Drivetrain Post")) {
            pendingTasks.add("Insert Engine");
        } else if (workPost.equals("Accessories Post")) {
            pendingTasks.add("Install Seats");
            pendingTasks.add("Install Airco");
        }
        return pendingTasks;
    }

    public String selectTask (String task) {
        String taskDefinition = "Fix the task please";
        return taskDefinition;
    }

    public List<String> finishTask(String task) {
        List<String> pendingTasks = new ArrayList<>();
        if (task.equals("Install Seats")) {
            pendingTasks.add("Install Airco");
        } else if (task.equals("Install Airco")) {
            pendingTasks.add("Install Seats");
        }
        return pendingTasks;
    }
}
