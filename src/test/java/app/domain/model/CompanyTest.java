package app.domain.model;

import app.domain.store.ClientStore;
import app.domain.store.ClinicalAnalysisLaboratoryStore;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompanyTest {
    private Company o2 = new Company("I LIKE TRAINS");


    @Test
    public void getDesignation() {
        assertNotNull(o2.getDesignation());
        try{
            Company o = new Company("");
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getAuthFacade() {
        assertNotNull(o2.getAuthFacade());
    }

    @Test
    public void getClients() {
        assertNotNull(ClientStore.getClients());
    }

    @Test
    public void getEmployees() {
        assertNotNull(Company.getEmployeeStore().getEmployees());
    }

    @Test
    public void getClinical() {
        assertNotNull(Company.getClinical());
    }

    @Test
    public void getLaboratoryStore() {
        assertNotNull(o2.getLaboratoryStore());
    }

    @Test
    public void getClientStore() {
        assertNotNull(o2.getClientStore());
    }

    @Test
    public void getEmployeeStore() {
        assertNotNull(o2.getEmployeeStore());
    }

    @Test
    public void passwordGeneration() {
        assertNotNull(PasswordGenerationEmailSMS.passwordGeneration());
    }

    @Test
    public void getTypeOfTestStore(){
        assertNotNull(o2.getTypeOfTestStore());
    }

    @Test
    public void getParameterStore(){
        assertNotNull(o2.getParameterStore());
    }

    @Test
    public void getParametercategorylist() {
        assertNotNull(Company.getParametercategorylist());
    }

    @Test
    public void getParameterCategoryStore(){
        assertNotNull(Company.getParameterCategoryStore());
    }
    @Test
    public void getPhoneNumbers() {
        assertNotNull(Company.getPhoneNumbers());
    }

    @Test
    public void getCodeList() {
        assertNotNull(Company.getCodeList());
    }

    @Test
    public void getTestRegisterStore(){
        assertNotNull(o2.getRegisterTestStore());
    }

    @Test
    public void getRecordResultStore(){
        assertNotNull(o2.getRecordResultStore());
    }

    @Test
    public void getReportStore(){
        assertNotNull(o2.getReportStore());
    }

    @Test
    public void getValidateStore(){
        assertNotNull(o2.getValidateStore());
    }

    @Test
    public void getSampleRecordStore(){
        assertNotNull(o2.getSampleRecorderStore());
    }

}