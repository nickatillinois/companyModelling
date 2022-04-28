package controller;

import assemAssist.Company;
import assemAssist.exceptions.*;

import java.util.*;

public class GarageHolderController {

    private String garageHolder;
    private String chosenModel;
    private Company company;

    public GarageHolderController(Company company) {
        if(company == null) {
            throw new IllegalArgumentException("Company cannot be null");
        }
        this.company = company;
    }
    public ArrayList<String[]>[] newLogin(String garageHolder) {
        this.garageHolder = garageHolder;
        return this.company.newLogin(garageHolder);
    }
    public HashSet<String> wantsToOrder() {
        return company.getAvailableModels();
    }

    public TreeMap<String, HashSet<String>> selectModel(String carModel) throws IllegalModelException {
        this.chosenModel = carModel;
        return company.selectModel(carModel);
    }

    public String completeOrderingForm(TreeMap<String, String> chosenOptions) throws IllegalArgumentException, IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        return company.completeOrderingForm(chosenOptions, this.garageHolder, this.chosenModel);
    }

    public List<String> viewDetails(int carID, String garageHolderName) throws IllegalArgumentException, OrderNotFoundException {
        if (carID <= 0) {
            throw new IllegalArgumentException("Car ID cannot be less than or equal to 0");
        } else {
            return company.getOrderDetails(carID, garageHolderName);
        }
    }
}
