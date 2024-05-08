package app.domain.shared;

import org.apache.commons.math3.distribution.FDistribution;

/**
 * 
 */
public class SimpleVarianceAnalysisAnova extends VarianceAnalysis {
    private final double ST, SR, SE;
    private final double MSR, MSE;
    private final double F;
    private static final int NUMERATOR_DEGREES_OF_FREEDOM = 1;

    /**
     * Constructor class that initiates the necessary variables for the anova table
     * 
     * @param X                the X array
     * @param Y                the Y array
     * @param linearRegression the linear regression used to get some values from
     */
    public SimpleVarianceAnalysisAnova(double[] X, double[] Y, LinearRegression linearRegression) {
        double[] arrayST = new double[Y.length];
        double[] arraySR = new double[Y.length];
        double[] arraySE = new double[Y.length];
        for (int i = 0; i < Y.length; i++) {
            arrayST[i] = Math.pow(Y[i] - Calculations.average(Y), 2);
            arraySR[i] = Math.pow(linearRegression.predict(X[i]) - Calculations.average(Y), 2);
            arraySE[i] = Math.pow(Y[i] - linearRegression.predict(X[i]), 2);
        }

        ST = Calculations.summ(arrayST);
        SR = Calculations.summ(arraySR);
        SE = Calculations.summ(arraySE);

        MSR = SR; // SR/1 was simplified to just SR
        MSE = SE / (Y.length - 2);

        F = MSR / MSE;
    }

    /**
     * Gets the correspondent f-Snedecor value from the f-Snedecor chart
     * 
     * @param alpha            the alpha to calculate the significance value
     * @param degreesOfFreedom the degrees of freedom from the arrays
     * @return the correspondent f-Snedecor value
     */
    public double getfSnedecorValue(double alpha, int degreesOfFreedom) {
        FDistribution fd = new FDistribution(NUMERATOR_DEGREES_OF_FREEDOM, degreesOfFreedom);
        return fd.inverseCumulativeProbability(1 - alpha);
    }

    /**
     * Gets the SE
     * 
     * @return the SE
     */
    public double getSE() {
        return SE;
    }

    /**
     * Gets the SR
     * 
     * @return the SR
     */
    public double getSR() {
        return SR;
    }

    /**
     * Gets the ST
     * 
     * @return the ST
     */
    public double getST() {
        return ST;
    }

    /**
     * Gets the MSR
     * 
     * @return the MSR
     */
    public double getMSR() {
        return MSR;
    }

    /**
     * Gets the MSE
     * 
     * @return the MSE
     */
    public double getMSE() {
        return MSE;
    }

    /**
     * Gets the F-Value
     * 
     * @return the F-Value
     */
    public double getF() {
        return F;
    }
}
