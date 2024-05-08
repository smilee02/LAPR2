package app.domain.store;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.TypeOfTest;
import app.domain.store.ClinicalAnalysisLaboratoryStore;
import org.junit.Test;

import static org.junit.Assert.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;



public class ClinicalAnalysisLaboratoryStoreTest {
    ClinicalAnalysisLaboratoryStore clinicalAnalysisLaboratoryStore = new ClinicalAnalysisLaboratoryStore();
    @Test
    public void registerClinicalAnalysisLaboratory() {
        List<TypeOfTest> typeOfTestList = new ArrayList<>();
        typeOfTestList.add(new TypeOfTest("test","test","test"));
        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory=clinicalAnalysisLaboratoryStore.RegisterClinicalAnalysisLaboratory("ajdis","ahdgs","yushd",Long.parseLong("25343424342"),Long.parseLong("7675867576"),typeOfTestList);
        assertEquals("ajdis",clinicalAnalysisLaboratory.getLaboratoryId());
    }


    @Test
    public void getClinical() {
        assertNotNull(clinicalAnalysisLaboratoryStore.getClinical());
    }

    @Test
    public void validateClinicalAnalysisLaboratory() {
     List<TypeOfTest> testArr = new ArrayList<>();
        List<ClinicalAnalysisLaboratory> calStore = new ArrayList<>();
        testArr.add(new TypeOfTest("a","a","a"));
        testArr.add(new TypeOfTest("b","a","g"));
        ClinicalAnalysisLaboratoryStore testStore = new ClinicalAnalysisLaboratoryStore();
        testStore.RegisterClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong("91827364542"),Long.parseLong("9182736454"),testArr);
        testStore.RegisterClinicalAnalysisLaboratory("abcd2","b","b",Long.parseLong("91827364542"),Long.parseLong("9182736454"),testArr);
        //assertFalse(testStore.validateClinicalAnalysisLaboratory());
        testStore.RegisterClinicalAnalysisLaboratory("abcd1","a","a",Long.parseLong("91827364542"),Long.parseLong("9182736454"),testArr);
        System.out.println(testStore.getClinical());
        //assertFalse(testStore.validateClinicalAnalysisLaboratory());
    }

    @Test
    public void saveClinicalAnalysisLaboratory() {
        //The upper test already cover this
    }
}