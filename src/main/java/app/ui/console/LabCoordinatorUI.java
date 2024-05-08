package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class LabCoordinatorUI implements Runnable {
    public LabCoordinatorUI() {
        /**
         * We dont need any parameter
         */
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Validate the Work Performed ", new ValidateUI()));
        options.add(new MenuItem("List all the work to be validated", new WorkToBeValidatedListUI()));
        options.add(new MenuItem("List all the work validated", new ValidatedWorkUI()));
        options.add(new MenuItem("Test Overview", new TestOverviewUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nLaboratory Coordinator Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
