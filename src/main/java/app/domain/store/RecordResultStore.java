package app.domain.store;

import app.controller.ValidateController;
import app.domain.model.Test;
import app.domain.model.TestParameterResult;
import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.RecordResult;
import app.domain.model.RegisterTest;
import app.domain.shared.Files;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Store for RecordResult
 *
 * 
 */
public class RecordResultStore implements Serializable, Files {
    private Test test = new Test();
    private RecordResult recordResult;
    private List<TestParameterResult> testParameterResultList;
    private static List<RecordResult> testResultsWithTests = new ArrayList<>();

    public RecordResultStore() {
        try {
            testResultsWithTests = (List<RecordResult>) Files.decrypt("RecordResultStore.ser");
        } catch (Exception e) {

        }
        /**
         * No parameters needed
         */
    }

    /**
     * Create a new Test Parameter Result object
     * 
     * @param parameter    parameter chosen
     * @param result       result for this parameter
     * @param accessKey    to access the external modules
     * @param registerTest chosen test
     * @param company
     * @return
     */
    public TestParameterResult newTestParameterResult(Parameter parameter, double result, int accessKey,
            RegisterTest registerTest, Company company) {
        return test.addTestResult(parameter.getCode(), result, accessKey, registerTest, company);
    }

    /**
     * Create a new Test Parameter Result object
     * 
     * @param parameter    parameter chosen
     * @param result       result for this parameter
     * @param accessKey    to access the external modules
     * @param registerTest chosen test
     * @param company
     * @param metric       metric inserted
     * @return
     */
    public TestParameterResult newTestParameterResultWithOwnMetric(Parameter parameter, double result, int accessKey,
            RegisterTest registerTest, Company company, String metric) {
        return test.addTestResultWithMetric(parameter.getCode(), result, accessKey, registerTest, company, metric);
    }

    /**
     * Creates new RecordResult object
     * 
     * @param test chosen registered test
     * @return
     */
    public RecordResult createNewRecordResult(RegisterTest test) {

        testParameterResultList = new ArrayList<>();
        // System.out.println(test.getNhs());
        recordResult = new RecordResult(test, testParameterResultList);
        return recordResult;
    }

    /**
     * Adds test parameter result to the list
     * 
     * @param result the test parameter created
     * @return
     */
    public boolean addTestParameterResult(TestParameterResult result) {
        if (validateTestParameterResult(result)) {
            return testParameterResultList.add(result);
        }
        return false;
    }

    /**
     * Validates the test parameter result
     */
    public boolean validateTestParameterResult(TestParameterResult t) {
        if (t == null) {
            return false;
        }
        return !testParameterResultList.contains(t);
    }

    /**
     * Saves the record result to the system
     * 
     * @return if it was successfully added
     */
    public boolean saveRecordResult() {
        if (validateRecordResult(recordResult)) {
            ValidateController.addPositionToInsertRecordResults(recordResult);
            testResultsWithTests.add(recordResult);
            Files.encrypt("RecordResultStore.ser", testResultsWithTests);
            return true;
        }
        return false;
    }

    /**
     * Validates the Record Result created
     * 
     * @param t record result object
     * @return
     */
    public boolean validateRecordResult(RecordResult t) {
        if (t == null) {
            return false;
        }
        return !testResultsWithTests.contains(t);
    }

    /**
     * @return list of Recorded Results
     */
    public static List<RecordResult> getTestResultsWithTests() {
        return testResultsWithTests;
    }

    public List<TestParameterResult> getTestParameterResultList() {
        return testParameterResultList;
    }

    /**
     * @return the record result object
     */
    public RecordResult getRecordResult() {
        return recordResult;
    }
}
