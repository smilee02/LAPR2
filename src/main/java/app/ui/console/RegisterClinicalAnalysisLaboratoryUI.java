package app.ui.console;

import app.controller.CreateTypeTestController;
import app.domain.model.Company;
import app.domain.model.TypeOfTest;
import app.controller.ClinicalAnalysisLaboratoryController;
import app.domain.model.ClinicalAnalysisLaboratory;
import app.ui.console.utils.Utils;

import java.util.List;
import java.util.Scanner;

/**
 * Interface of Registering a Clinical Analysis Laboratory
 * 
 */

public class RegisterClinicalAnalysisLaboratoryUI implements Runnable {
    public static Scanner in = new Scanner(System.in);
    public static boolean exit = true;
    public static ClinicalAnalysisLaboratoryController labControl = new ClinicalAnalysisLaboratoryController();
    static CreateTypeTestController createTypeTestController = new CreateTypeTestController();

    public RegisterClinicalAnalysisLaboratoryUI() {
        /**
         * We dont need any parameter
         */
    }

    /**
     * Reads the option inserted
     * 
     * @return option
     */
    public static String laboratoryUI() {
        ClinicalAnalysisLaboratoryController.soutConsole("[Admin Interface]--RegisterClinicalAnalysisLaboratoryUI");
        ClinicalAnalysisLaboratoryController.soutConsole("CHOOSE OPTION:");
        ClinicalAnalysisLaboratoryController.soutConsole("r->(Register a Clinical Analysis Laboratory)");
        ClinicalAnalysisLaboratoryController.soutConsole("c->(Current number of Clinical Analysis Laboratory)");
        ClinicalAnalysisLaboratoryController.soutConsole("d->(Remove a Clinical Analysis Laboratory from network)");
        ClinicalAnalysisLaboratoryController.soutConsole("e->(Exit from Interface)");

        return Utils.readLineFromConsole("option:");
    }

    /**
     * Counts the number of laboratories that exist
     * 
     * @return number of labs
     */

    public static int laboratoryUINumber() {
        ClinicalAnalysisLaboratoryController.soutConsole("Laboratories:");
        listLaboratories();
        return labControl.ClinicalAnalysisLaboratoryCount();
    }

    /**
     * Removes the selected laboratory by the inserted number
     */
    public static void laboratoryUIRemove() {
        if (labControl.getGetLaboratory().getLaboratoryStore().getClinical().size() != 0) {
            ClinicalAnalysisLaboratoryController.soutConsole("Laboratories:");
            listLaboratories();
            boolean flag = true;
            int n = 0;
            while (flag) {
                String intermediario = Utils.readLineFromConsole("Remove(number):");
                flag = false;
                try {
                    n = Integer.parseInt(intermediario);
                    while (!laboratoryUIRemoveValidate(n,
                            labControl.getGetLaboratory().getLaboratoryStore().getClinical())) {
                        white();
                        listLaboratories();
                        ClinicalAnalysisLaboratoryController.soutConsole("[!]Input must be in list size");
                        intermediario = Utils.readLineFromConsole("Remove(number):");
                        n = Integer.parseInt(intermediario);
                    }
                } catch (NumberFormatException e) {
                    errorNum();
                    flag = true;
                }
            }
            labControl.ClinicalAnalysisLaboratoryRemove(n);
            ClinicalAnalysisLaboratoryController.soutConsole("");
            ClinicalAnalysisLaboratoryController.soutConsole("Removed laboratory:" + n);
            returnMain();
        } else {
            System.out.println("[!]None Clinical Analysis Laboratory to remove");
            returnMain();
        }
    }

    /**
     * Confirms the removal of a Laboratory
     * 
     * @param n
     * @param list
     * @return
     */
    public static boolean laboratoryUIRemoveValidate(int n, List<ClinicalAnalysisLaboratory> list) {
        int control = list.size();
        return n <= control && n >= 1;
    }

