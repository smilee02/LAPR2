package app.domain.shared;

import app.domain.model.Parameter;
import com.example1.ExternalModule3API;
import com.example2.EMRefValue;
import com.example2.ExternalModule2API;

import java.util.Date;

/**
 * Adapter for Blood
 *
 * 
 */
public class BloodAdapter implements ExternalModule {
    static ExternalModule2API externalModule2API = new ExternalModule2API();
    static ExternalModule3API externalModule3API = new ExternalModule3API();

    /**
     * Creates a reference value for a given parameter
     * 
     * @param parameter parameter
     * @param accessKey to access the external modules
     * @return
     */
    @Override
    public EMRefValue getReferenceValue(Parameter parameter, int accessKey) {
        String metric = externalModule3API.usedMetric(parameter.getCode(), accessKey);
        double minReferenceValue = externalModule3API.getMinReferenceValue(parameter.getCode(), accessKey);
        double maxReferenceValue = externalModule3API.getMaxReferenceValue(parameter.getCode(), accessKey);
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
        double minReferenceValue = externalModule3API.getMinReferenceValue(parameter.getCode(), accessKey);
        double maxReferenceValue = externalModule3API.getMaxReferenceValue(parameter.getCode(), accessKey);
        return new EMRefValue(parameter.getCode(), metric, minReferenceValue, maxReferenceValue, new Date());
    }

}
