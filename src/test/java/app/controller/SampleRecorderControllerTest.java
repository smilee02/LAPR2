package app.controller;

import app.domain.model.Company;
import org.junit.Test;

import static java.lang.String.valueOf;
import static org.junit.Assert.assertEquals;


public class SampleRecorderControllerTest {
    SampleRecorderController instance = new SampleRecorderController();
    Company companySampleRecorder = App.getInstance().getCompany();

    @Test
    public void validateInteger1() {
        String Integer1 = "33";
        boolean expresult = true;
        boolean result = instance.ValidateInteger(Integer1);
        assertEquals(expresult, result);
    }

    @Test
    public void validateInteger2() {
        String Integer2 = "US05";
        boolean expresult = false;
        boolean result = instance.ValidateInteger(Integer2);
        assertEquals(expresult, result);

    }

    @Test
    public void validateFirstAnswer1() {
        String FirstAnswer1 = "1";
        boolean expresult = true;
        boolean result = instance.ValidateFirstAnswer(FirstAnswer1);
        assertEquals(expresult, result);
    }

    @Test
    public void validateFirstAnswer2() {
        String FirstAnswer2 = "5";
        boolean expresult = false;
        boolean result = instance.ValidateFirstAnswer(FirstAnswer2);
        assertEquals(expresult, result);
    }

    @Test
    public void validateFirstAnswer3() {
        String FirstAnswer3 = "FirstAnswer3";
        boolean expresult = false;
        boolean result = instance.ValidateFirstAnswer(FirstAnswer3);
        assertEquals(expresult, result);
    }

    @Test
    public void validateFirstAnswer4() {
        String FirstAnswer1 = "2";
        boolean expresult = true;
        boolean result = instance.ValidateFirstAnswer(FirstAnswer1);
        assertEquals(expresult, result);
    }

    @Test
    public void validateFirstAnswer5() {
        String FirstAnswer1 = "3";
        boolean expresult = true;
        boolean result = instance.ValidateFirstAnswer(FirstAnswer1);
        assertEquals(expresult, result);
    }

    @Test
    public void validateFirstAnswer6() {
        String FirstAnswer1 = "4";
        boolean expresult = true;
        boolean result = instance.ValidateFirstAnswer(FirstAnswer1);
        assertEquals(expresult, result);
    }


    /*@Test
    public void validateTestSelected1() {
        int RandomNumber = 2;
        int test = companySampleRecorder.getSampleRecorderStore().getTestList().size() - RandomNumber;
        String TestSelected = valueOf(test);
        boolean result = instance.ValidateTestSelected(TestSelected);
        assertEquals(result,true);
    }*/

    @Test
    public void validateTestSelected2() {
        int RandomNumber = 2;
        String TestSelected = valueOf(companySampleRecorder.getSampleRecorderStore().getTestList().size() + RandomNumber);
        boolean expresult = false;
        boolean result = instance.ValidateTestSelected(TestSelected);
        assertEquals(expresult, result);
    }

    @Test
    public void validateTestSelected3() {
        String TestSelected = "Test Selected";
        boolean expresult = false;
        boolean result;
        if (!instance.ValidateInteger(TestSelected)) {
            result = false;
        } else result = instance.ValidateTestSelected(TestSelected);
        assertEquals(expresult, result);
    }

    @Test
    public void validateAnswer3first() {
        String Answer3 = valueOf(2);
        boolean expresult = true;
        boolean result = instance.ValidateAnswer3(Answer3);
        assertEquals(expresult, result);
    }

    @Test
    public void validateAnswer3second() {
        String Answer3 = valueOf(4);
        boolean expresult = false;
        boolean result = instance.ValidateAnswer3(Answer3);
        assertEquals(expresult, result);
    }

    @Test
    public void validateAnswer3third() {
        String Answer3 = "Teste";
        boolean expresult = false;
        boolean result = instance.ValidateAnswer3(Answer3);
        assertEquals(expresult, result);
    }

    @Test
    public void validateAnswer3fourth() {
        String Answer3 = valueOf(1);
        boolean expresult = true;
        boolean result = instance.ValidateAnswer3(Answer3);
        assertEquals(expresult, result);
    }

    @Test
    public void validateAnswer3fifth() {
        String Answer3 = valueOf(3);
        boolean expresult = true;
        boolean result = instance.ValidateAnswer3(Answer3);
        assertEquals(expresult, result);
    }

    @Test
    public void validateBooleanAnswer1() {
        String Teste = "Yes";
        boolean expresult = true;
        boolean result = instance.ValidateBooleanAnswer(Teste);
        assertEquals(expresult, result);
    }

    @Test
    public void validateBooleanAnswer2() {
        String Teste = "YES";
        boolean expresult = true;
        boolean result = instance.ValidateBooleanAnswer(Teste);
        assertEquals(expresult, result);
    }

    @Test
    public void validateBooleanAnswer3() {
        String Teste = "yes";
        boolean expresult = true;
        boolean result = instance.ValidateBooleanAnswer(Teste);
        assertEquals(expresult, result);
    }

    @Test
    public void validateBooleanAnswer4() {
        String Teste = "NO";
        boolean expresult = true;
        boolean result = instance.ValidateBooleanAnswer(Teste);
        assertEquals(expresult, result);
    }

    @Test
    public void validateBooleanAnswer5() {
        String Teste = "No";
        boolean expresult = true;
        boolean result = instance.ValidateBooleanAnswer(Teste);
        assertEquals(expresult, result);
    }

    @Test
    public void validateBooleanAnswer6() {
        String Teste = "no";
        boolean expresult = true;
        boolean result = instance.ValidateBooleanAnswer(Teste);
        assertEquals(expresult, result);
    }

    @Test
    public void validateBooleanAnswer7() {
        String Teste = "n";
        boolean expresult = true;
        boolean result = instance.ValidateBooleanAnswer(Teste);
        assertEquals(expresult, result);
    }

    @Test
    public void validateBooleanAnswer8() {
        String Teste = "N";
        boolean expresult = true;
        boolean result = instance.ValidateBooleanAnswer(Teste);
        assertEquals(expresult, result);
    }

    @Test
    public void validateBooleanAnswer9() {
        String Teste = "Y";
        boolean expresult = true;
        boolean result = instance.ValidateBooleanAnswer(Teste);
        assertEquals(expresult, result);
    }

    @Test
    public void validateBooleanAnswer10() {
        String Teste = "y";
        boolean expresult = true;
        boolean result = instance.ValidateBooleanAnswer(Teste);
        assertEquals(expresult, result);
    }

    @Test
    public void validateBooleanAnswer11() {
        String Teste = "Teste";
        boolean expresult = false;
        boolean result = instance.ValidateBooleanAnswer(Teste);
        assertEquals(expresult, result);
    }

    @Test
    public void validate12digits1() {
        String Teste = "123456789012";
        boolean expresult = true;
        boolean result = instance.Validate12digits(Teste);
        assertEquals(expresult, result);
    }

    @Test
    public void validate12digits2() {
        String Teste = "12345678912";
        boolean expresult = false;
        boolean result = instance.Validate12digits(Teste);
        assertEquals(expresult, result);
    }

    @Test
    public void validate12digits3() {
        String Teste = "teste";
        boolean expresult = false;
        boolean result = instance.Validate12digits(Teste);
        assertEquals(expresult, result);
    }

}