    /**
     * Registers a laboratory
     */
    public static void laboratoryUIRegister() {
        String laboratoryId = laboratoryIdAssign();

        while (!laboratoryIdValidate(laboratoryId)) {
            white();
            ClinicalAnalysisLaboratoryController.soutConsole("[!]Laboratory ID must be five alphanumeric characters");
            laboratoryId = laboratoryIdAssign();
        }
        String name = nameAssign();
        while (!nameValidate(name)) {
            white();
            ClinicalAnalysisLaboratoryController
                    .soutConsole("[!]Name must be a string with no more than 20 characters and not empty");
            name = nameAssign();
        }
        String adress = adressAssign();

        while (!adressValidate(adress)) {
            white();
            ClinicalAnalysisLaboratoryController
                    .soutConsole("[!]Address must be a string with no more than 30 characters and not empty");
            adress = adressAssign();
        }
        boolean flag = true;
        long phoneNumber = 0;
        while (flag) {
            String intermediario = phoneNumberAssign();
            flag = false;
            try {
                phoneNumber = Long.parseLong(intermediario);
                while (!phoneNumberValidate(phoneNumber)) {
                    white();
                    ClinicalAnalysisLaboratoryController.soutConsole("[!]Phone Number must be 11 digit number");
                    intermediario = phoneNumberAssign();

                    phoneNumber = Long.parseLong(intermediario);
                }
            } catch (NumberFormatException e) {
                errorNum();
                flag = true;
            }
        }
        flag = true;
        long tin = 0;
        while (flag) {
            String intermediario = Utils.readLineFromConsole("TIN:");
            flag = false;
            try {
                tin = Long.parseLong(intermediario);
                while (!tinValidate(tin)) {
                    white();
                    ClinicalAnalysisLaboratoryController.soutConsole("[!]TIN number must be a 10 digit number");
                    intermediario = Utils.readLineFromConsole("TIN:");
                    tin = Long.parseLong(intermediario);
                }
            } catch (NumberFormatException e) {
                errorNum();
                flag = true;
            }
        }
        flag = true;
        int tests = 0;
        while (flag) {
            white();
            List<TypeOfTest> testType = createTypeTestController.getTypeOfTestList();
            int i = 0;
            for (int x = 0; x < testType.size(); x++) {

                ClinicalAnalysisLaboratoryController.soutConsole(i + "-" + testType.get(x));
                i++;
            }
            String intermediario = Utils.readLineFromConsole("Tests Type:");
            flag = false;
            try {
                tests = Integer.parseInt(intermediario);
                while (tests < 1 && tests > testType.size()) {
                    white();
                    ClinicalAnalysisLaboratoryController
                            .soutConsole("Tests Type must be a number from 1 to " + testType.size());
                    for (int x = 0; x < testType.size(); x++) {
                        ClinicalAnalysisLaboratoryController.soutConsole((x + 1) + "-" + testType.get(x));
                    }
                    intermediario = Utils.readLineFromConsole("Tests Type:");
                    tests = Integer.parseInt(intermediario);
                }
            } catch (NumberFormatException e) {
                errorNum();
                flag = true;
            }
        }

        labControl.RegisterClinicalAnalysisLaboratory(laboratoryId, name, adress, phoneNumber, tin,
                createTypeTestController.getTypeOfTestList());
        ClinicalAnalysisLaboratoryController.soutConsole("laboratoryId:" + laboratoryId);
        ClinicalAnalysisLaboratoryController.soutConsole("name:" + name);
        ClinicalAnalysisLaboratoryController.soutConsole("adress:" + adress);
        ClinicalAnalysisLaboratoryController.soutConsole("phoneNumber:" + phoneNumber);
        ClinicalAnalysisLaboratoryController.soutConsole("TIN:" + tin);
        ClinicalAnalysisLaboratoryController.soutConsole("testTypes:" + createTypeTestController.getTypeOfTestList());
        if (!labControl.getGetLaboratory().getLaboratoryStore().validateClinicalAnalysisLaboratory()) {
            String option = Utils.readLineFromConsole("Would you want to save? y/n");

            while (!option.equals("y") && !option.equals("n")) {
                white();
                ClinicalAnalysisLaboratoryController.soutConsole("[!]Answer must be y(yes) or n(no)");
                option = in.nextLine();
                white();
            }

            if (option.equals("y")) {
                labControl.SaveClinicalAnalysisLaboratory();
            }
        }
        returnMain();

    }

    /**
     * Validates the inserted Laboratory ID
     * 
     * @param laboratoryId
     * @return
     */
    public static boolean laboratoryIdValidate(String laboratoryId) {
        if (laboratoryId.trim().isEmpty())
            return false;
        return laboratoryId.length() == 5;
    }

    /**
     * Validates the inserted name
     * 
     * @param name
     * @return
     */
    public static boolean nameValidate(String name) {
        if (name.trim().isEmpty())
            return false;
        return name.length() <= 20;
    }

