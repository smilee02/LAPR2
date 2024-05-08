package app.domain.model;

import java.io.Serializable;

/**
 * 
 */
public class Parameter implements Serializable {

    /**
     * Parameter's code
     */
    private final String code;

    /**
     * Parameter's name
     */
    private final String name;

    /**
     * Parameter's description
     */
    private final String description;

    /**
     * Create an object of the type Parameter
     *
     * @param code        The Parameter's code
     * @param name        The Parameter's name
     * @param description The Parameter's description
     */
    public Parameter(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
        Company.addCode(code);
    }

    /**
     * Returns the code of the Parameter
     *
     * @return String with the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the name of the Parameter
     *
     * @return String with the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the Parameter
     *
     * @return String with the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a String with the Parameter's information
     *
     * @return String with the Parameter's information
     */
    @Override
    public String toString() {
        return String.format("Parameter: %n Code: %s %n Name: %s %n Description: %s %n ", code, name, description);
    }

    /**
     * Validates the provided code
     *
     * @param code Wanted to validate code
     * @return A number informing the type of error that may or may not have
     *         occurred
     */
    public static int validateCode(String code) {
        boolean seeIfExists = false;
        for (int i = 0; i < Company.getCodeList().size(); i++) {
            if (code.equals(Company.getCodeList().get(i))) {
                seeIfExists = true;
                break;
            }
        }

        if (code.length() != 5 || seeIfExists) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Validates the provided name
     *
     * @param name Wanted to validate name
     * @return A number informing the type of error that may or may not have
     *         occurred
     */
    public static int validateName(String name) {
        if (name.length() > 8 || name.isEmpty()) {
            return 2;
        } else {
            return 0;
        }
    }

    /**
     * Validates the provided description
     *
     * @param description Wanted to validate description
     * @return A number informing the type of error that may or may not have
     *         occurred
     */
    public static int validateDescription(String description) {
        if (description.length() > 20 || description.isEmpty()) {
            return 3;
        } else {
            return 0;
        }
    }
}
