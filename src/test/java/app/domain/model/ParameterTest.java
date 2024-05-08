package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterTest {

    @Test
    public void getCode() {
        Parameter parameter = new Parameter("qwert", "RBC","Red Blood Cells");
        String expectedValue = "qwert";
        String realValue = parameter.getCode();
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void getName() {
        Parameter parameter = new Parameter("qwert", "RBC","Red Blood Cells");
        String expectedValue = "RBC";
        String realValue = parameter.getName();
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void getDescription() {
        Parameter parameter = new Parameter("qwert", "RBC","Red Blood Cells");
        String expectedValue = "Red Blood Cells";
        String realValue = parameter.getDescription();
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateCode() {
        int realValue = Parameter.validateCode("aaaad");
        int expectedValue = 0;
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateCode1() {
        int realValue = Parameter.validateCode("12345");
        int expectedValue = 0;
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateCode2() {
        int realValue = Parameter.validateCode("");
        int expectedValue = 1;
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateName() {
        int realValue = Parameter.validateName("RBC");
        int expectedValue = 0;
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateName1() {
        int realValue = Parameter.validateName("Red Blood Cells");
        int expectedValue = 2;
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateDescription() {
        int realValue = Parameter.validateDescription("Red Blood Cells");
        int expectedValue = 0;
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateDescription1() {
        int realValue = Parameter.validateDescription("Red Blood Cellsssssssss");
        int expectedValue = 3;
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateString(){
        Parameter p = new Parameter("a","a","a");
        String s = String.format("Parameter: %n Code: a %n Name: a %n Description: a %n ");
        assertNotNull(p.toString());
        assertEquals(s,p.toString());
    }
    @Test
    public void validateCode3(){
        Company.addCode("a");
        assertEquals(1,Parameter.validateCode("a"));
    }
}