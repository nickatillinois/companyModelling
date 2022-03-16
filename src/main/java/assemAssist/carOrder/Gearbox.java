package assemAssist.carOrder;

/**
 * Class representing a gearbox option.
 *
 * @author SWOP team 10
 */
public class Gearbox extends Option{

    /**
     * Creates a new gearbox option and adds the gearbox choices to choices.
     */
    public Gearbox() {
        super();
        addChoice("6 speed manual");
        addChoice("5 speed automatic");
    }
}