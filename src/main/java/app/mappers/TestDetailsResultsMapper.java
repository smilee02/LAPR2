package app.mappers;

import app.domain.model.RecordResult;
import app.mappers.dto.TestDetailsResultsDTO;

import java.util.ArrayList;
import java.util.List;

public class TestDetailsResultsMapper {

    public List<TestDetailsResultsDTO> toDTO(List<RecordResult> recordResults){
        List<TestDetailsResultsDTO> resultsDTOS = new ArrayList<>();
        for (int i = 0; i<recordResults.size();i++){
            resultsDTOS.add(new TestDetailsResultsDTO(recordResults.get(i)));
        }
        return resultsDTOS;
    }
}
