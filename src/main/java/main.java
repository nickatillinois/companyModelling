import assemAssist.CarModel;
import assemAssist.CarOrder;
import assemAssist.Company;
import assemAssist.Mechanic;
import assemAssist.exceptions.*;
import controller.GarageHolderController;
import controller.ManagerController;
import controller.MechanicController;
import ui.GarageHolderUI;
import ui.ManagerUI;
import ui.MechanicUI;
import ui.UI;

import java.util.TreeMap;

public class main {

    public static void main(String[] args) throws IllegalModelException, IllegalCompletionDateException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        Company company = new Company();
        GarageHolderController garageHolderController = new GarageHolderController(company);
        GarageHolderUI garageHolderUI = new GarageHolderUI(garageHolderController);
        Mechanic mechanic = new Mechanic(company.getProductionScheduler().getAssemblyLine());
        MechanicController mechanicController = new MechanicController(mechanic);
        MechanicUI mechanicUI = new MechanicUI(mechanicController);
        ManagerController managerController = new ManagerController(company);
        ManagerUI managerUI = new ManagerUI(managerController);
        //DEMO

       /* TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
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
        CarOrder carOrderA = new CarOrder("Danny Smeets", carModelA);
        CarOrder carOrderB = new CarOrder("Els Smeets", carModelB);
        CarOrder carOrderC = new CarOrder("Dirk Smeets", carModelA);
        CarOrder carOrderD = new CarOrder("Jan Smeets", carModelB);
        CarOrder carOrderE = new CarOrder("Jef Smeets", carModelA);
        CarOrder carOrderF = new CarOrder("Jef Smeets", carModelB);
        CarOrder carOrderG = new CarOrder("Jef Smeets", carModelA);
        CarOrder carOrderH = new CarOrder("Jef Smeets", carModelB);

        company.getProductionScheduler().getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderA);
        company.getProductionScheduler().getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderB);
        company.getProductionScheduler().getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderC);
        company.getProductionScheduler().getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderD);
        company.getProductionScheduler().getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderE);
        company.getProductionScheduler().getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderF);
        company.getProductionScheduler().getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderG);
        company.getProductionScheduler().getSchedulingAlgorithm().addOrderToProductionSchedule(carOrderH);*/
        new UI(garageHolderUI, managerUI, mechanicUI);

    }
}
