package app.domain.shared;

import org.apache.commons.math3.distribution.TDistribution;

/**
 * 
 */
public class HypothesisTests {
    private static final double A0 = 0;
    private static final double B0 = 0;

    /**
     * Creates the hypothesis test for the variable A
     * 
     * @param linearRegression the linear regression created from the arrays X and Y
     * @param X                array X
     * @param Y                array Y
     * @return the value of the hypothesis test for the variable A
     */
    public static double hypothesisTestA(LinearRegression linearRegression, double[] X, double[] Y) {
        int n = X.length;
        double intercept = linearRegression.intercept(), S, Sxx = linearRegression.Sxx(),
                average = Calculations.average(X);
        double summ = 0;
        for (int i = 0; i < n; i++) {
            summ += Math.pow(Y[i] - linearRegression.predict(X[i]), 2);
        }
        S = Math.sqrt((summ / (n - 2)));
        double topPart = intercept - A0;
        double bottomPart = S * Math.sqrt((1.0 / X.length) + (Math.pow(average, 2) / Sxx));
        return topPart / bottomPart;
    }

    /**
     * Creates the hypothesis test for the variable B
     * 
     * @param linearRegression the linear regression created from the arrays X and Y
     * @param X                array X
     * @param Y                array Y
     * @return the value of the hypothesis test for the variable B
     */
    public static double hypothesisTestB(LinearRegression linearRegression, double[] X, double[] Y) {
        int n = Y.length;
        double slope = linearRegression.slope(), S, Sxx = linearRegression.Sxx();
        double summ = 0;
        for (int i = 0; i < n; i++) {
            summ += Math.pow(Y[i] - linearRegression.predict(X[i]), 2);
        }
        S = Math.sqrt((summ / (n - 2)));
        double topPart = slope - B0;
        double bottomPart = S / Math.sqrt(Sxx);
        return topPart / bottomPart;
    }

    /**
     * Gets the correspondent T-Value according to the degrees of freedom and the
     * alpha
     * 
     * @param degreesOfFreedom degrees of freedom from the arrays
     * @param alpha            alpha for the significance level
     * @return the T-Value associated with the 2 given values
     */
    public static double getCorrespondentTValue(int degreesOfFreedom, double alpha) {
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
     * Makes the decision to reject or not the hypothesis test according to the
     * given values
     * 
     * @param linearRegression linear regression originated from the X and Y arrays
     * @param X                array X
     * @param Y                array Y
     * @param tryNumber        the number that decides which T-Value to be evaluated
     * @param alpha            the alpha to calculate the T-Value
     * @return the decision to reject or not the hypothesis test
     */
    public static String makeDecision(LinearRegression linearRegression, double[] X, double[] Y, int tryNumber,
            double alpha) {
        if (tryNumber == 1) {
            if (hypothesisTestA(linearRegression, X, Y) > getCorrespondentTValue(linearRegression.getLength() - 2,
                    alpha)) {
                return "Reject H0a";
            } else {
                return "No reject H0a";
            }
        } else {
            if (hypothesisTestB(linearRegression, X, Y) > getCorrespondentTValue(linearRegression.getLength() - 2,
                    alpha)) {
                return "Reject H0b";
            } else {
                return "No reject H0b";
            }
        }
    }
}
