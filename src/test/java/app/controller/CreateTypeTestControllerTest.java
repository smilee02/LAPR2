package app.controller;

import app.domain.model.TypeOfTest;
import org.junit.Test;



import static org.junit.Assert.*;

public class CreateTypeTestControllerTest {
    static CreateTypeTestController createTypeTestController = new CreateTypeTestController();

    @Test
    public void saveTypeTest() {
        TypeOfTest typeOfTest = new TypeOfTest("dlgfe", "Blood", "Seringue");
        boolean expectedValue = true;
        boolean realValue = createTypeTestController.saveTypeTest(typeOfTest);
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void verifyCode() {
        int expectedValue = 0;
        int realValue = CreateTypeTestController.verifyCode("djers");
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void verifyCode1() {
        int expectedValue = 0;
        int realValue = CreateTypeTestController.verifyCode("12345");
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void verifyDescription() {
        int expectedValue = 0;
        int realValue = CreateTypeTestController.verifyDescription("Blood");
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void verifyDescription1() {
        int expectedValue = 2;
        int realValue = CreateTypeTestController.verifyDescription("Bloodsksksksksksksksk");
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void verifyCollectingMethod() {
        int expectedValue = 0;
        int realValue = CreateTypeTestController.verifyCollectingMethod("Seringue");
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void verifyCollectingMethod1() {
        int expectedValue = 3;
        int realValue = CreateTypeTestController.verifyCollectingMethod("Seriaaangueawdawaawdkawdkwadkwadkdaw");
        assertEquals(expectedValue, realValue);
    }
}