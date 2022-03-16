package assemAssist.carOrder;

/**
 * Class representing a seats option.
 *
 * @author SWOP team 10
 */
public class Seats extends Option{

    /**
     * Creates a new seats option and adds the seats choices to choices.
     */
    public Seats() {
        super();
        addChoice("leather black");
        addChoice("leather white");
        addChoice("vinyl grey");
    }
}