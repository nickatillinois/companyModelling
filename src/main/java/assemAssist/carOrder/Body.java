package assemAssist.carOrder;

import assemAssist.exceptions.IllegalChoiceException;

/**
 * Class representing a body option.
 *
 * @author SWOP team 10
 */
public class Body extends Option{


    /**
     * Returns an array of available body choices.
     *
     * @return an array of available body choices.
     */
    public static String[] getAvailableChoices(){
        return new String[]{"sedan", "break"};
    }

    /**
     * Creates a new body option and sets the given customization choice.
     *
     * @param chosenChoice the given customization choice.
     * @throws IllegalArgumentException | chosenChoice is null
     *                                  | chosenChoice is the empty string
     * @throws IllegalChoiceException    | chosenChoice is not in getAvailableChoices()
     */
    public Body(String chosenChoice) throws IllegalChoiceException {
        super();
        if(chosenChoice == null){throw new IllegalArgumentException("A chosenChoice for  " +
                "a body option cannot be null.");}
        if(chosenChoice.length() == 0){throw new IllegalArgumentException("A chosenChoice for " +
                "a body option cannot be the empty string.");}
        boolean contains = false;
        for(String choice: getAvailableChoices()){
            if(choice.equalsIgnoreCase(chosenChoice)){
                contains = true;
                break;
            }
        }
        if(!contains) {
            throw new IllegalChoiceException("This choice is not available for this body option." +
                    " Please choose a customization that is available for this body option.");}
        this.chosenChoice = chosenChoice;
    }

    /**
     * Sets the given customization choice.
     *
     * @param chosenChoice the given customization choice.
     * @throws IllegalArgumentException | chosenChoice is null
     *                                  | chosenChoice is the empty string
     * @throws IllegalChoiceException    | chosenChoice is not in getAvailableChoices()
     */
    @Override
    public void setChosenChoice(String chosenChoice) throws IllegalChoiceException {
        if(chosenChoice == null){throw new IllegalArgumentException("A chosenChoice for  " +
                "a body option cannot be null.");}
        if(chosenChoice.length() == 0){throw new IllegalArgumentException("A chosenChoice for " +
                "a body option cannot be the empty string.");}
        boolean contains = false;
        for(String choice: getAvailableChoices()){
            if(choice.equalsIgnoreCase(chosenChoice)){
                contains = true;
                break;
            }
        }
        if(!contains) {
            throw new IllegalChoiceException("This choice is not available for this body option." +
                    "Please choose a customization that is available for this body option.");}
        this.chosenChoice = chosenChoice;
    }
}