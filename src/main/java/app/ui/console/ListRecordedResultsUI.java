package app.ui.console;

import app.controller.RecordResultsController;
import app.mappers.RecordResultMapper;
import app.ui.console.utils.Utils;

public class ListRecordedResultsUI implements Runnable {
    public void run(){
        RecordResultMapper mapper = new RecordResultMapper();
        Utils.showList(mapper.toRecordResultListDTO(RecordResultsController.getTestResultsWithTests()),"Results for Registered Tests:");
    }
}
