package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.store.ClientStore;
import app.domain.store.ClinicalAnalysisLaboratoryStore;
import app.domain.store.RegisterTestStore;
import app.ui.console.RegisterTestUI;
import auth.AuthFacade;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Connects the importedTestConectorToInterface to the rest of the classes that
 * are part of ImportedTest
 *
 * 
 */
public class importedTestController {
    private final Company company = Constants.COMPANY;// CHANGE TO CONSTANTConstants.COMPANY
    private Client client;
    private RegisterTest rt;
    private List<TypeOfTest> typeOfTestList = company.getTypeOfTestStore().getListOfTypeOfTest();
    private AuthFacade authFacade = company.getAuthFacade();

    /**
     * Empty Constructor
     */
    public importedTestController() {
        /**
         * this cnstructor doesnt need any parameters
         */
    }

    /**
     * @return Company object
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @param ccn Client citizen card number
     * @return a boolean if ccn exists in Client list or not
     */
    public boolean validateCCN(double ccn) {
        int cont = 0;
        for (Client x : ClientStore.getClients()) {
            if (x.getCitizenCardNumber() == ccn) {
                cont++;
            }
        }
        return cont > 0;
    }

    /**
     * @param name              Client name
     * @param citizenCardNumber Client citizen card number
     * @param nhsNumber         Client National Health Service number
     * @param birthDate         Client birthdate
     * @param tin               Client tax identification number
     * @param phoneNumber       Client phone number
     * @param email             Client email
     * @param adress            Client adress
     * @return boolean based if client is registered or not
     */
    public boolean registerClient(String name, double citizenCardNumber, double nhsNumber, String birthDate, double tin,
            double phoneNumber, String email, String adress) {
        client = new Client(name, citizenCardNumber, nhsNumber, birthDate, tin, phoneNumber, email, adress);
        if (validateCCN(citizenCardNumber)) {
            // client = new Client(name, citizenCardNumber, nhsNumber, birthDate, tin,
            // phoneNumber, email,adress);
            // System.out.println("Client exists");
            return false;
        } else {
            // client = new Client(name, citizenCardNumber, nhsNumber, birthDate, tin,
            // phoneNumber, email,adress);
            ClientStore.getClients().add(client);
            // System.out.println("Client Registered");
            return true;
        }
    }

    /**
     * @param test TypeOfTest name in string
     * @return int based on position of the TypeOfTest if exists, if not returns -1
     */
    public int validateTest(String test) {
        int cont = -1;
        for (int i = 0; i < typeOfTestList.size(); i++) {
            if (typeOfTestList.get(i).getDescription().equals(test)) {
                cont = i;
            }
        }
        return cont;
    }

    /**
     * @param test test TypeOfTest name in string
     * @return Object TypeOfTest
     */
    public TypeOfTest getTypeOfTest(String test) {
        int cont = validateTest(test);
        if (cont != -1)
            return typeOfTestList.get(cont);
        else
            return null;
    }

    /**
     * @param parameterCategoryList List of parameter Categories name in String
     * @param test                  test TypeOfTest name in string
     * @return an array of integers, based on parameter categories position
     */
    public int[] validateCategory(List<String> parameterCategoryList, String test) {
        int pos = validateTest(test);
        int[] cont = new int[parameterCategoryList.size()];
        Arrays.fill(cont, -2);
        if (pos == -1 && cont.length != 0) {
            cont[0] = -1;
        } else {
            int size = 0;
            if (pos != -1) {
                size = typeOfTestList.get(pos).getCategoriesList().size();
            }
            int size2 = parameterCategoryList.size();
            for (int x = 0; x < size; x++) {
                for (int i = 0; i < size2; i++) {
                    if (parameterCategoryList.get(i)
                            .equals(typeOfTestList.get(pos).getCategoriesList().get(x).getShortDescription())) {
                        cont[i] = x;
                    }
                }
            }
        }
        return cont;
    }

    /**
     * @param parameterCategoryList parameterCategoryList List of parameter
     *                              Categories name in String
     * @param test                  test test TypeOfTest name in string
     * @return a list of parameters based on parameterCategoryList and test
     */
    public List<Parameter> validateParameter(List<String> parameterCategoryList, String test) {
        int pos2 = validateTest(test);
        List<Parameter> parameterList = new ArrayList<>();
        if (pos2 != -1) {
            int[] pos = validateCategory(parameterCategoryList, test);
            for (int x = 0; x < pos.length; x++) {
                if (pos[x] != -2)
                    parameterList.addAll(typeOfTestList.get(pos2).getCategoriesList().get(pos[x]).getParameterList());
            }
        } else
            parameterList.add(0, new Parameter("error", "error", "error"));
        return parameterList;
    }

