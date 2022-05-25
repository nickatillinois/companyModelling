package assemAssist.constraint;

import assemAssist.CarModel;
import assemAssist.exceptions.IllegalConstraintException;
import assemAssist.exceptions.OptionThenComponentException;

import java.util.ArrayList;
import java.util.HashSet;


/**
 * Class representing the constraint that some options imply a component.
 *
 * @author SWOP team 10
 */
public class IfOptionThenComponent extends Constraint {

    /**
     * A list of at least 2 elements implying that the first element implies the second component element.
     */
    private ArrayList<String> pairs;

    /**
     * Constructor for the IfOptionThenComponent class.
     * @param constraintPairs A list of at exactly 2 elements implying that the first element implies the latter component.
     * @throws IllegalConstraintException | constraintPairs = null
     */
    protected IfOptionThenComponent(ArrayList<String> constraintPairs) throws IllegalConstraintException {
        super();
        if(constraintPairs == null) {
            throw new IllegalConstraintException("The pairs cannot be null.");
        }
        addOptionThenComponentPair(new ArrayList<>(constraintPairs));
    }


    /**
     * Function that accepts a list of 1 option string followed by 1 component string, so that the first element implies the second element.
     * For example, if the first element is "Green", and the second element is "wheels",
     * then a Green color implies that the car has wheels.
     * @param constraintPairs A list of 1 option string followed by 1 component string
     * @throws IllegalConstraintException thrown if:
     *                                   - the list doesn't have 2 elements
     *                                   - One of the strings is null
     *                                   - Some strings are duplicated
     *                                   - The list is already in the list of constraints
     */
    private void addOptionThenComponentPair(ArrayList<String> constraintPairs) throws IllegalConstraintException {
        if (constraintPairs.size() != 2) {
            throw new IllegalConstraintException("Pair must be 2 elements");
        }
        for (String option : constraintPairs) {
            if (option == null) {
                throw new IllegalConstraintException("Given list contains null strings.");
            }
            // each string in pair must be distinct
            // get only the distinct strings
            // take each string in pair and add it to the set in lower case
            HashSet<String> distinct = new HashSet<>(constraintPairs.stream().map(String::toLowerCase).collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
            if (constraintPairs.size() != distinct.size()) {
                throw new IllegalConstraintException("Given list contains duplicate strings.");
            }
        }
        // set each string in pair to lower case
        ArrayList<String> lowerCasePair = new ArrayList<>();
        for (String option : constraintPairs) {
            lowerCasePair.add(option.toLowerCase());
        }
        this.pairs = lowerCasePair;
    }


    /**
     * Method that checks if the chosen specifications are in line with the constraints
     *
     * @param chosenSpecifications The chosen specifications
     * @throws IllegalArgumentException   | chosenSpecifications = null
     * @throws OptionThenComponentException | IfOptionThenComponent is not satisfied
     */
    @Override
    protected void isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, OptionThenComponentException {
        if (chosenSpecifications == null) {
            throw new IllegalArgumentException("Chosen specifications is null");
        }
        // for each key in chosenSpecifications, get the value
        HashSet<String> chosenOptionsSet = new HashSet<>();
        for (String key : chosenSpecifications.getChosenOptions().keySet()) {
            String value = chosenSpecifications.getChosenOptions().get(key);
            chosenOptionsSet.add(value.toLowerCase());
        }
        // for each pair in pairs, check if the set of chosen options contains the pair
        if (chosenOptionsSet.contains(this.pairs.get(0).toLowerCase()) && !chosenSpecifications.getChosenOptions().containsKey(this.pairs.get(1).toLowerCase())) {
            String warning =  "Option " + this.pairs.get(0) + " implies component " + this.pairs.get(1) + ".\nBut component " + this.pairs.get(1) + " is not chosen.\nPlease choose component " + this.pairs.get(1) + ".";
            throw new OptionThenComponentException(putInBox(warning));
        }
    }


    /**
     * Method that puts a given string in a message box.
     * @param warning The string to be put in a message box.
     *                The string must be in the format:
     *                "Option [option] implies component [component].\nBut component [component] is not chosen.\nPlease choose component [component]."
     *                For example:
     *                "Option Green implies component wheels.\nBut component wheels is not chosen.\nPlease choose component wheels."
     * @throws IllegalArgumentException | warning = null
     * @return The string in a message box.
     */
    private String putInBox(String warning){
        String[] warningLines = warning.split("\n");
        return "\n" +
                "|" +
                "-".repeat(warningLines[0].length() / 3) +
                "!" +
                "-".repeat(warningLines[0].length() / 3) +
                "|\n" +
                warning +
                "\n" +
                "|" +
                "-".repeat(warningLines[0].length() / 3) +
                "!" +
                "-".repeat(warningLines[0].length() / 3) +
                "|\n";
    }


    /**
     * Method compares a given IfOptionThenComponent with this IfOptionThenComponent.
     * @param obj The other IfOptionThenComponent to compare with.
     * @return True if the other IfOptionThenComponent is equal to this IfOptionThenComponent,
     *        false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
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


    /**
     * Method that returns a hash code for this IfOptionThenComponent.
     * @return A hash code for this IfOptionThenComponent.
     */
    @Override
    public int hashCode() {
        int hash = 51;
        // hashcode so that 2 lists of pairs are equal if they have the same size and the same elements in pairs in the same order
        hash = 31 * hash + this.pairs.size();
        // take the sun of the hashcodes of all elements in pairs, except the first one
        int sum = 0;
        for (int i = 1; i < this.pairs.size(); i++) {
            sum += this.pairs.get(i).hashCode();
        }
        hash = hash + (sum - pairs.get(0).hashCode());
        return hash;
    }

}