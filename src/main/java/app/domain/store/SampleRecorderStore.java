package app.domain.store;

import app.domain.model.RegisterTest;
import app.domain.model.Sample;
import app.domain.model.TestAndSamples;
import app.domain.shared.BarcodeAdapter;
import app.domain.shared.Constants;
import app.domain.shared.Files;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SampleRecorderStore implements Serializable, Files {
    static List<Sample> samplesList;
    static List<TestAndSamples> testAndSamplesList = new ArrayList<>();
    private TestAndSamples testAndSamples;
    private static int barcodeCounter = 1;
    public SampleRecorderStore(){
        try{
            testAndSamplesList = (List<TestAndSamples>) Files.decrypt("SampleRecorderStoreTS.ser");
            samplesList = (List<Sample>) Files.decrypt("SampleRecorderStoreS.ser");
        }catch(Exception ignored){

        }
    }
    /**
     * This List gets all the existent tests on the system. This is also used to select which Test the user pretends to record new information, or change it.
     *
     * @return the List of the Tests.
     */
    public List<RegisterTest> getTestList() {
        return RegisterTestStore.getRegisterTestList();
    }

    public int getNumberOfSamples(int id) {
        int cont = 0;
        for (Sample s : samplesList) {
            if (s.getId() == id) {
                cont++;
            }
        }
        return cont;
    }

    /**
     * Creates a new Test with the respective samples.
     *
     * @param pos is the position in the TestList
     * @return the test with its respective samples.
     */
    public TestAndSamples newTestAndSamples(int pos) {
        samplesList = new ArrayList<>();
        testAndSamples = new TestAndSamples(getTestList().get(pos), samplesList);
        return testAndSamples;
    }


    /**
     * Selects a specific tests and reaches all the samples of that test.
     *
     * @param testSelectedInt is the index of the test list, the test selected.
     * @return the List with all the samples.
     */
    public List<Sample> getAllSamples(int testSelectedInt) {
        List<Sample> allSamples = new ArrayList<>();
        for (Sample x : samplesList) {
            if (x.getId() == testSelectedInt) {
                allSamples.add(x);
            }
        }
        return allSamples;
    }

    /**
     * Adds a unique sample to the List of samples.
     *
     * @param testSelected is the index of the test list, the test selected.
     * @param fillSample   is the barcode to be filled
     * @return
     */
    public boolean addSample(int testSelected, String fillSample) {
        Sample s = new Sample(fillSample, testSelected);
        System.out.println(s.getId());
        samplesList.add(s);
        Files.encrypt("SampleRecorderStoreS.ser",samplesList);
        return true;
    }

    /**
     * After creating a new Test, the samplesList was created and unchanged, and, as such, this method fills those samples.
     *
     * @param id      Index of the Test List, to select a specific test.
     * @param barcode
     * @return
     */
    public boolean addSamplesToNewTestAndSamples(int id, String barcode) {
        return addSample(id, barcode);
    }

    /**
     * Records the new test and its respective samples in the List.
     *
     * @return if the operation was successfull.
     */

    public boolean saveTestAndSample() {
        testAndSamplesList.add(testAndSamples);
        Files.encrypt("SampleRecorderStoreTS.ser",testAndSamplesList);
        return true;
    }

    /**
     * Gets all the samples of a test
     *
     * @param testRegistered index of the list of tests desired.
     * @return List with the samples.
     */
    public List<Sample> getSpecificBarcode(int testRegistered) {
        return getAllSamples(testRegistered);
    }

    /**
     * Changes a specific sample of a test.
     *
     * @param index      of the sample to be changed.
     * @param newbarcode to replace the previous sample.
     */
    public void setSpecificSample(int index, String newbarcode) {
    }

    /**
     * Generates the barcode sequentially.
     *
     * @return the barcode generated.
     */
    public String generateBarcodeCode() {
        int length = 11;
        String barcode = "";
        int digit = 0;
        int aux = barcodeCounter;
        while(aux!=0){
            aux = aux/10;
            digit++;
        }
        int zerosLeft = length-digit;
        for(int i=0;i<zerosLeft;i++){
            barcode = barcode + 0;
        }
        barcode = barcode + barcodeCounter;
        barcodeCounter++;
        return barcode;
    }

    /**
     * Generates the barcode image.
     *
     * @param barcode generated.
     */
    public void generateBarcodeImage(String barcode) {
        BarcodeAdapter barcodeAdapter = Constants.BARCODE_MODULE;  //Testes para o barcode
        try {
            barcodeAdapter.createUPCA(barcode);
        } catch (BarcodeException | OutputException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks which test has the sample and the barcode introduced.
     *
     * @param barcode which the user wants to search.
     * @return test which the sample belongs.
     */
    public static RegisterTest getTest(String barcode) {
        RegisterTest test = null;
        for (TestAndSamples x : testAndSamplesList) {
            for (Sample y : x.getSamplesList()) {
                if (y.getBarcode().equals(barcode)) {
                    test = x.getRegisterTest();
                }
            }
        }
        return test;
    }

    /**
     * Lists the test and the respective samples associated with that test.
     *
     * @return the list of samples.
     */
    public static List<TestAndSamples> getTestAndSamplesList() {
        return testAndSamplesList;
    }
}