package controller;

<<<<<<< HEAD
=======
import assemAssist.Company;
>>>>>>> parent of a6819f7 (Revert "Merge remote-tracking branch 'origin/main'")
import assemAssist.ProductionScheduler;
import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.exceptions.IllegalModelException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class GarageHolderController {

<<<<<<< HEAD
    private final ProductionScheduler productionScheduler;
=======
>>>>>>> parent of a6819f7 (Revert "Merge remote-tracking branch 'origin/main'")
    private String garageHolder;
    private String chosenModel;
    private Company company;


    public GarageHolderController(ProductionScheduler productionScheduler) {
        this.productionScheduler = productionScheduler;
    }
    public ArrayList<String[]>[] newLogin(String garageHolder) {
        this.garageHolder = garageHolder;
<<<<<<< HEAD
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
=======
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
>>>>>>> parent of a6819f7 (Revert "Merge remote-tracking branch 'origin/main'")
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
