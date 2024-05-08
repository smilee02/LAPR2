package app.domain.shared;

/**
 * 
 */
public abstract class VarianceAnalysis {

    /**
     * Makes the decision to reject or not the hypothesis according to the
     * calculated F-Snedecor value and the one from the table
     * 
     * @param F             the calculated F-Snedecor value
     * @param FSnedcorValue the F-Snedecor value from the table
     * @return the decision
     */
    public String makeDecision(double F, double FSnedcorValue) {
        if (F > FSnedcorValue) {
            return ("H0 is rejected \n" +
                    "The regression model is significant");
        } else {
            return ("H0 is accepted \n" +
                    "The regression model is not significant");
        }
    }

    /**
     * Abstract method to get the F-Snedecor value from the table
     * 
     * @param alpha            the alpha to calculate the significance value
     * @param degreesOfFreedom the degrees of freedom from the arrays
     * @return the F-Snedecor value
     */
    public abstract double getfSnedecorValue(double alpha, int degreesOfFreedom);
}
