package app.domain.model;

import app.domain.shared.Constants;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Object Register Test
 *
 * 
 */
public class RegisterTest implements Serializable {
    private String laboratoryId;
    private List<Parameter> parametersList;
    private long TIN;
    private Date data;
    private TypeOfTest test;
    private String nhs;
    private String testnumber;
    private Date Test_Chemical_DateHour;
    private Date Test_Doctor_DateHour;
    private Date Test_Validation_DateHour;

    /**
     * Constructor for a Register Test
     *
     * @param laboratoryId   laboratory identification
     * @param parametersList receives a list of parameters
     * @param TIN            Tax identification number
     * @param test           The specific test type
     * @param nhs            Number that classifies the registered test
     * @param testnumber     test creation number
     */
    public RegisterTest(List<Parameter> parametersList, long TIN, TypeOfTest test, String nhs, String testnumber,
            String laboratoryId) {
        this.TIN = TIN;
        this.laboratoryId = laboratoryId;
        this.parametersList = parametersList;
        this.data = new Date();
        this.test = test;
        this.nhs = nhs;
        this.testnumber = testnumber;
        this.Test_Chemical_DateHour = null;
        this.Test_Doctor_DateHour = null;
        this.Test_Validation_DateHour = null;
    }

    public void setTest_Chemical_DateHour(Date test_Chemical_DateHour) {
        Test_Chemical_DateHour = test_Chemical_DateHour;
    }

    public Date getTest_Validation_DateHour() {
        return Test_Validation_DateHour;
    }

    public Date getTest_Doctor_DateHour() {
        return Test_Doctor_DateHour;
    }

    public Date getTest_Chemical_DateHour() {
        return Test_Chemical_DateHour;
    }

    public void setTest_Doctor_DateHour(Date test_Doctor_DateHour) {
        Test_Doctor_DateHour = test_Doctor_DateHour;
    }

    public void setTest_Validation_DateHour(Date test_Validation_DateHour) {
        Test_Validation_DateHour = test_Validation_DateHour;
    }

    /**
     * @param TIN Client citizen card number
     *            sets the CCN to another
     */
    public void setCCN(long TIN) {
        this.TIN = TIN;
    }

    /**
     * date is set to when test was created
     */
    public void setData(Date date) {
        this.data = date;
    }

    /**
     * @return the test number
     */
    public String getTestnumber() {
        return testnumber;
    }

    /**
     * @param nhs Number that classifies the registered test
     *            sets the current nhs to the nhs received as parameter
     */
    public void setNhs(String nhs) {
        this.nhs = nhs;
    }

    /**
     * @return laboratory identification
     */
    public String getLaboratoryId() {
        return laboratoryId;
    }

    public void setLaboratoryId(String laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public void setTest(TypeOfTest test) {
        this.test = test;
    }

    /**
     * @param parametersList receives a list of parameters
     *                       replaces the current list with the new one
     */

    public void setParametersList(List<Parameter> parametersList) {
        this.parametersList = parametersList;
    }

    /**
     * @return the parameter list
     */
    public List<Parameter> getParametersList() {
        return parametersList;
    }

    /**
     * @return the ccn number
     */
    public long getCCN() {
        return TIN;
    }

    /**
     * @return the Number that classifies the registered test
     */
    public String getNhs() {
        return nhs;
    }

    /**
     * @param testnumber test creation number
     *                   set testnumber string with new one
     */
    public void setTestnumber(String testnumber) {
        this.testnumber = testnumber;
    }

    /**
     * @return the date of the creation test
     */
    public Date getData() {
        return data;
    }

    /**
     * @return a string of class information and values
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Parameter x : parametersList) {
            s.append(x.toString()).append("\n");
        }
        return test.toStringSampleRecorder() + "\nlaboratoryId: \n" + laboratoryId + "\nNHS Code: \n" + nhs
                + "\nTest Number: \n" + testnumber + "\nParameters: \n" + s + "\nCreated At: \n"
                + Constants.SIMPLE_DATE_FORMAT.format(data);
    }

    /**
     * @return the type of test of this test
     */
    public TypeOfTest getTypeOfTest() {
        return test;
    }
}
