package app.domain.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParameterCategoryTest {
    ParameterCategory o = new ParameterCategory("aaaaa","aa");
    List<Parameter> a = new ArrayList<>();
    Parameter p = new Parameter("a","a","a");
    Parameter m = new Parameter("e","e","e");

    @Test
    public void testToString() {
            assertNotNull(o.toString());
            o.addParameter(p);o.addParameter(m);
            /*String s = String.format("Category %n Code: aaaaa %n Description: aa %n Parameters: %n a%n e%n ");
            assertEquals(s,o.toString());*/
            assertNotNull(o.getShortDescription());
            assertNotNull(a);
    }
}