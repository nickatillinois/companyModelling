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
        return this.productionScheduler.newLogin(garageholder);
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
        return productionScheduler.completeOrderingForm(carOptions, this.garageHolder, this.chosenModel);
    }
}
