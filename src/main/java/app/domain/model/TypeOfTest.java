package app.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class TypeOfTest implements Serializable {

    /**
     * TypeOfTest's code
     */
    private final String code;

    /**
     * TypeOfTest's description
     */
    private final String description;

    /**
     * TypeOfTest's collecting methods
     */
    private final String collectingMethods;

    /**
     * TypeOfTest's list with the categories
     */
    private final List<ParameterCategory> categoriesList = new ArrayList<>();

    /**
     * Create an object of the type TypeOfTest.
     *
     * @param code              The TypeOfTest's code
     * @param description       The TypeOfTest's description
     * @param collectingMethods The TypeOfTest's collecting method
     */
    public TypeOfTest(String code, String description, String collectingMethods) {
        this.code = code;
        this.description = description;
        this.collectingMethods = collectingMethods;
    }

    /**
     * Adding the code of the TypeOfTest to the list of codes so it does not repeat
     * itself
     */
    public void addCode() {
        Company.addCode(this.code);
    }

    /**
     * Returns the code of the TypeOfTest
     *
     * @return String with the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the collecting methods of the TypeOfTest
     *
     * @return String with the collecting methods
     */
    public String getCollectingMethods() {
        return collectingMethods;
    }

    /**
     * Returns the description of the TypeOfTest
     *
     * @return String with the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string with the information of the TypeOfTest
     *
     * @return String with the TypeOfTest's information
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(String.format(
                "Type of test: %n Code: %s %n Description: %s %n Collecting Methods: %s %n Categories: %n ", code,
                description, collectingMethods));
        for (ParameterCategory parameterCategory : categoriesList) {
            string.append(parameterCategory.toString());
        }
        return string.toString();
    }

    public String toStringSampleRecorder() {
        StringBuilder string = new StringBuilder(
                String.format("Type of Test: %nDescription: %s %nCollecting methods: %s %n Categories: %n ",
                        description, collectingMethods));
        for (ParameterCategory parameterCategory : categoriesList) {
            string.append(parameterCategory.toString());
        }
        return string.toString();
    }

    public List<ParameterCategory> getCategoriesList() {
        return categoriesList;
    }

    /**
     * Adds the selected category to the TypeOfTest
     *
     * @param number number selected from te list of categories
     */
    public void addCategories(int number) {
        categoriesList.add(Company.getParametercategorylist().get(number));
    }

    /**
     * Validates the parameters that can be tested and returns a number that informs
     * the type of error that ocurred
     *
     * @param parameterToTest the parameter to be tested
     * @param parameterNumber informs the type of parameter that is going to be
     *                        tested
     * @return returns the type of error that occurred
     */
    public static int validateTypeTest(String parameterToTest, int parameterNumber) {

        if (parameterNumber == 1) { // testing the code

            boolean seeIfExists = false;
            for (int i = 0; i < Company.getCodeList().size(); i++) {
                if (parameterToTest.equals(Company.getCodeList().get(i))) {
                    seeIfExists = true;
                    break;
                }
            }

            if (parameterToTest.length() != 5 || seeIfExists) {
                return 1;
            }
        }

        if (parameterNumber == 2) { // testing the description
            if (parameterToTest.length() >= 15 || parameterToTest.isEmpty()) {
                return 2;
            }
        }

        if (parameterNumber == 3) { // testing the collecting method
            if (parameterToTest.length() >= 20 || parameterToTest.isEmpty()) {
                return 3;
            }
        }

        return 0;
    }
}
