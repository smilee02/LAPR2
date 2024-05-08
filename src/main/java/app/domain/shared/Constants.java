package app.domain.shared;

import app.controller.App;
import app.domain.model.Company;

import java.text.SimpleDateFormat;

public class Constants {

    public Constants() {
        /**
         * We dont need any parameters
         */
    }

    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";
    public static final String ROLE_MEDICAL_LAB_TECHNICIAN = "MEDICLABTECH";
    public static final String ROLE_CLINICAL_CHEMISTRY_TECHNOLOGIST = "CLINICHEMTECH";
    public static final String ROLE_SPECIALIST_DOCTOR = "SPECIALDOC";
    public static final String ROLE_LABORATORY_COORDINATOR = "LABCOORDINATOR";
    public static final String ROLE_CLIENT = "CLIENT";

    public static final String PARAMS_FILENAME = "config.properties";
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";
    public static SimpleDateFormat SIMPLE_DATE_FORMAT;
    public static ExternalModule BLOOD_MODULE;
    public static ExternalModule COVID_MODULE;
    public static BarcodeAdapter BARCODE_MODULE;
    public static App APP = App.getInstance();
    public static Company COMPANY = APP.getCompany();
    public static SortingAlgorithm SORTING_ALGORITHM;
}
