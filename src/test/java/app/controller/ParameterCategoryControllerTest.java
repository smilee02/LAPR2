package app.controller;

import app.domain.model.ParameterCategory;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterCategoryControllerTest {
    ParameterCategoryController testezinhos=new ParameterCategoryController();

    @Test
    public void validatecodeteste1() {
        String code="r1mms";
        int expresult=0;
        int result=testezinhos.validatecode(code);
        assertEquals(expresult,result);
        assertNotNull(testezinhos.validatecode(code));
    }
    @Test
    public void validatecodeteste2() {
        String code="r1ms";
        int expresult=1;
        int result=testezinhos.validatecode(code);
        assertEquals(expresult,result);
        assertNotNull(testezinhos.validatecode(code));
    }

    @Test
    public void validateshortDescription1() {
        String shortDescription="qwertyuiop";
        int expresult=0;
        int result=testezinhos.validateshortDescription(shortDescription);
        assertEquals(expresult,result);
        assertNotNull(testezinhos.validateshortDescription(shortDescription));
    }
    @Test
    public void validateshortDescription2() {
        String shortDescription="qweryuiop";
        int expresult=0;
        int result=testezinhos.validateshortDescription(shortDescription);
        assertEquals(expresult,result);
        assertNotNull(testezinhos.validateshortDescription(shortDescription));
    }
    @Test
    public void validateshortDescription3() {
        String shortDescription="qwerty0iop";
        int expresult=0;
        int result=testezinhos.validateshortDescription(shortDescription);
        assertEquals(expresult,result);
        assertNotNull(testezinhos.validateshortDescription(shortDescription));
    }

    @Test
    public void saveParameterCategory() {
        ParameterCategory s = new ParameterCategory("a","a");
        boolean expresult=true;
        boolean result=testezinhos.SaveParameterCategory(s);
        assertEquals(true,result);
        assertNotNull(testezinhos.SaveParameterCategory(s));
    }
}