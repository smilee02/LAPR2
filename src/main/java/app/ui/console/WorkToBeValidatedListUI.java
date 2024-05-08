package app.ui.console;

import app.controller.ValidateController;
import app.ui.console.utils.Utils;

public class WorkToBeValidatedListUI implements Runnable {
    static ValidateController validateController = new ValidateController();
    @Override
    public void run() {
        Utils.showList(ValidateController.getValidateDTOList(),"Work To Be Validated");
    }
}
