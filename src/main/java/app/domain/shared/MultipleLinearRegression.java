package app.domain.shared;

/**
 * 
 */
public class MultipleLinearRegression {
    private final double[][] X;
    private final double[][] Y;
    private final int length;
    private final double[][] beta;
    private final double b0;
    private final double b1;
    private final double b2;

    /**
     * Constructor for this class, creates the values for the linear regression
     * 
     * @param X1 the array with the 1st independent variables
     * @param X2 the array with the 2nd independent variables
     * @param Y  the array with the dependent variables
     */
    public MultipleLinearRegression(double[] X1, double[] X2, double[] Y) {

        length = X1.length;

        double[][] matrixX = new double[length][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < length; j++) {
                if (i == 0) {
                    matrixX[j][i] = 1;
                } else if (i == 1) {
                    matrixX[j][i] = X1[j];
                } else {
                    matrixX[j][i] = X2[j];
                }
            }
        }

        this.X = matrixX;

        double[][] matrixXTransposta = Calculations.matrixTransposta(matrixX);

        double[][] matrixXTranspostaTimesMatrixX = Calculations.multiplyMatrix(matrixXTransposta, matrixX);

        double[][] matrixY = new double[Y.length][1];
        for (int i = 0; i < matrixY.length; i++) {
            matrixY[i][0] = Y[i];
        }

        this.Y = matrixY;
        double[][] matrixXTranspostaTimesY = Calculations.multiplyMatrix(matrixXTransposta, matrixY);

        double[][] matrixXTranspostaTimesMatrixXInverted = Calculations.invert(matrixXTranspostaTimesMatrixX);

        beta = Calculations.multiplyMatrix(matrixXTranspostaTimesMatrixXInverted, matrixXTranspostaTimesY);

        this.b0 = beta[0][0];
        this.b1 = beta[1][0];
        this.b2 = beta[2][0];
    }

    /**
     * Gets the X array
     * 
     * @return the X array
     */
    public double[][] getX() {
        return X;
    }

    /**
     * Gets the Y array
     * 
     * @return the Y array
     */
    public double[][] getY() {
        return Y;
    }

    /**
     * Gets the length of variables from the arrays
     * 
     * @return the length of the variables from the arrays
     */
    public int getLength() {
        return length;
    }

    /**
     * Gets the Beta array
     * 
     * @return the Beta array
     */
    public double[][] getBeta() {
        return beta;
    }

    /**
     * Gets the 1st element from the Beta array
     * 
     * @return the 1st element from the Beta array
     */
    public double getB0() {
        return b0;
    }

    /**
     * Gets the 2nd element from the Beta array
     * 
     * @return the 2nd element from the Beta array
     */
    public double getB1() {
        return b1;
    }

    /**
     * Gets the 3rd element from the Beta array
     * 
     * @return the 3rd elemente from the Beta array
     */
    public double getB2() {
        return b2;
    }

    /**
     * Predicts the y from the 2 x values
     * 
     * @param X1 the 1st x value
     * @param X2 the 2nd x value
     * @return the calculated y
     */
    public double predict(double X1, double X2) {
        return (b0 + (b1 * X1) + (b2 * X2));
    }
}
