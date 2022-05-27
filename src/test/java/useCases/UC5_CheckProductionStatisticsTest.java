package useCases;

import assemAssist.AssemblyLine;
import assemAssist.Company;
import assemAssist.Mechanic;
import assemAssist.ProductionScheduler;
import assemAssist.exceptions.*;
import controller.GarageHolderController;
import controller.ManagerController;
import controller.MechanicController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ui.GarageHolderUI;
import ui.ManagerUI;
import ui.MechanicUI;
import ui.UI;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UC5_CheckProductionStatisticsTest {

    // Because our system works with real-time dates, showcasing the statistics use case is a bit difficult.
    // To really test it, we would have te run our program consecutively for multiple days, which is -
    // - obviously not possible. That's why the different statistics do not make sense mathematically.
    // This is also why this use case can only be tested for one day, not the pas x days.
    // A way around this would be to change what LocalDate.now() returns, but there wasn't enough time left
    // at the time of finishing these tests to actually implement this.

    private Company company;
    private AssemblyLine assemblyLine;

    @BeforeEach
    void init() throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        company = new Company();
        ProductionScheduler productionScheduler = company.getProductionScheduler();
        assemblyLine = productionScheduler.getAssemblyLine();
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red"); legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4"); legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual"); legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        company.completeOrderingForm(legalAOptions,"Luna Van den Bergh","A");
    }

    @Test
    public void checkProductionStatisticsEmpty() throws IllegalCompletionDateException, IllegalModelException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        ByteArrayInputStream in = new ByteArrayInputStream("3\ns\nd\n4".getBytes());
        System.setIn(in);

        ByteArrayOutputStream test = new ByteArrayOutputStream();
        PrintStream PS = new PrintStream(test);
        System.setOut(PS);

        new UI( new GarageHolderUI( new GarageHolderController(company)),
                new ManagerUI(      new ManagerController(company)),
                new MechanicUI(     new MechanicController( new Mechanic(assemblyLine) )));

        String output = test.toString();
        assertTrue(output.contains("the average of completed cars in a day is 0.0"));
        assertTrue(output.contains("the median of completed cars in a day is 0.0"));
        assertTrue(output.contains("the average delay on a car is 0.0"));
        assertTrue(output.contains("the median delay on a car is 0.0"));
    }

    @Test
    public void checkProductionStatisticsOneOrder() throws IllegalCompletionDateException, IllegalModelException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {

        ByteArrayInputStream in = new ByteArrayInputStream(("1\nw\n0\n0\nd\n80\n0\nd\n80\n" +
                                                            "1\nw\n1\n0\nd\n80\n0\nd\n80\n" +
                                                            "1\nw\n2\n0\nd\n80\n0\nd\n80\n0\nd\n80\n" +
                                                            "3\ns\nd\n4\n").getBytes());
        System.setIn(in);

        ByteArrayOutputStream test = new ByteArrayOutputStream();
        PrintStream PS = new PrintStream(test);
        System.setOut(PS);

        new UI( new GarageHolderUI( new GarageHolderController(company)),
                new ManagerUI(      new ManagerController(company)),
                new MechanicUI(     new MechanicController( new Mechanic(assemblyLine) )));

        String output = test.toString();
        assertTrue(output.contains("the average of completed cars in a day is 1.0"));
        assertTrue(output.contains("the median of completed cars in a day is 1.0"));
        assertTrue(output.contains("the average delay on a car is -149.0"));
        assertTrue(output.contains("the median delay on a car is -149.0"));
        assertTrue(output.contains("the last delay was on " + LocalDate.now() + ": -149.0 minutes"));

    }

    @Test
    void checkProductionStatisticsTwoOrders() throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {

        ByteArrayInputStream in = new ByteArrayInputStream(("1\nw\n0\n0\nd\n80\n0\nd\n80\n" +
                                                            "1\nw\n1\n0\nd\n80\n0\nd\n80\n" +
                                                            "1\nw\n2\n0\nd\n80\n0\nd\n80\n0\nd\n80\n" +
                                                            "2\nLuna Van den Bergh\nn\nn\nA\ns\n0\ns\n0\ns\n0\ns\n1\ns\n0\ns\n2\ns\n1\nq\nc\n" +
                                                            "1\nw\n0\n0\nd\n80\n0\nd\n80\n" +
                                                            "1\nw\n1\n0\nd\n80\n0\nd\n80\n" +
                                                            "1\nw\n2\n0\nd\n80\n0\nd\n80\n0\nd\n80\n" +
                                                            "3\ns\nd\n4\n").getBytes());
        System.setIn(in);

        ByteArrayOutputStream test = new ByteArrayOutputStream();
        PrintStream PS = new PrintStream(test);
        System.setOut(PS);

        new UI( new GarageHolderUI( new GarageHolderController(company)),
                new ManagerUI(      new ManagerController(company)),
                new MechanicUI(     new MechanicController( new Mechanic(assemblyLine) )));

        String output = test.toString();
        assertTrue(output.contains("the average of completed cars in a day is 2.0"));
        assertTrue(output.contains("the median of completed cars in a day is 2.0"));
        assertTrue(output.contains("the average delay on a car is -149.0"));
        assertTrue(output.contains("the median delay on a car is -149.0"));
        assertTrue(output.contains("the last delay was on " + LocalDate.now() + ": -149.0 minutes"));

    }

}
