package app.mappers.dto;

import app.domain.model.Parameter;
import app.domain.model.TypeOfTest;

import java.util.Date;
import java.util.List;

/**
 * Register Test Dto
 *
 * 
 */
public class RegisterTestdto {
    private final String laboratoryId;
    private final List<Parameter> parametersList;
    private final long TIN;
    private final Date data;
    private final TypeOfTest test;
    private final String nhs;
    private final String testnumber;

    /**
     * @return a string of class parameters
     */
    @Override
    public String toString() {
        return "Test Type:\n" + test.getCode() +
                ", TIN=" + TIN +
                ", data=" + data +
                ", test=" + test +
                ", nhs='" + nhs + '\'' +
                ", laboratoryId'" + laboratoryId + '\'' +
                ", testnumber='" + testnumber + '\'';
    }

    /**
     * Register Test Dto Constructor
     * 
     * @param parametersList list of parameters
     * @param TIN            Client citizen card number
     * @param test           The specific test type
     * @param nhs            Number that classifies the registered test
     * @param testnumber     test creation number
     */
    public RegisterTestdto(List<Parameter> parametersList, long TIN, TypeOfTest test, String nhs, String testnumber,
            String laboratoryId) {
        this.TIN = TIN;
        this.laboratoryId = laboratoryId;
        this.parametersList = parametersList;
        this.data = new Date();
        this.test = test;
        this.nhs = nhs;
        this.testnumber = testnumber;
    }

    /**
     *
     * @return the data present on class
     */
    public Date getData() {
        return data;
    }

    /**
     *
     * @return the Number that classifies the registered test present on class
     */
    public String getNhs() {
        return nhs;
    }

    /**
     *
     * @return the test creation number present on class
     */
    public String getTestnumber() {
        return testnumber;
    }

    /**
     *
     * @return the The specific test type present on class
     */
    public TypeOfTest getTest() {
        return test;
    }

    /**
     *
     * @return the Client citizen card number present on class
     */
    public long getCCN() {
        return TIN;
    }

    /**
     *
     * @return the parameter list present on parameter list
     */
    public List<Parameter> getParametersList() {
        return parametersList;
    }

    public String getLaboratoryId() {
        return laboratoryId;
    }

}
