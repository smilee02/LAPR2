package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestParameterTest {
    Company company = new Company("a");
    TestParameter testParameter = new TestParameter();
    /*@Test
    public void getParameter() {
        assertNotNull(testParameter.getParameter("1",company));
        company.getParameterStore().getListOfParameters().add(new Parameter("1","1","1"));
        assertNotNull(testParameter.getParameter("1",company));

    }*/
}