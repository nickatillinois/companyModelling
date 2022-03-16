package assemAssist.carOrder;

import assemAssist.exceptions.IllegalChoiceException;
import purecollections.PList;
/**
 * Class representing a car model option.
 *
 * @author SWOP Team 10
 */
public abstract class Option {

    /**
     * Creates a new immutable PList object containing strings of customizations for this kind of option.
     */
    protected PList<String> choices = PList.empty();

    /**
     * Contains the chosen choice for this option.
     */
    private String chosenChoice;

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
     * Sets the chosen choice for this kind of caroption to the given choice.
     *
     * @param chosenChoice The opted choice for this car option.
     * @throws IllegalArgumentException | chosenChoice is null
     *                                  | chosenChoice is the empty string
     * @throws IllegalChoiceException   | chosenChoice is not in the list of available choices
     *                                    for this kind of car option
     */
    public void setChosenChoice(String chosenChoice) throws IllegalChoiceException {
        if(chosenChoice == null){throw new IllegalArgumentException("A chosenChoice for  " +
                "an option cannot be null.");}
        if(chosenChoice.length() == 0){throw new IllegalArgumentException("A chosenChoice for " +
                "an option cannot be the empty string.");}
        boolean contains = false;
        for(String choice: getChoices()){
            if(choice.equalsIgnoreCase(chosenChoice)){
                contains = true;
            }
        }
        if(!contains) {
            throw new IllegalChoiceException("This choice is not available for this option.");}
        this.chosenChoice = chosenChoice;
    }
    /**
     * Returns the immutable PList of choices
     *
     * @return The choices for this kind of option.
     */
    public PList<String> getChoices(){
        return choices;
    }

    /**
     * Adds the given string of a choice to the end of the PList of choices.
     * If the given string is already in the PList of choices, the string is not added
     * to choices.
     *
     * @param choice  the string that is added to the PList of existing choices
     * @throws IllegalArgumentException | given choice is null
     *                                  | given choice is the empty string
     */
    public void addChoice(String choice){
        if(choice == null){throw new IllegalArgumentException("A choice for an option cannot be null.");}
        if(choice.length() == 0){throw new IllegalArgumentException("A choice for an option cannot be the empty string.");}
        if(getChoices().contains(choice)) return;
        choices = choices.plus(choice);
    }
}
