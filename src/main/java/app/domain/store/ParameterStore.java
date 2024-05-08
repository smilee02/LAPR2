package app.domain.store;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.shared.Files;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParameterStore implements Serializable, Files {
    private static List<Parameter> listOfParameters = new ArrayList<>();
    public ParameterStore() {
        try{
            listOfParameters = (List<Parameter>) Files.decrypt("ParameterStore.ser");
        }catch(Exception e){

        }

    }



    public boolean saveParameter(Parameter parameter) {
        listOfParameters.add(parameter);
        Files.encrypt("ParameterStore.ser",listOfParameters);
        return true;
    }

    public List<Parameter> getListOfParameters() {//ok
        return listOfParameters;
    }
}
