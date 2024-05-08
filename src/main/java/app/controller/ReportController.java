package app.controller;

import app.domain.model.Company;
import app.domain.model.Report;
import app.domain.model.TestParameterResult;
import app.domain.shared.Constants;
import app.domain.shared.ValidateTestParameterResult;

import java.util.List;

/**
 * 
 */
public class ReportController {

    private final Company company;

    /**
     * The constructor initializes the object of the class Company
     */
    public ReportController() {
        this.company = Constants.COMPANY;
    }

    /**
     * Validates the given report
     *
     * @param report Given report to be validated
     * @return True if the report is valid, false if not
     */
    public boolean validateReport(String report) {
        return Report.validateReport(report);
    }

    /**
     * Saves the given report in the ReportStore
     *
     * @param report Given report to be saved
     * @return True if the report is saved, false if not
     */
    public boolean saveReport(Report report) {
        return company.getReportStore().saveReport(report);
    }

    /**
     * Adds the given diagnosis to the given report
     *
     * @param report    Given report
     * @param diagnosis Given diagnosis to be added
     * @return True if the addition was successful, false if not
     */
    public boolean addDiagnosis(Report report, String diagnosis) {
        return report.addDiagnosis(diagnosis);
    }

    /**
     * Returns the list of existing reports
     *
     * @return The list of reports
     */
    public List<Report> getReportList() {
        return company.getReportStore().getListOfReport();
    }

    /**
     * Validates the TestParameterResult to make the diagnosis
     *
     * @param testParameterResult Given testParameterResult to be validated
     * @return The String with the evaluation of the given TestParameterResult
     */
    public String validateTestParameterResult(TestParameterResult testParameterResult) {
        return ValidateTestParameterResult.validateTestParameterResult(testParameterResult);
    }

    /**
     * Creates an object of the class Report
     *
     * @param report The report written by the user
     * @param id     The id of the Report
     * @return The created Report
     */
    public Report createReport(String report, int id) {
        return new Report(report, id);
    }

    /**
     * Prints the given word into the console
     *
     * @param word Given word to be printed
     */
    public static void inConsole(String word) {
        System.out.println(word);
    }

    public void importReports() {

    }
}
