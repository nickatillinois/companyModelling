package main.assemAssist.comparator;

import assemAssist.CarModel;
import assemAssist.CarOrder;
import assemAssist.comparator.CompletedCarOrderComparator;
import assemAssist.comparator.PendingCarOrderComparator;
import assemAssist.exceptions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ComparatorTest {
    private CarOrder order1;
    private CarOrder order2;
    private CarOrder order3;
    private CarOrder order4;
    private CarOrder order5;
    private CarOrder order6;
    private CarOrder order7;
    private CarOrder order8;

    @BeforeAll
    public void init() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException, IllegalCompletionDateException {
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red");
        legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4");
        legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual");
        legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        CarModel carModelA = new CarModel("A", legalAOptions, 60);
        order1 = new CarOrder("Danny Smeets", carModelA);
        order1.setEstCompletionTime(LocalDateTime.now().plusDays(1));
        order1.setCompleted();
        order1.setCompletionTime(LocalDateTime.of(2023, 1, 1, 0, 0));
        order2 = new CarOrder("Danny Smeets", carModelA);
        order2.setEstCompletionTime(LocalDateTime.now().plusDays(1));
        order2.setCompleted();
        order2.setCompletionTime(LocalDateTime.of(2024, 1, 1, 0, 0));
        order3 = new CarOrder("Danny Smeets", carModelA);
        order3.setEstCompletionTime(LocalDateTime.now().plusDays(1));
        order3.setCompleted();
        order3.setCompletionTime(LocalDateTime.of(2025, 1, 1, 0, 0));
        order4 = new CarOrder("Danny Smeets", carModelA);
        order4.setEstCompletionTime(LocalDateTime.now().plusDays(1));
        order4.setCompleted();
        order4.setCompletionTime(LocalDateTime.of(2026, 1, 1, 0, 0));
        order5 = new CarOrder("Danny Smeets", carModelA);
        order5.setEstCompletionTime(LocalDateTime.of(2026, 1, 1, 0, 0));
        order6 = new CarOrder("Danny Smeets", carModelA);
        order6.setEstCompletionTime(LocalDateTime.of(2027, 1, 1, 0, 0));
        order7 = new CarOrder("Danny Smeets", carModelA);
        order7.setEstCompletionTime(LocalDateTime.of(2028, 1, 1, 0, 0));
        order8 = new CarOrder("Danny Smeets", carModelA);
        order8.setEstCompletionTime(LocalDateTime.of(2029, 1, 1, 0, 0));
    }


    @Test
    public void testPendingComparator(){
        ArrayList<CarOrder> pendingList = new ArrayList<>();
        pendingList.add(order8);
        pendingList.add(order5);
        pendingList.add(order7);
        pendingList.add(order6);
        PendingCarOrderComparator pendingComparator = new PendingCarOrderComparator();
        pendingList.sort(pendingComparator);
        assertEquals(order5, pendingList.get(0));
        assertEquals(order6, pendingList.get(1));
        assertEquals(order7, pendingList.get(2));
        assertEquals(order8, pendingList.get(3));
    }

    @Test
    public void testCompletedComparator(){
        ArrayList<CarOrder> completedList = new ArrayList<>();
        completedList.add(order4);
        completedList.add(order2);
        completedList.add(order3);
        completedList.add(order1);
        CompletedCarOrderComparator completedComparator = new CompletedCarOrderComparator();
        completedList.sort(completedComparator);
        assertEquals(order1, completedList.get(0));
        assertEquals(order2, completedList.get(1));
        assertEquals(order3, completedList.get(2));
        assertEquals(order4, completedList.get(3));
    }
}
