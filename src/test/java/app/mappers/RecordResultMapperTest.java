package app.mappers;

import app.domain.model.*;
import app.domain.store.ValidateStore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RecordResultMapperTest {
    private List<TestParameterResult> testParameterResultList = new ArrayList<>();
    private static List<RecordResult> testResultsWithTests = new ArrayList<>();
    public static List<Parameter> parameterList = new ArrayList<>();
    public static final String ccn = "1234567812345678";
    private Company company = new Company("1");
    private Parameter parameter = new Parameter("1", "1", "1");
    private TypeOfTest typeOfTest = new TypeOfTest("blood", "blood", "blood");
    RegisterTest test2 = new RegisterTest(parameterList, Long.parseLong(ccn), typeOfTest, "111111111111", "11","abcd1");
    RecordResult r = new RecordResult(test2, testParameterResultList);
    RecordResultMapper mapper = new RecordResultMapper();

    @Test
    public void toDTO() {
        r.getResultList().add(new TestParameterResult(1.0,"-1.0",1,1,new Parameter("1","1","!")));
        assertNotNull(mapper.toDTO(r.getResultList().get(0)));
    }

    @Test
    public void toTestParameterListDTO() {
        assertNotNull(mapper.toTestParameterListDTO(r.getResultList()));
    }

    @Test
    public void testToDTO() {
        assertNotNull(mapper.toDTO(r));
    }

    @Test
    public void toRecordResultListDTO() {
        assertNotNull(mapper.toRecordResultListDTO(testResultsWithTests));
    }
}