package app.ui.console;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReportUITest {

    @Test
    public void validateErrors() {
        ReportUI reportUI = new ReportUI();
        boolean realValue = reportUI.validateErrors("banana", 1);
        assertTrue(realValue);
    }

    /*@Test
    public void validateErrors1() {
        ReportUI reportUI = new ReportUI();
        boolean realValue = reportUI.validateErrors("10", 1);
        assertTrue(realValue);
    }*/

    @Test
    public void validateErrors2() {
        ReportUI reportUI = new ReportUI();
        boolean realValue = reportUI.validateErrors("0", 1);
        assertFalse(realValue);
    }

    @Test
    public void validateErrors3() {
        ReportUI reportUI = new ReportUI();
        boolean realValue = reportUI.validateErrors("banana", 2);
        assertTrue(realValue);
    }

    @Test
    public void validateErrors4() {
        ReportUI reportUI = new ReportUI();
        boolean realValue = reportUI.validateErrors("yes", 2);
        assertFalse(realValue);
    }

    @Test
    public void validateErrors5() {
        ReportUI reportUI = new ReportUI();
        boolean realValue = reportUI.validateErrors("banana", 3);
        assertTrue(realValue);
    }

    @Test
    public void validateErrors6() {
        ReportUI reportUI = new ReportUI();
        boolean realValue = reportUI.validateErrors("5", 3);
        assertTrue(realValue);
    }

    @Test
    public void validateErrors7() {
        ReportUI reportUI = new ReportUI();
        boolean realValue = reportUI.validateErrors("1", 3);
        assertFalse(realValue);
    }
}