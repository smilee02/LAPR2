package app.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParameterCategory implements Serializable {
    private final String code;
    private final String shortDescription;

    private final List<Parameter> parameterList = new ArrayList<>();

    public ParameterCategory(String code, String shortDescription){
        this.code=code;
        this.shortDescription=shortDescription;
        Company.addCode(code);
    }

    public List<Parameter> getParameterList() {
        return parameterList;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void addParameter(Parameter parameter){
        parameterList.add(parameter);
    }

    @Override
    public String toString() {
        String string = String.format("Category %n Code: %s %n Description: %s %n Parameters: %n ", code, shortDescription);
        for (int i = 0; i < parameterList.size(); i++) {
            string += parameterList.get(i).getDescription() + "\n ";
        }
        return string;
    }
}
