package assemAssist.constraint;

import assemAssist.CarModel;
import assemAssist.Catalog;
import assemAssist.exceptions.IllegalConstraintException;
import assemAssist.exceptions.IllegalModelException;
import assemAssist.exceptions.OptionAThenOptionBException;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class representing the constraint that some options may imply other options.
 *
 * @author SWOP team 10
 */
public class IfOptionAThenOptionB extends Constraint {


    /**
     * A list of at least 2 elements implying that the first element implies the latter ones.
     */
    private ArrayList<String> constraintPairs;


    /**
     * Constructor for the IfOptionAThenOptionB class.
     * @param constraintPairs A list of at least 2 elements implying that the first element implies the latter ones.
     * @throws IllegalConstraintException | constraintPairs = null
     */
    protected IfOptionAThenOptionB(ArrayList<String> constraintPairs) throws IllegalConstraintException {
        super();
        if(constraintPairs == null) {
            throw new IllegalConstraintException("The constraintPairs cannot be null.");
        }
        addOptionAThenOptionBPair(new ArrayList<>(constraintPairs));
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
            HashSet<String> distinctPairs = new HashSet<>(pair.stream().map(String::toLowerCase).collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
            if (pair.size() != distinctPairs.size()) {
                throw new IllegalConstraintException("Given list contains duplicate strings.");
            }
        }
        ArrayList<String> lowerCasePair = new ArrayList<>();
        for (String option : pair) {
            lowerCasePair.add(option.toLowerCase());
        }
        this.constraintPairs = lowerCasePair;
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
    protected void isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, OptionAThenOptionBException, IllegalModelException {
        if (chosenSpecifications == null) {
            throw new IllegalArgumentException("Chosen specifications is null");
        }
        HashSet<String> chosenOptionsSet = new HashSet<>();
        for (String key : chosenSpecifications.getChosenOptions().keySet()) {
            String value = chosenSpecifications.getChosenOptions().get(key);
            chosenOptionsSet.add(value.toLowerCase());
        }
        // for each pair in pairs, check if the first element is in the set of chosen options
        boolean pairOK;
        if (chosenOptionsSet.contains(this.constraintPairs.get(0).toLowerCase())) {
            pairOK = false;
            // check if one of the other elements is in the set of chosen options
            for (int i = 1; i < this.constraintPairs.size(); i++) {
                if (chosenOptionsSet.contains(this.constraintPairs.get(i).toLowerCase())) {
                    pairOK = true;
                    break;
                }
            }
            if (!pairOK) {
                String causingComponent = getCausingAndImplyingComponent(chosenSpecifications)[0];
                String impliedComponent = getCausingAndImplyingComponent(chosenSpecifications)[1];
                HashSet<String> pairImplied = new HashSet<>(this.constraintPairs);
                pairImplied.remove(this.constraintPairs.get(0));
                String warning = "You chose a " +  this.constraintPairs.get(0) + " " + causingComponent + ".\nThis implies one of the following options for component " + impliedComponent + ": " + pairImplied.stream().toList() + ".\nPlease choose one of these options.";
                throw new OptionAThenOptionBException(putInBox(warning));
            }
        }
    }


    /**
     * Method that puts a given string in a message box.
     * @param warning The string to be put in a message box.
     *                The string must be in the format:
     *                "You chose a [option] [component].\nThis implies one of the following options for component [component]: [options].\nPlease choose one of these options."
     *                For example:
     *                "You chose a V6 engine.\nThis implies one of the following options for component engine: V6, V8.\nPlease choose one of these options."
     * @throws IllegalArgumentException | warning = null
     * @return The string in a message box.
     */
    private String putInBox(String warning) {
        if (warning == null) {
            throw new IllegalArgumentException("Warning is null");
        }
        String[] warningLines = warning.split("\n");
        return "\n" +
                "|" +
                "-".repeat(warningLines[0].length()) +
                "!" +
                "-".repeat(warningLines[0].length()) +
                "|\n" +
                warning +
                "\n" +
                "|" +
                "-".repeat(warningLines[0].length()) +
                "!" +
                "-".repeat(warningLines[0].length()) +
                "|\n";
    }


    /**
     * Method that returns the component that is implied by the first element in the list of options.
     * @param chosenSpecifications The chosen specifications
     * @return An array of two strings, the first one is the component that is implied by the first element in the list of options,
     *        and the second one is the component that is implied by the second element in the list of options.
     * @throws IllegalArgumentException | chosenSpecifications = null
     */
    private String[] getCausingAndImplyingComponent(CarModel chosenSpecifications) throws IllegalModelException {
        if (chosenSpecifications == null) {
            throw new IllegalArgumentException("Chosen specifications is null");
        }
        final String[] causingComponent = new String[1];
        final String[] impliedComponent = new String[1];
        new Catalog().getModel(chosenSpecifications.getModelName()).getAvailableOptions().forEach((key, value) -> {
            for (String option : value) {
                if (option.equalsIgnoreCase(this.constraintPairs.get(0))) {
                    causingComponent[0] = key;
                }
                if (option.equalsIgnoreCase(this.constraintPairs.get(1))) {
                    impliedComponent[0] = key;
                }
            }
        });
        return new String[]{causingComponent[0], impliedComponent[0]};
    }


    /**
     * Method compares a given IfOptionAThenOptionB with this IfOptionAThenOptionB.
     * @param obj The other IfOptionAThenOptionB to compare with.
     * @return True if the other IfOptionAThenOptionB is equal to this IfOptionAThenOptionB,
     *        false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass())
            return false;
        IfOptionAThenOptionB other = (IfOptionAThenOptionB) obj;
        if (this.constraintPairs.size() != other.constraintPairs.size()) return false;
        for (int i = 0; i < this.constraintPairs.size(); i++) {
            if (!this.constraintPairs.get(i).equals(other.constraintPairs.get(i))) return false;
        }
        return true;
    }


    /**
     * Method that returns a hash code for this IfOptionAThenOptionB.
     * @return A hash code for this IfOptionAThenOptionB.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        // hashcode so that 2 lists of pairs are equal iff they have the same size and the same elements in pairs in the same order
        hash = 31 * hash + this.constraintPairs.size();
        for (String pair : this.constraintPairs) {
            hash = 31 * hash + pair.hashCode();
        }
        int sum = 0;
        for (int i = 1; i < this.constraintPairs.size(); i++) {
            sum += this.constraintPairs.get(i).hashCode();
        }
        hash = hash + (sum - constraintPairs.get(0).hashCode());
        return hash;
    }
}