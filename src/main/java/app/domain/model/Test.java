package app.domain.model;

import app.domain.shared.BloodAdapter;
import app.domain.shared.Constants;
import app.domain.shared.CovidAdapter;
import app.domain.shared.ExternalModule;
import com.example2.EMRefValue;

import java.io.Serializable;

public class Test implements Serializable {
    /**
     * Used to create a result for a parameter
     */
    private final TestParameter testParameter = new TestParameter();

    public Test() {
        /**
         * There is no need for attributes
         */
    }

    /**
     * Gets a parameter for a given parameterId
     * 
     * @param parameterCode parameterId
     * @param company
     * @return parameter
     */
    public Parameter getTestParameterFor(String parameterCode, Company company) {

        return testParameter.getParameter(parameterCode, company);
    }

    /**
     * Gets the external module for a given test registered
     * 
     * @param test
     * @return
     */
    public ExternalModule getExternalModule(RegisterTest test) {
        ExternalModule em = null;
        String codeTypeOfTest = test.getTypeOfTest().getCode();
        if (codeTypeOfTest.equalsIgnoreCase("covid")) {
            em = Constants.COVID_MODULE;
        } else if (codeTypeOfTest.equalsIgnoreCase("blood")) {
            em = Constants.BLOOD_MODULE;
        }
        return em;
    }

    /**
     * Gets a reference value for a given parameter
     * 
     * @param parameter parameter chosen
     * @param test      registered test chosen
     * @param accessKey to access the external modules
     * @return
     */
    public EMRefValue getReferenceValue(Parameter parameter, RegisterTest test, int accessKey) {
        ExternalModule em = getExternalModule(test);
        return em.getReferenceValue(parameter, accessKey);
    }

    /**
     * Gets a reference value for a given parameter
     * 
     * @param parameter parameter chosen
     * @param test      registered test chosen
     * @param accessKey to access the external modules
     * @param metric    metric chosen
     * @return
     */
    public EMRefValue getReferenceValueWithMetric(Parameter parameter, RegisterTest test, int accessKey,
            String metric) {
        ExternalModule em = getExternalModule(test);
        return em.getReferenceValue(parameter, accessKey, metric);
    }

    /**
     * Creates a new TestParameterResult object
     * 
     * @param parameterCode parameterId
     * @param result        result for the parameter chosen
     * @param accessKey     to access the external modules
     * @param test          registered test chosen
     * @param company
     * @return
     */
    public TestParameterResult addTestResult(String parameterCode, double result, int accessKey, RegisterTest test,
            Company company) {
        Parameter p = getTestParameterFor(parameterCode, company);
        EMRefValue refValue = getReferenceValue(p, test, accessKey);
        return new TestParameterResult(result, refValue.getMetric(), refValue.getMinValue(), refValue.getMaxValue(), p);
    }

    /**
     * Creates a new TestParameterResult object
     * 
     * @param parameterCode parameterId
     * @param result        result for the parameter chosen
     * @param accessKey     to access the external modules
     * @param test          registered test chosen
     * @param company
     * @param metric        metric inserted
     * @return
     */
    public TestParameterResult addTestResultWithMetric(String parameterCode, double result, int accessKey,
            RegisterTest test, Company company, String metric) {
        Parameter p = getTestParameterFor(parameterCode, company);
        EMRefValue refValue = getReferenceValueWithMetric(p, test, accessKey, metric);
        return new TestParameterResult(result, metric, refValue.getMinValue(), refValue.getMaxValue(), p);
    }

}
