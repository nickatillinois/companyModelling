package useCases;

import assemAssist.*;
import assemAssist.exceptions.*;
import controller.GarageHolderController;
import controller.ManagerController;
import controller.MechanicController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ui.GarageHolderUI;
import ui.ManagerUI;
import ui.MechanicUI;
import ui.UI;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckProductionStatisticsTest {

    static Company company;
    static AssemblyLine assemblyLine;
    static ProductionScheduler productionScheduler;
    static Mechanic mechanic;

    @BeforeAll
    static void init() throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        company = new Company();
        productionScheduler = company.getProductionScheduler();
        assemblyLine = productionScheduler.getAssemblyLine();
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red"); legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4"); legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual"); legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        company.completeOrderingForm(legalAOptions,"Luna Van den Bergh","A");
        company.completeOrderingForm(legalAOptions,"Luna Van den Bergh","A");
        company.completeOrderingForm(legalAOptions,"Luna Van den Bergh","A");
        company.completeOrderingForm(legalAOptions,"Luna Van den Bergh","A");
    }

    @Test
    public void checkProductionStatisticsEmpty() throws IllegalCompletionDateException, IllegalModelException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException, IOException {
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
    public void checkProductionStatistics() throws IllegalCompletionDateException, IllegalModelException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {

        //TODO statistieken toevoegen

        ByteArrayInputStream in = new ByteArrayInputStream("3\ns\nd\n4\n".getBytes());
        System.setIn(in);

        ByteArrayOutputStream test = new ByteArrayOutputStream();
        PrintStream PS = new PrintStream(test);
        PrintStream old = System.out;
        System.setOut(PS);

        new UI( new GarageHolderUI( new GarageHolderController(company)),
                new ManagerUI(      new ManagerController(company)),
                new MechanicUI(     new MechanicController( new Mechanic(assemblyLine) )));

        System.setOut(old);
        System.out.println(test);

    }

}
