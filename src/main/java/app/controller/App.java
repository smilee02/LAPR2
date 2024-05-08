package app.controller;

import app.domain.model.*;
import app.domain.shared.*;
import app.domain.store.*;
import auth.AuthFacade;
import auth.UserSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

import java.text.SimpleDateFormat;
import java.util.Properties;

public class App {

    private final Company company;
    private final AuthFacade authFacade;

    private App() {
        Properties props = getProperties();
        this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
        this.authFacade = this.company.getAuthFacade();
        bootstrap();
        try {
            getBloodReferenceValues(props);
            getCovidReferenceValues(props);
            getBarcodeAPI(props);
            getSortingAlg(props);
            getDateFormat(props);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println(e);
        }
    }

    public Company getCompany() {
        return this.company;
    }

    public UserSession getCurrentUserSession() {
        return this.authFacade.getCurrentUserSession();
    }

    public boolean doLogin(String email, String pwd) {
        return this.authFacade.doLogin(email, pwd).isLoggedIn();
    }

    public void doLogout() {
        this.authFacade.doLogout();
    }

    private Properties getProperties() {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "Many Labs");

        // Read configured values
        try {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        // Date Format
        NhsShared.setPros(props);
        return props;
    }

    private void bootstrap() {
        this.authFacade.addUserRole(Constants.ROLE_ADMIN, Constants.ROLE_ADMIN);
        this.authFacade.addUserRole(Constants.ROLE_RECEPTIONIST, Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserRole(Constants.ROLE_MEDICAL_LAB_TECHNICIAN, Constants.ROLE_MEDICAL_LAB_TECHNICIAN);
        this.authFacade.addUserRole(Constants.ROLE_CLINICAL_CHEMISTRY_TECHNOLOGIST,
                Constants.ROLE_CLINICAL_CHEMISTRY_TECHNOLOGIST);
        this.authFacade.addUserRole(Constants.ROLE_SPECIALIST_DOCTOR, Constants.ROLE_SPECIALIST_DOCTOR);
        this.authFacade.addUserRole(Constants.ROLE_LABORATORY_COORDINATOR, Constants.ROLE_LABORATORY_COORDINATOR);
        this.authFacade.addUserRole(Constants.ROLE_CLIENT, Constants.ROLE_CLIENT);

        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", "123456", Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("Specialist Doctor", "spdt@lei.sem2.pt", "teste123",
                Constants.ROLE_SPECIALIST_DOCTOR);
        this.authFacade.addUserWithRole("Receptionist", "recep@lei.sem2.pt", "123412", Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserWithRole("Main Clinical Chemistry Technologist", "cct@lei.sem2.pt", "121212",
                Constants.ROLE_CLINICAL_CHEMISTRY_TECHNOLOGIST);
        this.authFacade.addUserWithRole("Lab Coordinator", "lb@lei.sem2.pt", "123123",
                Constants.ROLE_LABORATORY_COORDINATOR);
        this.authFacade.addUserWithRole("Medical Lab Technician", "mlt@lei.sem2.pt", "321321",
                Constants.ROLE_MEDICAL_LAB_TECHNICIAN);
        this.authFacade.addUserWithRole("Client", "user@user.com", "123458", Constants.ROLE_CLIENT);

        // Register Clinical Analysis Laboratory

        // Blood
        // Test--------------------------------------------------------------------------------
        // Hemogram
        // Category-------------------------------------------------------------------------
        ParameterCategory hemogram = new ParameterCategory("Hemogram", "Hemogram");
        ParameterCategoryStore parameterCategoryStore = new ParameterCategoryStore();
        parameterCategoryStore.SaveParameterCategory(hemogram);
        Parameter redBloodCells = new Parameter("WBC00", "WBC", "White Cell Count");
        company.getParameterStore().saveParameter(redBloodCells);
        hemogram.addParameter(redBloodCells);
        Parameter whiteBloodCells = new Parameter("RBC00", "RBC", "Red Blood Count");
        company.getParameterStore().saveParameter(whiteBloodCells);
        hemogram.addParameter(whiteBloodCells);
        Parameter platelets = new Parameter("PLT00", "PLT", "Platelet Count");
        company.getParameterStore().saveParameter(platelets);
        hemogram.addParameter(platelets);
        Parameter hemoglobin = new Parameter("HB000", "HB", "Hemoglobin");
        company.getParameterStore().saveParameter(hemoglobin);
        hemogram.addParameter(hemoglobin);
        TypeOfTest blood = new TypeOfTest("Blood", "Blood", "Sering");
        List<TypeOfTest> typeOfTestList = new ArrayList<>();
        typeOfTestList.add(blood);
        company.getLaboratoryStore().getClinical().add(new ClinicalAnalysisLaboratory("abcd1", "lab1", "rua joao 1",
                Long.parseLong("12345678912"), Long.parseLong("1234567891"), typeOfTestList));
        blood.getCategoriesList().add(hemogram);
        // Cholesterol-------------------------------------------------------------------------------
        ParameterCategory cholesterol = new ParameterCategory("Cholesterol", "Cholesterol");
        parameterCategoryStore.SaveParameterCategory(cholesterol);
        Parameter hdl00 = new Parameter("HDL00", "HDL", "High-density lipoprotein");
        company.getParameterStore().saveParameter(hdl00);
        cholesterol.addParameter(hdl00); // adds parameter to category
        blood.getCategoriesList().add(cholesterol); // adds category to the blood test
        company.getTypeOfTestStore().getListOfTypeOfTest().add(blood); // adds the blood test to the system
        // Covid
        // Test---------------------------------------------------------------------------------
        ParameterCategory result = new ParameterCategory("Covid", "Covid");
        parameterCategoryStore.SaveParameterCategory(result);
        Parameter igGan = new Parameter("IgGAN", "IgGAN", "IgGAN antibodies");
        company.getParameterStore().saveParameter(igGan);
        result.addParameter(igGan);
        TypeOfTest covid = new TypeOfTest("Covid", "Covid", "Swab");
        covid.getCategoriesList().add(result);
        company.getTypeOfTestStore().getListOfTypeOfTest().add(covid);
        List<TypeOfTest> typeOfTestList1 = new ArrayList<>();
        typeOfTestList1.add(blood);
        typeOfTestList1.add(covid);
        Company.getClinical().add(new ClinicalAnalysisLaboratory("001DO", "lab1", "rua 1",
                Long.parseLong("12345613241"), Long.parseLong("1241425152"), typeOfTestList1));
        Company.getClinical().add(new ClinicalAnalysisLaboratory("001LN", "lab2", "rua 2",
                Long.parseLong("12345613252"), Long.parseLong("1241425163"), typeOfTestList1));
        Company.getClinical().add(new ClinicalAnalysisLaboratory("001LR", "lab3", "rua 3",
                Long.parseLong("12345613263"), Long.parseLong("1241425174"), typeOfTestList1));
        Company.getClinical().add(new ClinicalAnalysisLaboratory("001MA", "lab4", "rua 4",
                Long.parseLong("12345613274"), Long.parseLong("1241425185"), typeOfTestList1));
        Company.getClinical().add(new ClinicalAnalysisLaboratory("001SO", "lab5", "rua 5",
                Long.parseLong("12345613285"), Long.parseLong("1241425196"), typeOfTestList1));
        Company.getClinical().add(new ClinicalAnalysisLaboratory("001WA", "lab6", "rua 6",
                Long.parseLong("12345613296"), Long.parseLong("1241425109"), typeOfTestList1));

        // Employees in the System
        /* client */
        // ClientStore.getClients().add(new Client("Rui Pedro da Silva António Gusmão",
        // Long.parseLong("1234123412341234"), Long.parseLong("1234123412"),
        // "23/9/1998", Long.parseLong("1234123412"), Long.parseLong("1234123412"),
        // "user@user.com", "Rua das Flores"));
        /* admin */
        Company.getEmployeeStore().getEmployees().add(new Employee("Amelia Baxter Handerson", "admin@lei.sem2.pt",
                Long.parseLong("44998877661"), "Rua Sousa Tavares", Constants.ROLE_ADMIN, 1111));
        /* recep */
        Company.getEmployeeStore().getEmployees().add(new Employee("Steve Peterson", "recep@lei.sem2.pt",
                Long.parseLong("44934455662"), "Rua Guerra Hector", Constants.ROLE_RECEPTIONIST, 1112));
        /* clinical chemistry technologist */
        Company.getEmployeeStore().getEmployees()
                .add(new Employee("Gervásio Paixão", "cct@lei.sem2.pt", Long.parseLong("46731297342"),
                        "Rua André Arrábida", Constants.ROLE_CLINICAL_CHEMISTRY_TECHNOLOGIST, 1113));
        /* lab coordinator */
        Company.getEmployeeStore().getEmployees().add(new Employee("Richard Richardson", "lb@lei.sem2.pt",
                Long.parseLong("4874249601"), "Avenida da Lamentação", Constants.ROLE_LABORATORY_COORDINATOR, 1114));
        /* medical lab technician */
        Company.getEmployeeStore().getEmployees()
                .add(new Employee("Francisco Francisco Francisco Castro", "mlt@lei.sem2.pt",
                        Long.parseLong("4763410909"), "Praça da Alegria", Constants.ROLE_MEDICAL_LAB_TECHNICIAN, 1115));
        /* specialist doctor */
        Company.getEmployeeStore().getEmployees().add(new Employee("Agapito Silva de Sousa", "spdt@lei.sem2.pt",
                Long.parseLong("4197567204"), "Rua Pedroso", Constants.ROLE_SPECIALIST_DOCTOR, 1116, 478648));

    }

    // Extracted from
    // https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static App singleton = null;

    public static App getInstance() {
        if (singleton == null) {
            synchronized (App.class) {
                singleton = new App();
            }
        }
        return singleton;
    }

    public static void getDateFormat(Properties props) {
        Constants.SIMPLE_DATE_FORMAT = new SimpleDateFormat(props.getProperty("dataConversion"));
    }

    public static void getBloodReferenceValues(Properties props)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String module = props.getProperty("bloodReferenceValuesAPI");
        Class<?> oClass = Class.forName(module);
        Constants.BLOOD_MODULE = (ExternalModule) oClass.newInstance();
    }

