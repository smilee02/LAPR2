package app.controller;


import app.domain.model.Employee;
import app.domain.model.Company;
import app.domain.model.PasswordGenerationEmailSMS;
import app.domain.shared.Constants;
import app.domain.store.EmployeeStore;
import auth.AuthFacade;
import auth.domain.model.Email;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController {

    private final Company manyLabs = Constants.COMPANY;
    private Employee emp;
    private static List<String> Roles = new ArrayList();
    private final AuthFacade auth = manyLabs.getAuthFacade();

    private static List<String> add() {
        Roles.add(Constants.ROLE_ADMIN);
        Roles.add(Constants.ROLE_CLINICAL_CHEMISTRY_TECHNOLOGIST);
        Roles.add(Constants.ROLE_LABORATORY_COORDINATOR);
        Roles.add(Constants.ROLE_MEDICAL_LAB_TECHNICIAN);
        Roles.add(Constants.ROLE_RECEPTIONIST);
        Roles.add(Constants.ROLE_SPECIALIST_DOCTOR);
        return Roles;
    }

    public EmployeeController() {
        Roles = add();
        this.emp = null;
    }

    public boolean createEmployee(String name, String email, long phoneNumber, String address, String organizationRole, int standardOcupationalCode) {
        this.emp = this.manyLabs.getEmployeeStore().Employee(name, email, phoneNumber, address, organizationRole, standardOcupationalCode);
        return this.manyLabs.getEmployeeStore().validateEmployee(emp);
    }

    public boolean createEmployee(String name, String email, long phoneNumber, String address, String organizationRole, int standardOcupationalCode, int din) {
        this.emp = this.manyLabs.getEmployeeStore().Employee(name, email, phoneNumber, address, organizationRole, standardOcupationalCode, din);
        return this.manyLabs.getEmployeeStore().validateEmployee(emp);
    }

    public boolean addUserWithRole(String name, String email, String password, String roleId) {
        this.auth.addUserWithRole(name, email, password, roleId);
        return this.auth.serialize();
    }

    public boolean existsUser(String email) {
        return this.auth.existsUser(email);
    }

    public boolean saveEmployee() {
        return this.manyLabs.getEmployeeStore().saveEmployee(emp);
    }

    @Override
    public String toString() {
        return emp.toString();
    }

    public static String password() {
        return PasswordGenerationEmailSMS.passwordGeneration();
    }

    public static List<Employee> employeesList() {
        return Company.getEmployeeStore().getEmployees();
    }

    public static List<Long> phoneList() {
        return Company.getPhoneNumbers();
    }

    public String emailService(String email) throws IOException {
        return PasswordGenerationEmailSMS.emailClientRegistration(email);
    }

    public static boolean checkName(String name) {
        if (name.length() > 35) {
            printConsole("This name is longer than 35 characters. If the complete name is longer than that, please omit one or more of them.");
            return false;
        }
        return true;
    }

    /**
     * Checks if the String number can be transformed into a long. If this is not possible a error message will pop up
     * And if the size of that string is different than the length defined as parameter it will return false
     * If the String number can be transformed into a long and the size is the same as length it will retun true.
     *
     * @param number
     * @param length
     * @param designation
     * @return
     */

    public static boolean checkNumber(String number, int length, String designation) {
        long aux = 0;
        String a ="The " + designation + " must be a " + length + " digit number. Make sure it was written properly.";
        try {
            aux = Long.parseLong(number);
        } catch (NumberFormatException e) {
            printConsole(a);
            return false;
        }
        if (number.length() != length) {
            printConsole(a);
            return false;
        }
        if (aux < 0) {
            printConsole(a);
            return false;
        }

        return true;
    }

    public boolean checkEmail(String email) {
        try {
            new Email(email);
        } catch (IllegalArgumentException e) {
            printConsole("This is an invalid e-mail adress. Make sure it was written properly.");
            return false;
        }
        if(auth.existsUser(email)){
            printConsole("There is already an user with this e-mail address.");
            return false;
        }
        return true;
    }


    public static boolean checkIfEmpty(String n) {
        if (n.equals("")) {
            printConsole("This can't be an empty field.");
            return false;
        } else return true;
    }

    public static boolean checkPhoneNumber(String phone) {
        long phoneNumber = Long.parseLong(phone);
        for (Long x : EmployeeController.phoneList()) {
            if (phoneNumber == x) {
                printConsole("There is already a user with this same phone number. Please choose another one.");
                return false;
            }
        }
        return true;
    }

    public static boolean checkSOCNumber(String number) {
        long socnumber = Long.parseLong(number);
        for (Employee x : EmployeeController.employeesList()) {
            if (socnumber == x.getStandardOcupationalCode()) {
                printConsole("There is already a user with the same SOC number. Make sure it was written properly.");
                return false;
            }
        }
        return true;
    }

    public static boolean checkDoctorIndexNumber(String number) {
        long din = Long.parseLong(number);
        for (Employee x : EmployeeController.employeesList()) {
            if (din == x.getDoctorIndexNumber()) {
                printConsole("There is already a user with the same Doctor Index Number number. Make sure it was written properly.");
                return false;
            }
        }
        return true;
    }

    public static boolean checkRole(String aux) {
        add();
        for (String x : Roles){
            if(aux.equals(x)){
                return true;
            }
        }
        return false;
    }
    public static boolean checkOption(String aux, int max, int min) {
        int k;
        try {
            k = Integer.parseInt(aux);
        } catch (NumberFormatException e) {
            return false;
        }
        return k >= min && k <= max;
    }

    public static void printConsole(String line){
        System.out.println(line);
    }
}
