package app.domain.shared;

import org.apache.commons.math3.distribution.TDistribution;

/**
 * 
 */
public class MultipleHypothesisTests {

    private final double IC1;
    private final double IC2;
    private final double t;

    /**
     * Creates the object of the type MultipleHypothesisTests to get the 2
     * confidence intervals from
     * 
     * @param multipleLinearRegression      the multiple linear regression created
     *                                      from the arrays X and Y
     * @param multipleVarianceAnalysisAnova the object of the type
     *                                      MultipleVarianceAnalysisAnova used to
     *                                      make the anova table
     * @param alpha                         the alpha used for the significance
     *                                      value
     * @param x1                            x1 variable
     * @param x2                            x2 variable
     */
    public MultipleHypothesisTests(MultipleLinearRegression multipleLinearRegression,
            MultipleVarianceAnalysisAnova multipleVarianceAnalysisAnova, double alpha, double x1, double x2) {
        double[][] xT0 = new double[1][3];
        xT0[0][0] = 1;
        xT0[0][1] = x1;
        xT0[0][2] = x2;
        double[][] x0 = Calculations.matrixTransposta(xT0), Cx0 = Calculations.invert(Calculations.multiplyMatrix(
                Calculations.matrixTransposta(multipleLinearRegression.getX()), multipleLinearRegression.getX())),
                beta = multipleLinearRegression.getBeta();
        double multiplication = Calculations.multiplyMatrix(Calculations.multiplyMatrix(xT0, Cx0), x0)[0][0],
                MQE = multipleVarianceAnalysisAnova.getMQE();
        t = getCorrespondentTValue((multipleLinearRegression.getLength() - 3), alpha);

        double triangle1 = t * Math.sqrt((MQE * (1 + multiplication))),
                y0 = Calculations.multiplyMatrix(xT0, beta)[0][0];
        IC1 = y0 - triangle1;
        IC2 = y0 + triangle1;
    }

    /**
     * Calculates the T-Value associated with the given data
     * 
     * @param multipleLinearRegression      the multiple linear regression used to
     *                                      get the T-Value
     * @param multipleVarianceAnalysisAnova the object of the type
     *                                      MultipleVarianceAnalysisAnova used to
     *                                      make the anova table
     * @param j                             the number of the of the variable to be
     *                                      used in the calculation of the T-Value
     * @return the calculated T-Value
     */
    public static double calculateActualTValue(MultipleLinearRegression multipleLinearRegression,
            MultipleVarianceAnalysisAnova multipleVarianceAnalysisAnova, int j) {
        double[][] B = multipleLinearRegression.getBeta();
        double o2 = multipleVarianceAnalysisAnova.getMQE();
        double[][] C = Calculations.invert(Calculations.multiplyMatrix(
                Calculations.matrixTransposta(multipleLinearRegression.getX()), multipleLinearRegression.getX()));
        return B[j][0] / Math.sqrt(o2 * C[j][j]);
    }

    /**
     * Gets the T-Value correspondent to the given data
     * 
     * @param degreesOfFreedom degrees of freedom used to find the T-Value
     * @param alpha            the alpha to calculate the significance level
     * @return the T-Value correspondent to the given data
     */
    public static double getCorrespondentTValue(int degreesOfFreedom, double alpha) {
        alpha = alpha / 2;
        TDistribution td = new TDistribution(degreesOfFreedom);
        double critTD;
        if (alpha > 0.5) {
            critTD = td.inverseCumulativeProbability(alpha);
        } else {
            critTD = td.inverseCumulativeProbability(1 - alpha);
        }
        return (critTD);
    }

    /**
     * Makes the decision to reject or not the hypothesis tests
     * 
     * @param multipleLinearRegression the multiple linear regression created from
     *                                 the X and Y arrays
     * @param alpha                    the alpha to get the correspondent T-Value
     * @param t                        the calculated T-Value
     * @param B                        the number of the variable to be evalueated
     * @return the decision
     */
    public static String makeDecision(MultipleLinearRegression multipleLinearRegression, double alpha, double t,
            int B) {
        if (B == 1) {
            if (t > getCorrespondentTValue((multipleLinearRegression.getLength() - 3), alpha)) {
                return "Reject H01";
            } else {
                return "No reject H01";
            }
        } else {
            if (t > getCorrespondentTValue((multipleLinearRegression.getLength() - 3), alpha)) {
                return "Reject H02";
            } else {
                return "No reject H02";
            }
        }
    }

    /**
     * Gets the 1st part of the confidence interval
     * 
     * @return the 1st part of the confidence interval
     */
    public double getIC1() {
        return IC1;
    }

    /**
     * Gets the 2nd part of the confidence interval
     * 
     * @return the 2nd part of the confidence interval
     */
    public double getIC2() {
        return IC2;
    }

    /**
     * Gets the calculated T-Value
     * 
     * @return calculated T-Value
     */
    public double getT() {
        return t;
    }
}
