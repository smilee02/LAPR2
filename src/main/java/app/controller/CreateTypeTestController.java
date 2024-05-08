package app.controller;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.model.TypeOfTest;
import app.domain.model.Parameter;
import app.domain.shared.Constants;
import app.domain.store.TypeOfTestStore;

import java.util.List;

/**
 * 
 */
public class CreateTypeTestController {

    private final Company company;

    /**
     * Creator of a CreateTypeOfTestController
     */
    public CreateTypeTestController() {
        this.company = Constants.COMPANY;
    }

    /**
     * Intermediary method to save a TypeOfTest
     *
     * @param typeOfTest TypeOfTest to be saved
     * @return success boolean
     */
    public boolean saveTypeTest(TypeOfTest typeOfTest) {
        return company.getTypeOfTestStore().saveTypeTest(typeOfTest);
    }

    public List<TypeOfTest> getTypeOfTestList() {
        return company.getTypeOfTestStore().getListOfTypeOfTest();
    }

    /**
     * Intermediary method to verify the given code
     *
     * @param code code to be verified
     * @return number indicating the type of error/success
     */
    public static int verifyCode(String code) {
        return TypeOfTest.validateTypeTest(code, 1);
    }

    /**
     * Intermediary method to verify the given description
     *
     * @param description description to be verified
     * @return number indicating the type of error/success
     */
    public static int verifyDescription(String description) {
        return TypeOfTest.validateTypeTest(description, 2);
    }

    /**
     * Intermediaty method to verify the given collecting method
     *
     * @param collectingMethod collecting method to be verified
     * @return number indicating the type of error/success
     */
    public static int verifyCollectingMethod(String collectingMethod) {
        return TypeOfTest.validateTypeTest(collectingMethod, 3);
    }

    /**
     * Returns the ParameterCategoryList
     *
     * @return ParameterCategoryList
     */
    public static List<ParameterCategory> getParameterCategoryList() {
        return Company.getParametercategorylist();
    }

    /**
     * Saves the category into the given TypeOfTest
     *
     * @param typeOfTest     given TypeOfTest
     * @param categoryNumber number of the wanted category
     */
    public static void saveCategories(TypeOfTest typeOfTest, int categoryNumber) {
        typeOfTest.addCategories(categoryNumber);
    }

    /**
     * Method to print the given string
     *
     * @param l given string
     */
    public static void display(String l) {
        System.out.println(l);
    }

    /**
     * Method to add the TypeOfTest's code to the list of codes
     *
     * @param typeOfTest the TypeOfTest whose code to be added
     */
    public static void addCode(TypeOfTest typeOfTest) {
        typeOfTest.addCode();
    }
}