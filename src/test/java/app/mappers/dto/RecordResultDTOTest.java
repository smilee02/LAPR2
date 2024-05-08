package app.mappers.dto;

import app.domain.model.Parameter;
import app.domain.model.RegisterTest;
import app.domain.model.TestParameterResult;
import app.domain.model.TypeOfTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class RecordResultDTOTest {
    List<Parameter> parameterList = new ArrayList<>();
    String ccn = "1234567812345678";
    public final RegisterTest rtd = new RegisterTest(parameterList,Long.parseLong(ccn),new TypeOfTest("blood","blood","blood"),"123456789121","000000000002","abcd1");
    public final RegisterTest rtdNull = new RegisterTest(null,Long.parseLong(ccn),null,null,null,"abcd1");
    private List<TestParameterResult> resultList = new ArrayList<>();
    private List<TestParameterResult> resultListnull;
    RecordResultDTO n = new RecordResultDTO(rtd,resultList,new Date());
    RecordResultDTO p = new RecordResultDTO(rtdNull,resultListnull,null);

    @Test
    public void getCreatedAt() {
        assertNotNull(n.getCreatedAt());
        assertNull(p.getCreatedAt());
    }

    @Test
    public void getTest() {
        assertNotNull(n.getTest());
    }

    @Test
    public void getResultList() {
        assertNotNull(n.getResultList());
        assertNull(p.getResultList());
    }

}