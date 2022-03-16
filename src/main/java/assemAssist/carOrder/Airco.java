package assemAssist.carOrder;


/**
 * Class representing an airco option.
 *
 * @author SWOP team 10
 */
public class Airco extends Option{

    /**
     * Creates a new airco option and adds the airco choices to choices.
     */
    public Airco() {
        super();
        addChoice("manual");
        addChoice("automatic climate control");
    }
}
