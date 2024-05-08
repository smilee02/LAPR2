package app.mappers;

import app.domain.model.Validate;
import app.mappers.dto.ValidateDTO;

import java.util.ArrayList;
import java.util.List;

public class ValidateMapper {
    /**
     * Transforms a validate object into a dto
     * 
     * @param validate object received by parameter
     * @return
     */
    public ValidateDTO toDTO(Validate validate) {
        return new ValidateDTO(validate.getTestRegistered(), validate.getTestResults(), validate.getDiagnosis());
    }

    /**
     * Transforms a validate List int a dto List
     * 
     * @param validateList list received by parameter
     * @return
     */
    public List<ValidateDTO> toDTO(List<Validate> validateList) {
        List<ValidateDTO> validateDTOList = new ArrayList<>();

        for (Validate validate : validateList) {
            validateDTOList.add(this.toDTO(validate));
        }
        return validateDTOList;
    }

}
