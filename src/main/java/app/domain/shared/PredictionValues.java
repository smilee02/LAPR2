package app.domain.shared;

/**
 * 
 */
public class PredictionValues {
    private final double y0;
    private final double triangle;

    /**
     * Creates an object of the type PredictionValues and it contains the variables
     * necessary to make the 2 intervals of confidence
     * 
     * @param X                 array with the variables X
     * @param Y                 array with the variables Y
     * @param arrayDailyTests   array with the tests of that current day
     * @param linearRegression  the linear regression created from the arrays X and
     *                          Y
     * @param significanceValue the significance value for the intervals of
     *                          confidence to be made from
     */
    public PredictionValues(double[] X, double[] Y, double[] arrayDailyTests, LinearRegression linearRegression,
            double significanceValue) {
        int n = X.length;
        double tc;
        double s;
        double restRoot;
        y0 = linearRegression.predict(arrayDailyTests.length);
        tc = HypothesisTests.getCorrespondentTValue((linearRegression.getLength() - 2), significanceValue / 2);
        double summ = 0;
        for (int i = 0; i < n; i++) {
            summ += Math.pow(Y[i] - linearRegression.predict(X[i]), 2);
        }
        s = Math.sqrt((summ / (n - 2)));
        restRoot = Math.sqrt(1 + (1 / (double) n)
                + (Math.pow(arrayDailyTests.length - Calculations.average(X), 2) / linearRegression.Sxx()));
        triangle = tc * s * restRoot;
    }

    /**
     * Method to return the triangle
     * 
     * @return the triangle
     */
    public double getTriangle() {
        return triangle;
    }

    /**
     * Method to return the y0
     * 
     * @return the y0
     */
    public double getY0() {
        return y0;
    }
}
