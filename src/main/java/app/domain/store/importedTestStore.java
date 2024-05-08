package app.domain.store;

import app.controller.App;
import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.shared.Files;
import auth.AuthFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Imported test store
 *
 * 
 */
public class importedTestStore implements Files, Serializable {
    private List<importedTest> importedTests = new ArrayList<>();
    private importedTest it;
    private Company company;
    private RecordResult recordResult;
    private RegisterTest rt;

    /**
     * Empty Constructor
     */
    public importedTestStore() {
        /**
         * this constructor doesnt need parameters
         *
         */
    }

    /**
     * @param client Client object
     * @return The imported test that has been creted
     */
    public importedTest createImportedTest(Client client) {
        return it = new importedTest(client, recordResult);
    }

    /**
     * fill the parameters values
     * 
     * @param rt            RegisterTest object
     * @param parameterList List of parameters
     * @param value         array of doubles that will be used to store the
     *                      parameters results
     */
    public void addNewParameterValue(RegisterTest rt, List<Parameter> parameterList, double[] value) {
        company.getRecordResultStore().createNewRecordResult(rt);
        int size = parameterList.size();
        for (int i = 0; i < size; i++) {
            if (value[i] != -1) {
                TestParameterResult testParameterResult = company.getRecordResultStore()
                        .newTestParameterResult(parameterList.get(i), value[i], 12345, rt, company);
                if (testParameterResult.getMaxRefValue() == -1.0 && testParameterResult.getMinRefValue() == -1.0
                        && testParameterResult.getMetric().equals("-1.0")) {

                } else
                    company.getRecordResultStore().addTestParameterResult(testParameterResult);
            }
        }
        recordResult = company.getRecordResultStore().getRecordResult();
        recordResult.setCreatedAt(recordResult.getTest().getTest_Chemical_DateHour());
        // System.out.println(recordResult.getTest().getTest_Chemical_DateHour());
        // System.out.println(recordResult.getTest().getTestnumber());
        // System.out.println(recordResult);
    }

    /**
     * @return Record result object
     */
    public RecordResult getRecordResult() {
        return recordResult;
    }

    /**
     * @param recordResult sets the record result object
     */
    public void setRecordResult(RecordResult recordResult) {
        this.recordResult = recordResult;
    }

    /**
     * @param it sets the imported test object
     */
    public void setIt(importedTest it) {
        this.it = it;
    }

    /**
     * saves the imported test
     */
    public void saveImportedTest() {
        importedTests.add(it);
    }

    /**
     * @param rt sets Register Test object
     */
    public void setRt(RegisterTest rt) {
        this.rt = rt;
    }

    /**
     * @param dateC        Report Date
     * @param dateV        Validation Date
     * @param dateD        Chemical Analysis Date
     * @param registerTest Register Test object
     * @param dateR        Registered Test Date
     * @return sets the dates of Register Test object
     */
    public boolean addRegisterTest(Date dateC, Date dateV, Date dateD, RegisterTest registerTest, Date dateR) {
        rt = registerTest;
        rt.setTest_Chemical_DateHour(dateC);
        rt.setTest_Validation_DateHour(dateV);
        rt.setTest_Doctor_DateHour(dateD);
        rt.setData(dateR);
        return true;
    }

    /**
     * @return saves the imported tests into the system respective stores
     */
    public boolean addToListRt() {// MEXE SÓ NESTE MÉTODO NA CENA DO AUTH
        for (importedTest x : importedTests) {
            RecordResultStore.getTestResultsWithTests().add(x.getRecordResult());
            // Files.encrypt("RecordResultStore.ser",RecordResultStore.getTestResultsWithTests());
            RegisterTestStore.getRegisterTestList().add(x.getRecordResult().getTest());
            Validate validate = new Validate(x.getRecordResult().getTest());// adicionar diagrama
            validate.setTestResults(x.getRecordResult());// adicionar diagrama
            Report report = new Report("", 1);
            report.setDate(x.getRecordResult().getTest().getTest_Doctor_DateHour());
            validate.setDiagnosis(report);
            validate.setValidateDate(x.getRecordResult().getTest().getTest_Validation_DateHour());// adicionar diagram
            company.getValidateStore().getValidateList().add(validate);// adicionar diagrama

            // Files.encrypt("RegisterTestStore.ser",RegisterTestStore.getRegisterTestList());
            // Files.encrypt("ValidateStoreDone.ser",company.getValidateStore().getValidateList());
            // System.out.println(x.getRecordResult().getTest().getTestnumber());
        }
        company.getValidateStore().serialize();
        // Files.encrypt("ValidateStoreDone.ser",company.getValidateStore().getValidateList());
        return true;
    }
    /*
     * public boolean validateStore(Client clientes){
     * int cont=0;
     * for(Client x:ClientStore.getClients()){
     * if(x.getCitizenCardNumber()==clientes.getCitizenCardNumber()||x.getNhsNumber(
     * )==clientes.getNhsNumber()||x.getTin()==clientes.getTin()||x.getPhoneNumber()
     * ==clientes.getPhoneNumber()){
     * cont++;
     * }
     * }
     * return cont==0;
     * }
     */

    /**
     * @return get company object
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @param company sets the compnay object
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * @return gets the imported test list
     */
    public List<importedTest> getImportedTests() {
        return importedTests;
    }

    /**
     * @return clears imported test list
     */
    public boolean clearList() {
        importedTests.clear();
        return true;
    }

    /**
     * @return get imported test
     */
    public importedTest getIt() {
        return it;
    }
}
