package assemAssist.constraint;

import assemAssist.CarModel;
import assemAssist.exceptions.IllegalConstraintException;
import assemAssist.exceptions.OptionThenComponentException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class IfOptionThenComponent extends Constraint {

    // set of lists of each 2 elements implying that option leads to Component.
    // if the array contains more than two elements, that indicates that the first element implicates the latter two.
    private static final HashSet<ArrayList<String>> pairs = new HashSet<>();

    protected IfOptionThenComponent() throws IllegalConstraintException {
        super();
        if(pairs.size() == 0) {
            addCurrentPairs();
        }
    }

    protected void addOptionThenComponentPair(ArrayList<String> pair) throws IllegalConstraintException {
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
        if (this.pairsContains(lowerCasePair)) {
            throw new IllegalConstraintException("Given list is already in the list of pairs.");
        }
        pairs.add(lowerCasePair);
    }

    // function to check if a given arraylist of strings is identical to an arraylist in pairs
    private boolean pairsContains(ArrayList<String> list) {
        for (ArrayList<String> pair : pairs) {
            // if each element in pair is also in list in the same sequence, return true
            if (pair.size() != list.size()) {
                continue;
            }
            // check if the first and the second element in pair are the same as the first and the second element in list
            if (pair.get(0).equals(list.get(0)) && pair.get(1).equals(list.get(1))) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, OptionThenComponentException {
        if (chosenSpecifications == null) {
            throw new IllegalArgumentException("Chosen specifications is null");
        }
        // for each key in chosenSpecifications, get the value
        // create a set of Strings of the values
        HashSet<String> chosenOptionsSet = new HashSet<>();
        for (String key : chosenSpecifications.getChosenOptions().keySet()) {
            String value = chosenSpecifications.getChosenOptions().get(key);
            chosenOptionsSet.add(value);
        }

        // for each pair in pairs, check if the set of chosen options contains the pair
        for (ArrayList<String> pair : pairs) {
            if (chosenOptionsSet.contains(pair.get(0)) && !chosenSpecifications.getChosenOptions().containsKey(pair.get(1))) {
                throw new OptionThenComponentException("Option: " + pair.get(0) + " implies component: " + pair.get(1) + ". But component: " + pair.get(1) + " is not chosen.");
            }
        }
        return true;
    }

    private void addCurrentPairs() throws IllegalConstraintException {
        this.addOptionThenComponentPair(new ArrayList<>(Arrays.asList("Sport", "Spoiler")));
    }

}