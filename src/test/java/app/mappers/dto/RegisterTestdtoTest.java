package app.mappers.dto;

import app.domain.model.Parameter;
import app.domain.model.TypeOfTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RegisterTestdtoTest {
    List<Parameter> parameterList = new ArrayList<>();
    String ccn = "1234567812345678";
    public final RegisterTestdto rtd = new RegisterTestdto(parameterList,Long.parseLong(ccn),new TypeOfTest("blood","blood","blood"),"123456789121","000000000002","abcd1");
    public final RegisterTestdto rtdNull = new RegisterTestdto(null,Long.parseLong(ccn),null,null,null,null);
    @Test
    public void getData() {
        assertNotNull(rtd.getData());
    }

    @Test
    public void getNhs() {
        assertNotNull(rtd.getNhs());
        assertNull(rtdNull.getNhs());
    }

    @Test
    public void getTestnumber() {
        assertNotNull(rtd.getTestnumber());
        assertNull(rtdNull.getTestnumber());
    }

    @Test
    public void getTest() {
        assertNotNull(rtd.getTest());
        assertNull(rtdNull.getTest());
    }

    @Test
    public void getCCN() {
        assertNotNull(rtd.getCCN());
        assertEquals(rtd.getCCN(),rtd.getCCN());
    }

    @Test
    public void getParametersList() {
        assertNotNull(rtd.getParametersList());
        assertNull(rtdNull.getParametersList());
    }
}