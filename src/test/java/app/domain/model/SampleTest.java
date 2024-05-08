package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class SampleTest {
    private String barcode = "hey";
    private int id = 0;
    private Sample s = new Sample(barcode,id);

    @Test
    public void getId() {
        assertNotNull(s.getId());
    }

    @Test
    public void getBarcode() {
        assertNotNull(s.getBarcode());
    }

    @Test
    public void testToString() {
        assertNotNull(s.toString());
        assertEquals("Barcode : hey",s.toString());
    }
}