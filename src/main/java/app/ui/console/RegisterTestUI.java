package app.ui.console;

import app.controller.ClinicalAnalysisLaboratoryController;
import app.controller.RegisterTestController;
import app.domain.model.*;
import app.mappers.RegisterTestMapper;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterTestUI implements Runnable {
    public static Scanner in = new Scanner(System.in);
    public static RegisterTestController registerTestController = new RegisterTestController();
    public RegisterTestMapper rtm;
    public static boolean exit = true;

    /**
     * Interface of Registering a test
     *
     * 
     */
    public RegisterTestUI() {
        /**
         * This class doesnt need constructor
         */
    }

    /**
     * Menu Register Test main interface
     *
     * @return Option string
     */
    public static String menuRegisterTest() {
        RegisterTestController.soutConsole("[Receptionist Interface]--RegisterTestClient");
        RegisterTestController.soutConsole("CHOOSE OPTION:");
        RegisterTestController.soutConsole("r->(Register a test for a client)");
        RegisterTestController.soutConsole("c->(Current number of client tests)");
        RegisterTestController.soutConsole("d->(Remove a client test)");
        RegisterTestController.soutConsole("e->(Exit from Interface)");

        return Utils.readLineFromConsole("option:");
    }

    /**
     * Register Test Register Interface
     */
    public static void RegisterTestRegister() {
        String laboratoryId = Utils.readLineFromConsole("laboratoryId:");
        if (!validatelab(laboratoryId)) {
            RegisterTestController.soutConsole("[!]laboratoryId number must be registered ");
            returnMainMenu();
        }
        while (!RegisterClinicalAnalysisLaboratoryUI.laboratoryIdValidate(laboratoryId)) {
            white();
            ClinicalAnalysisLaboratoryController.soutConsole("[!]Laboratory ID must be five alphanumeric characters");
            laboratoryId = Utils.readLineFromConsole("laboratoryId:");
        }
        int size = registerTestController.getTypeOfTestList().size();
        String NHS = Utils.readLineFromConsole("NHS:");
        while (!validateNHS(NHS)) {
            white();
            RegisterTestController.soutConsole("[!]NHS must be 12 alphanumeric");
            NHS = Utils.readLineFromConsole("NHS:");
        }
        String CCN = Utils.readLineFromConsole("TIN:");
        long ccnLong;
        while (!validateLong(CCN)) {
            CCN = Utils.readLineFromConsole("TIN:");
        }
        ccnLong = Long.parseLong(CCN);
        if (validateCCN(ccnLong)) {
            while (!validateCCN2(ccnLong)) {
                white();
                RegisterTestController.soutConsole("[!]TIN must be 10 digit");
                CCN = Utils.readLineFromConsole("TIN:");
                while (!validateLong(CCN)) {
                    CCN = Utils.readLineFromConsole("TIN:");
                }
                ccnLong = Long.parseLong(CCN);
                white();
            }
            for (int x = 0; x < size; x++) {
                RegisterTestController
                        .soutConsole(x + 1 + ")->" + registerTestController.getTypeOfTestList().get(x).getCode());
            }
            String n = Utils.readLineFromConsole("Option:");
            while (!validateLong(n)) {
                for (int x = 0; x < size; x++) {
                    RegisterTestController
                            .soutConsole(x + 1 + ")->" + registerTestController.getTypeOfTestList().get(x).getCode());
                }
                n = Utils.readLineFromConsole("Option:");
            }
            while (!validateNumber(Integer.parseInt(n))) {
                white();
                for (int x = 0; x < size; x++) {
                    RegisterTestController
                            .soutConsole(x + 1 + ")->" + registerTestController.getTypeOfTestList().get(x).getCode());
                }
                RegisterTestController
                        .soutConsole("[!]Number must be positive and less or equal than than option size");
                n = Utils.readLineFromConsole("Option:");
                while (!validateLong(n)) {
                    n = Utils.readLineFromConsole("Option:");
                }
            }
            List<Parameter> parameterList = registerTestController.getTestType(Integer.parseInt(n) - 1);
            white();
            // registerTestController.RegisterTest(ccnLong,
            // parameterListFinal(parameterList, n),
            // registerTestController.getTypeOfTestList().get(Integer.parseInt(n) - 1),NHS);
            if (registerTestController.RegisterTest(ccnLong, parameterListFinal(parameterList, n),
                    registerTestController.getTypeOfTestList().get(Integer.parseInt(n) - 1), NHS, laboratoryId)) {
                registerTestController.showDto();
                String control = Utils.readLineFromConsole("[?]You want to save? y/n");
                while (!control.equals("y") && !control.equals("n") && !control.equals("yes")
                        && !control.equals("no")) {
                    RegisterTestController.soutConsole("[!]Input must be y or yes or n or no");
                    control = Utils.readLineFromConsole("[?]You want to save? y/n");
                }
                if (control.equals("y") || control.equals("yes")) {
                    registerTestController.saveRegisterTest();
                }
            }
        } else {
            RegisterTestController.soutConsole("[!]Client Tin number must be registered ");
        }
        returnMainMenu();
    }

    /**
     * @param nhs Number that classifies the registered test
     * @return boolean based on nhs length
     */
    public static boolean validateNHS(String nhs) {
        return nhs.length() == 12;
    }

    /**
     * @param TIN Tax identification number
     * @return boolean based if it can convert string to long or not
     */
    public static boolean validateLong(String TIN) {
        long ccnLong = 0;
        try {
            ccnLong = Long.parseLong(TIN);
        } catch (NumberFormatException e) {
            RegisterTestController.soutConsole("[!]Every digit must be a number");
            return false;
        }
        return true;
    }

    /**
     * Interface used to Return to Register Test Main menu
     */
    public static void returnMainMenu() {
        String exit;
        exit = Utils.readLineFromConsole("[?]Return to main? y");
        while (!exit.equals("y") && !exit.equals("yes")) {
            white();
            RegisterTestController.soutConsole("[!]Input must be y or yes");
            exit = Utils.readLineFromConsole("[?]Return to main? y?");
        }

    }

    /**
     * Interface used to remove an element from Register Test List
     */
    public static void removeElement() {
        RegisterTestController.soutConsole("List Of Clients Tests:");
        int n = registerTestController.getRegisterTestList().size();
        for (int i = 0; i < n; i++) {
            RegisterTestController.soutConsole(i + 1 + ")->" + registerTestController.getRegisterTestList().get(i));
            RegisterTestController.soutConsole("----------//----------");
        }
        String option = Utils.readLineFromConsole("Element to remove [0 is NONE]:");
        while (!validateLong(option)) {
            option = Utils.readLineFromConsole("Element to remove [0 is NONE]:");
        }
        while (!validateRemove(option)) {
            white();
            RegisterTestController.soutConsole("[!]Number must be positive and less or equal than than elements size");
            option = Utils.readLineFromConsole("Element to remove [0 is NONE]:");
            while (!validateLong(option)) {
                option = Utils.readLineFromConsole("Element to remove [0 is NONE]:");
            }
        }
        if (Integer.parseInt(option) != 0) {
            registerTestController.removeElement(Integer.parseInt(option) - 1);
        }
    }

    /**
     * @param parameterList receives a list of parameters
     * @param n             its used to know what Type Of test to use
     * @return a new parameter list based on n and the previous parameter list
     */
    public static List<Parameter> parameterListFinal(List<Parameter> parameterList, String n) {
        white();
        boolean flag = false;
        String exit = "";
        int size = parameterList.size();
        List<Parameter> parameterListFinal = new ArrayList<>();
        while (!exit.equals("0")) {
            if (size != 0) {
                RegisterTestController.soutConsole("Available Parameters for Test Type "
                        + registerTestController.getTypeOfTestList().get(Integer.parseInt(n) - 1).getCode() + ":");
                printList(parameterList, size);
                if (flag) {
                    RegisterTestController.soutConsole("[!]Please input at least 1 parameter");
                }
                exit = Utils.readLineFromConsole("Option [0 to finish]:");
                while (!validateLong(exit)) {
                    printList(parameterList, size);
                    exit = Utils.readLineFromConsole("Option [0 to finish]:");
                }
                long exitInt = Long.parseLong(exit);
                while (!validateOption(exitInt, parameterList)) {
                    white();
                    printList(parameterList, size);
                    RegisterTestController.soutConsole("[!]Number must be in size of Parameters");
                    exit = Utils.readLineFromConsole("Option [0 to finish]:");
                    while (!validateLong(exit)) {
                        printList(parameterList, size);
                        exit = Utils.readLineFromConsole("Option [0 to finish]:");
                    }
                    exitInt = Long.parseLong(exit);
                }
                if (!exit.equals("0")) {
                    parameterListFinal.add(parameterList.get((int) (exitInt - 1)));
                    registerTestController.setParameterList(RemoveParameter(parameterList, (int) exitInt - 1));
                    size = parameterList.size();
                    flag = false;

                } else {
                    if (parameterListFinal.size() == 0) {
                        exit = "";
                        flag = true;
                    }
                }
            } else
                exit = "0";
        }
        registerTestController.setParameterList(parameterListFinal);
        return parameterListFinal;
    }

    /**
     * @param parameterList receives a list of parameters
     * @param n             int used to know what parameter to remove
     * @return a new list of parameters
     */
    public static List<Parameter> RemoveParameter(List<Parameter> parameterList, int n) {
        parameterList.remove(n);
        return parameterList;
    }

    /**
     * @param TIN Tax identification number
     * @return boolean based if ccn number is on client list of ccn
     */
    public static boolean validateCCN(long TIN) {
        int cont = 0;
        for (Client x : registerTestController.getClientList()) {
            if (x.getTin() == TIN) {
                cont++;
            }
        }
        return cont > 0;
    }

    public static boolean validatelab(String laboratoryId) {
        int cont = 0;
        for (ClinicalAnalysisLaboratory x : ClinicalAnalysisLaboratoryController.laboratoryList()) {
            if (x.getLaboratoryId() == laboratoryId) {
                cont++;
            }
        }
        return cont > 0;
    }

    /**
     * @param parameterList receives a list of parameters
     * @param size          int of size of a list
     *                      used to print a list of parameters
     */
    public static void printList(List<Parameter> parameterList, int size) {
        for (int i = 0; i < size; i++) {
            RegisterTestController.soutConsole(
                    i + 1 + ")->Name:" + parameterList.get(i).getName() + "\nCode:" + parameterList.get(i).getCode());
            RegisterTestController.soutConsole("--------------//--------------\n");
        }
    }

    /**
     * @param TIN Tax identification number
     * @return boolean based on CCN converted to string length
     */
    public static boolean validateCCN2(long TIN) {
        String CCNString = String.valueOf(TIN);
        return CCNString.length() == 10;
    }

    /**
     * @param n number to validate
     * @return boolean based if the requirements of the validate using n are
     *         followed
     */
    public static boolean validateNumber(int n) {
        return n > 0 && n <= registerTestController.getTypeOfTestList().size();
    }

    /**
     *
     * @param n             number to validate
     * @param parameterList receives a list of parameters
     * @return boolean based if the requirements of the validate using n nd
     *         parameter list are followed
     */
    public static boolean validateOption(long n, List<Parameter> parameterList) {
        return n >= 0 && n <= parameterList.size();
    }

    /**
     *
     * @return the size of the list registerTestController.getRegisterTestList()
     */
    public static int RegisterTestNumber() {
        return registerTestController.getRegisterTestList().size();
    }

    /**
     *
     * @param n String to be validated
     * @return boolean based if the requirements of the validate using n are
     *         followed
     */
    public static boolean validateRemove(String n) {
        long nLong = Long.parseLong(n);
        return nLong >= 0 && nLong <= registerTestController.getRegisterTestList().size();
    }

    /**
     * Cleans the console
     */
    public static void white() {
        for (int x = 0; x < 100; x++)
            ClinicalAnalysisLaboratoryController.soutConsole("");
    }

    /**
     * Run the interface
     */
    @Override
    public void run() {
        exit = true;
        while (exit) {
            white();
            String x = menuRegisterTest();
            while (!x.equals("r") && !x.equals("c") && !x.equals("d") && !x.equals("e")) {
                white();
                x = menuRegisterTest();
            }
            switch (x) {
                case "r":
                    white();
                    RegisterTestRegister();
                    white();
                    break;
                case "c":
                    white();
                    RegisterTestController.soutConsole("Current number of Tests->" + RegisterTestNumber());
                    returnMainMenu();
                    white();
                    break;
                case "d":
                    white();
                    if (RegisterTestNumber() > 0) {
                        removeElement();
                    } else {
                        System.out.println("[!]There are 0 Tests stored");
                    }
                    white();
                    break;
                case "e":
                    exit = false;
                    break;
                default:
                    break;
            }
        }
    }
}
