package app.controller;

import app.domain.model.Company;
import app.domain.model.TypeOfTest;
import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.shared.Constants;

import java.util.List;

/**
 * Connects the UI to the rest of the system
 * 
 */
public class ClinicalAnalysisLaboratoryController {
    private static final Company getLaboratory = Constants.COMPANY;
    private ClinicalAnalysisLaboratory cal;

    public ClinicalAnalysisLaboratoryController() {
        /**
         * Clear cause we dont have any parameters to put
         */
    }

    /**
     * Registers a new Clinical Analysis Laboratory
     * 
     * @param laboratoryId Laboratory ID
     * @param name         Name of Laboratory
     * @param adress       Address of Laboratory
     * @param phoneNumber  Phone Number of Laboratory
     * @param tin          Tax Identification Number of Laboratory
     * @param testsType    TestType of Laboratory
     * @return
     */
    public boolean RegisterClinicalAnalysisLaboratory(String laboratoryId, String name, String adress, long phoneNumber,
            long tin, List<TypeOfTest> testsType) {
        cal = getLaboratory.getLaboratoryStore().RegisterClinicalAnalysisLaboratory(laboratoryId, name, adress,
                phoneNumber, tin, testsType);
        if (getLaboratory.getLaboratoryStore().validateClinicalAnalysisLaboratory()) {
            soutConsole("[!]Laboratory already exists");
        } else {
            soutConsole("[+]Laboratory registered");
        }
        return getLaboratory.getLaboratoryStore().validateClinicalAnalysisLaboratory();
    }

    /**
     * Saves the clinical analysis laboratory
     * 
     * @return
     */
    public boolean SaveClinicalAnalysisLaboratory() {
        if (!getLaboratory.getLaboratoryStore().validateClinicalAnalysisLaboratory()) {
            getLaboratory.getLaboratoryStore().SaveClinicalAnalysisLaboratory();
            soutConsole("[+]Laboratory has been added successfully");
            return true;
        }
        return false;
    }

    /**
     * Counts the number of Clinical Analysis laboratory
     * 
     * @return
     */
    public int ClinicalAnalysisLaboratoryCount() {
        return getLaboratory.getLaboratoryStore().getClinical().size();
    }

    /**
     * Removes the laboratory by getting the number by parameter
     * 
     * @param n
     * @return
     */
    public boolean ClinicalAnalysisLaboratoryRemove(int n) {
        getLaboratory.getLaboratoryStore().getClinical().remove(n - 1);
        return true;
    }

    /**
     * Returns the laboratory
     * 
     * @return
     */
    public Company getGetLaboratory() {
        return getLaboratory;
    }

    public static List<ClinicalAnalysisLaboratory> laboratoryList() {
        return getLaboratory.getLaboratoryStore().getClinical();
    }

    /**
     * Returns the object created
     * 
     * @return
     */
    public ClinicalAnalysisLaboratory getCal() {
        return cal;
    }

    public static void soutConsole(String n) {
        System.out.println(n);
    }

    public static void soutConsoleNoLine(String n) {
        System.out.print(n);
    }
}
