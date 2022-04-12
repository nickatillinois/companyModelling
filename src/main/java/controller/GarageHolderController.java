package controller;

import assemAssist.ProductionScheduler;
import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.exceptions.IllegalModelException;

import java.util.ArrayList;
import java.util.List;

public class GarageHolderController {

    private final ProductionScheduler productionScheduler;
    private String garageHolder;
    private String chosenModel;


    public GarageHolderController(ProductionScheduler productionScheduler) {
        this.productionScheduler = productionScheduler;
    }
    public List<List<String>> newLogin(String garageHolder) {
        this.garageHolder = garageHolder;
        return this.productionScheduler.newLogin(garageHolder);
    }
    public List<String> wantsToOrder() {
        ArrayList<String> availableModels = new ArrayList<>();
        availableModels.addAll(ProductionScheduler.getAvailableModels());
        return availableModels;
    }

    public List<String> selectModel(String carModel) {
        this.chosenModel = carModel;
        return productionScheduler.selectModel(carModel);
    }

    public String completeOrderingForm(List<String> carOptions) throws IllegalChoiceException, IllegalModelException, IllegalCompletionDateException {
        return productionScheduler.completeOrderingForm(carOptions, this.garageHolder, this.chosenModel).toString();
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
