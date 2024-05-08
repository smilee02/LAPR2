package app.mappers;

import app.domain.model.*;
import app.domain.store.RecordResultStore;
import app.domain.store.ValidateStore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ValidateMapperTest {
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
    ValidateMapper mapper = new ValidateMapper();

    @Test
    public void toDTO() {
        Validate validate = new Validate(test2);
        validate.setTestResults(r);
        validate.setDiagnosis(rep);
        assertNotNull(mapper.toDTO(validate));
    }

    @Test
    public void testToDTO() {
        List<Validate> list = new ArrayList<>();
        Validate validate = new Validate(test2);
        validate.setTestResults(r);
        validate.setDiagnosis(rep);
        list.add(validate);
        assertNotNull(mapper.toDTO(list));
    }
}