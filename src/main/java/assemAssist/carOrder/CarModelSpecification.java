package assemAssist.carOrder;

/**
 * Class representing a car model specification.
 *
 * @author SWOP Team 10
 */
public class CarModelSpecification {

    /**
     * A string with the model Name of this car model.
     */
    private Body body;
    private Color color;
    private Engine engine;
    private Gearbox gearbox;
    private Seats seats;
    private Airco airco;
    private Wheels wheels;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public Seats getSeats() {
        return seats;
    }

    public void setSeats(Seats seats) {
        this.seats = seats;
    }

    public Airco getAirco() {
        return airco;
    }

    public void setAirco(Airco airco) {
        this.airco = airco;
    }

    public Wheels getWheels() {
        return wheels;
    }

    public void setWheels(Wheels wheels) {
        this.wheels = wheels;
    }

    public CarModelSpecification(Body body, Color color, Engine engine, Gearbox gearbox,
                                 Seats seats, Airco airco, Wheels wheels) {
        this.body = body;
        this.color = color;
        this.engine = engine;
        this.gearbox = gearbox;
        this.seats = seats;
        this.airco = airco;
        this.wheels = wheels;
    }
}
