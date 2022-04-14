package assemAssist.etc;

import assemAssist.CarOrder;

import java.util.Comparator;

public class CompletedCarOrderComparator implements Comparator {

    public CompletedCarOrderComparator() {
    }

    @Override
    public int compare(Object o1, Object o2) {
        CarOrder c1 = (CarOrder) o1;
        CarOrder c2 = (CarOrder) o2;
        if (c1.getCompletionTime().isBefore(c2.getCompletionTime())) {
            return 1;}
        else if (c1.getCompletionTime().isAfter(c2.getCompletionTime())){
            return -1;}
        else
            return 0;
    }

}
