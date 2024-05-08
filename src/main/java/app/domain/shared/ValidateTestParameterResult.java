package app.domain.shared;

import app.domain.model.TestParameterResult;

public class ValidateTestParameterResult {

    private static double MaxRefValue;
    private static double MinRefValue;
    private static double Result;

    public static String validateTestParameterResult(TestParameterResult testParameterResult) {
        MaxRefValue = testParameterResult.getMaxRefValue();
        MinRefValue = testParameterResult.getMinRefValue();
        Result = testParameterResult.getResult();
        if (Result < MaxRefValue && Result > MinRefValue) {
            return ("The " + testParameterResult.getParameter().getCode() + " is within the Minimum and Maximum Reference Values");
        }else if (Result < MinRefValue) {
            return ("The " + testParameterResult.getParameter().getCode() + " is below the Minimum Reference Value");
        }else {
            return ("The " + testParameterResult.getParameter().getCode() + " is above the Maximum Reference Value");
        }
    }
}
