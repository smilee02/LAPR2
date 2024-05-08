package app.controller;

import app.domain.model.Client;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ClientControllerTest {
    ClientController clientController = new ClientController();

    @Test
    public void checkName() {
        String name = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        assertFalse(ClientController.checkName(name));
        assertTrue(ClientController.checkName("a"));
    }

    @Test
    public void checkSex() {
        String sex = "d";
        assertFalse(ClientController.checkSex(sex));
        assertTrue(ClientController.checkSex("m"));
    }

    @Test
    public void checkDay() {
        String day = "33";
        int month = 2;
        int year = 1988;
        assertFalse(ClientController.checkDay(day, month, year));
        assertFalse(ClientController.checkDay("f",2,3));
        assertFalse(ClientController.checkDay("30",2,2020));
        assertFalse(ClientController.checkDay("30",2,2019));
        assertFalse(ClientController.checkDay("50",13,1998));
        assertTrue(ClientController.checkDay("2",2,2002));
    }

    @Test
    public void checkMonth() {
        String month = "323132";
        assertFalse(ClientController.checkMonth(month));
        assertFalse(ClientController.checkMonth("f"));
        assertTrue(ClientController.checkMonth("2"));
    }

    @Test
    public void checkYear() {
        String year = "1660";
        assertFalse(ClientController.checkYear(year));
        assertFalse(ClientController.checkYear("f"));
        assertFalse(ClientController.checkYear("9999999999"));
        assertTrue(ClientController.checkYear("2002"));
    }

    @Test
    public void checkNumber() {
        String number = "12";
        assertFalse(ClientController.checkNumber(number, 11, "r"));
        assertFalse(ClientController.checkNumber("f",1,"2"));
        assertFalse(ClientController.checkNumber("-1",1,"2"));
        assertTrue(ClientController.checkNumber(number,2,"r"));
    }

    @Test
    public void checkEmail() {
        String email = "r";
        ClientController c = new ClientController();
        assertFalse(c.checkEmail(email));
    }

    @Test
    public void makeBirthDate() {
        int day = 12;
        int month = 2;
        int year = 1998;
        String d = "12/2/1998";
        assertEquals("12/2/1998", ClientController.makeBirthDate(day, month, year));
    }

    @Test
    public void checkIfEmpty() {
        String n = "";
        assertFalse(ClientController.checkIfEmpty(n));
    }

    @Test
    public void createClient() {
        assertTrue(clientController.createClient("1", 1, 1, "22/6/1998", 1, 1, "r@g.com","a"));
        assertTrue(clientController.createClient("1", "s", 1, 1, "22/6/1998", 1, 1, "r@g.com","a"));
    }

    /*@Test
    public void addUser() {
        assertFalse(clientController.addUserWithRole("1", "1", "1", "1"));
    }
*/
    @Test
    public void exists() {
        assertFalse(clientController.existsUser("1@g.com"));
    }

    @Test
    public void saveC() {
        assertFalse(clientController.saveClient());
    }

    @Test
    public void getClientList(){
        assertNotNull(ClientController.clientList());
    }
    @Test
    public void getPassword(){
        assertNotNull(ClientController.password());
    }

    @Test
    public void getPhonelIST(){
        assertNotNull(ClientController.phoneList());
    }

    @Test
    public void emailSer() throws IOException {
        assertNotNull(clientController.emailService("r@g.com"));
    }
}