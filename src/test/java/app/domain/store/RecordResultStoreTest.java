package app.domain.store;

import app.domain.model.*;
import com.example2.EMRefValue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class RecordResultStoreTest {
    private RecordResultStore recordResultStore = new RecordResultStore();
    private app.domain.model.Test test = new app.domain.model.Test();
    private RecordResult recordResult;
    private List<TestParameterResult> testParameterResultList;
    private static List<RecordResult> testResultsWithTests = new ArrayList<>();
    public static List<Parameter> parameterList = new ArrayList<>();
    public static final String ccn = "1234567812345678";
    private Company company = new Company("1");
    private Parameter parameter = new Parameter("1","1","1");
    private TypeOfTest typeOfTest = new TypeOfTest("blood","blood","blood");

    /*@Test
    public void newTestParameterResult(){
        RegisterTest test = new RegisterTest(parameterList,Long.parseLong(ccn),typeOfTest,"111111111111","11","abcd1");
        assertNotNull(recordResultStore.newTestParameterResult(parameter,1.0,12345,test,company));
        TestParameterResult testP = recordResultStore.newTestParameterResult(parameter,1.0,12345,test,company);
        assertNotEquals(recordResultStore.newTestParameterResult(parameter,1.0,12345,test,company),testP);
    }
    @Test
    public void newTestParameterResultWithOwnMetric() {
        RegisterTest test = new RegisterTest(parameterList,Long.parseLong(ccn),typeOfTest,"111111111111","11","abcd1");
        assertNotNull(recordResultStore.newTestParameterResultWithOwnMetric(parameter,1.0,12345,test,company,"-1.0"));
        TestParameterResult testP = recordResultStore.newTestParameterResultWithOwnMetric(parameter,1.0,12345,test,company,"-1.0");
        assertNotEquals(recordResultStore.newTestParameterResultWithOwnMetric(parameter,1.0,12345,test,company,"-1.0"),testP);

    }
    */
    @Test
    public void createNewRecordResult(){
        RegisterTest test = new RegisterTest(parameterList,Long.parseLong(ccn),typeOfTest,"111111111111","11","abcd1");
        assertNotNull(recordResultStore.createNewRecordResult(test));
    }

    @Test
    public void addTestParameterResult() {
        RegisterTest test2 = new RegisterTest(parameterList,Long.parseLong(ccn),typeOfTest,"111111111111","11","abcd1");
        recordResultStore.createNewRecordResult(test2);
        TestParameterResult test = new TestParameterResult(2.0,"-1.0",1.0,2.0,new Parameter("1","1","1"));
        assertTrue(recordResultStore.addTestParameterResult(test));
        TestParameterResult t = null;
        assertFalse(recordResultStore.addTestParameterResult(t));
    }

    @Test
    public void saveRecordResult() {
        assertFalse(recordResultStore.saveRecordResult());
    }

    @Test
    public void getTestParameterResultList() {
        assertNull(recordResultStore.getTestParameterResultList());
    }

    @Test
    public void getRecordResult() {
        assertNull(recordResultStore.getRecordResult());
    }
}