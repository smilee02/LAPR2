package app.domain.store;

import app.domain.model.Company;
import app.domain.model.TypeOfTest;
import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.importedTest;
import app.domain.shared.Files;

import java.io.Serializable;
import java.util.List;

/**
 * 
 */
public class ClinicalAnalysisLaboratoryStore implements Serializable, Files {
    public ClinicalAnalysisLaboratoryStore() {
        /**
         * We dont need any parameter
         */
    }

    private ClinicalAnalysisLaboratory cal;

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
    public ClinicalAnalysisLaboratory RegisterClinicalAnalysisLaboratory(String laboratoryId, String name,
            String adress, long phoneNumber, long tin, List<TypeOfTest> testsType) {
        cal = new ClinicalAnalysisLaboratory(laboratoryId, name, adress, phoneNumber, tin, testsType);
        return cal;
    }

    /**
     * Validates the clinical analysis laboratory
     * 
     * @return
     */
    public boolean validateClinicalAnalysisLaboratory() {
        int cont = 0;
        for (ClinicalAnalysisLaboratory x : getClinical()) {
            if (x.getTin() == cal.getTin() || x.getLaboratoryId().equals(cal.getLaboratoryId())
                    || x.getAdress().equals(cal.getAdress()) || x.getPhoneNumber() == cal.getPhoneNumber())
                cont += 1;
        }
        return cont > 0;
    }

    /**
     * Saves the clinical analysis laboratory
     * 
     * @return
     */
    public boolean SaveClinicalAnalysisLaboratory() {
        if (validateClinicalAnalysisLaboratory()) {
            return false;
        } else {
            Company.getClinical().add(cal);
            Files.encrypt("ClinicalAnalysisLaboratoryStore.ser", Company.getClinical());
            return true;
        }
    }

    /**
     * Returns the List of Clinical Analysis Laboratories
     * 
     * @return
     */
    public List<ClinicalAnalysisLaboratory> getClinical() {
        return Company.getClinical();
    }
}
