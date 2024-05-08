package app.domain.store;

import app.controller.ValidateController;
import app.domain.model.*;
import app.domain.shared.Files;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

public class RegisterTestStore implements Serializable, Files {
    private static List<RegisterTest> registerTestList = new ArrayList<>();
    private RegisterTest rt;

    public RegisterTestStore() {
        try {
            registerTestList = (List<RegisterTest>) Files.decrypt("RegisterTestStore.ser");
        } catch (Exception e) {

        }
        /**
         * This Constructor doesnt need parameters
         */
    }

    /**
     * @param TIN           Tax identification number
     * @param parameterList receives a list of parameters
     * @param test          The specific test type
     * @param nhs           Number that classifies the registered test
     * @param testnumber    test creation number
     * @return Register Test object
     */
    public RegisterTest CreateRegisterTest(long TIN, List<Parameter> parameterList, TypeOfTest test, String nhs,
            String testnumber, String laboratoryId) {
        rt = new RegisterTest(parameterList, TIN, test, nhs, testnumber, laboratoryId);
        return rt;
    }

    /**
     * @return true if the Register test object NHS created isn't equal of any
     *         register test presented on the list
     */
    public boolean validateRegisterTestStore() {
        int cont = 0;
        for (RegisterTest x : registerTestList) {
            if (rt.getNhs().equals(x.getNhs())) {
                cont++;
            }
        }
        return cont == 0;
    }

    /**
     * @param cal set the object rt(Register test) to a new Register test object
     */
    public void setCal(RegisterTest cal) {
        this.rt = cal;
    }

    /**
     * @return the Register test object stored on class
     */
    public RegisterTest getRt() {
        return rt;
    }

    /**
     * @return true if validates its true then the object is added to a list
     */
    public boolean saveRegisterTest() {
        if (validateRegisterTestStore()) {
            registerTestList.add(rt);
            Files.encrypt("RegisterTestStore.ser", registerTestList);
            ValidateController.getWorkToBeValidatedList().add(new Validate(rt));
            Files.encrypt("ValidateStore.ser", ValidateController.getWorkToBeValidatedList());
            return true;
        } else
            return false;
    }

    /**
     * @param n element number to be removed
     *          remove an element index n
     */
    public void deleteRegisterTest(int n) {
        getRegisterTestList().remove(n);
    }

    /**
     * @return the Register test list
     */
    public static List<RegisterTest> getRegisterTestList() {
        return registerTestList;
    }
}
