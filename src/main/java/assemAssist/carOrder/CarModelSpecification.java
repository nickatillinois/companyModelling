package assemAssist.carOrder;

/**
 * Class representing a car model specification.
 *
 * @author SWOP Team 10
 */
public class CarModelSpecification {

    /**
     * A Body object representing the car body component.
     */
    private Body body;

    /**
     * A color object representing the car color component.
     */
    private Color color;

    /**
     * An engine object representing the car engine component.
     */
    private Engine engine;

    /**
     * A gearbox object representing the car gearbox component.
     */
    private Gearbox gearbox;

    /**
     * A seats object representing the car seats component.
     */
    private Seats seats;

    /**
     * An airco object representing the car airco component.
     */
    private Airco airco;

    /**
     * A wheels object representing the car wheels component.
     */
    private Wheels wheels;

    /**
     * Returns the body of this car model specification.
     *
     * @return Body
     */
    public Body getBody() {
        return body;
    }

    /**
     * Sets the body of this car model specification to the given body.
     *
     * @param body The body for this car model specification.
     * @throws IllegalArgumentException | body is null
     *
     */
    public void setBody(Body body) {
        if(body == null){throw new IllegalArgumentException("A body cannot be null.");}
        this.body = body;
    }

    /**
     * Returns the color of this car model specification.
     *
     * @return Color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of this car model specification to the given color.
     *
     * @param color The color for this car model specification.
     * @throws IllegalArgumentException | color is null
     *
     */
    public void setColor(Color color) {
        if(color == null){throw new IllegalArgumentException("A color cannot be null.");}
        this.color = color;
    }

    /**
     * Returns the engine of this car model specification.
     *
     * @return Engine
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * Sets the engine of this car model specification to the given engine.
     *
     * @param engine The engine for this car model specification.
     * @throws IllegalArgumentException | engine is null
     *
     */
    public void setEngine(Engine engine) {
        if(engine == null){throw new IllegalArgumentException("An engine cannot be null.");}
        this.engine = engine;
    }

    /**
     * Returns the gearbox of this car model specification.
     *
     * @return Gearbox
     */
    public Gearbox getGearbox() {
        return gearbox;
    }


    /**
     * Sets the gearbox of this car model specification to the given gearbox.
     *
     * @param gearbox The gearbox for this car model specification.
     * @throws IllegalArgumentException | gearbox is null
     *
     */
    public void setGearbox(Gearbox gearbox) {
        if(gearbox == null){throw new IllegalArgumentException("A gearbox cannot be null.");}
        this.gearbox = gearbox;
    }

    /**
     * Returns the seats of this car model specification.
     *
     * @return Seats
     */
    public Seats getSeats() {
        return seats;
    }

    /**
     * Sets the seats of this car model specification to the given seats.
     *
     * @param seats The seats for this car model specification.
     * @throws IllegalArgumentException | seats is null
     *
     */
    public void setSeats(Seats seats) {
        if(seats == null){throw new IllegalArgumentException("Seats cannot be null.");}
        this.seats = seats;
    }

    /**
     * Returns the airco of this car model specification.
     *
     * @return Airco
     */
    public Airco getAirco() {
        return airco;
    }

    /**
     * Sets the airco of this car model specification to the given airco.
     *
     * @param airco The airco for this car model specification.
     * @throws IllegalArgumentException | airco is null
     *
     */
    public void setAirco(Airco airco) {
        if(airco == null){throw new IllegalArgumentException("An airco cannot be null.");}
        this.airco = airco;
    }

    /**
     * Returns the Wheels of this car model specification.
     *
     * @return Wheels
     */
    public Wheels getWheels() {
        return wheels;
    }


    /**
     * Sets the wheels of this car model specification to the given wheels.
     *
     * @param wheels The wheels for this car model specification.
     * @throws IllegalArgumentException | wheels is null
     *
     */
    public void setWheels(Wheels wheels) {
        if(wheels == null){throw new IllegalArgumentException("Wheels cannot be null.");}
        this.wheels = wheels;
    }

    /**
     * Creates a new car model specification with a given body, color, engine, gearbox, seats, airco, wheels.
     *
     * @param body The body for the car model specification.
     * @param color The color for the car model specification.
     * @param engine The engine for the car model specification.
     * @param gearbox The gearbox for the car model specification.
     * @param seats The seats for the car model specification.
     * @param airco The airco for the car model specification.
     * @param wheels The wheels for the car model specification.
     * @throws IllegalArgumentException | body is null
     *                                  | color is null
     *                                  | engine is null
     *                                  | gearbox is null
     *                                  | seats is null
     *                                  | airco is null
     *                                  | wheels is null
     */
    public CarModelSpecification(Body body, Color color, Engine engine, Gearbox gearbox,
                                 Seats seats, Airco airco, Wheels wheels) {
        if(body == null){throw new IllegalArgumentException("A body cannot be null.");}
        if(color == null){throw new IllegalArgumentException("A color cannot be null.");}
        if(engine == null){throw new IllegalArgumentException("An engine cannot be null.");}
        if(gearbox == null){throw new IllegalArgumentException("A gearbox cannot be null.");}
        if(seats == null){throw new IllegalArgumentException("Seats cannot be null.");}
        if(airco == null){throw new IllegalArgumentException("An airco cannot be null.");}
        if(wheels == null){throw new IllegalArgumentException("Wheels cannot be null.");}
        this.body = body;
        this.color = color;
        this.engine = engine;
        this.gearbox = gearbox;
        this.seats = seats;
        this.airco = airco;
        this.wheels = wheels;
    }
}
