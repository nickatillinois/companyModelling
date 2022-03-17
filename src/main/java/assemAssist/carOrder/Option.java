package assemAssist.carOrder;

import assemAssist.exceptions.IllegalChoiceException;

/**
 * Class representing a car model option.
 *
 * @author SWOP Team 10
 */
public abstract class Option {

    /**
     * Contains the chosen choice for this option.
     */
    protected String chosenChoice;

    /**
     * Creates a new option object.
     */
    public Option() {
    }

    /**
     * Returns the opted choice for this kind of option.
     *
     * @return The chosen choice for this kind of option.
     */
    public String getChosenChoice() {
        return chosenChoice;
    }

    /**
     * Sets the chosen choice for this kind of car option to the given choice.
     *
     * @param chosenChoice The opted choice for this car option.
     * @throws IllegalArgumentException | chosenChoice is null
     *                                  | chosenChoice is the empty string
     * @throws IllegalChoiceException   | chosenChoice is not in the list of available choices
     *                                    for this kind of car option
     */
    public abstract void setChosenChoice(String chosenChoice) throws IllegalChoiceException;
}
