package app.domain.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 */
public class ClinicalAnalysisLaboratory implements Serializable {
    private String laboratoryId;
    private String name;
    private String adress;
    private long phoneNumber;
    private long tin;
    private List<TypeOfTest> testsType;

    /**
     * Constructor for a Laboratory
     * 
     * @param laboratoryId
     * @param name
     * @param adress
     * @param phoneNumber
     * @param tin
     * @param testsType
     */
    public ClinicalAnalysisLaboratory(String laboratoryId, String name, String adress, long phoneNumber, long tin,
            List<TypeOfTest> testsType) {
        this.laboratoryId = laboratoryId;
        this.name = name;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.tin = tin;
        this.testsType = testsType;
    }

    /**
     * Inserts the Laboratory ID
     * 
     * @param laboratoryId
     */
    public void setLaboratoryId(String laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    /**
     * Inserts the Name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Inserts the Address
     * 
     * @param adress
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * Inserts the Phone Number
     * 
     * @param phoneNumber
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Inserts the Tax Identification Number
     * 
     * @param tin
     */
    public void setTin(long tin) {
        this.tin = tin;
    }

    /**
     * Inserts the Types of Tests
     * 
     * @param testsType
     */
    public void setTestsType(List<TypeOfTest> testsType) {
        this.testsType = testsType;
    }

    /**
     * Return the LaboratoryID
     * 
     * @return
     */
    public String getLaboratoryId() {
        return laboratoryId;
    }

    /**
     * Return the Name
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Return the Address
     * 
     * @return
     */
    public String getAdress() {
        return adress;
    }

    /**
     * Return the Phone Number
     * 
     * @return
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Return the Tax Identification Number
     * 
     * @return
     */
    public long getTin() {
        return tin;
    }

    /**
     * Return the Types of Tests
     * 
     * @return
     */
    public List<TypeOfTest> getTestsType() {
        return testsType;
    }

}
