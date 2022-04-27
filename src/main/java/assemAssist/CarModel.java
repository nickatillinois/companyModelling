package assemAssist;

import assemAssist.exceptions.IllegalConstraintException;
import assemAssist.exceptions.IllegalModelException;
import assemAssist.constraint.Inspector;

import java.util.TreeMap;


public class CarModel {
    private String modelName;
    private TreeMap<String, String> chosenOptions;

    public String getModelName() {
        return modelName;
    }

    public CarModel(String modelName, TreeMap<String, String> chosenOptions) {
        this.modelName = modelName;
        this.chosenOptions = chosenOptions;
    }

    public boolean inspect() throws IllegalConstraintException, IllegalModelException {
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
