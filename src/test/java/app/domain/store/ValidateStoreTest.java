package app.domain.store;

import app.domain.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ValidateStoreTest {
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

    @Test
    public void validate() {
        Validate validate = new Validate(test2);
        assertFalse(store.validate(validate));
        validate.setTestResults(r);
        assertFalse(store.validate(validate));
        validate.setDiagnosis(rep);
        assertTrue(store.validate(validate));
    }

    @Test
    public void saveWorkToBeValidated() {
        Validate validate = new Validate(test2);
        assertTrue(store.saveWorkToBeValidated(validate));
        validate.setTestResults(r);
        validate.setDiagnosis(rep);
        assertFalse(store.saveWorkToBeValidated(validate));
    }

    @Test
    public void saveValidate() {
        Validate validate = new Validate(test2);
        validate.setTestResults(r);
        validate.setDiagnosis(rep);
        assertNotNull(store.saveValidate(validate));
    }

    @Test
    public void getValidateList() {
        assertNotNull(store.getValidateList());
    }

    @Test
    public void getWorkToBeValidatedList() {
        assertNotNull(store.getWorkToBeValidatedList());
    }

    @Test
    public void addValidated() {
        Validate validate = new Validate(test2);
        validate.setTestResults(r);
        validate.setDiagnosis(rep);
        store.getWorkToBeValidatedList().add(validate);
        assertTrue(store.addValidated(0));
    }

    @Test
    public void removeValidated() {
        Validate validate = new Validate(test2);
        validate.setTestResults(r);
        validate.setDiagnosis(rep);
        store.getWorkToBeValidatedList().add(validate);
        assertTrue(store.removeValidated(0));
    }
}