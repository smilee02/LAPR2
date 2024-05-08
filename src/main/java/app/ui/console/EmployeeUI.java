package app.ui.console;

import app.controller.EmployeeController;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.io.IOException;
import java.util.Scanner;

public class EmployeeUI implements Runnable {
    static Scanner in = new Scanner(System.in);
    static String option2;
    static EmployeeController employeeController = new EmployeeController();

    @Override
    public void run() {
        EmployeeController.printConsole(("Please insert the following attributes:"));
        String name = insertName();
        String address = insertAddress();
        long phoneNumber = insertPhoneNumber();
        String email = insertEmail();
        int soc = insertSocCode();
        String organizationRole = insertRole();
        int din = 0;
        if (organizationRole.equals(Constants.ROLE_SPECIALIST_DOCTOR)) {
            din = insertDoctorIndexNumber();
            option2 = Utils.readLineFromConsole("Is the data inserted correct?\n Employee: \n Name: " + name + " \n Email: " + email + " \n Phone Number: " + phoneNumber + "\n Address: " + address + " \n Organization Role: " + organizationRole + " \n SOC Code: " + soc + "\n Doctor Index Number: " + din+ "\n(1)Yes\n(2)No");
        } else {
            option2 = Utils.readLineFromConsole("Is the data inserted correct?\n Employee: \n Name: " + name + " \n Email: " + email + " \n Phone Number: " + phoneNumber + "\n Address: " + address + " \n Organization Role: " + organizationRole + " \n SOC Code: " + soc + "\n(1)Yes\n(2)No");
        }
        assert option2 != null;
        if (!(option2.equals("1") || option2.equals("2"))) {
            do {
                option2 = Utils.readLineFromConsole("This is not a valid option\n(1)Yes\n(2)No");
            } while (!EmployeeController.checkOption(option2, 2, 1));
        }
        switch (option2) {
            case "1":
                if (!organizationRole.equals(Constants.ROLE_SPECIALIST_DOCTOR)) {
                    employeeController.createEmployee(name, email, phoneNumber, address, organizationRole, soc);
                } else {
                    employeeController.createEmployee(name, email, phoneNumber, address, organizationRole, soc, din);
                }
                employeeController.saveEmployee();
                String password = null;
                try {
                    password = employeeController.emailService(email);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                employeeController.addUserWithRole(name, email, password, organizationRole);
                if (employeeController.existsUser(email)) {
                    EmployeeController.printConsole("\n\nThe Employee has been registered successfully.");
                }
                break;
            default:
                break;
        }
    }

    private static String insertName() {
        String name = "";
        do {
            name = Utils.readLineFromConsole("Name: ");
        } while (!EmployeeController.checkName(name) || !EmployeeController.checkIfEmpty(name));

        return name;
    }

    private static String insertAddress() {
        String address = "";
        do {
            address = Utils.readLineFromConsole("Address: ");
        } while (!EmployeeController.checkIfEmpty(address) || !EmployeeController.checkIfEmpty(address));
        return address;
    }

    private static long insertPhoneNumber() {
        String phone = "";
        do {
            phone = Utils.readLineFromConsole("Phone Number: ");
        } while (!EmployeeController.checkNumber(phone, 11, "Phone Number") || !EmployeeController.checkPhoneNumber(phone));
        return Long.parseLong(phone);
    }

    private static String insertEmail() {
        String email = "";
        do {
            email = Utils.readLineFromConsole("E-mail: ");

        } while (!employeeController.checkEmail(email) || !EmployeeController.checkIfEmpty(email));
        return email;
    }

    private static int insertSocCode() {
        String n = "";
        do {
            n = Utils.readLineFromConsole("Standard Occupational Code: ");

        } while (!EmployeeController.checkNumber(n, 4, "Standard Occupational Code") || !EmployeeController.checkSOCNumber(n));
        return Integer.parseInt(n);

    }

    private String insertRole() {
        String role = "";
        do {
            role = Utils.readLineFromConsole("Organization Role: \n"+"ADMINISTRATOR\n" + "RECEPTIONIST\n" + "MEDICLABTECH\n" + "CLINICHEMTECH\n" + "SPECIALDOC\n" + "LABCOORDINATOR" + "\nPlease write it the same as shown in the options");
        } while (!EmployeeController.checkRole(role));
        return role;
    }

    private int insertDoctorIndexNumber() {
        String num = "";
        do {
            num = Utils.readLineFromConsole("Doctor Index Number: ");
        } while (!EmployeeController.checkNumber(num, 6, "Doctor Index Number") || !EmployeeController.checkDoctorIndexNumber(num));
        return Integer.parseInt(num);
    }






}
