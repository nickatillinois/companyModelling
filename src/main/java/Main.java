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


/**
 * The main class of the program.
 * It creates the company, the garage holder, the manager and the mechanic.
 * It then starts the UI.
 * @author Team 10
 */
public class Main {

    public static void main(String[] args) throws IllegalModelException, IllegalCompletionDateException, IllegalConstraintException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException, InterruptedException {

        Company company = new Company();
        GarageHolderController garageHolderController = new GarageHolderController(company);
        GarageHolderUI garageHolderUI = new GarageHolderUI(garageHolderController);
        Mechanic mechanic = new Mechanic(company.getProductionScheduler().getAssemblyLine());
        MechanicController mechanicController = new MechanicController(mechanic);
        MechanicUI mechanicUI = new MechanicUI(mechanicController);
        ManagerController managerController = new ManagerController(company);
        ManagerUI managerUI = new ManagerUI(managerController);

        //DEMO
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red");
        legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4");
        legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual");
        legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        TreeMap<String, String> legalBOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalBOptions.put("color", "red");
        legalBOptions.put("body", "break");
        legalBOptions.put("engine", "v4");
        legalBOptions.put("seats", "leather white");
        legalBOptions.put("airco", "manual");
        legalBOptions.put("gearbox", "6 manual");
        legalBOptions.put("wheels", "winter");
        legalBOptions.put("spoiler", "low");

        company.completeOrderingForm(legalBOptions,"Jef Smeets","B");
        company.completeOrderingForm(legalAOptions,"Danny Smeets","A");
        company.completeOrderingForm(legalAOptions,"Dirk Smeets","A");
        company.completeOrderingForm(legalAOptions,"Jef Smeets","A");
        company.completeOrderingForm(legalAOptions,"Jef Smeets","A");
        company.completeOrderingForm(legalAOptions,"Jef Smeets","A");
        company.completeOrderingForm(legalAOptions,"Jef Smeets","A");
        company.completeOrderingForm(legalBOptions,"Jef Smeets","B");
        company.completeOrderingForm(legalBOptions,"Jef Smeets","B");

        new UI(garageHolderUI, managerUI, mechanicUI);

    }
}
