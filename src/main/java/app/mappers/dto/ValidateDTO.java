package app.mappers.dto;

import app.domain.model.RecordResult;
import app.domain.model.RegisterTest;
import app.domain.model.Report;
import app.domain.model.Validate;
import app.domain.shared.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidateDTO {
    SimpleDateFormat format = Constants.SIMPLE_DATE_FORMAT;
    private RegisterTest testRegistered;
    private RecordResult testResults;
    private Report diagnosis;

    /**
     * Creates a DTO of a Validate object
     *
     * @param testRegistered
     * @param testResults
     * @param diagnosis
     */

    public ValidateDTO(RegisterTest testRegistered, RecordResult testResults, Report diagnosis) {
        this.testRegistered = testRegistered;
        this.testResults = testResults;
        this.diagnosis = diagnosis;
    }

    /**
     * @return test registered of the validate object
     */
    public RegisterTest getTestRegistered() {
        return testRegistered;
    }

    /**
     * @return recorded results of a validated object
     */
    public RecordResult getTestResults() {
        return testResults;
    }

    /**
     * @return diagnosis of a validated object
     */
    public Report getDiagnosis() {
        return diagnosis;
    }

    /**
     * @return the class and variable information in a string form
     */
    @Override
    public String toString() {
        return String.format("\n Test Registered Date: %s \n Test Results Date: %s \n Diagnosis Date: %s \n",
                format.format(getTestRegistered().getData()), format.format(getTestResults().getCreatedAt()),
                format.format(getDiagnosis().getDate()));
    }

}
