package app.domain.model;

import java.io.Serializable;

/**
 * Register Client
 * 
 */
public class Client implements Serializable {
    private String name;
    private String sex;
    private double citizenCardNumber;
    private double nhsNumber;
    private final String birthDate;
    private double tin;
    private double phoneNumber;
    private final String email;
    private final String address;

    /**
     * Client Constructor to create a client
     * 
     * @param name              Client name
     * @param sex               Client sex
     * @param citizenCardNumber Client citizen number
     * @param nhsNumber         Client National Healthcare Service number
     * @param birthDate         Client birth date
     * @param tin               Client tax identification number
     * @param phoneNumber       Client phone number
     * @param email             Client email
     * @param address           Client address
     */
    public Client(String name, String sex, double citizenCardNumber, double nhsNumber, String birthDate, double tin,
            double phoneNumber, String email, String address) {
        this.name = name;
        this.sex = sex;
        this.citizenCardNumber = citizenCardNumber;
        this.nhsNumber = nhsNumber;
        this.birthDate = birthDate;
        this.tin = tin;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public void setTin(double tin) {
        this.tin = tin;
    }

    public void setPhoneNumber(double phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNhsNumber(double nhsNumber) {
        this.nhsNumber = nhsNumber;
    }

    public void setCitizenCardNumber(double citizenCardNumber) {
        this.citizenCardNumber = citizenCardNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Client Constructor to create a client
     * 
     * @param name              Client name
     * @param citizenCardNumber Client citizen number
     * @param nhsNumber         Client National Healthcare Service number
     * @param birthDate         Client birth date
     * @param tin               Client tax identification number
     * @param phoneNumber       Client phone number
     * @param email             Client email
     */
    public Client(String name, double citizenCardNumber, double nhsNumber, String birthDate, double tin,
            double phoneNumber, String email, String address) {
        this.name = name;
        this.citizenCardNumber = citizenCardNumber;
        this.nhsNumber = nhsNumber;
        this.birthDate = birthDate;
        this.tin = tin;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    /**
     * Return Client name
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Return Client email
     * 
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Return Client Citizen Card number
     * 
     * @return
     */
    public double getCitizenCardNumber() {
        return citizenCardNumber;
    }

    /**
     * Return Client National Healthcare Service number
     * 
     * @return
     */
    public double getNhsNumber() {
        return nhsNumber;
    }

    /**
     * Return Client phone number
     * 
     * @return
     */
    public double getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Return Client Tax identification number
     * 
     * @return
     */
    public double getTin() {
        return tin;
    }

    /**
     * Return Client birth date
     * 
     * @return
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Return Client sex
     * 
     * @return
     */
    public String getSex() {
        return sex;
    }

    /**
     * Return Client address
     * 
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns a string that have all parameters presented
     * 
     * @return
     */
    @Override
    public String toString() {
        if (sex != null) {
            return String.format(
                    "%nClient:%nName: %s%nSex: %s%nCitizen Card Number: %.0f%nNHS Number: %.0f%nBirth Date: %s%nTIN: %.0f%nPhone Number: %.0f%nEmail: %s",
                    name, sex, citizenCardNumber, nhsNumber, birthDate, tin, phoneNumber, email);
        } else {
            return String.format(
                    "%nClient:%nName: %s%nCitizen Card Number: %.0f%nNHS Number: %.0f%nBirth Date: %s%nTIN: %.0f%nPhone Number: %.0f%nEmail: %s",
                    name, citizenCardNumber, nhsNumber, birthDate, tin, phoneNumber, email);
        }
    }
}
