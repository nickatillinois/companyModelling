package main.assemAssist;


import assemAssist.CarModel;
import assemAssist.CarOrder;
import assemAssist.Company;
import assemAssist.exceptions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.*;

public class CarOrderTest {

    static Company company;
    static CarOrder carOrderA;
    static CarOrder carOrderB;
    static CarOrder carOrderC;
    static CarOrder carOrderD;
    static CarOrder carOrderE;
    static CarOrder carOrderF;

    @BeforeAll
    static void init() throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        company = new Company();
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red");
        legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4");
        legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual");
        legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        CarModel carModelA = new CarModel("A", legalAOptions);
        carOrderA = new CarOrder("Danny Smeets", carModelA,company.getWorkingTimeWorkingStation("A"));
        TreeMap<String, String> legalBOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalBOptions.put("color", "red");
        legalBOptions.put("body", "break");
        legalBOptions.put("engine", "v4");
        legalBOptions.put("seats", "leather white");
        legalBOptions.put("airco", "manual");
        legalBOptions.put("gearbox", "6 manual");
        legalBOptions.put("wheels", "winter");
        legalBOptions.put("spoiler", "low");
        CarModel carModelB = new CarModel("B", legalBOptions);
        carOrderB = new CarOrder("Sandy Smeets", carModelB,company.getWorkingTimeWorkingStation("B"));
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
        carOrderC = new CarOrder("Kim Smeets", carModelC,company.getWorkingTimeWorkingStation("C"));
        //company.addOrderToProductionSchedule(carOrderA);
        carOrderD = new CarOrder("Tanya Smeets", carModelC,company.getWorkingTimeWorkingStation("C"));
        carOrderE = new CarOrder("Kimberly Smeets", carModelC,company.getWorkingTimeWorkingStation("C"));
        carOrderF = new CarOrder("Vanessa Smeets", carModelC,company.getWorkingTimeWorkingStation("C"));
        carOrderD.setEstCompletionTime(LocalDateTime.now().minusDays(1));
        carOrderE.setEstCompletionTime(LocalDateTime.now().minusDays(2));
        carOrderF.setEstCompletionTime(LocalDateTime.now().minusDays(3));
        System.out.println(carOrderA.getEstCompletionTime());
        company.completeOrderingForm(legalAOptions,"Danny Smeets","B",company.getWorkingTimeWorkingStation("B"));
        company.completeOrderingForm(legalBOptions,"Sandy Smeets","B",company.getWorkingTimeWorkingStation("B"));
        company.completeOrderingForm(legalCOptions,"Kim Smeets","C",company.getWorkingTimeWorkingStation("C"));
        company.getProductionScheduler().advanceOrders(50);
        // add some completed orders
        carOrderD = new CarOrder("Kim Smeets", carModelC,company.getWorkingTimeWorkingStation("C"));
        carOrderE = new CarOrder("Kim Smeets", carModelC,company.getWorkingTimeWorkingStation("C"));
        carOrderF = new CarOrder("Kim Smeets", carModelC,company.getWorkingTimeWorkingStation("C"));



        carOrderD.setEstCompletionTime(LocalDateTime.now().plusDays(7));
        carOrderE.setEstCompletionTime(LocalDateTime.now().plusDays(9));
        carOrderF.setEstCompletionTime(LocalDateTime.now().plusDays(8));

        ArrayList<CarOrder> completedOrders = new ArrayList<>();
        carOrderD.setCompleted(true);
        carOrderE.setCompleted(true);
        carOrderF.setCompleted(true);
        completedOrders.add(carOrderD);
        completedOrders.add(carOrderE);
        completedOrders.add(carOrderF);
        company.setCompletedCarOrders(completedOrders);
    }

    @Test
    public void testGetCarOrders() {
        assertEquals(carOrderA.getWorkingMinutesWorkStation(), 50);
    }

}
