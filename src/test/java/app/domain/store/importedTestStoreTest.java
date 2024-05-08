package app.domain.store;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.RegisterTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class importedTestStoreTest {
    private importedTestStore importedTestStore = new importedTestStore();
    @Test
    public void createImportedTest() {
        importedTestStore.setRecordResult(null);
        assertNotNull(importedTestStore.createImportedTest(null));
        assertNull(importedTestStore.createImportedTest(null).getRecordResult());
        assertNull(importedTestStore.createImportedTest(null).getClient());
    }



    @Test
    public void saveImportedTest() {
        importedTestStore.setIt(null);
        importedTestStore.saveImportedTest();
        assertNotNull(importedTestStore.getImportedTests());
    }

    @Test
    public void addRegisterTest() {
        assertTrue(importedTestStore.addRegisterTest(null,null,null,new RegisterTest(null,Long.parseLong("1"),null,null,null,null),null));
    }

    /*@Test
    public void addToListRt() {
        assertTrue(importedTestStore.addToListRt());
    }*/

    @Test
    public void setCompany() {
        Company company = App.getInstance().getCompany();
        importedTestStore.setCompany(company);
        assertEquals(company,importedTestStore.getCompany());
    }

    @Test
    public void getImportedTests() {
        assertNotNull(importedTestStore.getImportedTests());
    }

    @Test
    public void clearList() {
        assertTrue(importedTestStore.clearList());
    }

    @Test
    public void getIt() {
        assertNull(importedTestStore.getIt());
    }
}