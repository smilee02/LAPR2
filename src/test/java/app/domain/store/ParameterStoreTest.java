package app.domain.store;

import app.domain.model.Parameter;
import app.domain.model.TypeOfTest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ParameterStoreTest {
    static ParameterStore parameterStore = new ParameterStore();
    @Test
    public void getListOfParameters() {
        List<Parameter> d = parameterStore.getListOfParameters();
        assertNotNull(d);
    }
}