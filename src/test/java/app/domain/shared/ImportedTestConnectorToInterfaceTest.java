package app.domain.shared;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ImportedTestConnectorToInterfaceTest {
    private ImportedTestConnectorToInterface importedTestConnectorToInterface = new ImportedTestConnectorToInterface();
    @Test
    public void convertLine() {
        assertEquals("s",importedTestConnectorToInterface.convertLine("s;o")[0]);
    }


    @Test
    public void clearList() {
        assertTrue(importedTestConnectorToInterface.clearList());
    }

    @Test
    public void registerClient() {
        String[] all = new String[14];
        for(int i=0;i<all.length;i++){
            all[i]="25/10/2001";
            all[i]="25/10/2001 13:55";

        }
        assertFalse(importedTestConnectorToInterface.registerClient(all));
    }

    @Test
    public void registerTest() {
        String[] all = new String[14];
        for(int i=0;i<all.length;i++){
            all[i]="25/10/2001";
            all[i]="25/10/2001 13:55";

        }
        assertFalse(importedTestConnectorToInterface.registerTest(all,all));
    }

    @Test
    public void fillArr() {
        List<String> all =new ArrayList<>();
        all.add("12");
        all.add("3");
        double[] arr = importedTestConnectorToInterface.fillArr(all);
        assertNotNull(importedTestConnectorToInterface.fillArr(all));
        assertEquals(2,importedTestConnectorToInterface.fillArr(all).length);
    }

    @Test
    public void checkNumber() {
        assertTrue(ImportedTestConnectorToInterface.checkNumber("123",3));
    }


    @Test
    public void getParametersORValues() {
        String[] all = new String[14];
        for(int i=0;i<all.length;i++){
            all[i]="25/10/2001";
            all[i]="25/10/2001 13:55";

        }
        List<Integer> pos = new ArrayList<>();
        pos.add(1);
        pos.add(2);
        pos.add(3);
        assertNotNull(importedTestConnectorToInterface.getParametersORValues(pos,all));
    }


}