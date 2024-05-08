package app.domain.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestAndSamplesTest {
    public static final String ccn = "1234567812345678";
    private List<ParameterCategory> parameterCategories = new ArrayList<>();
    private ParameterCategory p = new ParameterCategory("1","1");

    @Test
    public void getRegisterTest() {
        parameterCategories.add(p);
        List<Parameter> parameterList = parameterCategories.get(0).getParameterList();
        RegisterTest test = new RegisterTest(parameterList, Long.parseLong(ccn), new TypeOfTest("blood", "blood", "blood"), "123456789121", "123456789121","abcd1");
        List<Sample> samplesList = new ArrayList<>();
        TestAndSamples samples = new TestAndSamples(test, samplesList);

        assertNotNull(samples.getRegisterTest());
    }

    @Test
    public void getSamplesList() {
        parameterCategories.add(p);
        List<Parameter> parameterList = parameterCategories.get(0).getParameterList();
        RegisterTest test = new RegisterTest(parameterList, Long.parseLong(ccn), new TypeOfTest("blood", "blood", "blood"), "123456789121", "123456789121","abcd1");
        List<Sample> samplesList = new ArrayList<>();
        TestAndSamples samples = new TestAndSamples(test, samplesList);

        Sample s = new Sample("1", 0);
        Sample d = new Sample("11", 0);
        samplesList.add(s);
        samplesList.add(d);
        assertNotNull(samples.getSamplesList());
    }

}