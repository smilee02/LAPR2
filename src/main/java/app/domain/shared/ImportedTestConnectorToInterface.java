package app.domain.shared;

import app.controller.ClientController;
import app.controller.importedTestController;
import app.domain.model.*;
import app.domain.store.RecordResultStore;
import auth.domain.model.Email;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Connector to Interface Controller
 *
 * 
 */
public class ImportedTestConnectorToInterface {
    public importedTestController importedTestController = new importedTestController();
    public List<String> logs = new ArrayList<>();
    public int fail = 0;
    public ClientController clientController = new ClientController();
    public static final int TESTCODE = 0;
    public static final int NHSCODE = 1;
    public static final int LABID = 2;
    public static final int CITIZENCARDNUMBER = 3;
    public static final int NHSNUMBER = 4;
    public static final int TINNUMBER = 5;
    public static final int BIRTHDAY = 6;
    public static final int PHONENUMBER = 7;
    public static final int NAME = 8;
    public static final int EMAIL = 9;
    public static final int ADDRESS = 10;
    public static final int TESTTYPELINE = 11;
    public Scanner in;

    /**
     * @param line receives a line in string
     * @return string array of line splited ;
     */
    public String[] convertLine(String line) {
        return line.trim().split(";");
    }

    /**
     * @param path file path in string
     * @return the imported test list that the user imported and stores the
     *         information
     */
    public List<importedTest> filePart(String path) {
        File file = new File(path);
        String[] firstLineArr = new String[0];
        String firstLine;
        String otherLine;
        String[] otherLineArr;
        int cont = 0;
        int valid = 0;
        try {
            in = new Scanner(file);
            while (in.hasNextLine()) {
                if (cont == 0) {
                    firstLine = in.nextLine();
                    firstLineArr = convertLine(firstLine);
                    cont++;

                } else {
                    otherLine = in.nextLine();
                    otherLineArr = convertLine(otherLine);
                    if (registerClient(otherLineArr)) {
                        if (registerTest(firstLineArr, otherLineArr)) {
                            logs.add("Test number: " + otherLineArr[0] + "->Name:" + otherLineArr[8] + "->"
                                    + importedTestController.getRt().getTypeOfTest().getDescription()
                                    + "->Test Registered Success\n");
                            valid++;
                        } else {
                            // System.out.println("Invalid:"+importedTestController.getClient().getName());
                            fail++;
                        }
                    } else {
                        fail++;
                    }

                    cont++;

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println("Valid:"+valid);
        // System.out.println("Fails:"+fail);
        int total = cont - 1;
        // System.out.println("Total:"+total);

        importedTestController.storeTest();
        logFile();
        // System.out.println(RecordResultStore.getTestResultsWithTests().size());
        if (importedTestController.getList().size() == 0) {
            logs.clear();
            logs.add("That File was already imported or its not an accepted file");
        }
        return importedTestController.getList();
    }

    /**
     * @return clear imported test list
     */
    public boolean clearList() {
        importedTestController.clearList();
        return true;
    }

    /**
     * @param otherLine file line in string array
     * @return boolean based if client can be stored or information of client is
     *         valid
     */
    public boolean registerClient(String[] otherLine) {
        String name = otherLine[8];//
        String ccn = otherLine[3];//
        String nhs = otherLine[4];//
        String birthdate = otherLine[6];//
        String tin = otherLine[5];//
        String phoneNumber = otherLine[7];//
        String email = otherLine[9];//
        String adress = otherLine[10];//
        // System.out.println(email);
        if (validateName(name) && validateEmail(email) && validateDate(birthdate) && checkNumber(ccn, 16)
                && checkNumber(nhs, 10) && checkNumber(tin, 10) && checkNumber(phoneNumber, 11)) {
            importedTestController.registerClient(name, Double.parseDouble(ccn), Double.parseDouble(nhs), birthdate,
                    Double.parseDouble(tin), Double.parseDouble(phoneNumber), email, adress);

            return true;
        } else {
            logs.add("Test number: " + otherLine[0] + "->Name:" + name + "->failed["
                    + "Please check Client input fields {name,ccn,nhs,birthdate,tin,phoneNumber,email}]\n");
            return false;
        }

    }

    /**
     * @param firstLineArr first line of file as an array
     * @param otherLine    other line of file as an array
     * @return boolean based if the test is registered or not
     */
    public boolean registerTest(String[] firstLineArr, String[] otherLine) {
        String testType = otherLine[11];
        Date dateR = convertToDate(otherLine[otherLine.length - 4]);
        Date dateC = convertToDate(otherLine[otherLine.length - 3]);
        Date dateV = convertToDate(otherLine[otherLine.length - 2]);
        Date dateD = convertToDate(otherLine[otherLine.length - 1]);
        if (dateR != null && dateC != null && dateV != null && dateD != null) {
            importedTestController.validateTest(testType);
            List<Integer> pos = getCategoryLines(firstLineArr);
            List<String> parameterCategoryList = getCategories(pos, otherLine);
            importedTestController.validateCategory(parameterCategoryList, testType);
            List<Parameter> parameterList = importedTestController.validateParameter(parameterCategoryList, testType);
            List<String> parametersUser = getParametersORValues(pos, firstLineArr);
            parameterList = importedTestController.getParameterList(parameterList, parametersUser);
            if (importedTestController.registerTest(parameterList, importedTestController.getTypeOfTest(testType),
                    otherLine[1], otherLine[0], otherLine[2])
                    && importedTestController.addRt(dateC, dateV, dateD, dateR)) {
                List<String> stringList = getParametersORValues(pos, otherLine);
                importedTestController.registerRecordResult(parameterList, fillArr(stringList));
                return true;
            } else {
                logs.add("Test number: " + otherLine[0] + "->Name:" + otherLine[8] + "->failed["
                        + "Please check Test input fields {Parameters,Category,labId,TestType,testnumber,nhsCode}]\n");
                return false;
            }
        } else
            return false;
    }

    /**
     * pass values from string to an array of doubles
     *
     * @param stringList List of string with values
     * @return the array with values as double
     */
    public double[] fillArr(List<String> stringList) {
        double[] arr = new double[stringList.size()];
        for (int i = 0; i < arr.length; i++) {
            String test = stringList.get(i);
            arr[i] = Double.parseDouble(fixString(test));
        }
        return arr;
    }

    /**
     * converts string with values with "," to "."
     *
     * @param test string to be fixed
     * @return string fixed with "."
     */
    public String fixString(String test) {
        String fix = test.replace(",", ".");
        return fix;
    }

    /**
     * validates if date is valid or not
     *
     * @param Date receives a string that contains a date
     * @return boolean based if date is valid or not
     */
    public static boolean validateDate(String Date) {
        String[] birthdatearr = Date.trim().split("/");
        return ClientController.checkMonth(birthdatearr[1]) && ClientController.checkYear(birthdatearr[2])
                && ClientController.checkDay(birthdatearr[0], Integer.parseInt(birthdatearr[1]),
                        Integer.parseInt(birthdatearr[2]));
    }

    /**
     * @param Date string with date
     * @return Date was created from Date string
     */
    public static Date convertToDate(String Date) {
        String[] fix = Date.split(" ");
        if (validateDate(fix[0]) && validateHours(fix[1])) {
            String[] fix2 = fix[0].trim().split("/");
            String[] hours = fix[1].split(":");
            Date data = new Date(Integer.parseInt(fix2[2]) - 1900, Integer.parseInt(fix2[1]) - 1,
                    Integer.parseInt(fix2[0]), Integer.parseInt(hours[0]), Integer.parseInt(hours[1]));
            return data;
        } else
            return null;
    }

    /**
     *
     * @param Date receives date
     * @return boolean based if hours of date are correct
     */
    public static boolean validateHours(String Date) {
        String[] hours = Date.split(":");
        if (Integer.parseInt(hours[0]) < 24 && Integer.parseInt(hours[0]) >= 0 && Integer.parseInt(hours[1]) >= 0
                && Integer.parseInt(hours[0]) < 60)
            return true;
        else
            return false;
    }

    /**
     *
     * @param name receives a string that contains name
     * @return boolean based if name is validated
     */
    public static boolean validateName(String name) {
        return name.length() <= 35;
    }

    /**
     *
     * @param email receives a string that contains email
     * @return boolean based if email is validated
     */
    public static boolean validateEmail(String email) {
        try {
            Email emailValid = new Email(email);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param number string that contains number
     * @param length length that user want the number to be
     * @return boolean based if number has the specific length
     */
    public static boolean checkNumber(String number, int length) {
        long aux = 0;
        try {
            aux = Long.parseLong(number);
        } catch (NumberFormatException e) {
            return false;
        }
        if (number.length() != length) {
            return false;
        }
        if (aux < 0) {
            return false;
        }

        return true;
    }

    /**
     *
     * @param line First line as an array of strings
     * @return List integer of positions index where the word is Category
     */
    public List<Integer> getCategoryLines(String[] line) {

        List<Integer> pos = new ArrayList<>();
        for (int i = 0; i < line.length; i++) {
            if (line[i].equals("Category")) {
                pos.add(i);
            }
        }
        return pos;
    }

    /**
     *
     * @param pos       List of integers with position of categories
     * @param otherline line of file as array of strings
     * @return get file parameters or values of parameters
     */
    public List<String> getParametersORValues(List<Integer> pos, String[] otherline) {
        List<String> Parameters = new ArrayList<>();
        int size = pos.size();
        int calc;
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                calc = otherline.length - 4;
            } else {
                calc = pos.get(i + 1);
            }

            for (int x = pos.get(i) + 1; x < calc; x++) {
                if (!otherline[x].equals("NA")) {
                    Parameters.add(otherline[x]);
                }

            }
        }
        return Parameters;
    }

    /**
     *
     * @param pos       List of integers with position of categories
     * @param otherline line of file as array of strings
     * @return categories names from the otherline
     */
    public List<String> getCategories(List<Integer> pos, String[] otherline) {
        List<String> categories = new ArrayList<>();
        for (Integer po : pos) {
            if (!otherline[po].equals("NA")) {
                categories.add(otherline[po]);
            }
        }
        return categories;
    }

    /**
     * Generates a log file with errors and succeful actions
     */
    public void logFile() {
        try {
            Path path = Paths.get("logs/");
            Files.createDirectories(path);
            File file = new File("logs/logImport.txt");
            FileWriter myWriter = new FileWriter(file);
            for (String x : logs) {
                myWriter.write(x);
            }
            myWriter.close();
        } catch (IOException e) {

        }
    }

}
