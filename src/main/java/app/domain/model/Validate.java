package app.domain.model;

import app.domain.shared.Constants;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validate implements Serializable {
    String cannot = "cannot be a empty field.";
    private final RegisterTest testRegistered;
    private RecordResult testResults;
    private Report diagnosis;
    private Date validateDate;

    /**
     * Constructor of Validate object
     * 
     * @param testRegistered
     */
    public Validate(RegisterTest testRegistered) {
        this.testRegistered = testRegistered;
    }

    /**
     * Sets the recorded results of the object
     * 
     * @param testResults
     */
    public void setTestResults(RecordResult testResults) {
        this.testResults = testResults;
    }

    /**
     * Sets the diagnosis of the object
     * 
     * @param diagnosis
     */
    public void setDiagnosis(Report diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setValidateDate(Date validateDate) {
        this.validateDate = validateDate;
    }

    /**
     * Returns the registered test of this object
     * 
     * @return
     */
    public RegisterTest getTestRegistered() {
        return testRegistered;
    }

    /**
     * Returns the recorded results of this object
     * 
     * @return
     */
    public RecordResult getTestResults() {
        return testResults;
    }

    /**
     * Returns the diagnosis of this object
     * 
     * @return
     */
    public Report getDiagnosis() {
        return diagnosis;
    }

    public Date getValidateDate() {
        return validateDate;
    }

    /**
     * Validates the validate object and all of its parameters
     * 
     * @return
     */
    public boolean validate() {
        if (this.testRegistered != null) {
            if (this.testResults != null) {
                if (this.diagnosis != null) {
                    return true;
                } else {
                    System.out.println("Diagnosis " + cannot);
                    return false;
                }
            } else {
                System.out.println("Test Results" + cannot);
                return false;
            }
        } else {
            System.out.println("Registered Test " + cannot);
            return false;
        }
    }

    /**
     * Returns the information of class and variables
     * 
     * @return
     */
    @Override
    public String toString() {
        return "\nTest Registered at : " + Constants.SIMPLE_DATE_FORMAT.format(testRegistered.getData())
                + "\nResults Recorded at : " + Constants.SIMPLE_DATE_FORMAT.format(testResults.getCreatedAt())
                + "\nReport created at: " + Constants.SIMPLE_DATE_FORMAT.format(diagnosis.getDate());
    }
}
