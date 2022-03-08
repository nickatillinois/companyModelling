package assemAssist.carOrder;

public class CarModel {
    private String modelName;
    private CarModelSpecification carModelSpecification;

    public String getModelName() {
        return modelName;
    }
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    public CarModelSpecification getCarModelSpecification() {
        return carModelSpecification;
    }
    public void setCarModelSpecification(CarModelSpecification carModelSpecification) {
        this.carModelSpecification = carModelSpecification;
    }
    public CarModel(String modelName, CarModelSpecification carModelSpecification) {
        this.modelName = modelName;
        this.carModelSpecification = carModelSpecification;
    }

}
