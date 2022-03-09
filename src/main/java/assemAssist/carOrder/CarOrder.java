package assemAssist.carOrder;

import assemAssist.user.GarageHolder;

/**
 * Class representing a car order.
 *
 * @author SWOP Team 10
 */
public class CarOrder {
    private GarageHolder garageholder;
    private CarModel carModel;
    private boolean completed;

    public void setGarageholder(GarageHolder garageholder){
        this.garageholder = garageholder;
    }
    public GarageHolder getGarageholder() {
        return garageholder;
    }
    public CarModel getCarModel() {
        return carModel;
    }
    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }
    public boolean isCompleted(){return this.completed;}
    public void setCompleted(boolean completed){this.completed = completed;}
    public CarOrder(GarageHolder garageholder, CarModel carModel){
        this.garageholder = garageholder;
        this.carModel = carModel;
    }
}
