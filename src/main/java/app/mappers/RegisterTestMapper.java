package app.mappers;

import app.domain.model.RegisterTest;
import app.mappers.dto.RegisterTestdto;

import java.util.ArrayList;
import java.util.List;

/**
 * Register Test Mapper
 *
 * 
 */
public class RegisterTestMapper {
    private final List<RegisterTestdto> registerTestListdto = new ArrayList<>();

    public RegisterTestMapper() {
        /**
         * null
         */
    }

    /**
     * @param registerTest receives as a parameter an Object of type Register Test
     * @return creating an object of type RegisterTestDto
     */
    public RegisterTestdto dto(RegisterTest registerTest) {
        return new RegisterTestdto(registerTest.getParametersList(), registerTest.getCCN(),
                registerTest.getTypeOfTest(), registerTest.getNhs(), registerTest.getTestnumber(),
                registerTest.getLaboratoryId());
    }

    /**
     * @param registerTestList Receives as a parameter a Register Test list
     * @return a list of Register test dtos
     */
    public List<RegisterTestdto> dtoList(List<RegisterTest> registerTestList) {
        for (RegisterTest x : registerTestList) {
            registerTestListdto.add(new RegisterTestdto(x.getParametersList(), x.getCCN(), x.getTypeOfTest(),
                    x.getNhs(), x.getTestnumber(), x.getLaboratoryId()));
        }
        return registerTestListdto;
    }

}
