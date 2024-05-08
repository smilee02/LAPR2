package app.ui.console;

import app.domain.model.Company;
import app.ui.console.utils.Utils;

public class ListEmployeeUI implements Runnable {
    public ListEmployeeUI(){
        /**
         * We dont need any parameter
         */
    }

    @Override
    public void run() {
        Utils.showList(Company.getEmployeeStore().getEmployees(),"Employees:");
    }
}

