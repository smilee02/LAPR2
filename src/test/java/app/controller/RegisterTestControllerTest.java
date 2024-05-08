package app.controller;

import app.domain.model.Parameter;
import app.domain.model.RegisterTest;
import app.domain.model.TypeOfTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RegisterTestControllerTest {
    public RegisterTestController rtc = new RegisterTestController();
    public static List<Parameter> parameterList = new ArrayList<>();
    public static final String ccn = "1234567812345678";
    public static String number="12";
    @Test
    public void getClientList() {
        assertNotNull(rtc.getClientList());
    }

    @Test
    public void getTypeOfTestList() {
        assertNotNull(rtc.getTypeOfTestList());
    }

    /*@Test
    public void getTestType() {
        assertNotNull(rtc.getTestType(0));
    }*/

    @Test
    public void getParameterList() {
        assertNotNull(rtc.getParameterList());
    }

    @Test
    public void setParameterList() {
        List<Parameter> parameterList = new ArrayList<>();
        rtc.setParameterList(parameterList);
        assertEquals(parameterList,rtc.getParameterList());
        assertNotNull(rtc.getParameterList());
    }

    /*@Test
    public void registerTest() {
        assertTrue(rtc.RegisterTest(Long.parseLong(ccn),parameterList,new TypeOfTest("blood","blood","blood"),"123456781828","abcd1"));

    }*/

    @Test
    public void fillNumber() {
        assertEquals(12,rtc.fillNumber(number).length());
    }

    /*@Test
    public void saveRegisterTest() {
        rtc.RegisterTest(Long.parseLong(ccn),parameterList,new TypeOfTest("blood","blood","blood"),"123456781828","abcd1");
        assertTrue(rtc.saveRegisterTest());
    }*/

    @Test
    public void getRegisterTestList() {
        assertNotNull(rtc.getRegisterTestList());
    }

   /* @Test
    public void removeElement() {
        rtc.removeElement(0);
        assertEquals(0,rtc.getRegisterTestList().size());
    }*/


}