    /**
     * Validates the inserted address
     * 
     * @param adress
     * @return
     */
    public static boolean adressValidate(String adress) {
        if (adress.trim().isEmpty())
            return false;
        return adress.length() <= 30;
    }

    /**
     * Validates the inserted phone number
     * 
     * @param phoneNumber
     * @return
     */
    public static boolean phoneNumberValidate(long phoneNumber) {
        return String.valueOf(phoneNumber).length() == 11;
    }

    /**
     * Validates the inserted Tin number
     * 
     * @param tin
     * @return
     */
    public static boolean tinValidate(long tin) {
        return String.valueOf(tin).length() == 10;
    }

    /**
     * Validates the type test created
     * 
     * @param testsType
     * @return
     */
    public static boolean testTypeValidate(List<String/* testType */> testsType) {
        throw new IllegalArgumentException("[!]MODULE NOT CREATED");
    }

    /**
     * Cleans the console
     */
    public static void white() {
        for (int x = 0; x < 100; x++)
            ClinicalAnalysisLaboratoryController.soutConsole("");
    }

    /**
     * Returns to the main menu
     */
    public static void returnMain() {
        String option = Utils.readLineFromConsole("Return to main menu press y");
        while (!option.equals("y")) {
            white();
            option = Utils.readLineFromConsole("[!]Answer must be y(yes)");
            white();
        }
    }

    /**
     * Prints to the console that the input must be a number
     */
    public static void errorNum() {
        ClinicalAnalysisLaboratoryController.soutConsole("[!]Input must be a number");
    }

    /**
     * Assigns a number to the laboratory ID
     * 
     * @return
     */
    public static String laboratoryIdAssign() {
        return Utils.readLineFromConsole("laboratoryId:");
    }

    /**
     * Assigns a name to the Laboratory
     * 
     * @return
     */
    public static String nameAssign() {
        return Utils.readLineFromConsole("name:");
    }

    /**
     * Assigns a address to the Laboratory
     * 
     * @return
     */
    public static String adressAssign() {
        return Utils.readLineFromConsole("adress:");
    }

    /**
     * Assigns a phone Number to the laboratory
     * 
     * @return
     */
    public static String phoneNumberAssign() {
        return Utils.readLineFromConsole("phoneNumber:");
    }

    /**
     * Lists the laboratories
     */
    public static void listLaboratories() {
        for (int i = 0; i < labControl.getGetLaboratory().getLaboratoryStore().getClinical().size(); i++) {
            ClinicalAnalysisLaboratoryController.soutConsole((i + 1) + "º---------------Laboratory---------------");
            ClinicalAnalysisLaboratoryController.soutConsole("laboratoryId:"
                    + labControl.getGetLaboratory().getLaboratoryStore().getClinical().get(i).getLaboratoryId());
            ClinicalAnalysisLaboratoryController.soutConsole(
                    "name:" + labControl.getGetLaboratory().getLaboratoryStore().getClinical().get(i).getName());
            ClinicalAnalysisLaboratoryController.soutConsole(
                    "adress:" + labControl.getGetLaboratory().getLaboratoryStore().getClinical().get(i).getAdress());
            ClinicalAnalysisLaboratoryController.soutConsole("phoneNumber:"
                    + labControl.getGetLaboratory().getLaboratoryStore().getClinical().get(i).getPhoneNumber());
            ClinicalAnalysisLaboratoryController.soutConsole(
                    "TIN:" + labControl.getGetLaboratory().getLaboratoryStore().getClinical().get(i).getTin());
            ClinicalAnalysisLaboratoryController.soutConsole("Test Types:"
                    + labControl.getGetLaboratory().getLaboratoryStore().getClinical().get(i).getTestsType());
            ClinicalAnalysisLaboratoryController.soutConsole("------------------------------------------");
        }
    }

    /**
     * Run the interface
     */
    @Override
    public void run() {
        while (exit) {
            white();
            String x = laboratoryUI();
            while (!x.equals("r") && !x.equals("c") && !x.equals("d") && !x.equals("e")) {
                white();
                x = laboratoryUI();
            }
            switch (x) {
                case "r":
                    white();
                    laboratoryUIRegister();
                    white();
                    break;
                case "c":
                    white();
                    ClinicalAnalysisLaboratoryController
                            .soutConsole("Current number of laboratories->" + laboratoryUINumber());
                    returnMain();
                    white();
                    break;
                case "d":
                    white();
                    laboratoryUIRemove();
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
