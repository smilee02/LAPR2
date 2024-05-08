package app.domain.store;

import app.controller.ClinicalAnalysisLaboratoryController;
import app.domain.model.Validate;
import app.domain.shared.Files;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidateStore implements Serializable, Files {
    private List<Validate> workToBeValidatedList = new ArrayList<>();
    private static List<Validate> validatedList = new ArrayList<>();

    public ValidateStore() {
        try {
            workToBeValidatedList = (List<Validate>) Files.decrypt("ValidateStore.ser");
            validatedList = (List<Validate>) Files.decrypt("ValidateStoreDone.ser");
        } catch (Exception ignored) {

        }
        /**
         * Constructor doesn't need parameters
         */
    }

    /**
     * Validates the validate object to check if all of his parameters aren't empty
     * 
     * @param workToBeValidated validate object to be checked
     * @return
     */
    public boolean validate(Validate workToBeValidated) {
        if (workToBeValidated.getTestRegistered() != null) {
            if (workToBeValidated.getTestResults() != null) {
                if (workToBeValidated.getDiagnosis() != null) {

                    return true;
                } else {
                    ClinicalAnalysisLaboratoryController.soutConsole("Diagnosis cannot be a empty field.");
                    return false;
                }
            } else {
                ClinicalAnalysisLaboratoryController.soutConsole("Test Results cannot be a empty field.");
                return false;
            }
        } else {
            ClinicalAnalysisLaboratoryController.soutConsole("Registered Test cannot be a empty field.");
            return false;
        }
    }

    /**
     * If the validate object is valid it will be added to the list that is going to
     * be validated by the laboratory coordinator
     * 
     * @param validate parameter to be added
     * @return boolean of success of operation
     */
    public boolean saveWorkToBeValidated(Validate validate) {
        if (validate(validate)) {
            return false;
        } else {
            workToBeValidatedList.add(validate);
            Files.encrypt("ValidateStore.ser", workToBeValidatedList);
            return true;
        }

    }

    /**
     * Saves the object in the list of validated
     * 
     * @param validate parameter to be saved
     * @return boolean of success of operation
     */
    public boolean saveValidate(Validate validate) {
        validatedList.add(validate);
        Files.encrypt("ValidateStoreDone.ser", validatedList);
        return true;
    }

    /**
     *
     * @return list of validate objects
     */
    public List<Validate> getValidateList() {
        return validatedList;
    }

    public void serialize() {
        Files.encrypt("ValidateStoreDone.ser", validatedList);
    }

    /**
     *
     * @return list of work to be validated (validate objects)
     */
    public List<Validate> getWorkToBeValidatedList() {
        return workToBeValidatedList;
    }

    /**
     * Adds the validate object from the work validate list to the validate list
     * 
     * @param number
     * @return
     */
    public boolean addValidated(int number) {
        validatedList.add(workToBeValidatedList.get(number));
        Files.encrypt("ValidateStoreDone.ser", validatedList);
        return true;
    }

    /**
     * Removes the work validate object from the work to be validate list
     * 
     * @param number
     * @return
     */
    public boolean removeValidated(int number) {
        return workToBeValidatedList.remove(workToBeValidatedList.get(number));
    }
}
