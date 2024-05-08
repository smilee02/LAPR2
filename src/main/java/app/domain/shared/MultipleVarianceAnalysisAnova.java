package app.domain.shared;

import org.apache.commons.math3.distribution.FDistribution;

/**
 * 
 */
public class MultipleVarianceAnalysisAnova extends VarianceAnalysis {
    private final double SQT, SQR, SQE;
    private final double MQR, MQE;
    private final double F;
    private final double R2, R2Adjusted;
    private static final double K = 2;

    /**
     * Constructor for this class to calculate the variables required for the anova
     * table
     * 
     * @param multipleLinearRegression the multiple linear regression created from
     *                                 the variables to get the values from
     */
    public MultipleVarianceAnalysisAnova(MultipleLinearRegression multipleLinearRegression) {
        double[][] X = multipleLinearRegression.getX();
        double[][] Y = multipleLinearRegression.getY();
        int n = X.length;
        double[] YProvisional = new double[Y.length];
        for (int i = 0; i < Y.length; i++) {
            YProvisional[i] = Y[i][0];
        }
        double YTTimesY = Calculations.multiplyMatrix(Calculations.matrixTransposta(Y), Y)[0][0];
        double[][] beta = multipleLinearRegression.getBeta();

        SQT = YTTimesY - Math.pow(Calculations.average(YProvisional), 2) * Y.length;
        SQR = Calculations
                .multiplyMatrix(Calculations.multiplyMatrix(Calculations.matrixTransposta(beta),
                        Calculations.matrixTransposta(X)), Y)[0][0]
                - Math.pow(Calculations.average(YProvisional), 2) * Y.length;
        SQE = Calculations.multiplyMatrix(Calculations.matrixTransposta(Y), Y)[0][0] - Calculations.multiplyMatrix(
                Calculations.multiplyMatrix(Calculations.matrixTransposta(beta), Calculations.matrixTransposta(X)),
                Y)[0][0];

        MQR = SQR / K;
        MQE = SQE / (Y.length - (K + 1));

        F = MQR / MQE;

        R2 = SQR / SQT;
        R2Adjusted = 1 - ((n - 1) / (n - (K + 1))) * (1 - R2);
    }

    /**
     * Gets the SQT value
     * 
     * @return the SQT value
     */
    public double getSQT() {
        return SQT;
    }

    /**
     * Gets the SQR value
     * 
     * @return the SQR value
     */
    public double getSQR() {
        return SQR;
    }

    /**
     * Gets the SQE value
     * 
     * @return the SQE value
     */
    public double getSQE() {
        return SQE;
    }

    /**
     * Gets the MQR value
     * 
     * @return the MQR value
     */
    public double getMQR() {
        return MQR;
    }

    /**
     * Gets the MQE value
     * 
     * @return the MQE value
     */
    public double getMQE() {
        return MQE;
    }

    /**
     * Gets the F value
     * 
     * @return the F value
     */
    public double getF() {
        return F;
    }

    /**
     * Gets the R2 value
     * 
     * @return the R2 value
     */
    public double getR2() {
        return R2;
    }

    /**
     * Gets the R2Adjusted value
     * 
     * @return the R2Adjusted value
     */
    public double getR2Adjusted() {
        return R2Adjusted;
    }

    /**
     * Gets the F-Snedecor value associated with the given information
     * 
     * @param alpha            the alpha to calculate the significance value
     * @param degreesOfFreedom the degrees of freedom from the arrays
     * @return the F-Snedecor Value from the table
     */
    public double getfSnedecorValue(double alpha, int degreesOfFreedom) {
        FDistribution fd = new FDistribution(K, degreesOfFreedom);
        return fd.inverseCumulativeProbability(1 - alpha);
    }
}
