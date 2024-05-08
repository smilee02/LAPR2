package app.domain.model;

import app.domain.shared.Files;
import app.domain.store.*;
import app.domain.store.TestOverviewStore;
import auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Company implements Serializable {
    private final String designation;
    private final AuthFacade authFacade;
    private static List<ParameterCategory> parametercategorylist = new ArrayList<>();
    private final static List<String> codeList = new ArrayList<>();
    private final RecordResultStore recordResultStore = new RecordResultStore();
    private final ValidateStore validateStore = new ValidateStore();
    private final SampleRecorderStore sampleRecorderStore = new SampleRecorderStore();
    private final CheckingTestsStore checkingTestsStore = new CheckingTestsStore();

    private static final List<Long> phoneNumbers = new ArrayList<>();
    private static List<ClinicalAnalysisLaboratory> clinical = new ArrayList<>();
    private static ClientStore clientStore;
    private final TestOverviewStore testOverviewStore;
    private static EmployeeStore employeeStore;
    private final importedTestStore importedTestStore;
    private final ClinicalAnalysisLaboratoryStore laboratoryStore;
    private final RegisterTestStore registerTestStore;
    private final static ParameterCategoryStore parameterCategoryStore = new ParameterCategoryStore();
    private final TypeOfTestStore typeOfTestStore;
    private final ParameterStore parameterStore;
    private final ReportStore reportStore;
    private final NHSReportStore NHSReportStore;

    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");
        try {
            clinical = (List<ClinicalAnalysisLaboratory>) Files.decrypt("ClinicalAnalysisLaboratoryStore.ser");
            parametercategorylist = (List<ParameterCategory>) Files.decrypt("ParameterCategoryStore.ser");
        } catch (Exception e) {

        }
        this.designation = designation;
        this.authFacade = new AuthFacade();
        this.clientStore = new ClientStore();
        this.importedTestStore = new importedTestStore();
        importedTestStore.setCompany(this);
        this.testOverviewStore = new TestOverviewStore();
        this.registerTestStore = new RegisterTestStore();
        this.employeeStore = new EmployeeStore();
        this.laboratoryStore = new ClinicalAnalysisLaboratoryStore();
        this.typeOfTestStore = new TypeOfTestStore();
        this.parameterStore = new ParameterStore();
        this.reportStore = new ReportStore();
        this.NHSReportStore = new NHSReportStore();
    }

    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    public TestOverviewStore getTestOverviewStore() {
        return testOverviewStore;
    }

    public static List<ClinicalAnalysisLaboratory> getClinical() {
        return clinical;
    }

    public ClinicalAnalysisLaboratoryStore getLaboratoryStore() {
        return laboratoryStore;
    }

    public importedTestStore getImportedTestStore() {
        return importedTestStore;
    }

    public static ClientStore getClientStore() {
        return clientStore;
    }

    public RegisterTestStore getRegisterTestStore() {
        return registerTestStore;
    }

    public static EmployeeStore getEmployeeStore() {
        return employeeStore;
    }

    public TypeOfTestStore getTypeOfTestStore() {
        return typeOfTestStore;
    }

    public RecordResultStore getRecordResultStore() {
        return recordResultStore;
    }

    public ParameterStore getParameterStore() {
        return parameterStore;
    }

    public static ParameterCategoryStore getParameterCategoryStore() {
        return parameterCategoryStore;
    }

    public static List<ParameterCategory> getParametercategorylist() {
        return parametercategorylist;
    }

    public static List<Long> getPhoneNumbers() {
        return phoneNumbers;
    }

    public ReportStore getReportStore() {
        return reportStore;
    }

    public SampleRecorderStore getSampleRecorderStore() {
        return sampleRecorderStore;
    }

    public CheckingTestsStore getCheckingTestsStore() {
        return checkingTestsStore;
    }

    public static void addCode(String code) {
        codeList.add(code);
    }

    public static List<String> getCodeList() {
        return codeList;
    }

    public ValidateStore getValidateStore() {
        return validateStore;
    }

    public NHSReportStore getNHSReportStore() {
        return NHSReportStore;
    }
}
