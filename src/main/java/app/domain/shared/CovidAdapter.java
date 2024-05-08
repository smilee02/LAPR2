package app.domain.shared;

import app.domain.model.Parameter;
import app.domain.shared.ExternalModule;
import com.example2.EMRefValue;
import com.example3.CovidReferenceValues1API;

import java.util.Date;

/**
 * Adapter for Covid
 *
 * 
 */
public class CovidAdapter implements ExternalModule {
    static CovidReferenceValues1API values1API = new CovidReferenceValues1API();

    /**
     * Creates a reference value for a given parameter
     * 
     * @param parameter parameter
     * @param accessKey to access the external modules
     * @return
     */
    @Override
    public EMRefValue getReferenceValue(Parameter parameter, int accessKey) {
        String metric = values1API.usedMetric(parameter.getCode(), accessKey);
        double maxReferenceValue = values1API.getMaxReferenceValue(parameter.getCode(), accessKey);
        double minReferenceValue = values1API.getMinReferenceValue(parameter.getCode(), accessKey);
        return new EMRefValue(parameter.getCode(), metric, minReferenceValue, maxReferenceValue, new Date());
    }

    /**
     * Creates a reference value for a given parameter
     * 
     * @param parameter parameter
     * @param accessKey to access the external modules
     * @param metric    own metric
     * @return
     */
    @Override
    public EMRefValue getReferenceValue(Parameter parameter, int accessKey, String metric) {
        double maxReferenceValue = values1API.getMaxReferenceValue(parameter.getCode(), accessKey);
        double minReferenceValue = values1API.getMinReferenceValue(parameter.getCode(), accessKey);
        return new EMRefValue(parameter.getCode(), metric, minReferenceValue, maxReferenceValue, new Date());
    }

}