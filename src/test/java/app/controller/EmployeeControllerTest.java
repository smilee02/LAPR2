package app.controller;


import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeControllerTest {

    @Test
    public void checkName() {
        String name = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        assertFalse(EmployeeController.checkName(name));
    }

    @Test
    public void checkNumber() {
        String number = "12";
        assertFalse(EmployeeController.checkNumber(number, 11, "r"));
    }

    @Test
    public void checkEmail() {
        String email = "r";
        EmployeeController e = new EmployeeController();
        assertFalse(e.checkEmail(email));
    }
    @Test
    public void checkIfEmpty() {
        String n = "";
        assertFalse(EmployeeController.checkIfEmpty(n));
    }
}