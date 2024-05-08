package app.ui.console;

import app.controller.ValidateController;
import app.ui.console.utils.Utils;

public class ValidatedWorkUI implements Runnable {
    @Override
    public void run() {
        Utils.showList(ValidateController.getValidatedList(),"Validated Work");
    }
}
