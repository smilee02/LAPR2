package app.domain.model;

import java.io.Serializable;

public class TestParameter implements Serializable {
    private Parameter p = null;

    /**
     * Finds the parameter for a given parameter code
     * 
     * @param parameterID parameter code
     * @param company
     * @return the parameter
     */
    public Parameter getParameter(String parameterID, Company company) {
        int size = company.getParameterStore().getListOfParameters().size();
        for (int i = 0; i < size; i++) {
            if (parameterID.equals(company.getParameterStore().getListOfParameters().get(i).getCode())) {
                p = company.getParameterStore().getListOfParameters().get(i);
            }
        }
        return p;
    }
}
