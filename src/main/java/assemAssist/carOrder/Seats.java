package assemAssist.carOrder;

import assemAssist.ProductionScheduler;
import assemAssist.exceptions.IllegalChoiceException;

/**
 * Class representing a seats option.
 *
 * @author SWOP team 10
 */
public class Seats extends Option{



    /**
     * Creates a new seats option and sets the given customization choice.
     *
     * @param chosenChoice the given customization choice.
     * @throws IllegalArgumentException | chosenChoice is null
     *                                  | chosenChoice is the empty string
     * @throws IllegalChoiceException    | chosenChoice is not in getAvailableChoices()
     */
    public Seats(String chosenChoice) throws IllegalChoiceException {
        super();
        if(chosenChoice == null){throw new IllegalArgumentException("A chosenChoice for  " +
                "a seats option cannot be null.");}
        if(chosenChoice.length() == 0){throw new IllegalArgumentException("A chosenChoice for " +
                "a seats option cannot be the empty string.");}
        boolean contains = false;
        for(String choice: ProductionScheduler.getAvailableSeatsChoices()){
            if(choice.equalsIgnoreCase(chosenChoice)){
                contains = true;
                break;
            }
        }
        if(!contains) {
            throw new IllegalChoiceException("This choice is not available for this seats option." +
                    " Please choose a customization that is available for this seats option.");}
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
                "a seats option cannot be null.");}
        if(chosenChoice.length() == 0){throw new IllegalArgumentException("A chosenChoice for " +
                "a seats option cannot be the empty string.");}
        boolean contains = false;
        for(String choice: ProductionScheduler.getAvailableSeatsChoices()){
            if(choice.equalsIgnoreCase(chosenChoice)){
                contains = true;
                break;
            }
        }
        if(!contains) {
            throw new IllegalChoiceException("This choice is not available for this seats option." +
                    "Please choose a customization that is available for this seats option.");}
        this.chosenChoice = chosenChoice;
    }
}