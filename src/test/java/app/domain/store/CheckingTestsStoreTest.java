package app.domain.store;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckingTestsStoreTest {

    @Test
    public void getResultsOfClient() {
        CheckingTestsStore checkingTestsStore = new CheckingTestsStore();
        assertNotNull(checkingTestsStore.getResultsOfClient(99));
    }
}