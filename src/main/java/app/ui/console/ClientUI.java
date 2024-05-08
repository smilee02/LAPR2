package app.ui.console;

import app.controller.ClientController;
import app.controller.EmployeeController;
import app.ui.console.utils.Utils;

import java.io.IOException;
import java.util.Scanner;

/**
 * Client Interface that connects with controller
 * 
 */
public class ClientUI implements Runnable {
    static Scanner in = new Scanner(System.in);
    static String option;
    static ClientController clientController = new ClientController();

    public ClientUI() {
        /**
         * We dont need any parameter
         */
    }

    @Override
    /**
     * Runs the Client UI
     */
    public void run() {
        ClientController.print("Please insert the following attributes:");
        String name = putName();
        String address = putAddress();
        String sex = putSex();
        long cCN = putCCnNumber();
        long nHS = putNHSNumber();
        String birthdate = insertBirthDate();
        long tinNumber = putTin();
        long phone = putPhoneNumber();
        String email = putEmail();
        if (sex != null) {
            clientController.createClient(name, sex, cCN, nHS, birthdate, tinNumber, phone, email, address);

        } else {
            clientController.createClient(name, cCN, nHS, birthdate, tinNumber, phone, email, address);
        }
        option = Utils.readLineFromConsole("Is the data inserted correct?" + clientController + "\n(1)Yes\n(2)No");
        assert option != null;
        if (!(option.equals("1") || option.equals("2"))) {
            do {
                option = Utils.readLineFromConsole("This is not a valid option\n(1)Yes\n(2)No");
            } while (!EmployeeController.checkOption(option, 2, 1));
        }
        switch (option) {
            case "1":
                clientController.saveClient();
                String password = null;
                try {
                    password = clientController.emailService(email);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                clientController.addUserWithRole(name, email, password, "CLIENT");
                if (clientController.existsUser(email)) {
                    ClientController.print("\n\nThe Client has been registered successfully.");
                }
                break;
            default:
                break;
        }
    }

    /**
     * Reads the Client name
     * 
     * @return
     */
    private static String putName() {
        String aux = "";
        do {
            aux = Utils.readLineFromConsole("Name: ");
        } while (!ClientController.checkName(aux) || !ClientController.checkIfEmpty(aux));

        return aux;
    }

    private static String putAddress() {
        String string = "";
        do {
            string = Utils.readLineFromConsole("Address: ");
        } while (!ClientController.checkAddress(string) || !ClientController.checkIfEmpty(string));
        return string;
    }

    /**
     * Reads the Client sex
     * 
     * @return
     */
    private static String putSex() {
        String sex = "";
        do {
            sex = Utils.readLineFromConsole("Sex (Male(M)/Female(F)): ");
        } while (!ClientController.checkSex(sex));

        return sex;
    }

    /**
     * Reads the Client phone number
     * 
     * @return
     */
    private static long putPhoneNumber() {
        String phonenumber = "";
        do {
            phonenumber = Utils.readLineFromConsole("Phone Number: ");
        } while (!ClientController.checkNumber(phonenumber, 11, "Phone Number")
                || !ClientController.checkPhoneNumber(phonenumber));
        return Long.parseLong(phonenumber);
    }

    /**
     * Reads the Client Tax identification number
     * 
     * @return
     */
    private static long putTin() {
        String tinNumber = "";
        do {
            tinNumber = Utils.readLineFromConsole("Tax Identification Number: ");
        } while (!ClientController.checkNumber(tinNumber, 10, "TIN Number")
                || !ClientController.checkTinNumber(tinNumber));
        return Long.parseLong(tinNumber);
    }

    /**
     * Reads the National Healthcare Service number
     * 
     * @return
     */
    private static long putNHSNumber() {
        String nHS = "";
        do {
            nHS = Utils.readLineFromConsole("National HealthCare Service Number: ");
        } while (!ClientController.checkNumber(nHS, 10, "NHS Number") || !ClientController.checkNHSNumber(nHS));
        return Long.parseLong(nHS);
    }

    /**
     * Reads the Client citizen card number
     * 
     * @return
     */
    private static long putCCnNumber() {
        String cCN = "";
        do {
            cCN = Utils.readLineFromConsole("Citizen Card Number: ");
        } while (!ClientController.checkNumber(cCN, 16, "Citizen Card Number")
                || !ClientController.checkCitizenNumber(cCN));
        return Long.parseLong(cCN);
    }

    /**
     * Reads the Client birth date
     * 
     * @return
     */
    private static String insertBirthDate() {
        String number;
        ClientController.print("Birth Date\n");
        do {
            number = Utils.readLineFromConsole("-Year: ");

        } while (!ClientController.checkYear(number));
        int year = Integer.parseInt(number);
        do {
            number = Utils.readLineFromConsole("-Month: ");
        } while (!ClientController.checkMonth(number));
        int month = Integer.parseInt(number);
        do {
            number = Utils.readLineFromConsole("-Day: ");
        } while (!ClientController.checkDay(number, month, year));
        int day = Integer.parseInt(number);
        return ClientController.makeBirthDate(day, month, year);
    }

    /**
     * Reads the Client email
     * 
     * @return
     */
    private static String putEmail() {
        String email = "";
        do {
            email = Utils.readLineFromConsole("E-mail: ");

        } while (!clientController.checkEmail(email) || !EmployeeController.checkIfEmpty(email));
        return email;
    }

    /*
     * private static void makeSpace() {
     * System.out.println("\n\n\n\n\n");
     * }
     */

    public static boolean checkOption(String aux, int max, int min) {
        int k;
        try {
            k = Integer.parseInt(aux);
        } catch (NumberFormatException e) {
            return false;
        }
        return k >= min && k <= max;
    }

}
