package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {
    private Client o = new Client(null,null,0,0,null,0,0,null,null);
    private Client o2 = new Client("null","Female",20,21,"null",4,8,"null","rua johny");
    private Client o3 = new Client("Fred",22310,221341,"23/6/2000",4,8,"null","rua quim");

    @Test
    public void getName() {
        assertNotNull(o2.getName());
        assertNull(o.getName());
    }

    @Test
    public void getEmail() {
        assertNotNull(o2.getEmail());
        assertNull(o.getEmail());
    }

    @Test
    public void getCitizenCardNumber() {
        assertNotNull(o2.getCitizenCardNumber());
    }

    @Test
    public void getNhsNumber() {
        assertNotNull(o2.getNhsNumber());
    }

    @Test
    public void getPhoneNumber() {
        assertNotNull(o2.getPhoneNumber());
    }

    @Test
    public void getTin() {
        assertNotNull(o2.getTin());
    }

    @Test
    public void getBirthDate() {
        assertNotNull(o2.getBirthDate());
        assertNull(o.getBirthDate());
    }

    @Test
    public void getSex() {
        assertNull(o.getSex());
        assertNotNull(o2.getSex());
    }

    @Test
    public void tostring(){
        String s = String.format("%nClient:%nName: Fred%nCitizen Card Number: 22310%nNHS Number: 221341%nBirth Date: 23/6/2000%nTIN: 4%nPhone Number: 8%nEmail: null");
        assertEquals(s,o3.toString());
        String o = String.format("%nClient:%nName: null%nSex: Female%nCitizen Card Number: 20%nNHS Number: 21%nBirth Date: null%nTIN: 4%nPhone Number: 8%nEmail: null");
        assertEquals(o,o2.toString());
    }
}