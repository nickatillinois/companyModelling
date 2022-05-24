package useCases;

import assemAssist.CarModel;
import assemAssist.CarOrder;
import assemAssist.Company;
import assemAssist.Mechanic;
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
import java.io.Serial;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeSchedulingAlgorithm {
    static CarOrder carOrderA;
    static CarOrder carOrderB;
    static CarOrder carOrderC;
    static CarOrder carOrderD;
    static CarOrder carOrderE;
    static Company company;
    @BeforeAll
    static void init() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException, IllegalCompletionDateException {
        company = new Company();
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red");
        legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4");
        legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual");
        legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        CarModel carModelA = new CarModel("A", legalAOptions,company.getWorkingTimeWorkingStation("A"));
        TreeMap<String, String> legalBOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalBOptions.put("color", "red");
        legalBOptions.put("body", "break");
        legalBOptions.put("engine", "v4");
        legalBOptions.put("seats", "leather white");
        legalBOptions.put("airco", "manual");
        legalBOptions.put("gearbox", "6 manual");
        legalBOptions.put("wheels", "winter");
        legalBOptions.put("spoiler", "low");
        CarModel carModelB = new CarModel("B", legalBOptions,company.getWorkingTimeWorkingStation("B"));
        company = new Company();
        carOrderA = new CarOrder("Danny Smeets", carModelA );
        carOrderB = new CarOrder("Els Smeets", carModelB);
        carOrderC = new CarOrder("Dirk Smeets", carModelA);
        carOrderD = new CarOrder("Jan Smeets", carModelB);
        carOrderE = new CarOrder("Jef Smeets", carModelA);

        company.getProductionScheduler().getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderA);
        company.getProductionScheduler().getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderB);
        company.getProductionScheduler().getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderC);
        company.getProductionScheduler().getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderD);
        company.getProductionScheduler().getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderE);
    }
    @Test
    public void setFifo() throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException, CloneNotSupportedException {
        ByteArrayInputStream in = new ByteArrayInputStream("3\na\n0\n4".getBytes());
        System.setIn(in);
        new UI(new GarageHolderUI(new GarageHolderController(company)),new ManagerUI(new ManagerController(company)),
                new MechanicUI(new MechanicController(new Mechanic(company.getProductionScheduler().getAssemblyLine()))));
        List<CarOrder> carOrderList = new ArrayList<>();
        carOrderList.add(carOrderA);
        carOrderList.add(carOrderB);
        carOrderList.add(carOrderC);
        carOrderList.add(carOrderD);
        carOrderList.add(carOrderE);
        assertEquals(company.getProductionScheduler().getSchedulers().get(0),company.getProductionScheduler().getSchedulingAlgorithm());
        assertEquals(carOrderList, company.getProductionScheduler().getSchedulingAlgorithm().getProductionSchedule());
    }

    @Test
    public  void setBatch() throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        String string = carOrderA.getCarModel().getChosenOptionsString();
        ByteArrayInputStream in = new ByteArrayInputStream("3\na\n1\n0\n4".getBytes());
        System.setIn(in);
        new UI(new GarageHolderUI(new GarageHolderController(company)),new ManagerUI(new ManagerController(company)),
                new MechanicUI(new MechanicController(new Mechanic(company.getProductionScheduler().getAssemblyLine()))));
        assertEquals(company.getProductionScheduler().getSchedulers().get(1),company.getProductionScheduler().getSchedulingAlgorithm());
        List<CarOrder> carOrderList = new ArrayList<>();
        carOrderList.add(carOrderA);
        carOrderList.add(carOrderC);
        carOrderList.add(carOrderE);
        carOrderList.add(carOrderB);
        carOrderList.add(carOrderD);
        assertEquals(carOrderList, company.getProductionScheduler().getSchedulingAlgorithm().getProductionSchedule());

    }
}
