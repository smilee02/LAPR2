package app.domain.model;


import com.example2.EMRefValue;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TestParameterResultTest {
    TestParameterResult testParameterResult = new TestParameterResult(1.0,"-1.0",1.0,2.0,new Parameter("WB432","W12300","1230"));


    @Test
    public void getResult() {
        double below = testParameterResult.getResult()-1;
        double above = testParameterResult.getResult()+1;
        assertNotEquals(below,testParameterResult.getResult());
        assertNotEquals(above,testParameterResult.getResult());
    }



    @Test
    public void getMetric() {
        assertNotNull(testParameterResult.getMetric());
    }

    @Test
    public void setResult() {
        TestParameterResult result = new TestParameterResult(2.0,"-1.0",1.0,2.0,new Parameter("WBC00","WBC00","WBC00"));
        result.setResult(3.0);
        assertNotEquals(testParameterResult,result);
    }


    @Test
    public void setMetric() {
        TestParameterResult result = new TestParameterResult(2.0,"-1.0",1.0,2.0,new Parameter("W31","W310","1"));
        result.setMetric("3");
        assertNotEquals(testParameterResult,result);
    }

    @Test
    public void testToString() {
        TestParameterResult result = new TestParameterResult(2.0,"-1.0",1.0,2.0,new Parameter("120","4123","W123"));
        assertNotEquals(testParameterResult.toString(),result.toString());
    }
}
