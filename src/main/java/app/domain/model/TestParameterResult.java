package app.domain.model;

import com.example2.EMRefValue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Result of a test Parameter Object
 *
 * 
 */
public class TestParameterResult implements Serializable {
    private double result;
    private String metric;
    private final double minRefValue;
    private final double maxRefValue;
    private final Parameter parameter;

    /**
     * Constructor
     *
     * @param result result of a parameter chosen
     * @param metric metric of a parameter
     *
     */
    public TestParameterResult(double result, String metric, double minRefValue, double maxRefValue,
            Parameter parameter) {
        this.result = result;
        this.metric = metric;
        this.minRefValue = minRefValue;
        this.maxRefValue = maxRefValue;
        this.parameter = parameter;

    }

    /**
     * @return the result
     */
    public double getResult() {
        return result;
    }

    /**
     * @return the metric
     */
    public String getMetric() {
        return metric;
    }

    public double getMinRefValue() {
        return minRefValue;
    }

    public double getMaxRefValue() {
        return maxRefValue;
    }

    public Parameter getParameter() {
        return parameter;
    }

    /**
     * Replaces the old result with this new one
     * 
     * @param result result of a chosen parameter
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * Replaces the old metric with a new one
     * 
     * @param metric metric of a parameter
     */
    public void setMetric(String metric) {
        this.metric = metric;
    }

    /**
     * @return a string of class information and values
     */
    @Override
    public String toString() {
        return parameter.getCode() + "   " + result + " " + metric + "   [" + minRefValue + "," + maxRefValue + "]   "
                + metric;
    }

}
