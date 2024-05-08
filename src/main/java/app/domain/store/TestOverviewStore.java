package app.domain.store;
import app.domain.model.*;

import java.io.Serializable;
import java.util.List;

public class TestOverviewStore implements Serializable {
   private static final List<Client> clientList= ClientStore.getClients();

    public List<RegisterTest> getTestList() {
        return RegisterTestStore.getRegisterTestList();
    }
    /*Parte criada no Sprint D*/
    public static Client getSpecificClient(int pos){
        return clientList.get(pos);
    }

    public static List<RecordResult> RecordResultList(){
        return RecordResultStore.getTestResultsWithTests();
    }
}
