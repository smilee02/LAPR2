package app.domain.shared;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinearRegressionTest {
    private LinearRegression linearRegression = new LinearRegression(getArray(),getArray());

    @Test
    public void intercept() {
        assertEquals(0.0,linearRegression.intercept(),0.0);
    }

    @Test
    public void slope() {
        assertEquals(1.0,linearRegression.slope(),0.0);
    }

    @Test
    public void r2() {
        assertEquals(1.0,linearRegression.R2(),0.0);
    }

    @Test
    public void r2Adjusted() {
        assertEquals(1.0,linearRegression.R2Adjusted(),0.0);
    }

    @Test
    public void interceptStdErr() {
        assertEquals(0.0,linearRegression.interceptStdErr(),0.0);
    }

    @Test
    public void slopeStdErr() {
        assertEquals(0.0,linearRegression.slopeStdErr(),0.0);
    }

    @Test
    public void predict() {
        assertEquals(1.0,linearRegression.predict(1.0),0.0);
    }

    @Test
    public void sxx() {
        assertEquals(0.6666666666666667,linearRegression.Sxx(),0.0);
    }

    @Test
    public void sxy() {
        assertEquals(0.6666666666666667,linearRegression.Sxy(),0.0);
    }

    @Test
    public void syy() {
        assertEquals(0.6666666666666667,linearRegression.Syy(),0.0);
    }

    @Test
    public void getLength() {
        assertEquals(3.0,linearRegression.getLength(),0.0);
    }

    public double[] getArray(){
        double[] arr = new double[3];
        arr[0] = 3.0;
        arr[1] = 2.0;
        arr[2] = 3.0;
        return arr;
    }
}