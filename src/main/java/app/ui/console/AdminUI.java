package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AdminUI implements Runnable {
    public AdminUI() {
        /**
         * We dont need any parameter
         */
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register a new Employee ", new EmployeeUI()));
        options.add(new MenuItem("List all Employees ", new ListEmployeeUI()));
        options.add(new MenuItem("Register a new Clinical Analysis Laboratory ",
                new RegisterClinicalAnalysisLaboratoryUI()));
        options.add(new MenuItem("Register a new Type Of Test ", new CreateTypeTestUI()));
        options.add(new MenuItem("Register a new Parameter and Categorize It ", new ParameterUI()));
        options.add(new MenuItem("Register a new Parameter Category ", new ParameterCategoryUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
