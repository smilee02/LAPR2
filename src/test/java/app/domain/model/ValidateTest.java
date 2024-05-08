package app.domain.model;


import com.example2.EMRefValue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ValidateTest {
    public static List<Parameter> parameterList = new ArrayList<>();
    public static final String ccn = "1234567812345678";
    private RegisterTest test = new RegisterTest(parameterList,Long.parseLong(ccn),new TypeOfTest("blood","blood","blood"),"123456789121","123456789121","abcd1");
    Date date = new Date();
    TestParameterResult t  = new TestParameterResult(1,"2",1.0,2.0,new Parameter("WBC00","WBC00","WBC00"));
    List<TestParameterResult> list = new ArrayList<>();
    RecordResult  recordResult = new RecordResult(test,list);
    RecordResult  recordResult2 = new RecordResult(test,list);
    private Validate validate = new Validate(test);
    private Validate validate2 = new Validate(test);

    @Test
    public void setTestResults() {
        TestParameterResult t = new TestParameterResult(2.0,"-1.0",1.0,2.0,new Parameter("3","2","WBC00"));
        recordResult2.getResultList().add(t);
        validate.setTestResults(recordResult);
        validate.setTestResults(recordResult2);
        assertNotEquals(validate,validate2);
    }

    @Test
    public void setDiagnosis() {
        Report r = new Report("1", 3);
        Report e = new Report("hey", 1);
        validate.setDiagnosis(r);
        validate.setDiagnosis(e);
        assertNotEquals(validate,validate2);
    }

    @Test
    public void getTestRegistered() {
        assertNotNull(validate.getTestRegistered());
    }

    @Test
    public void getTestResults() {
        validate.setTestResults(recordResult);
        assertNotNull(validate.getTestResults());
    }

    @Test
    public void getDiagnosis() {
        assertNotNull(validate);
        Report e = new Report("hey", 1);
        validate.setDiagnosis(e);
        assertNotNull(validate);
    }

    @Test
    public void validate() {
        assertFalse(validate.validate());
        validate.setTestResults(recordResult);
        assertFalse(validate.validate());
        Report e = new Report("hey", 1);
        validate.setDiagnosis(e);
        assertTrue(validate.validate());
    }

    @Test
    public void testToString() {
    }
}
