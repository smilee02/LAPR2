package app.controller;

import app.domain.model.*;
import app.domain.store.ValidateStore;
import com.example2.EMRefValue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class RecordResultsControllerTest {
    static RecordResultsController recordResultsController = new RecordResultsController();
    private List<TestParameterResult> testParameterResultList;
    private static List<RecordResult> testResultsWithTests = new ArrayList<>();
    public static List<Parameter> parameterList = new ArrayList<>();
    public static final String ccn = "1234567812345678";
    private Company company = new Company("1");
    private Parameter parameter = new Parameter("1", "1", "1");
    private TypeOfTest typeOfTest = new TypeOfTest("blood", "blood", "blood");
    RegisterTest test2 = new RegisterTest(parameterList, Long.parseLong(ccn), typeOfTest, "111111111111", "11","abcd1");
    RecordResult r = new RecordResult(test2, testParameterResultList);
    Report rep = new Report("H", 0);
    ValidateStore store = new ValidateStore();
    TestParameterResult testParameterResult;

    @Test
    public void getTestResultsWithTests() {
        assertNotNull(RecordResultsController.getTestResultsWithTests());
    }

    @Test
    public void getParametersFromARegisteredTest() {
        assertEquals(recordResultsController.getParametersFromARegisteredTest(test2),parameterList);
    }

    @Test
    public void createNewTestResultsRecord() {
        assertNotNull(recordResultsController.createNewTestResultsRecord(test2));
    }


    @Test
    public void tryToGetANumber() {
        assertFalse(RecordResultsController.tryToGetANumber("f"));
        assertTrue(RecordResultsController.tryToGetANumber("2.0"));
    }

    @Test
    public void option() {
        assertFalse(RecordResultsController.option("f",3,3));
        assertFalse(RecordResultsController.option("2",4,5));
        assertTrue(RecordResultsController.option("2",1,3));
    }

    @Test
    public void validateTestParameterResult() {
        assertFalse(RecordResultsController.validateTestParameterResult(testParameterResult));
    }

    @Test
    public void saveRecordResults() {
        assertTrue(recordResultsController.saveRecordResults());
    }

    @Test
    public void getTestRegisteredList() {
        assertNotNull(RecordResultsController.getTestRegisteredList());
    }

    @Test
    public void addTestParameterResult() {
        assertFalse(recordResultsController.addTestParameterResult(testParameterResult));
    }

    @Test
    public void createCopyParameterList() {
        assertNotNull(recordResultsController.createCopyParameterList(test2));
    }

    @Test
    public void removeSelectedParameter() {
        TestParameterResult t = new TestParameterResult(1.0,"1",1.0,2.0,new Parameter("W31","W312","WB31123"));
        assertNotNull(recordResultsController.removeSelectedParameter(t,parameterList));
    }
}