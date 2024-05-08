package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.store.SampleRecorderStore;

import java.util.ArrayList;
import java.util.List;

public class SampleRecorderController {
    Company company = Constants.COMPANY;

    /**
     * Method created so that the Company does not interact directly with the UI.
     *
     * @return Company.
     */
    public SampleRecorderStore getSampleRecorderStore() {
        return company.getSampleRecorderStore();
    }

    public SampleRecorderController() {
    }

    /**
     * Method to print a message, whether it is a String, a Test or what is needed int the UI. It is receives a parameter of the type Object, so it can ease this process, and print every type of information. Also, the existance of this method saves resources.
     *
     * @param Message Parameter received to be printed
     */
    public void Show(Object Message) {
        System.out.println(Message);
    }

    /**
     * Validates a integer received, checking if it is a Integer or a String. This method is useful in a way that helps the program not ending if it's supposed to write a Integer and the user doesn't do so, instead having a loop, until a Integer is written.
     *
     * @param FirstAnswer Inputs received that need to be Integers.
     * @return True if is a Integer, False if it is not.
     */
    public boolean ValidateInteger(String FirstAnswer) {
        try {
            Long.parseLong(FirstAnswer);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the answer is a Integer, and proceeds to check if it is one of the options given in the first menu (4).
     *
     * @param FirstAnswer What the user pretends to do first.
     * @return True if is a valid answer, false if it isn't.
     */
    public boolean ValidateFirstAnswer(String FirstAnswer) {
        try {
            Integer.parseInt(FirstAnswer);
        } catch (NumberFormatException e) {
            return false;
        }
        int Answer = Integer.parseInt(FirstAnswer);
        if (Answer > 0 && Answer < 5) {
            return true;
        }
        return false;
    }

    /**
     * Checks if a Test belongs to the Test List in the Company, and if the pick of the User is valid.
     *
     * @param TestSelected introduced by the User.
     * @return true, if the test selected belongs to the list, false if the pick is invalid.
     */
    public boolean ValidateTestSelected(String TestSelected) {
        if (Integer.parseInt(TestSelected) < 1) {
            return false;
        }
        if (ValidateInteger(TestSelected)) {
            if (getSampleRecorderStore().getTestList().size() + 1 >= Integer.parseInt(TestSelected)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the answer is a Integer, and proceeds to check if it is one of the options given in the menu (3).
     *
     * @param Answer3 What the user pretends to do pick.
     * @return True if is a valid answer, false if it isn't.
     */
    public boolean ValidateAnswer3(String Answer3) {
        try {
            Integer.parseInt(Answer3);
        } catch (NumberFormatException e) {

            return false;
        }
        int Answer = Integer.parseInt(Answer3);
        if (Answer > 0 && Answer < 4) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the answer is a Boolean, accepting the answers yes, y, no or n.
     *
     * @param BooleanAnswer Input written.
     * @return True if is a valid answer, false if it isn't.
     */
    public boolean ValidateBooleanAnswer(String BooleanAnswer) {
        String BooleanAnswerToLowerCase = BooleanAnswer.toLowerCase();
        if (BooleanAnswerToLowerCase.equals("yes") || BooleanAnswerToLowerCase.equals("no") || BooleanAnswerToLowerCase.equals("y") || BooleanAnswerToLowerCase.equals("n")) {
            return true;
        } else return false;
    }

    public boolean Validate12digits(String Sample) {
        int count = 0;
        if (!ValidateInteger(Sample)) {
            return false;
        }
        long SampleInt = Long.parseLong(Sample);
        while (SampleInt != 0) {
            SampleInt = SampleInt / 10;
            count++;
        }
        return count == 12;
    }

    /**
     * Reaches a specific test introduced by the user.
     * @param i index that the user pretends to get the Test on the Test List.
     * @return the test relative to the index the user pretends.
     */
    public RegisterTest SpecificTest(int i) {
        return company.getSampleRecorderStore().getTestList().get(i - 1);
    }

    /**
     * Returns the tests that have 0 samples, or that have a sample without a barcode.
     */
    public void TestsWNoSamples() {
        List<RegisterTest> testList = company.getSampleRecorderStore().getTestList();
        List<TestAndSamples> testsAndSamples = SampleRecorderStore.getTestAndSamplesList();
        List<RegisterTest> testsWnoSamples = new ArrayList<>();
        List<RegisterTest> testWSamples = new ArrayList<>();
        for(TestAndSamples s : testsAndSamples){
            testWSamples.add(s.getRegisterTest());
        }
        for(RegisterTest t : testList){
            if (!testWSamples.contains(t)){
                testsWnoSamples.add(t);
            }
        }
        int size = testsWnoSamples.size();
        if (size>0){
            for (RegisterTest t : testsWnoSamples){
                Show(t.toString());
            }
        }
        else {
            Show("\nAll tests have at least one sample assigned to them.\n\n");
        }

    }

    /**
     * Changes a sample pretended by the user to one written.
     *
     * @param TestSelected Test where the sample belongs.
     * @param index        Number of the sample that the user wants to change.
     * @param newbarcode   introduced by the user, to become the new barcode.
     */
    public void setSpecificSample(int TestSelected, int index, String newbarcode) {
        company.getSampleRecorderStore().setSpecificSample(index, newbarcode);
    }

    /**
     * When the user wants to record new samples, the number of samples has to be changed, and this method does just that.
     * @param NumberOfSamplesToCollect Number of new samples.
     * @param TestSelectedInt Test where the Sample belongs.
     */
   /* public void AddSamples(int NumberOfSamplesToCollect, int TestSelectedInt) {
        company.getSampleRecorderStore().getTestList().get(TestSelectedInt - 1).setNumberOfSamples(companySampleRecorder.getSampleRecorderStore().getTestList().get(TestSelectedInt - 1)
                .getNumberOfSamples() + NumberOfSamplesToCollect);
    }*/

    /**
     * Specifies the number of samples a Test has.
     *
     * @param TestSelectedInt Test where the samples the user wants to reach belongs.
     * @return the number of samples of a specific test.
     */
    public int getNumberOfSamples(int TestSelectedInt) {
        return company.getSampleRecorderStore().getNumberOfSamples(TestSelectedInt);
    }


    /**
     * Reaches all the recorded Samples of a specific test.
     * @param TestSelectedInt Test where the samples the user wants to reach.
     * @return all the samples of a specific test.
     */
    public List<Sample> AllSamples(int TestSelectedInt) {
        return company.getSampleRecorderStore().getAllSamples(TestSelectedInt);
    }

    public String getSpecificBarcode(int i, int y) {
        List<Sample> samples = company.getSampleRecorderStore().getSpecificBarcode(i);
        return samples.get(y - 1).toString();
    }

    /**
     * Generates the sample barcode code.
     * @return barcode code generated
     */
    public String generateBarcodeCode() {
        return company.getSampleRecorderStore().generateBarcodeCode();
    }

    /**
     * Generates the sample barcode image.
     * @param barcode image generated.
     */
    public void generateBarcodeImage(String barcode) {
        company.getSampleRecorderStore().generateBarcodeImage(barcode);
    }

    /**
     * Adds a barcode to a chosen Test.
     * @param testChosen introduced by the user
     * @param barcode that will be added.
     * @return if the operation was successfull.
     */
    public boolean addSample(int testChosen, String barcode) {
        return company.getSampleRecorderStore().addSample(testChosen, barcode);
    }

    /**
     * Saves a sample that belongs to a Test.
     * @return if operation was successful.
     */
    public boolean saveSampleAndTest() {
        return company.getSampleRecorderStore().saveTestAndSample();
    }

    /**
     * Adds to the List of test another test, and the list of samples of that test to be filled.
     * @param pos of the List.
     * @return the updated data.
     */
    public TestAndSamples createNewTestAndSamples(int pos) {
        return company.getSampleRecorderStore().newTestAndSamples(pos);
    }

    /**
     * Method to access the List of tests and the samples that are associated with that test.
     * @return the data.
     */
    public List<TestAndSamples> getTestAndSamples() {
        return SampleRecorderStore.getTestAndSamplesList();
    }
}
