package assemAssist.constraint;

import assemAssist.CarModel;
import assemAssist.exceptions.IllegalConstraintException;
import assemAssist.exceptions.OptionAThenOptionBException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class IfOptionAThenOptionB extends Constraint {

    // set of lists of each 2 elements implying that option A leads to option B
    // if the array contains more than two elements, that indicates that the first element implicates the latter two.
    private HashSet<ArrayList<String>> pairs = new HashSet<>();

    protected IfOptionAThenOptionB() throws IllegalConstraintException {
        super();
        if(pairs.size() == 0) {
            addCurrentPairs();
        }
    }

    protected void addOptionAThenOptionBPair(ArrayList<String> pair) throws IllegalConstraintException {
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
        if (this.pairsContains(lowerCasePair)) {
            throw new IllegalConstraintException("Given list is already in the list of pairs.");
        }
        pairs.add(lowerCasePair);
    }

    protected void addOptionAAndOptionBPair(ArrayList<String> pair) throws IllegalConstraintException {
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

    // function to check if a given arraylist of strings is identical to an arraylist in pairs
    private boolean pairsContains(ArrayList<String> list) {
        for (ArrayList<String> pair : pairs) {
            // if each element in pair is also in list in the same sequence, return true
            if (pair.size() != list.size()) {
                continue;
            }
            int equalEls = 0;
            for (int i = 0; i < pair.size(); i++) {
                if (pair.get(i).equals(list.get(i))) {
                    equalEls += 1;
                }
            }
            if (equalEls == pair.size()) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, OptionAThenOptionBException {
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
        for (ArrayList<String> pair : pairs) {
            boolean pairOK;
            if (chosenOptionsSet.contains(pair.get(0).toLowerCase())) {
                pairOK = false;
                // check if one of the other elements is in the set of chosen options
                for (int i = 1; i < pair.size(); i++) {
                    if (chosenOptionsSet.contains(pair.get(i).toLowerCase())) {
                        pairOK = true;
                        break;
                    }
                }
                if (!pairOK) {
                    // take a set consisting of the elements in pair and remove the first element
                    HashSet<String> pairImplied = new HashSet<>(pair);
                    pairImplied.remove(pair.get(0));
                    throw new OptionAThenOptionBException("Option " + pair.get(0) + " implies one of the following options: " + pairImplied.stream().toList());
                }
            }
        }
        return true;
    }

    private void addCurrentPairs() throws IllegalConstraintException {
        this.addOptionAThenOptionBPair(new ArrayList<>(Arrays.asList("Sport", "V6", "V8")));
        this.addOptionAThenOptionBPair(new ArrayList<>(Arrays.asList("V8", "Manual")));

    }

}