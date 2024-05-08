package app.controller;

import app.domain.model.Client;
import app.domain.model.Parameter;
import app.domain.model.RegisterTest;
import app.domain.model.TypeOfTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class importedTestControllerTest {
    private static final Double DOUBLE16 = Double.parseDouble("1234567812345678");
    private static final Double DOUBLE10 = Double.parseDouble("1234567891");
    private importedTestController importedTestController = new importedTestController();

    @Test
    public void getCompany() {
        assertNotNull(importedTestController.getCompany());
    }

    @Test
    public void validateCCN() {
        assertFalse(importedTestController.validateCCN(DOUBLE10));
    }

    @Test
    public void registerClient() {
        assertTrue(importedTestController.registerClient("lorry",DOUBLE16,DOUBLE10,"date", DOUBLE10, DOUBLE10,"hello","hello"));
        assertFalse(importedTestController.registerClient("lorry",DOUBLE16,DOUBLE10,"date", DOUBLE10, DOUBLE10,"hello","hello"));
    }

    @Test
    public void validateTest() {
        assertEquals(-1,importedTestController.validateTest("johny"));
    }

    @Test
    public void getTypeOfTest() {
        assertNotNull(importedTestController.getTypeOfTest("Blood"));
    }

    @Test
    public void validateCategory() {
        List<String> parameterCategoryList = new ArrayList<>();
        parameterCategoryList.add("Blood");
        assertNotNull(importedTestController.validateCategory(parameterCategoryList,"Blood"));
        assertEquals(1,importedTestController.validateCategory(parameterCategoryList,"Blood").length);
    }

    @Test
    public void validateParameter() {
        List<String> parameterCategoryList = new ArrayList<>();
        parameterCategoryList.add("Blood");
        assertNotNull(importedTestController.validateParameter(parameterCategoryList,"Blood"));
        assertEquals(0,importedTestController.validateParameter(parameterCategoryList,"Blood").size());
    }

    @Test
    public void getParameterList() {
        List<String> parameterCategoryList = new ArrayList<>();
        parameterCategoryList.add("Blood");
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(new Parameter("blood","blood","blood"));
        assertNotNull(importedTestController.getParameterList(parameterList,parameterCategoryList));
        assertEquals(0,importedTestController.getParameterList(parameterList,parameterCategoryList).size());
    }

    @Test
    public void validateTestNumber() {
        assertTrue(importedTestController.validateTestNumber("000000000000003023"));
    }

    @Test
    public void validateNHS() {
        assertTrue(importedTestController.validateNHS("1234567891"));
    }

    @Test
    public void validateLaboratoryId() {
        assertFalse(importedTestController.validateLaboratoryId("abc57"));
    }

    @Test
    public void registerTest() {
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(new Parameter("blood","blood","blood"));
        assertFalse(importedTestController.registerTest(parameterList,new TypeOfTest("b","b","b"),"12345678","12345678","123"));
    }

    @Test
    public void getRt() {
        assertNull(importedTestController.getRt());
    }

    @Test
    public void addRt() {
        Date data = new Date(2000, Calendar.NOVEMBER,2);
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(new Parameter("blood","blood","blood"));
        importedTestController.setRt(new RegisterTest(parameterList,Long.parseLong("1"),new TypeOfTest("blood","blood","blodd"),"1","1","1"));
        assertTrue(importedTestController.addRt(data,data,data,data));
    }

    @Test
    public void getClient() {
        assertNull(importedTestController.getClient());
    }

    @Test
    public void storeTest() {
        assertTrue(importedTestController.storeTest());
    }

    @Test
    public void validateError() {
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(new Parameter("blood","blood","blood"));
        assertFalse(importedTestController.validateError(parameterList));
    }


    @Test
    public void getList() {
        assertNotNull(importedTestController.getList());
    }

    @Test
    public void clearList() {
        importedTestController.clearList();
        assertEquals(0,importedTestController.getList().size());
    }
}