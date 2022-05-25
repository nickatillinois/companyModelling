package assemAssist.constraint;

import assemAssist.CarModel;
import assemAssist.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


/**
 * Class representing the constraint that some options imply a component.
 *
 * @author SWOP team 10
 */
public class IfOptionThenComponent extends Constraint {

    /**
     * Set of lists of each two elemtens implying that the first element implies the second.
     *
     */
    private ArrayList<String> pairs;

    /**
     * Initializes the pairs of options and components.
     */
    protected IfOptionThenComponent(ArrayList<String> pairs) throws IllegalConstraintException {
        super();
        addOptionThenComponentPair(pairs);
    }


    /**
     * Function that accepts a list of 1 option string followed by 1 component string, so that the first element implies the second element.
     * For example, if the first element is "Green", and the second element is "wheels",
     * then a Green color implies that the car has wheels.
     * @param pair A list of 1 option string followed by 1 component string
     * @throws IllegalConstraintException thrown if:
     *                                   - the list doesn't have 2 elements
     *                                   - One of the strings is null
     *                                   - Some strings are duplicated
     *                                   - The list is already in the list of constraints
     */
    private void addOptionThenComponentPair(ArrayList<String> pair) throws IllegalConstraintException {
        // must be 2 elements
        if (pair.size() != 2) {
            throw new IllegalConstraintException("Pair must be 2 elements");
        }
        for (String option : pair) {
            if (option == null) {
                throw new IllegalConstraintException("Given list contains null strings.");
            }
            // each string in pair must be distinct
            // get only the distinct strings
            // take each string in pair and add it to the set in lower case
            HashSet<String> distinct = new HashSet<>(pair.stream().map(String::toLowerCase).collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
            if (pair.size() != distinct.size()) {
                throw new IllegalConstraintException("Given list contains duplicate strings.");
            }
        }
        // set each string in pair to lower case
        ArrayList<String> lowerCasePair = new ArrayList<>();
        for (String option : pair) {
            lowerCasePair.add(option.toLowerCase());
        }
        pairs =lowerCasePair;
    }


    /**
     * method that checks if the chosen specifications are in line with the constraints
     *
     * @param chosenSpecifications The chosen specifications
     * @throws IllegalArgumentException   | chosenSpecifications = null
     * @throws OptionThenComponentException | IfOptionThenComponent is not satisfied
     *
     */
    @Override
    protected void isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, OptionThenComponentException {
        if (chosenSpecifications == null) {
            throw new IllegalArgumentException("Chosen specifications is null");
        }
        // for each key in chosenSpecifications, get the value
        // create a set of Strings of the values
        HashSet<String> chosenOptionsSet = new HashSet<>();
        for (String key : chosenSpecifications.getChosenOptions().keySet()) {
            String value = chosenSpecifications.getChosenOptions().get(key);
            chosenOptionsSet.add(value.toLowerCase());
        }

        // for each pair in pairs, check if the set of chosen options contains the pair
        if (chosenOptionsSet.contains(this.pairs.get(0).toLowerCase()) && !chosenSpecifications.getChosenOptions().containsKey(this.pairs.get(1).toLowerCase())) {
            String warning =  "Option " + this.pairs.get(0) + " implies component " + this.pairs.get(1) + ".\nBut component " + this.pairs.get(1) + " is not chosen.\nPlease choose component " + this.pairs.get(1) + ".";
            //Split warning into 4 equal lines
            String[] warningLines = warning.split("\n");
            StringBuilder boxMessage = new StringBuilder();
            boxMessage.append("\n");
            boxMessage.append("|");
            boxMessage.append("-".repeat(warningLines[0].length()/3));
            boxMessage.append("!");
            boxMessage.append("-".repeat(warningLines[0].length()/3));
            boxMessage.append("|\n");
            boxMessage.append(warning);
            boxMessage.append("\n");
            boxMessage.append("|");
            boxMessage.append("-".repeat(warningLines[0].length()/3));
            boxMessage.append("!");
            boxMessage.append("-".repeat(warningLines[0].length()/3));
            boxMessage.append("|\n");
            throw new OptionThenComponentException(boxMessage.toString());
        }
    }

    @Override
    protected void reset() {
        // clear the list of pairs
        pairs.clear();
    }

    /**
     * Method that adds the pairs given in the assignment to the list of pairs.
     */
    private void addCurrentPairs() throws IllegalConstraintException {
        this.addOptionThenComponentPair(new ArrayList<>(Arrays.asList("Sport", "Spoiler")));
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass())
            return false;
        IfOptionThenComponent other = (IfOptionThenComponent) obj;
        // 2 lists of pairs are equal if they have the same size and the same elements in pairs in the same order
        // if the size is different, the lists are not equal
        if (this.pairs.size() != other.pairs.size()) return false;
        // if the size is the same, check if the elements in pairs are the same
        for (int i = 0; i < this.pairs.size(); i++) {
            if (!this.pairs.get(i).equals(other.pairs.get(i))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 51;
        // hashcode so that 2 lists of pairs are equal if they have the same size and the same elements in pairs in the same order
        hash = 31 * hash + this.pairs.size();
        for (String pair : this.pairs) {
            hash = 31 * hash + pair.hashCode();
            int pairHash = pair.hashCode();
            int pairHash2 = pair.hashCode();
        }
        // take the sun of the hashcodes of all elements in pairs, except the first one
        int sum = 0;
        for (int i = 1; i < this.pairs.size(); i++) {
            sum += this.pairs.get(i).hashCode();
        }
        hash = hash + (sum - pairs.get(0).hashCode());
        return hash;
    }

}