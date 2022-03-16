package assemAssist.carOrder;

/**
 * Class representing a body option.
 *
 * @author SWOP team 10
 */
public class Body extends Option{

    /**
     * Creates a new body option and adds the body choices to choices.
     */
    public Body() {
        super();
        addChoice("sedan");
        addChoice("break");
    }
}