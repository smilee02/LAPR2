package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.PasswordGenerationEmailSMS;
import app.domain.model.RecordResult;
import app.domain.store.ClientStore;
import app.domain.store.RecordResultStore;
import app.mappers.dto.RecordResultDTO;
import app.mappers.dto.TestDetailsResultsDTO;
import auth.AuthFacade;
import auth.domain.model.Email;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Connect the UI to the Company
 *
 * 
 */
public class ClientController {

    private final Company manyLabs;
    private Client c;
    private final AuthFacade auth;

    /**
     * Creating ClientController Object
     */
    public ClientController() {
        this.manyLabs = App.getInstance().getCompany();
        this.c = null;
        this.auth = manyLabs.getAuthFacade();
    }

    /**
     * Creating a Client
     *
     * @param name              Client name
     * @param sex               Client sex
     * @param citizenCardNumber Client citizen card number
     * @param nhsNumber         Client NationalHealthService Number
     * @param birthDate         data de nascimento do cliente
     * @param tin               Client tax identificantion number
     * @param phoneNumber       Client phone number
     * @param email             Client email
     * @return Validates if Client was created
     */
    public boolean createClient(String name, String sex, long citizenCardNumber, long nhsNumber, String birthDate,
            long tin, long phoneNumber, String email, String address) {
        this.c = this.manyLabs.getClientStore().createClient(name, sex, citizenCardNumber, nhsNumber, birthDate, tin,
                phoneNumber, email, address);
        return this.manyLabs.getClientStore().validateClient(c);
    }

    /**
     * Creating a Client
     *
     * @param name              Client name
     * @param citizenCardNumber Client citizen card number
     * @param nhsNumber         Client NationalHealthService Number
     * @param birthDate         data de nascimento do cliente
     * @param tin               Client tax identification number
     * @param phoneNumber       Client phone number
     * @param email             Client email
     * @return Validates if Client was created
     */
    public boolean createClient(String name, long citizenCardNumber, long nhsNumber, String birthDate, long tin,
            long phoneNumber, String email, String address) {
        this.c = this.manyLabs.getClientStore().createClient(name, citizenCardNumber, nhsNumber, birthDate, tin,
                phoneNumber, email, address);
        return this.manyLabs.getClientStore().validateClient(c);
    }

    /**
     * Add UserWithRole
     *
     * @param name     Client name
     * @param email    Client email
     * @param password Client password
     * @param roleId   Client role id
     * @return Add user role
     */
    public boolean addUserWithRole(String name, String email, String password, String roleId) {
        this.auth.addUserWithRole(name, email, password, roleId);
        return this.auth.serialize();
    }

    /**
     * See if User exists
     *
     * @param email Client email
     * @return If email already exists
     */
    public boolean existsUser(String email) {
        return this.auth.existsUser(email);
    }

    /**
     * Saves the client
     *
     * @return Client save
     */
    public boolean saveClient() {
        return this.manyLabs.getClientStore().saveClient(c);
    }

    @Override
    /**
     * String of Client parameters
     * 
     * @return returns a string
     */
    public String toString() {
        return c.toString();
    }

    /**
     * Generates the password
     *
     * @return returns the generated password
     */
    public static String password() {
        return PasswordGenerationEmailSMS.passwordGeneration();
    }

    /**
     * Clients list
     *
     * @return List of clients
     */
    public static List<Client> clientList() {
        return Company.getClientStore().getClients();
    }

    /* Parte criada no Sprint D */
    public static Client getSpecificClient(int pos) {
        return Company.getClientStore().getSpecificClient(pos);
    }

