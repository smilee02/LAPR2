package app.ui.console;

import app.domain.model.Parameter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RegisterTestUITest {

    @Test
    public void validateNHS() {
        String nhsTrue = "123456789121";
        String nhsFalse= "123";
        assertTrue(RegisterTestUI.validateNHS(nhsTrue));
        assertFalse(RegisterTestUI.validateNHS(nhsFalse));

    }

    @Test
    public void validateLong() {
        String nhsTrue = "123456789121";
        String nhsFalse= "monkey";
        assertTrue(RegisterTestUI.validateLong(nhsTrue));
        assertFalse(RegisterTestUI.validateLong(nhsFalse));
    }


    @Test
    public void removeParameter() {
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(new Parameter("blood","blood","blood"));
        assertEquals(0,RegisterTestUI.RemoveParameter(parameterList,0).size());
    }

    @Test
    public void validateCCN() {
        long ccn = Long.parseLong("1234567812345678");
        assertFalse(RegisterTestUI.validateCCN(ccn));
    }

    @Test
    public void validateCCN2() {
        long ccnTrue = Long.parseLong("1234567812");
        long ccnFalse = Long.parseLong("12345678123456");
        assertTrue(RegisterTestUI.validateCCN2(ccnTrue));
        assertFalse(RegisterTestUI.validateCCN2(ccnFalse));
    }

    /*@Test
    public void validateNumber() {
        assertFalse(RegisterTestUI.validateNumber(0));
        assertFalse(RegisterTestUI.validateNumber(1));
    }*/
}