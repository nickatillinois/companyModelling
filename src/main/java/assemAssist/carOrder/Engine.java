package assemAssist.carOrder;

/**
 * Class representing an engine option.
 *
 * @author SWOP team 10
 */
public class Engine extends Option{

    /**
     * Creates a new engine option and adds the engine choices to choices.
     */
    public Engine() {
        super();
        addChoice("standard 2l 4 cilinders");
        addChoice("performance 2.5l 6 cilinders");
    }
}