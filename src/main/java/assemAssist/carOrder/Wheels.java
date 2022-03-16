package assemAssist.carOrder;

/**
 * Class representing a wheels option.
 *
 * @author SWOP team 10
 */
public class Wheels extends Option{

    /**
     * Creates a new wheels option and adds the wheels choices to choices.
     */
    public Wheels() {
        super();
        addChoice("comfort");
        addChoice("sports (low profile)");
    }
}