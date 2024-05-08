package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.store.RecordResultStore;
import app.domain.store.RegisterTestStore;
import app.domain.store.SampleRecorderStore;
import app.mappers.RecordResultMapper;
import app.mappers.dto.RecordResultDTO;
import app.mappers.dto.TestParameterResultDTO;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller of the Recording of the results
 * 
 */
public class RecordResultsController {
    static Company company = Constants.COMPANY;
    static RecordResultStore recordResultStore = new RecordResultStore();
    static String aux;

    /**
     * Prints in the console the string passed as a parameter
     * 
     * @param string printed
     */
    public static void print(String string) {
        System.out.print(string);
    }

    /**
     * @return the list with the results for the various tests
     */
    public static List<RecordResult> getTestResultsWithTests() {
        return RecordResultStore.getTestResultsWithTests();
    }

    public boolean verifyTestRegisteredBySampleCode(String sampleCode) {
        try {
            RegisterTest t = SampleRecorderStore.getTest(sampleCode);
            if (t == null) {
                print("There is no test registered for this sample please make sure that a sample for a test was collected");
                return false;
            }
        } catch (NullPointerException e) {
            print("There is no test registered for this sample please make sure that a sample for a test was collected");
            return false;
        }
        return true;
    }

    public RegisterTest getTestRegisteredBySampleCode(String sampleCode) {
        return SampleRecorderStore.getTest(sampleCode);
    }

    /**
     * @param test the parameters are from this test
     * @return the parameters from the test
     */
    public List<Parameter> getParametersFromARegisteredTest(RegisterTest test) {
        return test.getParametersList();
    }

    /**
     * Creates a new Test with results empty
     * 
     * @param registerTest test chosen
     * @return a RecordResultObject
     */
    public RecordResult createNewTestResultsRecord(RegisterTest registerTest) {
        return company.getRecordResultStore().createNewRecordResult(registerTest);
    }

    /**
     * Creates a new Result for a parameter
     * 
     * @param parameterList list of parameters from a given test
     * @param accessKey     key to access the external modules
     * @param test          test registered chosen
     * @return
     */
    public static TestParameterResult recordTestResults(List<Parameter> parameterList, int accessKey,
            RegisterTest test) {
        int pos;
        do {
            pos = Utils.showAndSelectIndex(parameterList, "Parameters Associated with This Test");
        } while (!checkParameter(pos, parameterList));
        Parameter parameter = parameterList.get(pos);
        do {
            aux = Utils.readLineFromConsole("Insert the result value of this parameter:");
        } while (!tryToGetANumber(aux));
        double resultValue = Double.parseDouble(aux);
        do {
            aux = Utils.readLineFromConsole("Would you like to insert a metric for this parameter?\n(1)Yes\n(2)No");
        } while (!option(aux, 1, 2));
        if (Integer.parseInt(aux) == 1) {
            aux = Utils.readLineFromConsole("Insert the metric desired for this parameter:");
            return company.getRecordResultStore().newTestParameterResultWithOwnMetric(parameter, resultValue, accessKey,
                    test, company, aux);
        }
        return company.getRecordResultStore().newTestParameterResult(parameter, resultValue, accessKey, test, company);
    }

    private static boolean checkParameter(int pos, List<Parameter> parameterList) {
        try {
            Parameter p = parameterList.get(pos);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            print("0 is not a valid option please choose between 1 and " + (parameterList.size() + 1) + "\n\n");
            return false;
        }
        return true;
    }

    /**
     * Checks if it is a double
     * 
     * @param aux the variable to be checked
     * @return
     */
    public static boolean tryToGetANumber(String aux) {
        try {
            double a = Double.parseDouble(aux);
        } catch (NumberFormatException e) {
            print("The result value must be a number");
            return false;
        }
        return true;
    }

    /**
     * Checks if it is a int and is within the range of max and min
     * 
     * @param aux the variable to be checked
     * @param min the minimum of the option
     * @param max the maximum of the option
     * @return boolean telling if it is in range or not
     */
    public static boolean option(String aux, int min, int max) {
        int op;
        try {
            op = Integer.parseInt(aux);
        } catch (NumberFormatException e) {
            print("This is not a valid option");
            return false;
        }
        if ((op <= max) && op >= min) {
            return true;
        } else
            return false;
    }

    /**
     * Validates the Result of a Test Parameter
     * 
     * @param t the one to be validated
     * @return
     */
    public static boolean validateTestParameterResult(TestParameterResult t) {
        return company.getRecordResultStore().validateTestParameterResult(t);
    }

    /**
     * Saves the results in a List
     * 
     * @return if it saved correctly or not
     */
    public boolean saveRecordResults() {
        return company.getRecordResultStore().saveRecordResult();
    }

    /**
     * @return list of the registered tests
     */
    public static List<RegisterTest> getTestRegisteredList() {
        return RegisterTestStore.getRegisterTestList();
    }

    /**
     * Adds the result of one test parameter to the results for a test
     * 
     * @param result the one to be added
     * @return if it was successful
     */
    public boolean addTestParameterResult(TestParameterResult result) {
        return company.getRecordResultStore().addTestParameterResult(result);
    }

    /**
     * Creates a copy of the list of parameters
     * 
     * @param test the registered test chosen
     * @return a list of all parameters from the test chosen
     */
    public List<Parameter> createCopyParameterList(RegisterTest test) {
        return new ArrayList<>(getParametersFromARegisteredTest(test));
    }

    /**
     * After selecting a parameter x it removes it from the copy created for
     * aesthetic purposes
     * 
     * @param result        associated with the parameter to be remover
     * @param parameterList the parameter list which x is going to be removed
     * @return the list without x
     */
    public List<Parameter> removeSelectedParameter(TestParameterResult result, List<Parameter> parameterList) {
        Parameter parameter = null;
        String parameterCode = result.getParameter().getCode();
        int length = company.getParameterStore().getListOfParameters().size();
        for (int j = 0; j < length; j++) {
            if (parameterCode.equals(company.getParameterStore().getListOfParameters().get(j).getCode())) {
                parameter = company.getParameterStore().getListOfParameters().get(j);
            }
        }
        parameterList.remove(parameter);

        return parameterList;
    }

    public TestParameterResultDTO toDTO(TestParameterResult result) {
        RecordResultMapper mapper = new RecordResultMapper();
        return mapper.toDTO(result);
    }

    public RecordResultDTO toDTO(RecordResult result) {
        RecordResultMapper mapper = new RecordResultMapper();
        return mapper.toDTO(result);
    }
}
