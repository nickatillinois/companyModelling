package assemAssist.carOrder;

/**
 * Class representing a color option.
 *
 * @author SWOP team 10
 */
public class Color extends Option{

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