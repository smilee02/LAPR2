package app.ui.console;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.TypeOfTest;
import app.ui.console.RegisterClinicalAnalysisLaboratoryUI;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterClinicalAnalysisLaboratoryUITest {

    @Test
    public void laboratoryUINumber() {
    }

    @Test
    public void laboratoryUIRemoveValidate() {
        int n = 1;
        int n2 = 0;
        int n3 = 12;
        int n4 = -1;
        List<TypeOfTest> testArr = new ArrayList<>();
        testArr.add(new TypeOfTest("a","a","a"));
        testArr.add(new TypeOfTest("b","a","g"));
        ClinicalAnalysisLaboratory test = new ClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong("91827364542"),Long.parseLong("9182736454"),testArr);
        ClinicalAnalysisLaboratory test2 = new ClinicalAnalysisLaboratory("abcd2","b","b",Long.parseLong("91827364542"),Long.parseLong("9182736454"),testArr);
        ClinicalAnalysisLaboratory test3 = new ClinicalAnalysisLaboratory("abcd3","c","c",Long.parseLong("91827364542"),Long.parseLong("9182736454"),testArr);
        List<ClinicalAnalysisLaboratory> list = new ArrayList<>();
        list.add(test);
        list.add(test2);
        list.add(test3);
        assertTrue(RegisterClinicalAnalysisLaboratoryUI.laboratoryUIRemoveValidate(n,list));
        assertFalse(RegisterClinicalAnalysisLaboratoryUI.laboratoryUIRemoveValidate(n2,list));
        assertFalse(RegisterClinicalAnalysisLaboratoryUI.laboratoryUIRemoveValidate(n3,list));
        assertFalse(RegisterClinicalAnalysisLaboratoryUI.laboratoryUIRemoveValidate(n4,list));
    }

    @Test
    public void laboratoryIdValidate() {
        String laboratoryId = "";
        String laboratoryId2 = "abcd1";
        String laboratoryId3 = "ab";
        String laboratoryId4 = "   ";
        assertFalse(RegisterClinicalAnalysisLaboratoryUI.laboratoryIdValidate(laboratoryId));
        assertTrue(RegisterClinicalAnalysisLaboratoryUI.laboratoryIdValidate(laboratoryId2));
        assertFalse(RegisterClinicalAnalysisLaboratoryUI.laboratoryIdValidate(laboratoryId3));
        assertFalse(RegisterClinicalAnalysisLaboratoryUI.laboratoryIdValidate(laboratoryId4));
    }

    @Test
    public void nameValidate() {
        String name = "";
        String name2 = "Rua do Carvalhal 23";
        String name3 = "Rua do Carvalhal 23 3ºd test test test tedst test testst test test";
        String name4 = "   ";
        assertFalse(RegisterClinicalAnalysisLaboratoryUI.nameValidate(name));
        assertTrue(RegisterClinicalAnalysisLaboratoryUI.nameValidate(name2));
        assertFalse(RegisterClinicalAnalysisLaboratoryUI.nameValidate(name3));
        assertFalse(RegisterClinicalAnalysisLaboratoryUI.nameValidate(name4));
    }

    @Test
    public void adressValidate() {
        String adress = "";
        String adress2 = "Rua do Carvalhal 23 3ºd";
        String adress3 = "Rua do Carvalhal 23 3ºd test test test tedst test testst test test";
        String adress4 = "   ";
        assertFalse(RegisterClinicalAnalysisLaboratoryUI.adressValidate(adress));
        assertTrue(RegisterClinicalAnalysisLaboratoryUI.adressValidate(adress2));
        assertFalse(RegisterClinicalAnalysisLaboratoryUI.adressValidate(adress3));
        assertFalse(RegisterClinicalAnalysisLaboratoryUI.adressValidate(adress4));
    }
    @Test
    public void phoneNumberValidate() {
        String phoneNumber="-12345678912";
        String phoneNumber2="91234567819";
        long phoneNumber3=0;
        assertFalse(RegisterClinicalAnalysisLaboratoryUI.phoneNumberValidate(Long.parseLong(phoneNumber)));
        assertTrue(RegisterClinicalAnalysisLaboratoryUI.phoneNumberValidate(Long.parseLong(phoneNumber2)));
        assertFalse(RegisterClinicalAnalysisLaboratoryUI.phoneNumberValidate(phoneNumber3));
    }

    @Test
    public void tinValidate() {
        long tin=-1234567892;
        long tin2=1234567891;
        long tin3 = 0;
        assertFalse(RegisterClinicalAnalysisLaboratoryUI.tinValidate(tin));
        assertTrue(RegisterClinicalAnalysisLaboratoryUI.tinValidate(tin2));
        assertFalse(RegisterClinicalAnalysisLaboratoryUI.tinValidate(tin3));
    }
}