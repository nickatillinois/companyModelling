package classTests;

import assemAssist.AssemblyLine;
import assemAssist.AssemblyTask;
import assemAssist.CarOrder;
import assemAssist.ProductionScheduler;
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
    static CarOrder orderA;
    static CarOrder orderB;

    @BeforeEach
    void setUp() throws IllegalChoiceException, IllegalModelException {
        assemblyLine = new AssemblyLine();
       /* ProductionScheduler.addModel("Jaguar");*/


    }

    @Test
    void getWorkStations() {
        ArrayList<WorkStation> workStationstest = new ArrayList<>();
        workStationstest.add(new CarBodyPost());
        workStationstest.add(new DrivetrainPost());
        workStationstest.add(new AccessoriesPost());
        assertEquals(workStationstest,assemblyLine.getWorkStations());
    }

    @Test
    void nextDay() {
        assemblyLine.nextDay();
        assertEquals(0,assemblyLine.getMinutesWorkedToday());
        assemblyLine.move(orderA,120);
        assertEquals(120,assemblyLine.getMinutesWorkedToday());
        for(AssemblyTask assemblyTask : assemblyLine.getWorkStations().get(0).getTasks())
            assemblyTask.setIsCompleted(true);
        assemblyLine.move(null, 16*60);
        assertEquals(18*60,assemblyLine.getMinutesWorkedToday());
        for(AssemblyTask assemblyTask : assemblyLine.getWorkStations().get(1).getTasks())
            assemblyTask.setIsCompleted(true);
        assemblyLine.move(null, 2*60);
        assertEquals(20*60, assemblyLine.getMinutesWorkedToday());
        for(AssemblyTask assemblyTask : assemblyLine.getWorkStations().get(2).getTasks())
            assemblyTask.setIsCompleted(true);
        assemblyLine.move(null,2*60);
        assertEquals(22*60, assemblyLine.getMinutesWorkedToday());
        assemblyLine.nextDay();
        assertEquals(((22-6)-(22-(22-6))*60),assemblyLine.remainWorkingTime());
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
        List<String> cannotmove = new ArrayList<>();
        cannotmove.add("Blocked");
        cannotmove.add("Car Body Post");
        assertEquals(cannotmove,assemblyLine.canNotMove());
        assemblyLine.getWorkStations().get(1).setCurrentOrder(orderA);
        assemblyLine.getWorkStations().get(0).setCurrentOrder(orderB);
        for(AssemblyTask assemblyTask : assemblyLine.getWorkStations().get(0).getTasks())
            assemblyTask.setIsCompleted(true);
        List<String> cannotmove2 = new ArrayList<>();
        cannotmove2.add("Blocked");
        cannotmove2.add("Drivetrain Post");
        assertEquals(cannotmove2,assemblyLine.canNotMove());
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