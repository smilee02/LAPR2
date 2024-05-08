package app.domain.store;

import app.domain.model.Parameter;
import app.domain.model.RegisterTest;
import app.domain.model.TypeOfTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RegisterTestStoreTest {
    public static RegisterTestStore rts = new RegisterTestStore();
    public static List<Parameter> parameterList = new ArrayList<>();
    public static final String ccn = "1234567812345678";
    public static RegisterTest test;
    public static RegisterTest test2 = new RegisterTest(parameterList,Long.parseLong(ccn),new TypeOfTest("blood","blood","blood"),"123456789121","000000000002","abcd1");
    @Test
    public void createRegisterTest() {
        test = rts.CreateRegisterTest(Long.parseLong(ccn),parameterList,new TypeOfTest("blood","blood","blood"),"123456789121","000000000002","abcd1");
        assertEquals(test2.getCCN(), test.getCCN());
        assertEquals(test2.getTestnumber(), test.getTestnumber());
        assertEquals(test2.getNhs(), test.getNhs());
    }

    @Test
    public void validateRegisterTestStore() {
        //assertFalse(rts.validateRegisterTestStore());
        rts.CreateRegisterTest(Long.parseLong(ccn),parameterList,new TypeOfTest("blood","blood","blood"),"123456789124","000000000002","abcd1");
        assertTrue(rts.validateRegisterTestStore());
    }

    @Test
    public void setCal() {
        rts.setCal(test);
        assertEquals(test,rts.getRt());
    }

    /*@Test
    public void saveRegisterTest() {
        assertTrue(rts.saveRegisterTest());
    }*/

    /*@Test
    public void deleteRegisterTest() {
        rts.saveRegisterTest();
        rts.deleteRegisterTest(0);
        assertEquals(2, RegisterTestStore.getRegisterTestList().size());
    }*/

    @Test
    public void getRegisterTestList() {
        List<RegisterTest> test = RegisterTestStore.getRegisterTestList();
        assertEquals(test, RegisterTestStore.getRegisterTestList());
        assertNotNull(RegisterTestStore.getRegisterTestList());
    }
}