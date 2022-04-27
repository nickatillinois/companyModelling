import assemAssist.AssemblyLine;
import assemAssist.Company;
import assemAssist.Mechanic;
import assemAssist.CarOrder;
import assemAssist.ProductionScheduler;
import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.exceptions.IllegalModelException;
import controller.GarageHolderController;
import controller.ManagerController;
import controller.MechanicController;
import ui.GarageHolderUI;
import ui.ManagerUI;
import ui.MechanicUI;
import ui.UI;

public class main {

    public static void main(String[] args) throws IllegalModelException, IllegalChoiceException, IllegalCompletionDateException {
        AssemblyLine assemblyLine = new AssemblyLine();
        ProductionScheduler productionScheduler = new ProductionScheduler();
        Company company = new Company();
        GarageHolderController garageHolderController = new GarageHolderController(company);
        GarageHolderUI garageHolderUI = new GarageHolderUI(garageHolderController);



        /*//DEMO
        CarModelSpecification cmf = new CarModelSpecification(new Body("sedan"),new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        String modelName = "jaguar";
        CarModel carModel = new CarModel(modelName, cmf);
        String garageHolder = "Nick Degelin";
        CarOrder carOrder1 = new CarOrder(garageHolder, carModel);

        CarModelSpecification cmf2 = new CarModelSpecification(new Body("sedan"),new Color("blue"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        CarModel carModel2 = new CarModel(modelName, cmf2);
        CarOrder carOrder2 = new CarOrder(garageHolder, carModel2);

        String garageHolder2 = "Raf Sablon";
        CarModelSpecification cmf3 = new CarModelSpecification(new Body("sedan"),new Color("blue"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        CarModel carModel3 = new CarModel(modelName, cmf3);
        CarOrder carOrder3 = new CarOrder(garageHolder2, carModel3);
        productionScheduler.getAssemblyLine().getWorkStations().get(0).setCurrentOrder(carOrder3);
        productionScheduler.getAssemblyLine().getWorkStations().get(1).setCurrentOrder(carOrder2);
        productionScheduler.getAssemblyLine().getWorkStations().get(2).setCurrentOrder(carOrder1);
        for (AssemblyTask task :productionScheduler.getAssemblyLine().getWorkStations().get(2).getTasks()){
            task.setIsCompleted(true);
        }
        for (AssemblyTask task :productionScheduler.getAssemblyLine().getWorkStations().get(1).getTasks()){
            task.setIsCompleted(true);
        }
        // Tot hier DEMO*/

        Mechanic mechanic = new Mechanic(productionScheduler, assemblyLine);

        ManagerController managerController = new ManagerController(company);
        ManagerUI managerUI = new ManagerUI(managerController);

        MechanicController mechanicController = new MechanicController(mechanic);
        MechanicUI mechanicUI = new MechanicUI(mechanicController);

        UI ui = new UI(garageHolderUI, managerUI, mechanicUI);
    }
}
