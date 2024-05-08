package app.controller;

import app.domain.model.Company;
import app.domain.shared.Constants;
import app.domain.store.NHSReportStore;

public class AutomaticNHSReportController {

    private final Company company;

    public AutomaticNHSReportController() {
        this.company = Constants.COMPANY;
    }

    public NHSReportStore getNHSReportStore() {
        return company.getNHSReportStore();
    }

    public void createAndSendReport() {
        NHSReportStore nhsReportStore = getNHSReportStore();
        
    }
}
