package controller;

import assemAssist.CarOrder;
import assemAssist.Company;
import assemAssist.ProductionScheduler;
import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.exceptions.IllegalModelException;

import java.util.ArrayList;
import java.util.List;

public class GarageHolderController {

    private final Company company;
    private String garageHolder;
    private String chosenModel;


    public GarageHolderController(Company company) {
        this.company = company;
    }
    public List<List<String>> newLogin(String garageHolder) {
        this.garageHolder = garageHolder;
        List<List<String>> orders = new ArrayList<>();
        //return company.getOrdersFromGarageHolder(garageHolder);
        //^omzetten naar list<list<string>>
        return orders;
    }

    public List<String> wantsToOrder() {
        return new ArrayList<>(company.getAvailableModels());
    }

    public List<String> selectModel(String carModel) throws IllegalModelException {
        this.chosenModel = carModel;
        List<String> model = new ArrayList<>();
        //return company.selectModel(carModel);
        //^omzetten naar list<string>
        return model;
    }

    // TODO implement
    public String completeOrderingForm(List<String> carOptions) throws IllegalChoiceException, IllegalModelException, IllegalCompletionDateException {
        // company.addOrderToProductionSchedule(new CarOrder());
        return " ";
    }

    public List<String> viewDetails(int carID, String garageHolderName) throws IllegalArgumentException {
        if (carID != 1) {
            throw new IllegalArgumentException("This is not a valid carID");
        } else {
            List<String> details = new ArrayList<>();
            details.add("Model: Jaguar");
            details.add("Color: Green");
            return details;
        }
    }
}