    public List<TestDetailsResultsDTO> getTestsOfClient(Client c) {
        List<TestDetailsResultsDTO> list = new ArrayList<>();
        for (RecordResult y : RecordResultStore.getTestResultsWithTests()) {
            if (c.getTin() == y.getTest().getCCN()) {
                list.add(new TestDetailsResultsDTO(y));
            }
        }

        Comparator<TestDetailsResultsDTO> comparator = new Comparator<TestDetailsResultsDTO>() {
            @Override
            public int compare(TestDetailsResultsDTO o1, TestDetailsResultsDTO o2) {
                if (o1.getRecordResult().getTest().getData().compareTo(o2.getRecordResult().getTest().getData()) > 0) {
                    return -1;
                } else if (o2.getRecordResult().getTest().getData()
                        .compareTo(o2.getRecordResult().getTest().getData()) < 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
        Collections.sort(list, comparator);
        return list;
    }

    /**
     * Client phone number list
     *
     * @return list of clients phone numbers
     */
    public static List<Long> phoneList() {
        return Company.getPhoneNumbers();
    }

    /**
     * Send an email
     *
     * @param email Clients email
     * @return send an email
     * @throws IOException
     */
    public String emailService(String email) throws IOException {
        return PasswordGenerationEmailSMS.emailClientRegistration(email);
    }

    /**
     * Validates the name of a client
     *
     * @param name Clients name
     * @return boolean if its valid name
     */
    public static boolean checkName(String name) {
        if (name.length() > 35) {
            print("This name is longer than 35 characters. If the complete name is longer than that, please omit one or more of them.");
            return false;
        }
        return true;
    }

    /**
     * Checks if sex corresponds to the Acceptance Criteria, this means is only
     * female or male
     *
     * @param sex Clients sex
     * @return boolean if its a valid sex
     */
    public static boolean checkSex(String sex) {
        if (!(sex.equalsIgnoreCase("m") || sex.equalsIgnoreCase("f") || sex.equals(""))) {
            print(sex
                    + " is not an available sex. The only ones available are Male and Female. Please choose one of them.");
            return false;
        }
        return true;
    }

    /**
     * Check if its a valid day
     *
     * @param day   Client input day
     * @param month Client input month
     * @param year  Client input year
     * @return boolean if its a valid day
     */
    public static boolean checkDay(String day, int month, int year) {
        boolean leapyear = ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
        int d = 0;
        try {
            d = Integer.parseInt(day);
        } catch (NumberFormatException e) {
            // print("This is not a valid day. Make sure it was written properly.");
            return false;
        }
        if (d < 0 || d > 31) {
            // print("This is not a valid day. Make sure it was written properly.");
            return false;
        }
        if (month == 2 && leapyear && d > 29) {
            // print("This is not a valid day for February. It only has 28 days. Make sure
            // it was written properly.");
            return false;
        }
        if (month == 2 && !leapyear && d > 28) {

            // print("This is not a valid day for February. It only has 28 days. Make sure
            // it was written properly.");
            return false;

        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) && d > 30) {
            // print("This is not a valid day for a month with 30 days. Make sure it was
            // written properly.");
            return false;

        }

        return true;
    }

    /**
     * Validates Client input month
     *
     * @param month Client input month
     * @return boolean if its a valid month
     */
    public static boolean checkMonth(String month) {
        int m = 0;
        try {
            m = Integer.parseInt(month);
        } catch (NumberFormatException e) {
            // print("This is not a valid month. Make sure it was written properly.");
            return false;
        }
        if (m > 12 || m < 0) {
            // print("This is not a valid month. Make sure it was written properly.");
            return false;
        }
        return true;
    }

    /**
     * Validates Client input year
     *
     * @param year Client input year
     * @return boolean if its a valid year
     */
    public static boolean checkYear(String year) {
        int y = 0;
        try {
            y = Integer.parseInt(year);
        } catch (NumberFormatException e) {
            // print("This is not a valid year. Make sure it was written properly and it's
            // not bigger than the year we are on.");
            return false;
        }
        if (y > LocalDate.now().getYear()) {
            // print("This is not a valid year. Make sure it was written properly and it's
            // not bigger than the year we are on.");
            return false;
        }
        if (LocalDate.now().getYear() - y > 149) {
            // print("Unfortunately the limit for the age is around 150 years, which is not
            // the case.");
            return false;
        }
        return true;
    }

    /**
     * Checks if the String number can be transformed into a long. If this is not
     * possible a error message will pop up
     * And if the size of that string is different than the length defined as
     * parameter it will return false
     * If the String number can be transformed into a long and the size is the same
     * as length it will retun true.
     *
     * @param number
     * @param length
     * @param designation
     * @return boolean if its a valida number, checking all inputed parameters
     */

    public static boolean checkNumber(String number, int length, String designation) {
        long aux = 0;
        String b = "The " + designation + " must be a " + length + " digit number. Make sure it was written properly.";
        try {
            aux = Long.parseLong(number);
        } catch (NumberFormatException e) {
            print(b);
            return false;
        }
        if (number.length() != length) {
            print(b);
            return false;
        }
        if (aux < 0) {
            print(b);
            return false;
        }

        return true;
    }

    /**
     * Validates the inputed email and see if exists
     *
     * @param email
     * @return
     */
    public boolean checkEmail(String email) {
        try {
            new Email(email);
        } catch (IllegalArgumentException e) {
            print("This is an invalid e-mail adress. Make sure it was written properly.");
            return false;
        }
        if (auth.existsUser(email)) {
            print("There is already a user with this same e-mail address. Please choose another one.");
            return false;
        }
        return true;
    }

    /**
     * Gets these parameters and returns a string being the birth date
     *
     * @param day
     * @param month
     * @param year
     * @return
     */
    public static String makeBirthDate(int day, int month, int year) {
        String birthDate = "";
        birthDate = day + "/" + month + "/" + year;
        return birthDate;
    }

    /**
     * Checks if a string is empty
     *
     * @param n
     * @return
     */
    public static boolean checkIfEmpty(String n) {
        if (n.equals("")) {
            print("This can't be an empty field.");
            return false;
        } else
            return true;
    }

    /**
     * Check if phone number is valid
     *
     * @param phone Client phone number
     * @return
     */
    public static boolean checkPhoneNumber(String phone) {
        long phoneNumber = Long.parseLong(phone);
        for (Long x : ClientController.phoneList()) {
            if (phoneNumber == x) {
                print("There is already a user with this same phone number. Please choose another one.");
                return false;
            }
        }
        return true;
    }

    /**
     * Validate Client Citizen Number
     *
     * @param number Client citizen number
     * @return
     */
    public static boolean checkCitizenNumber(String number) {
        long citizenumber = Long.parseLong(number);
        for (Client x : ClientController.clientList()) {
            if (citizenumber == x.getCitizenCardNumber()) {
                print("There is already a user with the same citizen card number. Make sure it was written properly.");
                return false;
            }
        }
        return true;
    }

    /**
     * Validates Client National Healthcare Service Number
     *
     * @param number Clients NHS number
     * @return
     */
    public static boolean checkNHSNumber(String number) {
        long nhs = Long.parseLong(number);
        for (Client x : ClientController.clientList()) {
            if (nhs == x.getNhsNumber()) {
                print("There is already a user with the same NHS number. Make sure it was written properly.");
                return false;
            }
        }
        return true;
    }

    /**
     * Validates Tax Identification Number
     *
     * @param number Client tin number
     * @return
     */
    public static boolean checkTinNumber(String number) {
        long tin = Long.parseLong(number);
        for (Client x : ClientController.clientList()) {
            if (tin == x.getTin()) {
                print("There is already a user with the same TIN number. Make sure it was written properly.");
                return false;
            }
        }
        return true;
    }

    /**
     * Validates the address
     * 
     * @param address Client address
     * @return
     */
    public static boolean checkAddress(String address) {
        if (address.length() > 30) {
            print("This address is longer than 30 characters.");
            return false;
        }
        return true;
    }

    /**
     * Prints the aux value
     *
     * @param aux
     */
    public static void print(String aux) {
        System.out.println(aux);
    }
}
