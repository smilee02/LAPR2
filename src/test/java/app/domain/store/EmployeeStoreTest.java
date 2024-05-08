package app.domain.store;

import app.domain.model.Company;
import app.domain.model.Employee;



import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeStoreTest {
    private static final EmployeeStore emp = new EmployeeStore();
    static Employee employee1 = new Employee("A","a@g.com",Long.parseLong("12341234123"),"a","SpecialDoc",1234,123456);
    static Employee employee2 = new Employee("a","r@g.com",Long.parseLong("12341234123"),"a","SpecialDoc",1235,123543);

    @Test
    public void createEmployee(){
        Employee employee = emp.Employee("A","a@g.com",Long.parseLong("12341234123"),"a","SpecialDoc",1234,123456);
        assertNotEquals(emp.Employee("A","a@g.com",Long.parseLong("12341234123"),"a","SpecialDoc",1234,123456),employee);
        Employee employee1 = emp.Employee("A","a@g.com",Long.parseLong("12341234123"),"a","SpecialDoc",1234);
        assertNotEquals(emp.Employee("A","a@g.com",Long.parseLong("12341234123"),"a","SpecialDoc",1234),employee1);

    }

    @Test
    public void validateEmployee() {
        Employee employee = null;
        assertFalse(emp.validateEmployee(employee));
        emp.getEmployees().add(employee2);
        assertTrue(emp.validateEmployee(employee1));
        emp.getEmployees().remove(employee2);
    }

    @Test
    public void validateClient2(){
        assertNotNull(employee2);
    }
    @Test
    public void saveClient(){
        Employee employee = null;
        assertFalse(emp.saveEmployee(employee));
        assertTrue(emp.saveEmployee(employee2));
    }
}