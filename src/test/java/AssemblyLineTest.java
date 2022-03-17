import assemAssist.AssemblyLine;
import assemAssist.workStation.AccessoriesPost;
import assemAssist.workStation.CarBodyPost;
import assemAssist.workStation.DrivetrainPost;
import assemAssist.workStation.WorkStation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssemblyLineTest {
    static AssemblyLine assemblyLine;
    static CarBodyPost carBodyPost;
    static DrivetrainPost drivetrainPost;
    static AccessoriesPost accessoriesPost;
    @BeforeEach
    void setUp() {
        carBodyPost = new CarBodyPost();
        drivetrainPost = new DrivetrainPost();
        accessoriesPost = new AccessoriesPost();
        ArrayList<WorkStation> workStations = new ArrayList<>();
        workStations.add(carBodyPost);
        workStations.add(drivetrainPost);
        workStations.add(accessoriesPost);
        assemblyLine = new AssemblyLine(workStations);


    }

    @Test
    void getWorkStations() {
    }

    @Test
    void setWorkStations() {
    }

    @Test
    void getMaxWorkingHoursToday() {
    }

    @Test
    void setMaxWorkingHoursToday() {
    }

    @Test
    void getHoursWorkedToday() {
    }

    @Test
    void setHoursWorkedToday() {
    }

    @Test
    void nextDay() {
    }

    @Test
    void getCurrentState() {
    }

    @Test
    void remainWorkingTime() {
    }

    @Test
    void canMove() {
    }

    @Test
    void canNotMove() {
    }

    @Test
    void move() {
    }
}