    /**
     * @param original List of parameters that exist program
     * @param user     List of parametr names in strings
     * @return List of parameter, that contains the parameters that exist in
     *         simultaneous on user and original
     */
    public List<Parameter> getParameterList(List<Parameter> original, List<String> user) {
        int cont = 0;
        List<Parameter> finalParameterList = new ArrayList<>();
        if (!original.get(0).getCode().equals("error")) {
            for (String x : user) {
                for (Parameter y : original) {
                    if (x.equals(y.getCode())) {
                        finalParameterList.add(y);
                    }
                }
            }
        } else
            finalParameterList.add(new Parameter("error", "error", "error"));
        return finalParameterList;
    }

    /**
     * @param testnumber testnumber code
     * @return boolean based if the testnumber already exists on RegisterTestList
     */
    public boolean validateTestNumber(String testnumber) {
        int cont = 0;
        for (RegisterTest x : RegisterTestStore.getRegisterTestList()) {
            if (x.getTestnumber().equals(testnumber)) {
                cont++;
            }
        }
        return cont == 0;
    }

    /**
     * @param nhs nhs code of Register Test
     * @return boolean based if the nhs code exists on RegistertestList
     */
    public boolean validateNHS(String nhs) {
        int cont = 0;
        for (RegisterTest x : RegisterTestStore.getRegisterTestList()) {
            if (x.getNhs().equals(nhs)) {
                cont++;
            }
        }
        return cont == 0;
    }

    /**
     * @param laboratoryId Clinical Analysis Laboratory id
     * @return boolean if the laboratoryId exists on Clinical Laboratory List
     */
    public boolean validateLaboratoryId(String laboratoryId) {
        int cont = 0;
        for (ClinicalAnalysisLaboratory x : company.getLaboratoryStore().getClinical()) {
            if (x.getLaboratoryId().equals(laboratoryId)) {
                cont++;
            }
        }
        return cont > 0;
    }

    /**
     * @param parametersList List of test Parameters
     * @param test           TypeOfTest object
     * @param nhs            Register Test nhs code
     * @param testnumber     Register Test test number code
     * @param laboratoryId   Clinical Analysis Laboratory Id
     * @return boolean based if it registered a new test
     */
    public boolean registerTest(List<Parameter> parametersList, TypeOfTest test, String nhs, String testnumber,
            String laboratoryId) {
        boolean validateNHS = RegisterTestUI.validateNHS(nhs);
        boolean validateNHS2 = validateNHS(nhs);
        boolean validateTestNumber = validateTestNumber(testnumber);
        boolean validateLaboratoryId = validateLaboratoryId(laboratoryId);
        boolean flag = true;
        if (validateError(parametersList)) {
            flag = false;
        }
        if (validateLaboratoryId && validateNHS && validateNHS2 && validateTestNumber && flag) {
            rt = new RegisterTest(parametersList, (long) client.getTin(), test, nhs, testnumber, laboratoryId);
            return true;
        } else
            return false;
    }

    /**
     * @param rt Sets a RegisterTest Object
     */
    public void setRt(RegisterTest rt) {
        this.rt = rt;
    }

    /**
     * @return RegisterTest Object value
     */
    public RegisterTest getRt() {
        return rt;
    }

    /**
     * @param dateC Report Date
     * @param dateV Validation Date
     * @param dateD Chemical Analysis Date
     * @param dateR Registered Test Date
     * @return boolean based if it added the parameters dates
     */
    public boolean addRt(Date dateC, Date dateV, Date dateD, Date dateR) {
        return company.getImportedTestStore().addRegisterTest(dateC, dateV, dateD, rt, dateR);
    }

    /**
     * @return Client Object
     */
    public Client getClient() {
        return client;
    }

    /**
     * @return boolean based if it saved the imported tests
     */
    public boolean storeTest() {
        return company.getImportedTestStore().addToListRt();
    }

    /**
     * @param client sets a Client object
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @param parametersList List of Parameters
     * @return boolean based if in that List there is a parameter code with code
     *         error
     */
    public boolean validateError(List<Parameter> parametersList) {
        int cont = 0;
        for (Parameter x : parametersList) {
            if (x.getCode().equals("error")) {
                cont++;
            }
        }
        return cont > 0;
    }

    /**
     * @param parameterList List of parameters
     * @param value         array of doubles with parameter test values
     * @return boolean and it register a new Record test result
     */
    public boolean registerRecordResult(List<Parameter> parameterList, double[] value) {
        company.getImportedTestStore().addNewParameterValue(rt, parameterList, value);
        company.getImportedTestStore().createImportedTest(client);
        company.getImportedTestStore().saveImportedTest();
        return true;
    }

    /**
     * @return Imported tests list
     */
    public List<importedTest> getList() {
        return company.getImportedTestStore().getImportedTests();
    }

    /**
     * Clears imported test list
     */
    public void clearList() {
        company.getImportedTestStore().clearList();
    }

    /**
     * @param n String to be printed on console
     */
    public static void soutConsole(String n) {
        System.out.println(n);
    }
}
