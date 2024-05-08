package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.store.ClientStore;
import app.domain.store.RegisterTestStore;
import app.domain.store.TypeOfTestStore;
import app.mappers.RegisterTestMapper;
import app.mappers.dto.RegisterTestdto;

import java.util.ArrayList;
import java.util.List;

import static app.controller.ClinicalAnalysisLaboratoryController.soutConsole;

/**
 * Connects the UI to the rest of the classes that are part of RegisterTest
 *
 * 
 */
public class RegisterTestController {
    private TypeOfTestStore testsStore = new TypeOfTestStore();
    private List<Parameter> parameterList = new ArrayList<>();
    private List<Client> clientList = ClientStore.getClients();
    private RegisterTest rt;
    private Company company = Constants.COMPANY;
    private List<TypeOfTest> typeOfTestList = testsStore.getListOfTypeOfTest();

    public RegisterTestController() {
        /**
         * Clear cause we dont have any parameters to put
         */
    }

    /**
     * @return the list of clients in registered in the system
     */
    public List<Client> getClientList() {
        return clientList;
    }

    /**
     * @return the list of the type of tests that are registered in the system
     */
    public List<TypeOfTest> getTypeOfTestList() {
        return typeOfTestList;
    }

    /**
     * Creates a list of all parameters that are associated to a specific type of
     * test
     *
     * @return a parameter list
     */
    public List<Parameter> getTestType(int testNumber) {
        parameterList = new ArrayList<>();
        for (ParameterCategory x : testsStore.getListOfTypeOfTest().get(testNumber).getCategoriesList()) {
            parameterList.addAll(x.getParameterList());
        }
        return parameterList;
    }

    /**
     * @return the parameter list
     */
    public List<Parameter> getParameterList() {
        return parameterList;
    }

    /**
     * @param parameterList the list that we want store
     *                      sets the parameter list with another parameter list
     */
    public void setParameterList(List<Parameter> parameterList) {
        this.parameterList = parameterList;
    }

    /**
     * @param TIN           citizen card number from client
     * @param parameterList the parameter list
     * @param test          type of test
     * @param nhs           the number that will be specific for every test
     *                      Register a test and validates
     * @return true if a test its successfully registered
     */
    public boolean RegisterTest(long TIN, List<Parameter> parameterList, TypeOfTest test, String nhs,
            String laboratoryId) {
        String testnumber = fillNumber(String.valueOf(getRegisterTestList().size() + 1));
        rt = company.getRegisterTestStore().CreateRegisterTest(TIN, parameterList, test, nhs, testnumber, laboratoryId);
        if (company.getRegisterTestStore().validateRegisterTestStore()) {
            soutConsole("[+]Test registered");
        } else
            soutConsole("[!]Test already exists");
        return company.getRegisterTestStore().validateRegisterTestStore();
    }

    /**
     * @param testnumber the string that we want to fill
     *                   fills a string with 0 until size of string is 12
     * @return the specific string
     */
    public String fillNumber(String testnumber) {
        for (int i = testnumber.length(); i < 12; i++) {
            testnumber = "0" + testnumber;
        }
        return testnumber;
    }

    /**
     * Prints the toString of the mapper and dto
     */
    public void showDto() {
        RegisterTestMapper mapper = new RegisterTestMapper();
        RegisterTestdto dto;
        dto = mapper.dto(rt);
        soutConsole(dto.toString());
    }

    /**
     * Vaçidates a specific test and saves it
     *
     * @return true if the test is validated
     */
    public boolean saveRegisterTest() {
        if (company.getRegisterTestStore().validateRegisterTestStore()) {
            company.getRegisterTestStore().saveRegisterTest();
            soutConsole("[+]Test has been added successfully");
            return true;
        } else
            return false;
    }

    /**
     * Acess to the mapper to get the dto test list
     *
     * @return the dto test list
     */
    public List<RegisterTestdto> getRegisterTestList() {
        return new RegisterTestMapper().dtoList(RegisterTestStore.getRegisterTestList());
    }

    /**
     * @param n its the element index to remove on list
     *          Access a method in store that will delete an element from a list
     *          based on the value of n
     */
    public void removeElement(int n) {
        company.getRegisterTestStore().deleteRegisterTest(n);
        soutConsole("[-]Removed Successfully");
    }

    /**
     * @param n String received
     *          prints n
     */
    public static void soutConsole(String n) {
        System.out.println(n);
    }
}
