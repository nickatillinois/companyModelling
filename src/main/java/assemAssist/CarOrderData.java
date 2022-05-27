package assemAssist;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * This class represent objects that hold the information of a car order. This allows the domain layer to pass on this info
 * without having to pass any objects from the CarOrder class.
 */

public class CarOrderData {

    private final String MODELNAME;
    private final Map<String, String> CAROPTIONS;
    private final int ORDERID;
    private final String GARAGEHOLDER;
    private final LocalDateTime ORDERINGTIME;
    private final LocalDateTime ESTCOMPLETIONTIME;
    private final LocalDateTime COMPLETIONTIME;
    private final boolean COMPLETED;

    public String getMODELNAME() {
        return MODELNAME;
    }

    public Map<String, String> getCAROPTIONS() {
        return CAROPTIONS;
    }

    public int getORDERID() {
        return ORDERID;
    }

    public String getGARAGEHOLDER() {
        return GARAGEHOLDER;
    }

    public LocalDateTime getORDERINGTIME() {
        return ORDERINGTIME;
    }

    public LocalDateTime getESTCOMPLETIONTIME() {
        return ESTCOMPLETIONTIME;
    }

    public LocalDateTime getCOMPLETIONTIME() {
        return COMPLETIONTIME;
    }

    public boolean isCOMPLETED() {
        return COMPLETED;
    }

    public CarOrderData(String modelName, Map<String, String> carOptions, int orderID, String garageHolder, LocalDateTime orderingTime, LocalDateTime estCompletionTime, LocalDateTime completionTime, boolean completed) {
        this.MODELNAME = modelName;
        this.CAROPTIONS = carOptions;
        this.ORDERID = orderID;
        this.GARAGEHOLDER = garageHolder;
        this.ORDERINGTIME = orderingTime;
        this.ESTCOMPLETIONTIME = estCompletionTime;
        this.COMPLETIONTIME = completionTime;
        this.COMPLETED = completed;
    }
}
