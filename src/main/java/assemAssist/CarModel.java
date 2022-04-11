package assemAssist;

import java.util.Map;

public class CarModel {
    private String modelName;
    private Map<String, String> specification;

    public CarModel(String modelName, Map<String, String> specification) {
        this.modelName = modelName;
        this.specification = specification;
    }


    public Map<String, String> getSpecification() {
        return specification;
    }

    public String getChosenOptions() {
        StringBuilder sb = new StringBuilder();
        for (String key : specification.keySet()) {
            sb.append(key).append(": ").append(specification.get(key)).append("\n");
        }
        return sb.toString();
    }
    public String getChosenModelName() {
        return modelName;
    }

}
