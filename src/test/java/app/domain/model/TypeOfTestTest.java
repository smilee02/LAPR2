package app.domain.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TypeOfTestTest {

    @Test
    public void getCode() {
        TypeOfTest typeOfTest = new TypeOfTest("qweps", "Blood", "Seringue");
        String expectedValue = "qweps";
        String realValue = typeOfTest.getCode();
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void getCollectingMethods() {
        TypeOfTest typeOfTest = new TypeOfTest("qweps", "Blood", "Seringue");
        String expectedValue = "Seringue";
        String realValue = typeOfTest.getCollectingMethods();
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void getDescription() {
        TypeOfTest typeOfTest = new TypeOfTest("qweps", "Blood", "Seringue");
        String expectedValue = "Blood";
        String realValue = typeOfTest.getDescription();
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateTypeTest() {
        int expectedValue = 0;
        int realValue = TypeOfTest.validateTypeTest("skrse", 1);
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateTypeTest1() {
        TypeOfTest typeOfTest = new TypeOfTest("qwert", "Blood", "Seringue");
        typeOfTest.addCode();
        int expectedValue = 1;
        int realValue = TypeOfTest.validateTypeTest("qwert", 1);
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateTypeTest2() {
        int expectedValue = 1;
        int realValue = TypeOfTest.validateTypeTest("", 1);
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateTypeTest3() {
        int expectedValue = 0;
        int realValue = TypeOfTest.validateTypeTest("12345", 1);
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateTypeTest4() {
        int expectedValue = 0;
        int realValue = TypeOfTest.validateTypeTest("Blood", 2);
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateTypeTest5() {
        int expectedValue = 2;
        int realValue = TypeOfTest.validateTypeTest("aaaaaaaaaaaaaaaaaaa", 2);
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateTypeTest6() {
        int expectedValue = 2;
        int realValue = TypeOfTest.validateTypeTest("", 2);
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateTypeTest7() {
        int expectedValue = 0;
        int realValue = TypeOfTest.validateTypeTest("Seringue", 3);
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateTypeTest8() {
        int expectedValue = 3;
        int realValue = TypeOfTest.validateTypeTest("aaaaaaaaaaaaaaaaaaaaaaaaaa", 3);
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateTypeTest9() {
        int expectedValue = 3;
        int realValue = TypeOfTest.validateTypeTest("", 3);
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void validateTypeTest10() {
        int expectedValue = 2;
        int realValue = TypeOfTest.validateTypeTest("aaaaaaaaaaaaaaa", 2);
        assertEquals(expectedValue, realValue);

    }
    @Test
    public void validateTypeTest11() {
        int expectedValue = 3;
        int realValue = TypeOfTest.validateTypeTest("aaaaaaaaaaaaaaaaaaaa", 3);
        assertEquals(expectedValue, realValue);

    }
    /*@Test
    public void string(){
        TypeOfTest t = new TypeOfTest("a","a","a");
        ParameterCategory o = new ParameterCategory("a","a");
        List<Parameter> a = new ArrayList<>();
        Parameter p = new Parameter("","","");
        Parameter m = new Parameter("","","");
        o.addParameter(p);o.addParameter(m);
        Company.getParametercategorylist().add(o);
        t.addCategories(0);
        String s = "Type of test: \n Code: a \n Description: a \n Collecting Methods: a \n Categories: \n Category \n" +
                " Code: a \n" +
                " Description: a \n" +
                " Parameters: \n";
        assertEquals(s,t.toString());
    }*/
}