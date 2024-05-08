package app.mappers.dto;

import app.domain.model.*;
import app.domain.store.RecordResultStore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestDetailsResultsDTOTest {
    private RecordResultStore recordResultStore = new RecordResultStore();
    private app.domain.model.Test test = new app.domain.model.Test();
    private RecordResult recordResult;
    private List<TestParameterResult> testParameterResultList = new ArrayList<>();
    private static List<RecordResult> testResultsWithTests = new ArrayList<>();
    public static List<Parameter> parameterList = new ArrayList<>();
    public static final String ccn = "1234567812345678";
    private Company company = new Company("1");
    private Parameter parameter = new Parameter("1","1","1");
    private TypeOfTest typeOfTest = new TypeOfTest("blood","blood","blood");
    private RegisterTest registerTest = new RegisterTest(parameterList,1,typeOfTest,"1","1","!");

    @Test
    public void getString() {
        testParameterResultList.add(new TestParameterResult(1,"-1.0",-1.0,-1.0,new Parameter("1","1","1")));
        recordResult = new RecordResult(registerTest,testParameterResultList);
       TestDetailsResultsDTO detailsResultsDTO = new TestDetailsResultsDTO(recordResult);
        assertNotNull(detailsResultsDTO);
        assertNotNull(detailsResultsDTO.getRecordResult());
        assertEquals("1   1.0 -1.0   [-1.0,-1.0]   -1.0\n",detailsResultsDTO.getResultsForATest());
        assertEquals(String.format("Test Type: blood%n" +
                "Categories:%n"),detailsResultsDTO.getTest());
        assertEquals("1   1.0 -1.0   [-1.0,-1.0]   -1.0\n",detailsResultsDTO.resultsOnlyString());
         assertEquals("",detailsResultsDTO.categorites());
        assertEquals(String.format("Test Type: blood%n" +
                "Categories:%n"),detailsResultsDTO.testString());
    }

}