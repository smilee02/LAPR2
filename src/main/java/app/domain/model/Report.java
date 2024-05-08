package app.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 */
public class Report implements Serializable {

    private final String report;
    private final List<String> diagnosis;
    private final int id;
    private Date date;

    /**
     * The variable with the maximum of words possible in the report
     */
    private static final int NUMEROPALAVRAS = 400;

    /**
     * The constructor saving the report and the id of the Report, the date when it
     * was made and initializing the array with the diagnosis
     *
     * @param report The report on the sample
     * @param id     The id of the report to identify the respective sample
     */
    public Report(String report, int id) {
        this.report = report;
        this.id = id;
        this.diagnosis = new ArrayList<>();
        this.date = new Date();
    }

    /**
     * Validates the given report to see if it has less words than the maximum words
     * allowed
     *
     * @param report Given report to be validated
     * @return True if the report is valid, false if not
     */
    public static boolean validateReport(String report) {
        String[] wordList = report.split(" ");
        return wordList.length <= NUMEROPALAVRAS;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Adds a diagnosis to the Report's list of diagnosis
     *
     * @param givenDiagnosis Given diagnosis to be added
     * @return True if the diagnosis is added with success, false if not
     */
    public boolean addDiagnosis(String givenDiagnosis) {
        return diagnosis.add(givenDiagnosis);
    }

    /**
     * Returns the date when the Report was made
     *
     * @return The date of the creation of the Report
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the id of the Report
     *
     * @return The Report's id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the report of the Report
     *
     * @return The Report's report
     */
    public String getReport() {
        return report;
    }
}
