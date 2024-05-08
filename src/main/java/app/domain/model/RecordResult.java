package app.domain.model;

import app.domain.shared.Constants;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The Results of a given test Object
 *
 * 
 */
public class RecordResult implements Serializable, Comparable<RecordResult> {

    private final RegisterTest test;
    private final List<TestParameterResult> resultList;
    private Date createdAt;

    /**
     * Constructor
     *
     * @param test       registered test chosen
     * @param resultList the list of results for the test parameters
     */
    public RecordResult(RegisterTest test, List<TestParameterResult> resultList) {
        this.test = test;
        this.resultList = resultList;
        this.createdAt = new Date();
    }

    /**
     * @return registered test
     */
    public RegisterTest getTest() {
        return test;
    }

    /**
     * @return list of results
     */
    public List<TestParameterResult> getResultList() {
        return resultList;
    }

    /**
     * @return date the results were created
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return a string of class information and values
     */
    @Override
    public String toString() {
        String results = "";
        for (TestParameterResult x : resultList) {
            results = results + "\n" + x.toString();
        }
        return "\nResults List:\n" + results +
                "\nDate of Test Results Created:" + Constants.SIMPLE_DATE_FORMAT.format(createdAt);
    }

    @Override
    public int compareTo(RecordResult recordResult) {
        return getCreatedAt().compareTo(recordResult.getCreatedAt());
    }
}
