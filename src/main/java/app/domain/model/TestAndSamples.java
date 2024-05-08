package app.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TestAndSamples implements Serializable {
    private final RegisterTest registerTest;
    private final List<Sample> samplesList;

    /**
     * The Test and its respective samples constructor
     * @param registerTest is the new test.
     * @param samplesList is the new sample.
     */
    public TestAndSamples(RegisterTest registerTest, List<Sample> samplesList){
        this.registerTest = registerTest;
        this.samplesList = samplesList;
        Date date = new Date();
    }

    /**
     *
     * @return the test
     */
    public RegisterTest getRegisterTest() {
        return registerTest;
    }

    /**
     *
     * @return the list of the samples.
     */
    public List<Sample> getSamplesList() {
        return samplesList;
    }

    @Override
    public String toString() {
        return "TestAndSamples{" +
                "registerTest=" + registerTest +
                ", samplesList=" + samplesList +
                '}';
    }
}
