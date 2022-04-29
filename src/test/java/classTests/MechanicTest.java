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

public class MechanicTest {

    static Mechanic mechanic;
    static AssemblyLine assemblyLine;

    static CarOrder orderA;
    static CarOrder orderB;
    static CarOrder orderC;

    @BeforeEach
    void init() {}

    @Test
    void getAssemblyLineTest() {}

    @Test
    void getAllWorkStationsTest() {}

    @Test
    void selectWorkStationTest() { // also tests mechanic.getWorkStation()

    }

    @Test
    void selectTaskTest() {}

    @Test
    void finishTaskTest() {}

    @Test
    void getCurrentStateAssembly() {}
}
