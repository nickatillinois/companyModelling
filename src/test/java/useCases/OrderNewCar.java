package useCases;

import assemAssist.*;
import assemAssist.exceptions.*;
import assemAssist.workStation.WorkStation;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderNewCar {


    static CarOrder carOrderA;
    static CarOrder carOrderB;
    static CarOrder carOrderC;
    static CarOrder carOrderD;
    static CarOrder carOrderE;
    static Company company;


    @BeforeAll
    static void init() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException, IllegalCompletionDateException {
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red");
        legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4");
        legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual");
        legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        CarModel carModelA = new CarModel("A", legalAOptions);
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
        company = new Company();
        company.completeOrderingForm(legalAOptions,"Danny Smeets","A",company.getWorkingTimeWorkingStation("A"));
        company.completeOrderingForm(legalAOptions,"Danny Smeets","B",company.getWorkingTimeWorkingStation("B"));
        company.completeOrderingForm(legalAOptions,"Danny Smeets","A",company.getWorkingTimeWorkingStation("A"));
        company.completeOrderingForm(legalAOptions,"Jan Smeets","B",company.getWorkingTimeWorkingStation("B"));
        company.completeOrderingForm(legalAOptions,"Jan Smeets","A",company.getWorkingTimeWorkingStation("A"));
    }
    @Test
    public void orderLegalA() throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException, CloneNotSupportedException {
        // bestel nieuwe auto A met juiste opties
        assertEquals(company.getOrdersFromGarageHolder("Danny Smeets")[0].size(), 3);
        ByteArrayInputStream in = new ByteArrayInputStream("2\nDanny Smeets\nn\nA\ns\n0\ns\n0\ns\n0\ns\n0\ns\n0\ns\n0\ns\n0\nq\n4".getBytes());
        System.setIn(in);
        new UI(new GarageHolderUI(new GarageHolderController(company)),new ManagerUI(new ManagerController(company)),
                new MechanicUI(new MechanicController(new Mechanic(company.getProductionScheduler().getAssemblyLine()))));
        assertEquals(company.getOrdersFromGarageHolder("Danny Smeets")[0].size(), 4);
        System.out.print("According to the assembly line, " +
                "\nthe assembly line can advance:  ");
        System.out.println(company.getProductionScheduler().getAssemblyLine().canMove());
        advanceLine();
        System.out.print("According to the assembly line, " +
                "\nthe assembly line can advance:  ");
        advanceLine();
        advanceLine();
        advanceLine();
        advanceLine();
        //TODO: waarom is er hier nog 1 pending order? moet er naar volgende dag geschakeld woren ofzoiets?
        assertEquals(company.getOrdersFromGarageHolder("Danny Smeets")[0].size(), 1);

    }
        void advanceLine(){
            for(AssemblyTask assemblyTask : company.getProductionScheduler().getAssemblyLine().getWorkStations().get(0).getTasks())
                assemblyTask.setCompleted();
            for(AssemblyTask assemblyTask : company.getProductionScheduler().getAssemblyLine().getWorkStations().get(1).getTasks())
                assemblyTask.setCompleted();
            for(AssemblyTask assemblyTask : company.getProductionScheduler().getAssemblyLine().getWorkStations().get(2).getTasks())
                assemblyTask.setCompleted();
            for(WorkStation workStation: company.getProductionScheduler().getAssemblyLine().getWorkStations()){
                System.out.println(workStation.getName() + ": " + workStation.isFinished());}
            System.out.println(company.getProductionScheduler().advanceOrders(50));
        }

}
