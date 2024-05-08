package app.domain.store;

import app.controller.EmployeeController;
import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.shared.Files;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmployeeStore implements Serializable, Files {

    private static List<Employee> employees = new ArrayList<Employee>();
    public EmployeeStore() {
        try{
            employees = (List<Employee>) Files.decrypt("EmployeeStore.ser");
        }catch(Exception e){

        }
        /**
         * We dont need any parameter
         */
    }

    public Employee Employee(String name, String email, long phoneNumber, String address, String organizationRole, int standardOcupationalCode) {
        Company.getPhoneNumbers().add(phoneNumber);
        return new Employee(name, email, phoneNumber, address, organizationRole, standardOcupationalCode);
    }

    public Employee Employee(String name, String email, long phoneNumber, String address, String organizationRole, int standardOcupationalCode, int din) {
        Company.getPhoneNumbers().add(phoneNumber);
        return new Employee(name, email, phoneNumber, address, organizationRole, standardOcupationalCode, din);
    }

    public boolean validateEmployee(Employee emp) {
        if (emp == null) {
            return false;
        }
        return !employees.contains(emp);

    }

    public boolean saveEmployee(Employee emp) {
        if (!validateEmployee(emp)) {
            return false;
        }else{
            employees.add(emp);
            Files.encrypt("EmployeeStore.ser",employees);
            return true;
        }

    }

    public static List<Employee> getEmployees() {
        return employees;
    }
}
