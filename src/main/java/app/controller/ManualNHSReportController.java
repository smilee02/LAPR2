package app.controller;

import app.domain.model.Company;
import app.domain.shared.*;
import app.domain.store.NHSReportStore;

import java.util.Date;

/**
 * 
 */
public class ManualNHSReportController {

    private final Company company = App.getInstance().getCompany();

    public ManualNHSReportController() {
    }

    public NHSReportStore getNHSReportStore() {
        return company.getNHSReportStore();
    }

    public String createReport(Date date1, Date date2, int independentVariable, int historicalPoints, int choice,
            double significanceLevel) {
        return getNHSReportStore().createReport(date1, date2, independentVariable, historicalPoints, choice,
                significanceLevel);
    }

    public LinearRegression createLinearRegression(Date date1, Date date2, int independentVariable) {
        return getNHSReportStore().createLinearRegression(date1, date2, independentVariable);
    }

    public void sendReport(String report) {
        getNHSReportStore().sendReport(report);
    }

}
