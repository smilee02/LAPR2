package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {

    static Employee emp = new Employee("Joao","joao@gmail.com",Long.parseLong("12341234123"),"Rua dos Corvos","SPECIALDOC",1234,223344);
    static Employee emp2 = new Employee("Rui","r@t.com",Long.parseLong("12341278123"),"Rua dos Trevos","RECEPTIONIST",1232);
    @Test
    public void getName() {
        String realResult = emp.getName();
        String expResult = "Joao";
        String name = null;
        assertNotNull(realResult);
        assertEquals(expResult, realResult);
        assertNull(name);
    }

    @Test
    public void getEmail() {
        String realResult = emp.getEmail();
        String expResult = "joao@gmail.com";
        String email = null;
        assertNotNull(realResult);
        assertEquals(expResult, realResult);
        assertNull(email);
    }

    @Test
    public void getAddress() {
        String realResult = emp.getAddress();
        String expResult = "Rua dos Corvos";
        String address = null;
        assertNotNull(realResult);
        assertEquals(expResult, realResult);
        assertNull(address);
    }

    @Test
    public void getEmployeeId() {
        String realResult = emp.getEmployeeId();
        String empId = null;
        assertNotNull(realResult);
        assertNull(empId);
    }

    @Test
    public void getDoctorIndexNumber() {
        int realResult = emp.getDoctorIndexNumber();
        int expResult = 223344;

        assertNotNull(realResult);
        assertEquals(expResult, realResult);
    }

    @Test
    public void getPhoneNumber() {
        long realResult = emp.getPhoneNumber();
        long expResult = Long.parseLong("12341234123");

        assertNotNull(realResult);
        assertEquals(expResult, realResult);
    }

    @Test
    public void getOrganizationRole() {
        String realResult = emp.getOrganizationRole();
        String expResult = "SPECIALDOC";
        String orgId = null;
        assertNotNull(realResult);
        assertEquals(expResult, realResult);
        assertNull(orgId);
    }

    @Test
    public void getStandardOcupationalCode() {
        long realResult = emp.getStandardOcupationalCode();
        long expResult = Long.parseLong("1234");

        assertNotNull(realResult);
        assertEquals(expResult, realResult);
    }


    @Test
    public void employeeId(){
        assertNotNull(emp.getEmployeeId());
    }

    @Test
    public void tostring(){
        assertNotNull(emp.toString());
    }
}