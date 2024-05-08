package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.store.ClientStore;
import app.domain.store.RecordResultStore;
import app.mappers.ValidateMapper;
import app.mappers.dto.ValidateDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class ValidateController {
    static Company manyLabs = Constants.COMPANY;

    public ValidateController() {
        /**
         * Constructor does not need any parameters
         */
    }

    /**
     * Sets the test results for a specific validate object
     * 
     * @param position    of validate object
     * @param testResults results that are going to be saved
     */
    public static void setTestResults(int position, RecordResult testResults) {
        getWorkToBeValidatedList().get(position).setTestResults(testResults);
    }

    /**
     * Sets the diagnostics for a specific validate object
     * 
     * @param position  of validate object
     * @param diagnosis that is going to be saved in the object
     */
    public static void setDiagnostics(int position, Report diagnosis) {
        getWorkToBeValidatedList().get(position).setDiagnosis(diagnosis);
    }

    /**
     * Returns the validate object in the position
     * 
     * @param position of validate object
     * @return
     */
    public Validate getValidate(int position) {
        return getWorkToBeValidatedList().get(position);
    }

    /**
     * Just prints the message passed as a parameter
     */
    public static void printConsole(String string) {
        System.out.print(string);
    }

    /**
     * Returns the work to be validated list
     * 
     * @return
     */
    public static List<Validate> getWorkToBeValidatedList() {
        return manyLabs.getValidateStore().getWorkToBeValidatedList();
    }

    /**
     * Returns the validated list
     * 
     * @return
     */
    public static List<Validate> getValidatedList() {
        return manyLabs.getValidateStore().getValidateList();
    }

    /**
     * Return the list to be shown
     * 
     * @return
     */
    public static List<Validate> showItemsComplete() {
        List<Validate> valid = new ArrayList<>();
        for (Validate validate : getWorkToBeValidatedList()) {
            if (manyLabs.getValidateStore().validate(validate)) {
                valid.add(validate);
            }
        }
        return valid;
    }

    /**
     * Returns the validate list in dto form
     * 
     * @return
     */
    public static List<ValidateDTO> getValidateDTOList() {
        ValidateMapper mapper = new ValidateMapper();
        return mapper.toDTO(showItemsComplete());

    }

    /**
     * Adds the recorded results to validate object
     * 
     * @param result
     */
    public static void addPositionToInsertRecordResults(RecordResult result) {
        for (Validate v : getWorkToBeValidatedList()) {
            if (result.getTest().equals(v.getTestRegistered())) {
                v.setTestResults(result);
            }
        }
    }

    /**
     * Adds the diagnosis to a validate object
     * 
     * @param report
     */
    public static void addPositionToInsertReport(Report report) {
        RecordResult result = RecordResultStore.getTestResultsWithTests().get(report.getId());
        for (Validate v : getWorkToBeValidatedList()) {
            if (result.getTest().equals(v.getTestRegistered())) {
                v.setDiagnosis(report);
            }
        }
    }

    /**
     * Adds the validated object to the validated list
     * 
     * @param number position of validate object
     * @return
     */
    public boolean addValidated(int number) {
        return manyLabs.getValidateStore().addValidated(number);
    }

    /**
     * Removes the validated object from the work to be validated list
     * 
     * @param number position
     * @return
     */
    public boolean removeValidated(int number) {
        return manyLabs.getValidateStore().removeValidated(number);
    }

    /**
     * Saves the validated object in the list
     * 
     * @param validate
     * @return
     */
    public boolean saveValidate(Validate validate) {
        return manyLabs.getValidateStore().saveValidate(validate);
    }

    public void sendNotification(int number) {
        String email = "";
        long ccn = getValidatedList().get(number).getTestRegistered().getCCN();
        for (Client x : ClientStore.getClients()) {
            if (x.getTin() == ccn) {
                email = x.getEmail();
            }
        }
        PasswordGenerationEmailSMS.notificationSending(email);
    }
}
