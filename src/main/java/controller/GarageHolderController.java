package controller;

import java.util.ArrayList;
import java.util.List;

public class GarageHolderController {

    public GarageHolderController(){}

    public List<String> newLogin() {
        List overview = new ArrayList<>();
        overview.add("Jaguar, Finished");
        overview.add("Jeep, Pending");
        return overview;
    }

    public List<String> wantsToOrder() {
        List availableModels = new ArrayList();
        availableModels.add("Jaguar");
        availableModels.add("Jeep");
        return availableModels;
    }

    public List<String> selectModel(String model) {
        List<String> orderingForm = new ArrayList<>();
        if (model == "Jaguar") {
            orderingForm.add("Color: red, blue");
            orderingForm.add("Seats: 2, 4");
        } else if (model == "Jeep") {
            orderingForm.add("Color: green, blue");
            orderingForm.add("Seats: 4, 6");
        }
        return orderingForm;
    }
}
