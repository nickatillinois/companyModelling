package controller;

import assemAssist.ProductionScheduler;
import assemAssist.carOrder.*;
import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.exceptions.IllegalModelException;

import java.util.*;

public class GarageHolderController {

    private final ProductionScheduler productionScheduler;
    private String garageHolder;
    private String chosenModel;


    public GarageHolderController(ProductionScheduler productionScheduler) {
        this.productionScheduler = productionScheduler;
    }
    public List<List<String>> newLogin(String garageholder) {
        this.garageHolder = garageholder;
        ArrayList<CarOrder> allOrders = new ArrayList<>();
        allOrders.addAll(productionScheduler.getOrdersFromGarageHolder(garageholder));
        ArrayList<CarOrder> pendingOrders = new ArrayList<>();
        ArrayList<CarOrder> finishedOrders = new ArrayList<>();
        for (CarOrder carOrder: allOrders) {
            if(!carOrder.isCompleted()){
                pendingOrders.add(carOrder);
            }else{
                finishedOrders.add(carOrder);
            }
        }
        ArrayList<String> pendingOrdersString = new ArrayList<>();
        for (CarOrder carOrder: pendingOrders) {
            pendingOrdersString.add("Model: " + carOrder.getCarModel().getModelName() + "; Estimated completion date: " + carOrder.getCompletionTime().toString() + "; Options: " +
                    carOrder.getCarModel().getCarModelSpecification().getBody().getChosenChoice() + ", " +
                    carOrder.getCarModel().getCarModelSpecification().getColor().getChosenChoice() + ", " +
                    carOrder.getCarModel().getCarModelSpecification().getEngine().getChosenChoice() + ", " +
                    carOrder.getCarModel().getCarModelSpecification().getGearbox().getChosenChoice() + ", " +
                    carOrder.getCarModel().getCarModelSpecification().getSeats().getChosenChoice() + ", " +
                    carOrder.getCarModel().getCarModelSpecification().getAirco().getChosenChoice() + ", " +
                    carOrder.getCarModel().getCarModelSpecification().getWheels().getChosenChoice());
        }
        ArrayList<String> finishedOrdersString = new ArrayList<>();
        finishedOrders.sort(Comparator.comparing(CarOrder::getCompletionTime));
        for (CarOrder carOrder: finishedOrders) {
           finishedOrdersString.add("Model: " + carOrder.getCarModel().getModelName() + "; Estimated completion date: " + carOrder.getCompletionTime().toString() + "; Options: " +
                   carOrder.getCarModel().getCarModelSpecification().getBody().getChosenChoice() + ", " +
                   carOrder.getCarModel().getCarModelSpecification().getColor().getChosenChoice() + ", " +
                   carOrder.getCarModel().getCarModelSpecification().getEngine().getChosenChoice() + ", " +
                   carOrder.getCarModel().getCarModelSpecification().getGearbox().getChosenChoice() + ", " +
                   carOrder.getCarModel().getCarModelSpecification().getSeats().getChosenChoice() + ", " +
                   carOrder.getCarModel().getCarModelSpecification().getAirco().getChosenChoice() + ", " +
                   carOrder.getCarModel().getCarModelSpecification().getWheels().getChosenChoice());
        }

        List<List<String>> overview = new ArrayList<>();
        overview.add(pendingOrdersString);
        overview.add(finishedOrdersString);
        return overview;
    }

    public List<String> wantsToOrder() {
        ArrayList<String> availableModels = new ArrayList<>();
        availableModels.addAll(ProductionScheduler.getAvailableModels());
        return availableModels;
    }

    public List<String> selectModel(String carModel) {
        this.chosenModel = carModel;
        List<String> orderingForm = new ArrayList<>();

        String bodyString = "";
        Set<String> bodies = new HashSet<>(Arrays.asList(ProductionScheduler.getAvailableBodyChoices()));
        Iterator<String> iterator7 = bodies.iterator();
        while (iterator7.hasNext()) {
            String name = iterator7.next();
            if (!iterator7.hasNext()) {
                bodyString += name;
                break;
            }
            bodyString += name + ", ";

        }
        orderingForm.add("Body: " + bodyString);


        String colorString = "";
        Set<String> colors = new HashSet<>(Arrays.asList(ProductionScheduler.getAvailableColorChoices()));
        Iterator<String> iterator = colors.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (!iterator.hasNext()) {
                colorString += name;
                break;
            }
            colorString += name + ", ";

        }
        orderingForm.add("Color: " + colorString);

        String engineString = "";
        Set<String> engines = new HashSet<>(Arrays.asList(ProductionScheduler.getAvailableEngineChoices()));
        Iterator<String> iterator2 = engines.iterator();
        while (iterator2.hasNext()) {
            String name = iterator2.next();
            if (!iterator2.hasNext()) {
                engineString += name;
                break;
            }
            engineString += name + ", ";

        }
        orderingForm.add("Engine: " + engineString);

        String gearboxString = "";
        Set<String> gears = new HashSet<>(Arrays.asList(ProductionScheduler.getAvailableGearboxChoices()));
        Iterator<String> iterator3 = gears.iterator();
        while (iterator3.hasNext()) {
            String name = iterator3.next();
            if (!iterator3.hasNext()) {
                gearboxString += name;
                break;
            }
            gearboxString += name + ", ";

        }
        orderingForm.add("GearBox: " + gearboxString);

        String seatsString = "";
        Set<String> seats = new HashSet<>(Arrays.asList(ProductionScheduler.getAvailableSeatsChoices()));
        Iterator<String> iterator4 = seats.iterator();
        while (iterator4.hasNext()) {
            String name = iterator4.next();
            if (!iterator4.hasNext()) {
                seatsString += name;
                break;
            }
            seatsString += name + ", ";

        }
        orderingForm.add("Seats: " + seatsString);

        String aircoString = "";
        Set<String> aircos = new HashSet<>(Arrays.asList(ProductionScheduler.getAvailableSeatsChoices()));
        Iterator<String> iterator6 = aircos.iterator();
        while (iterator6.hasNext()) {
            String name = iterator6.next();
            if (!iterator6.hasNext()) {
                aircoString += name;
                break;
            }
            aircoString += name + ", ";

        }
        orderingForm.add("Airco: " + aircoString);

        String wheelsString = "";
        Set<String> wheels = new HashSet<>(Arrays.asList(ProductionScheduler.getAvailableWheelsChoices()));
        Iterator<String> iterator5 = wheels.iterator();
        while (iterator5.hasNext()) {
            String name = iterator5.next();
            if (!iterator5.hasNext()) {
                wheelsString += name;
                break;
            }
            wheelsString += name + ", ";

        }
        orderingForm.add("Wheels: " + wheelsString);

        return orderingForm;
    }

    public String completeOrderingForm(List<String> carOptions) throws IllegalChoiceException, IllegalModelException, IllegalCompletionDateException {
        CarModelSpecification cmf = new CarModelSpecification(new Body(carOptions.get(0)),new Color(carOptions.get(1)),
                new Engine(carOptions.get(2)),new Gearbox(carOptions.get(3)),
                new Seats(carOptions.get(4)),new Airco(carOptions.get(5)),new Wheels(carOptions.get(6)));
        CarModel carModel = new CarModel(chosenModel, cmf);
        CarOrder carOrder = new CarOrder(garageHolder, carModel);
        productionScheduler.addOrderToProductionSchedule(carOrder);
        return carOrder.getCompletionTime().toString();
    }
}
