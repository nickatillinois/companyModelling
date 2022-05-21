package assemAssist.constraint;

import assemAssist.CarModel;
import assemAssist.exceptions.IllegalConstraintException;
import assemAssist.exceptions.OptionAThenOptionBException;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class representing the constraint that each model needs to be in the catalog of the products offered by the car manufacturing company.
 *
 * @author SWOP team 10
 */
public class IfOptionAThenOptionB extends Constraint {


    /**
     * The set of lists of each 2 elements implying that option A leads to option B.
     * If the array contains more than two elements, that indicates that the first element implicates the latter two.
     */
    private ArrayList<String> pairs;


    /**
     * Constructor for the IfOptionAThenOptionB class.
     */
    protected IfOptionAThenOptionB(ArrayList<String> pairs) throws IllegalConstraintException {
        super();
        addOptionAThenOptionBPair(pairs);
    }

    /**
     * Function that accepts a list of options strings, so that the first element implies one of the latter elements.
     * For example, if the first element is "Green", and the two other elements "V6" and "V8",
     * then Green implies V6 or V8.
     * @param pair The list of options that are implied by the first element in the list.
     * @throws IllegalConstraintException thrown if:
     *                                   - the list has less than 2 elements
     *                                   - One of the strings is null
     *                                   - Some strings are duplicated
     *                                   - The list is already in the list of constraints
     */
    private void addOptionAThenOptionBPair(ArrayList<String> pair) throws IllegalConstraintException {
        if (pair.size() < 2) {
            throw new IllegalConstraintException("Given list must have at least 2 option strings.");
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
        this.pairs = lowerCasePair;
    }


    /**
     * Function that accepts a list of 1 option string followed by 1 option string, so that the first element implies
     * the second element and vice versa.
     * For example, if the first element is "sedan", and the second element is "Green",
     * then a sedan body implies a Green color and vice versa.
     * @param pair A list of two option strings
     * @throws IllegalConstraintException thrown if:
     *                                   - the list doesn't have 2 elements
     *                                   - One of the strings is null
     *                                   - Some strings are duplicated
     *                                   - The list is already in the list of constraints
     */
    private void addOptionAAndOptionBPair(ArrayList<String> pair) throws IllegalConstraintException {
        // must be of size 2
        if (pair.size() != 2) {
            throw new IllegalConstraintException("Given list is not of size 2.");
        }
        this.addOptionAThenOptionBPair(pair);
        ArrayList<String> reversed = new ArrayList<>();
        reversed.add(pair.get(1));
        reversed.add(pair.get(0));
        this.addOptionAThenOptionBPair(reversed);
    }

    /**
     * Method that checks if the chosen specifications are in line with the constraints
     *
     * @param chosenSpecifications The chosen specifications
     * @throws IllegalArgumentException   | chosenSpecifications = null
     * @throws OptionAThenOptionBException | IfOptionAThenOptionB is not satisfied
     *
     */
    @Override
    protected void isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, OptionAThenOptionBException {
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
        // for each pair in pairs, check if the first element is in the set of chosen options
        boolean pairOK;
        if (chosenOptionsSet.contains(this.pairs.get(0).toLowerCase())) {
            pairOK = false;
            // check if one of the other elements is in the set of chosen options
            for (int i = 1; i < this.pairs.size(); i++) {
                if (chosenOptionsSet.contains(this.pairs.get(i).toLowerCase())) {
                    pairOK = true;
                    break;
                }
            }
            if (!pairOK) {
                // take a set consisting of the elements in pair and remove the first element
                HashSet<String> pairImplied = new HashSet<>(this.pairs);
                pairImplied.remove(this.pairs.get(0));
                throw new OptionAThenOptionBException("Option " + this.pairs.get(0) + " implies one of the following options: " + pairImplied.stream().toList());
            }
        }
    }

    @Override
    protected void reset() {
        // clear the list of pairs
        pairs.clear();
    }
    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass())
            return false;
        IfOptionAThenOptionB other = (IfOptionAThenOptionB) obj;
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
        int hash = 7;
        // hashcode so that 2 lists of pairs are equal if they have the same size and the same elements in pairs in the same order
        hash = 31 * hash + this.pairs.size();
        for (String pair : this.pairs) {
            hash = 31 * hash + pair.hashCode();
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