package app.domain.store;

import app.domain.model.Sample;
import app.domain.model.TestAndSamples;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class SampleRecorderStoreTest {
    SampleRecorderStore sampleRecorderStore = new SampleRecorderStore();
    @Test
    public void getTestList() {
        assertNotNull(sampleRecorderStore.getTestList());
    }

    @Test
    public void getNumberOfSamples() {
        assertEquals(0,sampleRecorderStore.getNumberOfSamples(0));
    }


    @Test
    public void getAllSamples() {
        assertNotNull(sampleRecorderStore.getAllSamples(0));
    }



    @Test
    public void saveTestAndSample() {
        assertTrue(sampleRecorderStore.saveTestAndSample());
    }

    @Test
    public void getSpecificBarcode() {
        List<Sample> samples=sampleRecorderStore.getSpecificBarcode(0);
        assertNotNull(sampleRecorderStore.getSpecificBarcode(0));
    }


    @Test
    public void generateBarcodeCode() {
        assertNotNull(sampleRecorderStore.generateBarcodeCode());
    }



    @Test
    public void getTestAndSamplesList() {
        assertNotNull(SampleRecorderStore.getTestAndSamplesList());
    }

}