package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class importedTestTest {
    private importedTest importedTest = new importedTest(null,null);

    @Test
    public void getClient() {
        assertNull(importedTest.getClient());
    }

    @Test
    public void convertDouble() {
        assertEquals("0000000000000007.0",importedTest.convertDouble(7));
    }

    @Test
    public void getRecordResult() {
        assertNull(importedTest.getRecordResult());
    }
}