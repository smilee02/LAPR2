package app.domain.store;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.ParameterCategory;
import app.domain.shared.Files;

import java.io.Serializable;
import java.util.List;

public class ParameterCategoryStore implements Serializable, Files {
    private String code;
    private String shortDescription;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    @Override
    public String toString() {
    return "Company{" +
                "code='" + code + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                '}';
    }

    public ParameterCategoryStore() {
        /**
         * We dont need any parameter
         */
    }

    public boolean SaveParameterCategory(ParameterCategory PC) {
        if(ValidateParameterCategory(PC)){
            Company.getParametercategorylist().add(PC);
            Files.encrypt("ParameterCategoryStore.ser",Company.getParametercategorylist());
            return true;
        }
        else return false;
    }

    public boolean ValidateParameterCategory(ParameterCategory PC) {
        if (PC == null) {
            return false;
        }
        if (Company.getParametercategorylist().contains(PC)) {
            return false;

        } else return true;
    }
}