    public static void getCovidReferenceValues(Properties props)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String module = props.getProperty("covidReferenceValuesAPI");
        Class<?> oClass = Class.forName(module);
        Constants.COVID_MODULE = (ExternalModule) oClass.newInstance();
    }

    public static void getBarcodeAPI(Properties props)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String module = props.getProperty("barcodeAPI");
        Class<?> oClass = Class.forName(module);
        Constants.BARCODE_MODULE = (BarcodeAdapter) oClass.newInstance();
    }

    public static void getSortingAlg(Properties props)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String alg1 = props.getProperty("mergeSortIterative");
        Class<?> oClass1 = Class.forName(alg1);
        SortingAlgorithm sortingAlgorithm1 = (SortingAlgorithm) oClass1.newInstance();
        double elapsedTime1 = ExecutionTimeAlgorithmChecker.mergeOrderTIN();
        String alg2 = props.getProperty("bubbleSort");
        Class<?> oClass2 = Class.forName(alg2);
        SortingAlgorithm sortingAlgorithm2 = (SortingAlgorithm) oClass2.newInstance();
        double elapsedTime2 = ExecutionTimeAlgorithmChecker.bubbleOrderTIN();
        if (elapsedTime1 > elapsedTime2) { // elapsedTime1-->100000 to test bubbleSort
            Constants.SORTING_ALGORITHM = sortingAlgorithm2;
        } else if (elapsedTime1 < elapsedTime2) {// elapsedTime1-->0 to test bubbleSort
            Constants.SORTING_ALGORITHM = sortingAlgorithm1;
        } else {
            Constants.SORTING_ALGORITHM = sortingAlgorithm1;
        }
    }
}
