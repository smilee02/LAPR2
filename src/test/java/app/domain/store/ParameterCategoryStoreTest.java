package app.domain.store;

import static org.junit.Assert.*;

import app.domain.model.ParameterCategory;
import org.junit.Test;


public class ParameterCategoryStoreTest {
    ParameterCategoryStore testes2= new ParameterCategoryStore();
    ParameterCategory PCtrue =new ParameterCategory("rocky","Kingstonzz");
    ParameterCategory PCfalse =new ParameterCategory("rocky","Kingstonzz");

    @Test
    public void getCode(){
        assertNull(testes2.getCode());
    }
    @Test
    public void validateParameterCategory1() {

        boolean expresult = true;
        boolean result =testes2.ValidateParameterCategory(PCtrue);
        assertEquals(expresult,result);
    }

    @Test
    public void saveParameterCategory1() {
        boolean expresult=true;
        boolean result= testes2.SaveParameterCategory(PCtrue);
        assertEquals(expresult,result);
    }

    @Test
    public void validateParameterCategory2() {
        boolean expresult = false;
        testes2.SaveParameterCategory(PCfalse);
        boolean result =testes2.ValidateParameterCategory(PCfalse);
        assertEquals(expresult,result);
    }
    @Test
    public void saveParameterCategory2() {
        boolean expresult=false;
        testes2.SaveParameterCategory(PCfalse);
        boolean result= testes2.SaveParameterCategory(PCfalse);
        assertEquals(expresult,result);
    }
}