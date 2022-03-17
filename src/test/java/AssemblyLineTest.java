import assemAssist.AssemblyLine;
import assemAssist.AssemblyTask;
import assemAssist.carOrder.*;
import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalModelException;
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
    static CarOrder orderA;
    static CarOrder orderB;

    @BeforeEach
    void setUp() throws IllegalChoiceException, IllegalModelException {
        carBodyPost = new CarBodyPost();
        drivetrainPost = new DrivetrainPost();
        accessoriesPost = new AccessoriesPost();
        ArrayList<WorkStation> workStations = new ArrayList<>();
        workStations.add(carBodyPost);
        workStations.add(drivetrainPost);
        workStations.add(accessoriesPost);
        assemblyLine = new AssemblyLine(workStations);
        CarModel.addModel("Jaguar");
        Body body = new Body("sedan");
        CarModelSpecification specification = new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        orderA = new CarOrder("A",new CarModel("Jaguar", specification));
        orderB = new CarOrder("B",new CarModel("Jaguar", specification));


    }

    @Test
    void getWorkStations() {
        ArrayList<WorkStation> workStationstest = new ArrayList<>();
        workStationstest.add(carBodyPost);
        workStationstest.add(drivetrainPost);
        workStationstest.add(accessoriesPost);
        assertEquals(workStationstest,assemblyLine.getWorkStations());
    }

    @Test
    void nextDay() {
        assemblyLine.nextDay();
        assertEquals(0,assemblyLine.getHoursWorkedToday());
        assemblyLine.move(orderA,2);
        assertEquals(2,assemblyLine.getHoursWorkedToday());
        assemblyLine.move(null, 16);
        assertEquals(18,assemblyLine.getHoursWorkedToday());
        assemblyLine.move(null, 2);
        assertEquals(20, assemblyLine.getHoursWorkedToday());
        assemblyLine.move(null,2);
        assertEquals(22, assemblyLine.getHoursWorkedToday());
        assemblyLine.nextDay();
        assertEquals((22-6)-(22-(22-6)),assemblyLine.remainWorkingTime());
    }

    @Test
    void getCurrentState() {
        ArrayList<CarOrder> test = new ArrayList<>();
        test.add(null);
        test.add(null);
        test.add(null);
        assertEquals(test, assemblyLine.getCurrentState());
        ArrayList<CarOrder> test2 = new ArrayList<>();
        test2.add(orderA);
        test2.add(null);
        test2.add(null);
        assemblyLine.getWorkStations().get(0).setCurrentOrder(orderA);
        assertEquals(test2,assemblyLine.getCurrentState());

    }


    @Test
    void canMove() {
        assemblyLine.getWorkStations().get(0).setCurrentOrder(orderA);
        assert(!assemblyLine.canMove());
        for(AssemblyTask assemblyTask : assemblyLine.getWorkStations().get(0).getTasks())
            assemblyTask.setIsCompleted(true);
        assert(assemblyLine.canMove());
    }

    @Test
    void canNotMove() {
        assemblyLine.getWorkStations().get(0).setCurrentOrder(orderA);
        List<CarOrder> cannotmove = new ArrayList<>();
        cannotmove.add(orderA);
        assertEquals(cannotmove,assemblyLine.canNotMove());
        assemblyLine.getWorkStations().get(1).setCurrentOrder(orderA);
        assemblyLine.getWorkStations().get(0).setCurrentOrder(orderB);
        for(AssemblyTask assemblyTask : assemblyLine.getWorkStations().get(0).getTasks())
            assemblyTask.setIsCompleted(true);
        assertEquals(cannotmove,assemblyLine.canNotMove());
    }

    @Test
    void move() {
        assemblyLine.getWorkStations().get(1).setCurrentOrder(orderA);
        assemblyLine.getWorkStations().get(0).setCurrentOrder(orderB);
        for(AssemblyTask assemblyTask : assemblyLine.getWorkStations().get(0).getTasks())
            assemblyTask.setIsCompleted(true);
        for(AssemblyTask assemblyTask : assemblyLine.getWorkStations().get(1).getTasks())
            assemblyTask.setIsCompleted(true);
        assemblyLine.move(null, 1);
        assertEquals(22-6-1,assemblyLine.remainWorkingTime());
        assertEquals(orderA, assemblyLine.getWorkStations().get(2).getCurrentOrder());
        for(AssemblyTask assemblyTask : assemblyLine.getWorkStations().get(1).getTasks())
            assemblyTask.setIsCompleted(true);
        for(AssemblyTask assemblyTask : assemblyLine.getWorkStations().get(2).getTasks())
            assemblyTask.setIsCompleted(true);
        assemblyLine.move(null,1);
        assertEquals(22-6-2,assemblyLine.remainWorkingTime());
        assertEquals(orderB, assemblyLine.getWorkStations().get(2).getCurrentOrder());
        assert(orderA.isCompleted());

    }
}