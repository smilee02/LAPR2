package app.domain.shared;


import app.domain.model.Parameter;
import com.example2.EMRefValue;
import com.example2.ExternalModule2API;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class BloodAdapterTest {

    @Test
    public void getReferenceValue() {
        ExternalModule em = new BloodAdapter();
        EMRefValue exp = new EMRefValue("1","-1.0",-1.0,-1.0,new Date());
        EMRefValue real = em.getReferenceValue(new Parameter("1","1","1"),12345);
        assertNotNull(real);
        assertEquals(exp.toString(),real.toString());
        ExternalModule2API externalModule2API = new ExternalModule2API();
        assertNotNull(externalModule2API);
        EMRefValue exp2 = new EMRefValue("1",":)",-1.0,-1.0,new Date());
        EMRefValue real2 = em.getReferenceValue(new Parameter("1","1","1"),12345,":)");
        assertNotNull(real2);
        assertEquals(exp2.toString(),real2.toString());
    }

}
