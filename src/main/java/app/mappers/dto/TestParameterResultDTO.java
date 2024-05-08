package app.mappers.dto;

import app.domain.model.Parameter;
import com.example2.EMRefValue;

public class TestParameterResultDTO {
    private double result;
    private String metric;
    private double minRefValue;
    private double maxRefValue;
    private Parameter parameter;

    public TestParameterResultDTO(double result, String metric,double minRefValue, double maxRefValue, Parameter parameter) {
        this.result = result;
        this.metric = metric;
        this.minRefValue = minRefValue;
        this.maxRefValue = maxRefValue;
        this.parameter = parameter;

    }

    public double getResult() {
        return result;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public double getMaxRefValue() {
        return maxRefValue;
    }

    public double getMinRefValue() {
        return minRefValue;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public void setMaxRefValue(double maxRefValue) {
        this.maxRefValue = maxRefValue;
    }

    public void setMinRefValue(double minRefValue) {
        this.minRefValue = minRefValue;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return parameter.getCode() + "   " + result + " " + metric + "   [" + minRefValue + "," + maxRefValue + "]   " + metric;
    }
}
