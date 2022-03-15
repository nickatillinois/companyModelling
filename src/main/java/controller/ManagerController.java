package controller;

import java.util.ArrayList;
import java.util.List;

public class ManagerController {

    public ManagerController() {}

    public List<List<String>> newLogin() {
        List currentAndFutureStatus = new ArrayList();

        List<String> currentStatus = new ArrayList();
        currentStatus.add("Car Body Post ; Model: Jaguar, Color: Green, Seats: 4 ; Assembly Car Body: Done, Paint Car: Pending");
        currentStatus.add("Drivetrain Post ; Model: Jeep, Color: Red, Seats: 4 ; Insert Engine: Done, Insert Gearbox: Done");
        currentStatus.add("Accessories Post ; Model: Jeep, Color: Blue, Seats: 6 ; Install Seats: Done, Install Airco: Pending, Mount Wheels: Pending");
        currentAndFutureStatus.add(currentStatus);

        List<String> futureStatus = new ArrayList();
        futureStatus.add("Car Body Post ; Model: Jaguar, Color: Red, Seats: 2");
        futureStatus.add("Drivetrain Post ; Model: Jaguar, Color: Green, Seats: 4");
        futureStatus.add("Accessories Post ; Model: Jeep, Color: Red, Seats: 4");
        currentAndFutureStatus.add(futureStatus);

        return currentAndFutureStatus;
    }

    public List<String> confirmAdvance(int currentPhaseTime) {
        List<String> statusAfterAdvance = new ArrayList();
        statusAfterAdvance.add("Blocked");
        statusAfterAdvance.add("Drivetrain Post");
        statusAfterAdvance.add("Accessories Post");

        return statusAfterAdvance;
    }


}
