package app.domain.store;

import app.domain.model.TypeOfTest;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.*;

public class TypeOfTestStoreTest {
    static TypeOfTestStore typeOfTestStore = new TypeOfTestStore();
    @Test
    public void getListOfTypeOfTest() {
        List<TypeOfTest> d = typeOfTestStore.getListOfTypeOfTest();
        assertNotNull(d);
    }
}