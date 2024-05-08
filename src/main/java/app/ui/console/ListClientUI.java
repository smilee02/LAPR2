package app.ui.console;

import app.domain.model.Company;
import app.domain.store.ClientStore;
import app.ui.console.utils.Utils;

public class ListClientUI implements Runnable {
    public ListClientUI(){
        /**
         * We dont need any parameter
         */
    }

    @Override
    public void run() {
        Utils.showList(ClientStore.getClients(),"Clients:");
    }
}

