
package integrationTests;

import assemAssist.*;
import assemAssist.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class productSchedulerTest {
    private static ProductionScheduler productionScheduler;
    static CarOrder carOrderA;
    static CarOrder carOrderB;
    static CarOrder carOrderC;

    @BeforeEach
    public void init() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        productionScheduler = new ProductionScheduler();
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red");
        legalAOptions.put("body", "sedan");
        legalAOptions.put("engine", "v4");
        legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual");
        legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        CarModel carModelA = new CarModel("A", legalAOptions);
        carOrderA = new CarOrder("Danny Smeets", carModelA);
        TreeMap<String, String> legalBOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalBOptions.put("color", "red");
        legalBOptions.put("body", "sedan");
        legalBOptions.put("engine", "v4");
        legalBOptions.put("seats", "leather white");
        legalBOptions.put("airco", "manual");
        legalBOptions.put("gearbox", "6 manual");
        legalBOptions.put("wheels", "winter");
        legalBOptions.put("spoiler", "low");
        CarModel carModelB = new CarModel("B", legalBOptions);
        carOrderB = new CarOrder("Sandy Smeets", carModelB);
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
    }

    @Test
    public void getCurrentSchedulingAlgorithm() {
        assertEquals(productionScheduler.getSchedulingAlgorithm().getName(), "FIFO");
        assertEquals(productionScheduler.getSchedulingAlgorithm().getName(), productionScheduler.getSchedulingAlgorithms().get(0));
    }

    @Test
    public void setCurrentSchedulingAlgorithm() {
        assertEquals("FIFO", productionScheduler.getSchedulingAlgorithm().getName());
        productionScheduler.setSchedulingAlgorithm(productionScheduler.getSchedulers().get(1));
        assertEquals("Specification Batch", productionScheduler.getSchedulingAlgorithm().getName());
        assertEquals(productionScheduler.getSchedulers().get(1), productionScheduler.getSchedulingAlgorithm());
    }

    @Test
    public void getProductionSchedule() throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        productionScheduler.getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderA);
        productionScheduler.getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderB);
        productionScheduler.getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderC);
        List<CarOrder> expected = new ArrayList<>();
        expected.add(carOrderA);
        expected.add(carOrderB);
        expected.add(carOrderC);
        assertEquals(expected, productionScheduler.getSchedulingAlgorithm().getProductionSchedule());
        productionScheduler.advanceOrders(50);
        expected.remove(carOrderA);
        assertEquals(expected, productionScheduler.getSchedulingAlgorithm().getProductionSchedule());
    }

    @Test
    public void getOrdersFromGaragHolder() throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        productionScheduler.getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderA);
        productionScheduler.getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderB);
        productionScheduler.getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderC);
        List<CarOrder> carOrderList = new ArrayList<>();
        carOrderList.add(carOrderA);
        assertEquals(carOrderList,productionScheduler.getOrdersFromGarageHolder("Danny Smeets"));
        assertEquals(new ArrayList<>(), productionScheduler.getPendingOrdersFromGarageHolder("Danny Smeets"));
        productionScheduler.advanceOrders(50);
        assertEquals(carOrderList,productionScheduler.getOrdersFromGarageHolder("Danny Smeets"));
        assertEquals(carOrderList, productionScheduler.getPendingOrdersFromGarageHolder("Danny Smeets"));
    }

    @Test
    public void getPendingOrders() throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        productionScheduler.getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderA);
        productionScheduler.getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderB);
        productionScheduler.getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderC);
        List<CarOrder> carOrderList = new ArrayList<>();
        carOrderList.add(carOrderA);
        assertEquals(new ArrayList<>(), productionScheduler.getPendingOrders());
        productionScheduler.advanceOrders(50);
        assertEquals(carOrderList, productionScheduler.getPendingOrders());

    }

    @Test
    public void selectAlgorithm(){
        productionScheduler.selectSchedulingAlgorithm("Specification Batch");
        assertEquals("Specification Batch", productionScheduler.getSchedulingAlgorithm().getName());
    }
}