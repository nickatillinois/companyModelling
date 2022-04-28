package classTests;

import assemAssist.CarModel;
import assemAssist.CarOrder;
import assemAssist.exceptions.*;
import assemAssist.schedulingAlgorithm.Batch;
import assemAssist.schedulingAlgorithm.FIFO;
import assemAssist.schedulingAlgorithm.SchedulingAlgorithm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class SchedulingAlgorithmTest {
    private static SchedulingAlgorithm fifo ;
    private static SchedulingAlgorithm batch ;
    static CarOrder carOrderA;
    static CarOrder carOrderB;
    static CarOrder carOrderC;
    static CarOrder carOrderD;
    static CarOrder carOrderE;

    @BeforeEach
    public void init() throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        fifo = new FIFO();
        batch = new Batch();
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red");
        legalAOptions.put("body", "sedan");
        legalAOptions.put("engine", "v4");
        legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual");
        legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        CarModel carModelA = new CarModel("A", legalAOptions);
        carOrderB = new CarOrder("Sandy Smeets", carModelA);
        TreeMap<String, String> legalCOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalCOptions.put("color", "black");
        legalCOptions.put("body", "sport");
        legalCOptions.put("engine", "v6");
        legalCOptions.put("seats", "leather white");
        legalCOptions.put("airco", "manual");
        legalCOptions.put("gearbox", "6 manual");
        legalCOptions.put("wheels", "winter");
        legalCOptions.put("spoiler", "low");
        CarModel carModelC = new CarModel("C", legalCOptions);
        carOrderC = new CarOrder("Kim Smeets", carModelC);
        carOrderD = new CarOrder("Kim Smeets", carModelA);
        carOrderE = new CarOrder("Sandy Smeets", carModelA);
        carOrderA = new CarOrder("Danny Smeets", carModelC);
        batch.addOrderToProductionSchedule(carOrderA);
        batch.addOrderToProductionSchedule(carOrderB);
        batch.addOrderToProductionSchedule(carOrderC);
        batch.addOrderToProductionSchedule(carOrderD);
        batch.addOrderToProductionSchedule(carOrderE);
        fifo.addOrderToProductionSchedule(carOrderA);
        fifo.addOrderToProductionSchedule(carOrderB);
        fifo.addOrderToProductionSchedule(carOrderC);
        fifo.addOrderToProductionSchedule(carOrderD);
        fifo.addOrderToProductionSchedule(carOrderE);

    }

    @Test
    void possibleBatch() {
        List<String> BatchList = new ArrayList<>();
        BatchList.add(carOrderB.getCarModel().getChosenOptionsString());
        assertEquals(BatchList,batch.possibleBatch());
        assertNull(fifo.possibleBatch());
    }

    @Test
    void selectBatch() {
        batch.selectBatch(carOrderB.getCarModel().getChosenOptionsString());
        assertEquals(carOrderB, batch.getNextCarOrder());
    }

    @Test
    void getNextCarOrder() {
        assertEquals(carOrderA,fifo.getNextCarOrder());
        batch.selectBatch(carOrderB.getCarModel().getChosenOptionsString());
        assertEquals(carOrderB, batch.getNextCarOrder());
    }

    @Test
    void getName() {
        assertEquals("FIFO", fifo.getName());
        assertEquals("Specification Batch", batch.getName());
    }

    @Test
    void getProductionSchedule() {
        List<CarOrder> fifolist = new ArrayList<>();
        fifolist.add(carOrderA);
        fifolist.add(carOrderB);
        fifolist.add(carOrderC);
        fifolist.add(carOrderD);
        fifolist.add(carOrderE);
        assertEquals(fifolist, fifo.getProductionSchedule());
        List<CarOrder> bachlist = new ArrayList<>();
        bachlist.add(carOrderB);
        bachlist.add(carOrderD);
        bachlist.add(carOrderE);
        bachlist.add(carOrderA);
        bachlist.add(carOrderC);
        batch.selectBatch(carOrderB.getCarModel().getChosenOptionsString());
        assertEquals(bachlist,batch.getProductionSchedule());
    }

    @Test
    void addOrder() throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        SchedulingAlgorithm fifo = new FIFO();
        fifo.addOrderToProductionSchedule(carOrderA);
        assertTrue(fifo.getProductionSchedule().contains(carOrderA));
        SchedulingAlgorithm batch = new Batch();
        batch.addOrderToProductionSchedule(carOrderA);
        assertTrue(fifo.getProductionSchedule().contains(carOrderA));
    }
}