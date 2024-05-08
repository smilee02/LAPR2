package app.domain.model;

import app.domain.model.Test;
import com.example2.EMRefValue;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestTest {
    private TestParameter testParameter = new TestParameter();
    private Test test = new Test();
    private Company company = new Company("m");
    public static List<Parameter> parameterList = new ArrayList<>();
    public static final String ccn = "1234567812345678";
    private Parameter p = new Parameter("1","1","1");


    @org.junit.Test
    public void getExternalModule() {
        RegisterTest test2 = new RegisterTest(parameterList,Long.parseLong(ccn),new TypeOfTest("blood","blood","blood"),"123456789121","123456789121","abcd1");
        assertNotNull(test.getExternalModule(test2));
        RegisterTest test3 = new RegisterTest(parameterList,Long.parseLong(ccn),new TypeOfTest("covid","blood","blood"),"123456789121","123456789121","abcd1");
        assertNotNull(test.getExternalModule(test3));
    }

    @org.junit.Test
    public void getReferenceValue() {
        RegisterTest test2 = new RegisterTest(parameterList,Long.parseLong(ccn),new TypeOfTest("blood","blood","blood"),"123456789121","123456789121","abcd1");
        assertNotNull(test.getReferenceValue(p,test2,12345));
        EMRefValue emRefValue = test.getReferenceValue(p,test2,12345);
        assertEquals(test.getReferenceValue(p,test2,12345),emRefValue);
    }

    @org.junit.Test
    public void getReferenceValueWithMetric() {
        RegisterTest test2 = new RegisterTest(parameterList,Long.parseLong(ccn),new TypeOfTest("blood","blood","blood"),"123456789121","123456789121","abcd1");
        assertNotNull(test.getReferenceValue(p,test2,12345));
        EMRefValue emRefValue = test.getReferenceValueWithMetric(p,test2,12345,"1");
        assertEquals(test.getReferenceValueWithMetric(p,test2,12345,"1"),emRefValue);
    }


    @org.junit.Test
    public void getTestParameter() {
        Parameter p = new Parameter("1","1","1");
        company.getParameterStore().getListOfParameters().add(p);
        System.out.println("eeeee");
        assertEquals(testParameter.getParameter("1",company),p);
    }

    @org.junit.Test
    public void addTestResult(){
        RegisterTest test2 = new RegisterTest(parameterList,Long.parseLong(ccn),new TypeOfTest("blood","blood","blood"),"123456789121","123456789121","a");
        TestParameterResult t = test.addTestResult("WBC00",1.0,12345,test2,company);
        assertEquals(test.addTestResult("WBC00",1.0,12345,test2,company).toString(),t.toString());
        assertNotNull(test.addTestResult("WBC00",1.0,12345,test2,company));
    }

    @org.junit.Test
    public void addTestResultmetric(){
        RegisterTest test2 = new RegisterTest(parameterList,Long.parseLong(ccn),new TypeOfTest("blood","blood","blood"),"123456789121","123456789121","a");
        TestParameterResult t = test.addTestResultWithMetric("WBC00",1.0,12345,test2,company,"-1");
        assertEquals(test.addTestResultWithMetric("WBC00",1.0,12345,test2,company,"-1").toString(),t.toString());
        assertNotNull(test.addTestResultWithMetric("WBC00",1.0,12345,test2,company,"-1"));
    }
}