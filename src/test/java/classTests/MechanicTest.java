package classTests;

import assemAssist.*;
import assemAssist.exceptions.*;
import assemAssist.workStation.AccessoriesPost;
import assemAssist.workStation.CarBodyPost;
import assemAssist.workStation.DrivetrainPost;
import assemAssist.workStation.WorkStation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class MechanicTest {

    static Mechanic mechanic;
    static AssemblyLine assemblyLine;

    static CarOrder orderA;
    static CarOrder orderB;
    static CarOrder orderC;

    @BeforeEach
    void init() {
        assemblyLine = new AssemblyLine();
        mechanic = new Mechanic(assemblyLine);


    }

    @Test
    void getAssemblyLineTest() {
        assertEquals(assemblyLine, mechanic.getAssemblyLine());
    }

    @Test
    void getAllWorkStationsTest() {
        List<String> expWorkStations = List.of("Car Body Post", "Drivetrain Post", "Accessories Post");
        assertEquals(expWorkStations, mechanic.getAllWorkStations());
    }

    @Test
    void selectWorkStationTest() { // also tests mechanic.getWorkStation()
        mechanic.selectWorkStation("Drivetrain Post");
        assertEquals(new DrivetrainPost(), mechanic.getWorkStation());
        assertThrows(IllegalArgumentException.class, () -> mechanic.selectWorkStation(null));
        assertThrows(IllegalArgumentException.class, () -> mechanic.selectWorkStation("Motorcycle Post"));
    }

    @Test
    void selectTaskTest() {}

    @Test
    void finishTaskTest() {}

    @Test
    void getCurrentStateAssembly() {}
}
