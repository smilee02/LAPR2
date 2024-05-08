package app.domain.store;

import app.controller.ValidateController;
import app.domain.model.RecordResult;
import app.domain.model.Report;
import app.domain.shared.Files;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class ReportStore implements Serializable, Files {

    /**
     * Constructor of the ReportStore
     */
    private static List<Report> listOfReport = new ArrayList<>();

    public ReportStore() {
        try {
            listOfReport = (List<Report>) Files.decrypt("ReportStore.ser");
        } catch (Exception e) {

        }
        /**
         * We dont need any parameter
         */
    }

    /**
     * Initializing the list of reports
     */

    /**
     * Returns the list of the existing reports
     *
     * @return A List with reports
     */
    public List<Report> getListOfReport() {
        return listOfReport;
    }

    /**
     * Saves a given report in the list containing all existing reports, returning
     * true or false according to the success of the saving
     *
     * @param report Report to be saved
     * @return True if the saving is successful, false if not
     */
    public boolean saveReport(Report report) {
        ValidateController.addPositionToInsertReport(report);
        listOfReport.add(report);
        Files.encrypt("ReportStore.ser", listOfReport);
        return true;
    }

    /*
     * public void importReports() {
     * 
     * }
     */
}
