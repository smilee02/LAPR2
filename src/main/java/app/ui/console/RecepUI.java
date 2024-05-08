package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class RecepUI implements Runnable {
    public RecepUI() {
        /**
         * We dont need any parameter
         */
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register a new Client ", new ClientUI()));
        options.add(new MenuItem("List all Clients ", new ListClientUI()));
        options.add(new MenuItem("Register a test for a client", new RegisterTestUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
