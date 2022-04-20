package assemAssist.etc;

import assemAssist.CarOrder;

import java.util.Comparator;

public class PendingCarOrderComparator implements Comparator {

    public PendingCarOrderComparator() {
    }

    @Override
    public int compare(Object o1, Object o2) {
        CarOrder c1 = (CarOrder) o1;
        CarOrder c2 = (CarOrder) o2;
        if (c1.getEstCompletionTime().isBefore(c2.getEstCompletionTime())) {
            return 1;}
        else if (c1.getEstCompletionTime().isAfter(c2.getEstCompletionTime())){
            return -1;}
        else
            return 0;
    }

}
