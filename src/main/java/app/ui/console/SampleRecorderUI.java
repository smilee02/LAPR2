package app.ui.console;

import app.controller.SampleRecorderController;
import app.domain.model.Test;
import app.ui.console.utils.Utils;

import java.util.Scanner;

public class SampleRecorderUI implements Runnable {
    static Scanner ler = new Scanner(System.in);

    public void run() {

        int options = 0;
        int i;
        int flag = 0;
        int y;
        int light = 0;
        long fillSampleInt;
        final int firstOption = 1;
        final int secondOption = 2;
        final int thirdOption = 3;
        final int fourthOption = 4;
        String sampleToChange;
        String booleanAnswer;
        String booleanAnswer2;
        String firstanswer;
        String testSelected;
        String newSample;
        String NumberOfSamplesToCollect;
        String fillSample;
        String answer3;
        SampleRecorderController sampleRecorderController = new SampleRecorderController();
        do {
            sampleRecorderController.Show("What is the functionality you pretend?");
            sampleRecorderController.Show("1 - Check all the tests with samples");
            sampleRecorderController.Show("2 - Check all the tests for which there are no samples collected.");
            sampleRecorderController.Show("3 - Record the sample in the scope of a selected test.");
            sampleRecorderController.Show("4 - Exit");
            firstanswer = ler.nextLine();
            while (!sampleRecorderController.ValidateFirstAnswer(firstanswer)) {
                sampleRecorderController.Show("Please write a number between 1 and 4");
                firstanswer = ler.nextLine();
            }
            int firstAnswerInt = Integer.parseInt(firstanswer);
            if (firstAnswerInt == firstOption) {
                Utils.showList(sampleRecorderController.getTestAndSamples(),"Tests with Samples");
            }


            if (firstAnswerInt == secondOption) {
                sampleRecorderController.Show("The tests that have no samples are:");
                sampleRecorderController.TestsWNoSamples();
            }


            if (firstAnswerInt == thirdOption) {
                sampleRecorderController.Show("First select a test from the list we possess.");
                for (i = 1; i < sampleRecorderController.getSampleRecorderStore().getTestList().size()+1; i++)
                    if ((sampleRecorderController.SpecificTest(i) != null)) {
                        sampleRecorderController.Show(i + " - " + sampleRecorderController.SpecificTest(i));

                    }
                sampleRecorderController.Show("Option:\n");
                testSelected = ler.nextLine();
                int TestSelectedInt = Integer.parseInt(testSelected);
                while (!(sampleRecorderController.ValidateTestSelected(testSelected))) {
                    sampleRecorderController.Show("You have invalid data. Please type a number from the test list above");
                    testSelected = ler.nextLine();
                    TestSelectedInt = Integer.parseInt(testSelected);
                }
                sampleRecorderController.Show("You have selected test number " + TestSelectedInt + " which is " + sampleRecorderController.SpecificTest(TestSelectedInt) + ". Would you like to continue? (Y/N)");
                booleanAnswer = ler.nextLine();
                while (!(sampleRecorderController.ValidateBooleanAnswer(booleanAnswer))) {
                    sampleRecorderController.Show("Please write Yes or No");
                    booleanAnswer = ler.nextLine();
                }
                if (booleanAnswer.equalsIgnoreCase("YES") || booleanAnswer.equalsIgnoreCase("Y")) {
                    sampleRecorderController.createNewTestAndSamples(TestSelectedInt-1);
                    do {
                        sampleRecorderController.Show("Do you wish to:");
                        sampleRecorderController.Show("1- Change a existent sample"); /*new feature*/
                        sampleRecorderController.Show("2- Record a new sample");
                        sampleRecorderController.Show("3- Exit this menu");
                        answer3 = ler.nextLine();
                        while (!sampleRecorderController.ValidateAnswer3(answer3)) {
                            sampleRecorderController.Show("Please write a number between 1 and 3.");
                            answer3 = ler.nextLine();
                        }
                        int Answer3Int = Integer.parseInt(answer3);
                        if (Answer3Int == firstOption) {
                            if (sampleRecorderController.getNumberOfSamples(TestSelectedInt) == 0) {
                                sampleRecorderController.Show("You can't change a Test that has 0 samples. You can only change a Test in which a sample is 1.  If you would like to record a new one, please type 2. If you would like to select a different test, exit. You will now return to the menu.");
                                light = 1;
                            }
                            if (light == 0) {
                                sampleRecorderController.Show("Which sample do you want to change?");
                                for (y = 1; y < sampleRecorderController.getNumberOfSamples(TestSelectedInt) + 1; y++) {
                                    sampleRecorderController.Show(y + " - " + sampleRecorderController.getSpecificBarcode(y, TestSelectedInt));
                                }
                                sampleToChange = ler.nextLine();
                                while (!(sampleRecorderController.ValidateInteger(sampleToChange)) && (Integer.parseInt(sampleToChange)) < y + 1 && (Integer.parseInt(sampleToChange)) > 0) {
                                    sampleRecorderController.Show("Please write a valid number");
                                    sampleToChange = ler.nextLine();
                                }
                                int SampleToChangeInt = Integer.parseInt(sampleToChange);
                                newSample = sampleRecorderController.generateBarcodeCode(); //Gera o codigo de barras ( so os numeros)
                                sampleRecorderController.Show("You are about to change " + sampleRecorderController.getSpecificBarcode(SampleToChangeInt, TestSelectedInt) + " to " + newSample + ". Are you sure? (Y/N)");
                                booleanAnswer2 = ler.nextLine();
                                while (!(sampleRecorderController.ValidateBooleanAnswer(booleanAnswer2))) {
                                    sampleRecorderController.Show("Please write Yes or No");
                                    booleanAnswer2 = ler.nextLine();
                                }
                                if (booleanAnswer2.equalsIgnoreCase("YES") || booleanAnswer2.equalsIgnoreCase("Y")) {
                                    sampleRecorderController.setSpecificSample(TestSelectedInt, SampleToChangeInt - 1, newSample);
                                    sampleRecorderController.generateBarcodeImage(newSample);
                                    sampleRecorderController.Show("Your new sample was successfully recorded, your test is now " + sampleRecorderController.SpecificTest(TestSelectedInt));
                                }
                                if (booleanAnswer2.equalsIgnoreCase("NO") || booleanAnswer2.equalsIgnoreCase("N")) {
                                    break;
                                }
                            }
                        }
                        if (Answer3Int == secondOption) {
                            sampleRecorderController.Show("Please introduce the number of samples to record");
                            NumberOfSamplesToCollect = ler.nextLine();
                            while (!sampleRecorderController.ValidateInteger(NumberOfSamplesToCollect)) {
                                sampleRecorderController.Show("You have introduced an invalid number of samples to record, please type only numeric digits.");
                                NumberOfSamplesToCollect = ler.nextLine();
                                sampleRecorderController.ValidateInteger(NumberOfSamplesToCollect);
                            }

                            int cont = Integer.parseInt(NumberOfSamplesToCollect)-1;
                            sampleRecorderController.Show("You now have added " + NumberOfSamplesToCollect + " samples to your test, therefore the total number of samples is " + (sampleRecorderController.getNumberOfSamples(TestSelectedInt))+NumberOfSamplesToCollect);
                            int aux  = sampleRecorderController.getNumberOfSamples(TestSelectedInt) - Integer.parseInt(NumberOfSamplesToCollect) + 1;
                            int aux2 = sampleRecorderController.getNumberOfSamples(TestSelectedInt) +1;
                            for (int k = aux; k < aux2; k++) { //Tirei +1
                                String barcode = sampleRecorderController.generateBarcodeCode();
                                sampleRecorderController.Show("The barcode for the " + (Integer.parseInt(NumberOfSamplesToCollect)-cont) + "ª sample is " + barcode);
                                sampleRecorderController.generateBarcodeImage(barcode);
                                if(sampleRecorderController.addSample(TestSelectedInt,barcode)){
                                    sampleRecorderController.Show("Sample has been added successfully");
                                }else {
                                    sampleRecorderController.Show("There was a problem generating the barcode");
                                }
                                cont--;
                            }
                            sampleRecorderController.Show("You have successfully registered all samples regarding test " + sampleRecorderController.SpecificTest(TestSelectedInt) + ". As such, your new samples of this test are " + sampleRecorderController.AllSamples(TestSelectedInt));
                            cont=0;

                        }
                            if (Answer3Int == thirdOption) {
                                flag = 1;
                                sampleRecorderController.saveSampleAndTest();
                            }
                            light = 0;

                    } while (flag == 0) ;
                    }
                    if (booleanAnswer.equalsIgnoreCase("NO") || booleanAnswer.equalsIgnoreCase("N")) {
                        sampleRecorderController.Show("You will now return to the main menu.");
                    }

                }
                if (firstAnswerInt == fourthOption) {
                    sampleRecorderController.Show("We appreciate your usage of our app.");
                    options = 9;
                }

                options++;


            } while (options < fourthOption) ;



    }
}


