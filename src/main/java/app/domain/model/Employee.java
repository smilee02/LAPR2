package app.domain.model;

import java.io.Serializable;

/**
 * Creates Employs with roles
 * 
 */
public class Employee implements Serializable {
    private static int nemployees = 0;
    private String employeeId = "";
    private final String organizationRole;
    private final String name;
    private final String address;
    private final long phoneNumber;
    private final String email;
    private final int standardOcupationalCode;
    private int doctorIndexNumber;

    /**
     * Create Employee
     * 
     * @param name                    Employee name
     * @param email                   Employee email
     * @param phoneNumber             Employee phone number
     * @param address                 Employee adress
     * @param organizationRole        Employee organization Role
     * @param standardOcupationalCode Employee standart Ocupational code
     */
    public Employee(String name, String email, long phoneNumber, String address, String organizationRole,
            int standardOcupationalCode) {
        nemployees++;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.standardOcupationalCode = standardOcupationalCode;
        this.employeeId = employeeIdGeneration();
        this.organizationRole = organizationRole;
    }

    /**
     * Create Employee
     * 
     * @param name                    Employee name
     * @param email                   Employee email
     * @param phoneNumber             Employee phone number
     * @param address                 Employee adress
     * @param organizationRole        Employee organization Role
     * @param standardOcupationalCode Employee standart Ocupational code
     * @param doctorIndexNumber       Employee doctor index number
     */
    public Employee(String name, String email, long phoneNumber, String address, String organizationRole,
            int standardOcupationalCode, int doctorIndexNumber) {
        nemployees++;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.standardOcupationalCode = standardOcupationalCode;
        this.employeeId = employeeIdGeneration();
        this.organizationRole = organizationRole;
        this.doctorIndexNumber = doctorIndexNumber;
    }

    /**
     * Return Employee name
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Return Employee email
     * 
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Return Employee adress
     * 
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Return Employee id
     * 
     * @return
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Return Employee doctor index number
     * 
     * @return
     */
    public int getDoctorIndexNumber() {
        return doctorIndexNumber;
    }

    /**
     * Return Employee phone number
     * 
     * @return
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Return Employee organization role
     * 
     * @return
     */
    public String getOrganizationRole() {
        return organizationRole;
    }

    /**
     * Return Employee standart ocupational code
     * 
     * @return
     */
    public int getStandardOcupationalCode() {
        return standardOcupationalCode;
    }

    /**
     * Generates an id to employee
     * 
     * @return
     */
    private String employeeIdGeneration() {
        int auxNumber = nemployees;
        int digits = 0;
        String[] aux = name.split(" ");
        for (String x : aux) {
            employeeId = employeeId + String.valueOf(x.charAt(0)).toUpperCase();
        }
        while (auxNumber != 0) {
            auxNumber = auxNumber / 10;
            digits++;
        }
        int howmanyzeros = 5 - digits;
        for (int i = 0; i < howmanyzeros; i++) {
            employeeId = employeeId + 0;
        }
        employeeId = employeeId + nemployees;
        return employeeId;
    }

    /**
     * Returns a string with all constructor parameters
     * 
     * @return
     */
    @Override
    public String toString() {
        return String.format(
                "Employee:%nName: %s%nEmail: %s%nAddress: %s%nEmployeeID: %s%nPhone Number: %s%nStandard Occupational Code: %s%nOrganization Role: %s%n",
                name, email, address, employeeId, phoneNumber, standardOcupationalCode, organizationRole);
    }
}
