package app.domain.model;


import com.example2.EMRefValue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class RecordResultTest {
    List<Parameter> p = new ArrayList<>();
    TypeOfTest test = new TypeOfTest("1","1","1");
    RegisterTest rt = new RegisterTest(p,1,test,"2","2","abcd1");
    Date date = new Date();
    TestParameterResult t  = new TestParameterResult(1,"2", 1.0,2.0,new Parameter("0","W0",""));
    List<TestParameterResult> list = new ArrayList<>();
    RecordResult  recordResult = new RecordResult(rt,list);

    @Test
    public void getTest() {
        assertNotNull(recordResult.getTest());
    }

    @Test
    public void getResultList() {
        assertNotNull(recordResult.getResultList());
    }

    @Test
    public void getCreatedAt() {
        assertNotNull(recordResult.getCreatedAt());
    }

    @Test
    public void tostring(){
        assertNotNull(list);
        assertNotNull(recordResult);
        String s = "";
        assertNotEquals(s,recordResult.toString());
    }

}
