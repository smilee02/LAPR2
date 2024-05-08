package app.domain.store;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.RecordResult;
import app.domain.shared.Constants;
import app.domain.shared.Files;
import app.mappers.TestDetailsResultsMapper;
import app.mappers.dto.TestDetailsResultsDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckingTestsStore implements Serializable, Files {

    //Company company = Constants.APP.getCompany();

    public CheckingTestsStore(){
        /*
          Doesn't need any parameters
         */
    }

    public List<TestDetailsResultsDTO> getResultsOfClient(double tin) {
        List<RecordResult> recordResults = new ArrayList<>();
        RecordResult result = null;
        long tif = (long) tin;
        List<RecordResult> recordResultList = RecordResultStore.getTestResultsWithTests();
        for (RecordResult x : recordResultList){
            if (x.getTest().getCCN()==tif){
                recordResults.add(x);
            }
        }
        TestDetailsResultsMapper mapper = new TestDetailsResultsMapper();
        List<TestDetailsResultsDTO> testDetailsResultsDTOS = mapper.toDTO(recordResults);
        return testDetailsResultsDTOS;
    }
}
