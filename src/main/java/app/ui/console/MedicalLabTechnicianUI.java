package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MedicalLabTechnicianUI implements Runnable {
    public MedicalLabTechnicianUI() {
        /**
         * We dont need any parameter
         */
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Record samples in scope of a given test ", new SampleRecorderUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nMedical Lab Technician:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
