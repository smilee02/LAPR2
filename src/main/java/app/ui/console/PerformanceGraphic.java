package app.ui.console;

import app.controller.ClinicalAnalysisLaboratoryController;
import app.controller.TestOverviewController;
import app.domain.model.RegisterTest;
import app.domain.model.Validate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static app.controller.BruteForceAlgorithm.BruteForceAlgorithm1;
import static app.controller.BruteForceAlgorithm.BruteForceAlgorithm2;

public class PerformanceGraphic {
    public List<Integer> PerformanceCalculation(Date firstDateformat, Date secondDateformat) {
        int intervaloftime = 0, hour, starthour = 8, minute = 0, count = 0, endinghour = 20;
        TestOverviewController testOverviewController = new TestOverviewController();
        List<RegisterTest> registerTest = new ArrayList<>();
        List<Integer> ResSubVal = new ArrayList<>();
        List<Validate> validateTest = new ArrayList<>();
        Date currentday= firstDateformat;
        while (!currentday.after(secondDateformat)) {
            hour = starthour;
            while (hour < endinghour) {
                for (int i = 1; i < testOverviewController.getRegisterTest().size(); i++) {
                    if (testOverviewController.SpecificTest(i).getData().getDay() == currentday.getDay()) {
                        if (testOverviewController.SpecificTest(i).getData().getHours() == hour && testOverviewController.SpecificTest(i).getData().getMinutes() >= minute && testOverviewController.SpecificTest(i).getData().getMinutes() < minute + 30) {
                            registerTest.add(testOverviewController.SpecificTest(i));
                            ClinicalAnalysisLaboratoryController.soutConsole("testes do dia " + currentday.getDay()+" "+ testOverviewController.SpecificTest(i).getData().getDay()+" date"+ testOverviewController.SpecificTest(i).getData());
                        }
                        if (testOverviewController.testResult(i).getValidateDate().getHours() == hour && testOverviewController.testResult(i).getValidateDate().getMinutes() > minute && testOverviewController.testResult(i).getValidateDate().getMinutes() < minute + 30) {
                            validateTest.add(testOverviewController.testResult(i));
                            ClinicalAnalysisLaboratoryController.soutConsole("validate hora = " + testOverviewController.testResult(i).getValidateDate());
                        }
                    }
                }
                ClinicalAnalysisLaboratoryController.soutConsole("registertestsize " + registerTest.size() + "  validatetestsize" + validateTest.size());
                ResSubVal.add(intervaloftime, registerTest.size() - validateTest.size());
                // diferenca de novos testes ou seja da rececionista, e de resultados obtidos, ou seja validados
                validateTest.removeAll(validateTest);
                registerTest.removeAll(registerTest);
                minute = minute + 30;
                count++;
                intervaloftime = intervaloftime + 1;
                if (count == 2) {
                    hour = hour + 1;
                    minute = 0;
                    count = 0;
                }
            }
            hour = starthour;
            minute = 0;
            count = 0;
            currentday = testOverviewController.addOneDay(currentday);
        }
        return ResSubVal;
    }

    public int MaximumSum(Date firstDateformat, Date secondDateformat) {
        return BruteForceAlgorithm1(PerformanceCalculation(firstDateformat, secondDateformat));

    }

    public List<Integer> ValuesofSum(Date firstDateformat, Date secondDateformat) {
        return BruteForceAlgorithm2(PerformanceCalculation(firstDateformat, secondDateformat));
    }
}