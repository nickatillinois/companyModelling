package assemAssist.carOrder;

/**
 * Class representing a body option.
 *
 * @author SWOP team 10
 */
public class Body extends Option{

    /**
     * The chosen option for this body.
     */
    private BodyEnum bodyChoice;

    /**
     * Creates a new body option.
     */

    public BodyEnum getBodyChoice() {
        return bodyChoice;
    }

    /**
     * Sets the chosen option for this body to the given body.
     *
     * @param bodyChoice the chosen option for this body.
     * @throws IllegalArgumentException | body is null
     *
     */
    public void setBodyChoice(BodyEnum bodyChoice) {
        this.bodyChoice = bodyChoice;
    }
    public Body() {
        super();
    }

}