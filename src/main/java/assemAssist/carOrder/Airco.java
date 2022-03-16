package assemAssist.carOrder;


/**
 * Class representing an airco option.
 *
 * @author SWOP team 10
 */
public class Airco extends Option{

    /**
     * The chosen option for this airco
     */
    private String aircoChoice;

    /**
     * Creates a new airco option and adds the airco choices to choices.
     */
    public Airco() {
        super();
        addChoice("manual");
        addChoice("automatic climate control");
    }
}
