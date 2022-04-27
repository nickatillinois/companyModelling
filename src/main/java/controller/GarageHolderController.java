package controller;

import assemAssist.Company;
import assemAssist.ProductionScheduler;
import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.exceptions.IllegalModelException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class GarageHolderController {

    private String garageHolder;
    private String chosenModel;
    private Company company;


    public GarageHolderController(Company company) {
        this.company = company;
    }
    public ArrayList<String[]>[] newLogin(String garageHolder) {
        this.garageHolder = garageHolder;
        return this.company.newLogin(garageHolder);
    }
    public HashSet<String> wantsToOrder() {
        return company.getAvailableModels();
    }

    public Map<String, HashSet<String>> selectModel(String carModel) throws IllegalModelException {
        this.chosenModel = carModel;
        return company.selectModel(carModel);
    }

    public String completeOrderingForm(List<String> carOptions) throws IllegalChoiceException, IllegalModelException, IllegalCompletionDateException {
        return company.completeOrderingForm((Map<String, String>) carOptions, this.garageHolder, this.chosenModel);
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
