package app.domain.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Date;

import static org.junit.Assert.*;

public class RegisterTestTest {
    public static List<Parameter> parameterList = new ArrayList<>();
    public static final String ccn = "1234567812345678";
    RegisterTest test;
    RegisterTest test2;
    public RegisterTestTest(){
        parameterList.add(new Parameter("BLOOD","blood","blood"));
        test = new RegisterTest(null,Long.parseLong(ccn),null,null,null,null);
        test2 = new RegisterTest(parameterList,Long.parseLong(ccn),new TypeOfTest("blood","blood","blood"),"123456789121","123456789121","abcd1");
    }
    @Test
    public void setCCN() {
        test.setCCN(Long.parseLong(ccn));
        assertEquals(Long.parseLong(ccn),test.getCCN());
    }

    @Test
    public void setData() {
        test.setData(null);
        Date date = test.getData();
        assertEquals(date,test.getData());
    }

    @Test
    public void getTestnumber() {
        assertNull(test.getTestnumber());
        assertNotNull(test2.getTestnumber());
    }

    @Test
    public void setNhs() {
        test2.setNhs("123456789");
        assertEquals("123456789",test2.getNhs());
    }

    @Test
    public void setParametersList() {
        assertNull(test.getTestnumber());
        assertNotNull(test2.getTestnumber());
    }

    @Test
    public void getParametersList() {
        assertNull(test.getParametersList());
        assertNotNull(test2.getParametersList());
    }

    @Test
    public void getCCN() {
        test.setCCN(Long.parseLong(ccn));
        assertEquals(Long.parseLong(ccn),test.getCCN());
    }

    @Test
    public void getNhs() {
        assertNull(test.getNhs());
        assertNotNull(test2.getNhs());
    }

    @Test
    public void setTestnumber() {
        test.setTestnumber("1234");
        assertEquals("1234",test.getTestnumber());
    }

    @Test
    public void getData() {
        test.setData(null);
        Date date = test.getData();
        assertNull(date);
        assertEquals(null,test.getData());
        //assertNotNull(date);
    }

    @Test
    public void getTypeOfTest() {
        assertNull(test.getTypeOfTest());
        assertNotNull(test2.getTypeOfTest());
    }
}