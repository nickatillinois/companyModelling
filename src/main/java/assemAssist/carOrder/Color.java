package assemAssist.carOrder;

/**
 * Class representing a color option.
 *
 * @author SWOP team 10
 */
public class Color extends Option{

    /**
     * The chosen option for this color.
     */
    private String colorChoice;

    /**
     * Creates a new color option and adds the color choices to choices.
     */
    public Color() {
        super();
        addChoice("red");
        addChoice("blue");
        addChoice("black");
        addChoice("white");
    }
}