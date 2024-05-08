package app.domain.shared;

import app.domain.model.Parameter;
import com.example2.EMRefValue;


public interface ExternalModule {
    /**
     * Returns a reference value for a parameter
     * @param parameter parameter
     * @param accessKey to access the external modules
     * @return
     */
    EMRefValue getReferenceValue(Parameter parameter, int accessKey);
    /**
     * Returns a reference value for a parameter
     * @param parameter parameter
     * @param accessKey to access the external modules
     * @param metric inserted metric
     * @return
     */
    EMRefValue getReferenceValue(Parameter parameter, int accessKey,String metric);


}
