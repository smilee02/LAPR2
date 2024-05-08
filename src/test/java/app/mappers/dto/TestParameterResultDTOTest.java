package app.mappers.dto;

import app.domain.model.Parameter;
import com.example2.EMRefValue;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TestParameterResultDTOTest {
    private double result = 2.0;
    private String metric = "mg/L";
    private EMRefValue emRefValue = new EMRefValue("PLT00",metric,1.0,3.0,new Date());
    private TestParameterResultDTO testParameterResultDTO = new TestParameterResultDTO(result,metric,emRefValue.getMinValue(),emRefValue.getMaxValue(),new Parameter("PLT00","P","a"));

    @Test
    public void getResult() {
        assertNotEquals(testParameterResultDTO.getResult()-1,testParameterResultDTO.getResult());
        assertNotEquals(testParameterResultDTO.getResult()+1,testParameterResultDTO.getResult());
    }



    @Test
    public void getMetric() {
        assertNotNull(testParameterResultDTO.getMetric());
    }

    @Test
    public void gets(){
        assertNotNull(testParameterResultDTO.getResult());
        assertNotNull(testParameterResultDTO.getParameter());
        assertNotNull(testParameterResultDTO.getMetric());
        assertNotNull(testParameterResultDTO.getMaxRefValue());
        assertNotNull(testParameterResultDTO.getMinRefValue());
        assertNotNull(testParameterResultDTO.toString());
    }
    @Test
    public void sets(){
        testParameterResultDTO.setMinRefValue(-3);
        assertNotNull(testParameterResultDTO);
        assertEquals(-3,testParameterResultDTO.getMinRefValue(),0.0);
        testParameterResultDTO.setMaxRefValue(-3);
        assertNotNull(testParameterResultDTO);
        assertEquals(-3,testParameterResultDTO.getMinRefValue(),0.0);
        testParameterResultDTO.setResult(-3);
        assertNotNull(testParameterResultDTO);
        assertEquals(-3,testParameterResultDTO.getResult(),0.0);
        testParameterResultDTO.setMetric("f");
        assertNotNull(testParameterResultDTO);
        assertEquals("f",testParameterResultDTO.getMetric());
    }

}