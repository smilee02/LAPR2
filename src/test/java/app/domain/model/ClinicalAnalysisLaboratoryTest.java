package app.domain.model;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClinicalAnalysisLaboratoryTest {
    @Test
    public void getLaboratoryId() {
        String phoneNumber = "98989878675";
        String tin = "9898987867";
        List<TypeOfTest> test = new ArrayList<>();
        test.add(new TypeOfTest("agdfg","a","a"));
        ClinicalAnalysisLaboratory o = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        ClinicalAnalysisLaboratory o2 = new ClinicalAnalysisLaboratory(null,"a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        assertNotNull(o.getLaboratoryId());
        assertNull(o2.getLaboratoryId());
    }

    @Test
    public void getName() {
        String phoneNumber = "98989878675";
        String tin = "9898987867";
        List<TypeOfTest> test = new ArrayList<>();
        test.add(new TypeOfTest("agdfg","a","a"));
        ClinicalAnalysisLaboratory o = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        ClinicalAnalysisLaboratory o2 = new ClinicalAnalysisLaboratory("abcd1",null,"a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        assertNotNull(o.getName());
        assertNull(o2.getName());
    }

    @Test
    public void getAdress() {
        String phoneNumber = "98989878675";
        String tin = "9898987867";
        List<TypeOfTest> test = new ArrayList<>();
        test.add(new TypeOfTest("agdfg","a","a"));
        ClinicalAnalysisLaboratory o = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        assertNotNull(o.getAdress());
    }

    @Test
    public void getPhoneNumber() {
        String phoneNumber = "98989878675";
        String tin = "9898987867";
        List<TypeOfTest> test = new ArrayList<>();
        test.add(new TypeOfTest("agdfg","a","a"));
        ClinicalAnalysisLaboratory o = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        ClinicalAnalysisLaboratory o2 = new ClinicalAnalysisLaboratory("abcd1","a","a",0,Long.parseLong(tin),test);
        assertNotNull(o.getPhoneNumber());
        assertEquals(0,o2.getPhoneNumber());
    }

    @Test
    public void getTin() {
        String phoneNumber = "98989878675";
        String tin = "9898987867";
        List<TypeOfTest> test = new ArrayList<>();
        test.add(new TypeOfTest("agdfg","a","a"));
        ClinicalAnalysisLaboratory o = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        ClinicalAnalysisLaboratory o2 = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),0,test);
        assertNotNull(o.getTin());
        assertEquals(0,o2.getTin());
    }

    @Test
    public void getTestsType() {
        String phoneNumber = "98989878675";
        String tin = "9898987867";
        List<TypeOfTest> test = new ArrayList<>();
        test.add(new TypeOfTest("agdfg","a","a"));
        ClinicalAnalysisLaboratory o = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        ClinicalAnalysisLaboratory o2 = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),null);
        assertNull(o2.getTestsType());
        assertNotNull(o.getTestsType());
    }

    @Test
    public void setTin(){
        String phoneNumber = "98989878675";
        String tin = "9898987867";
        List<TypeOfTest> test = new ArrayList<>();
        test.add(new TypeOfTest("agdfg","a","a"));
        ClinicalAnalysisLaboratory o = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        ClinicalAnalysisLaboratory o2 = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        o2.setTin(Long.parseLong("1212121212"));
        assertNotEquals(o,o2);
    }

    @Test
    public void setName(){
        String phoneNumber = "98989878675";
        String tin = "9898987867";
        List<TypeOfTest> test = new ArrayList<>();
        test.add(new TypeOfTest("agdfg","a","a"));
        ClinicalAnalysisLaboratory o = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        ClinicalAnalysisLaboratory o2 = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        o2.setName("hey");
        assertNotEquals(o,o2);
    }

    @Test
    public void setLabId(){
        String phoneNumber = "98989878675";
        String tin = "9898987867";
        List<TypeOfTest> test = new ArrayList<>();
        test.add(new TypeOfTest("agdfg","a","a"));
        ClinicalAnalysisLaboratory o = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        ClinicalAnalysisLaboratory o2 = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        o2.setLaboratoryId("hey12");
        assertNotEquals(o,o2);
    }

    @Test
    public void setAddress(){
        String phoneNumber = "98989878675";
        String tin = "9898987867";
        List<TypeOfTest> test = new ArrayList<>();
        test.add(new TypeOfTest("agdfg","a","a"));
        ClinicalAnalysisLaboratory o = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        ClinicalAnalysisLaboratory o2 = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        o2.setAdress("hey12");
        assertNotEquals(o,o2);
    }

    @Test
    public void setPhoneNumber(){
        String phoneNumber = "98989878675";
        String tin = "9898987867";
        List<TypeOfTest> test = new ArrayList<>();
        test.add(new TypeOfTest("agdfg","a","a"));
        ClinicalAnalysisLaboratory o = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        ClinicalAnalysisLaboratory o2 = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        o2.setPhoneNumber(Long.parseLong("12121212121"));
        assertNotEquals(o,o2);
    }

    @Test
    public void setTestTypes(){
        String phoneNumber = "98989878675";
        String tin = "9898987867";
        List<TypeOfTest> test = new ArrayList<>();
        test.add(new TypeOfTest("agdfg","a","a"));
        List<TypeOfTest> typeOfTests = new ArrayList<>();
        ClinicalAnalysisLaboratory o = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        ClinicalAnalysisLaboratory o2 = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong(phoneNumber),Long.parseLong(tin),test);
        o2.setTestsType(typeOfTests);
        assertNotEquals(o,o2);
    }
}