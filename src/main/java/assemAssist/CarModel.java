package assemAssist;

import assemAssist.exceptions.*;
import assemAssist.constraint.Inspector;

import java.util.TreeMap;


public class CarModel {


    private final String modelName;
    private final TreeMap<String, String> chosenOptions;

    public String getModelName() {
        return modelName;
    }

    public CarModel(String modelName, TreeMap<String, String> chosenOptions) {
        this.modelName = modelName;
        this.chosenOptions = chosenOptions;
    }

    public boolean inspect() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        return new Inspector(this).inspect();
    }
    public TreeMap<String, String> getChosenOptions() {
        return chosenOptions;
    }

    public String getChosenOptionsString() {
        StringBuilder sb = new StringBuilder();
        for (String key : chosenOptions.keySet()) {
            sb.append(key).append(": ").append(chosenOptions.get(key)).append("\n");
        }
        return sb.toString();
    }

}
