package app.controller;

import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.shared.Constants;

/**
 * 
 */
public class ParameterController {

    private final Company company;

    /**
     * Constructor of a instance ParameterController
     */
    public ParameterController() {
        this.company = Constants.COMPANY;
    }

    /**
     * Method to save a parameter
     *
     * @param parameter parameter to save
     * @return success message
     */
    public boolean saveParameter(Parameter parameter) {
        return company.getParameterStore().saveParameter(parameter);
    }

    /**
     * Intermediary method to validate a code
     *
     * @param code code to validate
     * @return the type of error/success number
     */
    public static int validateCode(String code) {
        return Parameter.validateCode(code);
    }

    /**
     * Intermediary method to validate a name
     *
     * @param name name to validate
     * @return the type of error/success number
     */
    public static int validateName(String name) {
        return Parameter.validateName(name);
    }

    /**
     * Intermediary method to validate a description
     *
     * @param description description to validate
     * @return the type of error/success number
     */
    public static int validateDescription(String description) {
        return Parameter.validateDescription(description);
    }

    /**
     * Method to print the given strings in the console
     *
     * @param word word to print
     */
    public static void inConsole(String word) {
        System.out.println(word);
    }

    /**
     * Method to print the given string and skip to the next line
     *
     * @param word word to print
     */
    public static void inConsoleNoLn(String word) {
        System.out.print(word);
    }
}
