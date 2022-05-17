package useCases;

import assemAssist.CarOrder;
import assemAssist.Company;
import assemAssist.exceptions.*;
import controller.GarageHolderController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ui.GarageHolderUI;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UseCase1And2Test {

    static CarOrder carOrderA;
    static CarOrder carOrderB;
    static CarOrder carOrderC;
    static CarOrder carOrderD;
    static CarOrder carOrderE;
    static Company company;
    static GarageHolderController controller;
    static GarageHolderUI ui;

    @BeforeAll
    static void init() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException, IllegalCompletionDateException, InterruptedException {
        company = new Company();
        controller = new GarageHolderController(company);
        ui = new GarageHolderUI(controller);
        ByteArrayInputStream in = new ByteArrayInputStream("2\ns".getBytes());
        System.setIn(in);
        TreeMap<String, String> legalOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalOptions.put("color", "red");
        legalOptions.put("body", "break");
        legalOptions.put("engine", "v4");
        legalOptions.put("seats", "leather white");
        legalOptions.put("airco", "manual");
        legalOptions.put("gearbox", "6 manual");
        legalOptions.put("wheels", "winter");
        company.completeOrderingForm(legalOptions,"Tom Smets","A",company.getWorkingTimeWorkingStation("A"));
        company.completeOrderingForm(legalOptions,"Tom Smets","A",company.getWorkingTimeWorkingStation("A"));
        ArrayList<CarOrder> completedCarOrders = new ArrayList<>();
        List<CarOrder>[] orders = company.getOrdersFromGarageHolder("Tom Smets");
        // set every order in orders completed
        for (List<CarOrder> order : orders) {
            for (CarOrder carOrder : order) {
                TimeUnit.SECONDS.sleep(2);
                carOrder.setCompleted(true);
                completedCarOrders.add(carOrder);

            }
        }
        company.setCompletedCarOrders(completedCarOrders);
        company.getProductionScheduler().advanceOrders(50);
        company.completeOrderingForm(legalOptions,"Tom Smets","A",company.getWorkingTimeWorkingStation("A"));
        company.getProductionScheduler().advanceOrders(50);
        company.completeOrderingForm(legalOptions,"Tom Smets","A",company.getWorkingTimeWorkingStation("A"));
    }

    @Test
    void testLists() {
        List<CarOrder>[] orders = company.getOrdersFromGarageHolder("Tom Smets");
        assertEquals(2, orders[0].size());
        assertEquals(2, orders[1].size());
    }

    @Test
    void testIllegalModel() throws IllegalModelException {
        TreeMap<String, String> illegalOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        illegalOptions.put("color", "red");
        illegalOptions.put("body", "sport");
        illegalOptions.put("engine", "v4");
        illegalOptions.put("seats", "leather white");
        illegalOptions.put("airco", "automatic");
        illegalOptions.put("gearbox", "6 manual");
        illegalOptions.put("wheels", "winter");
        try {
            company.completeOrderingForm(illegalOptions,"Tom Smets","B",company.getWorkingTimeWorkingStation("B"));
        } catch (IllegalModelException | IllegalCompletionDateException | IllegalConstraintException | OptionThenComponentException | OptionAThenOptionBException | RequiredComponentException e) {
            assertEquals("Option sport implies one of the following options: [v6, v8]", e.getMessage());
        }
    }
}
