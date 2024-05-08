package app.mappers;

import app.domain.model.RecordResult;
import app.domain.model.TestParameterResult;
import app.mappers.dto.RecordResultDTO;
import app.mappers.dto.TestParameterResultDTO;

import java.util.ArrayList;
import java.util.List;

public class RecordResultMapper {

    public TestParameterResultDTO toDTO(TestParameterResult testParameterResult) {
        return new TestParameterResultDTO(testParameterResult.getResult(),testParameterResult.getMetric(), testParameterResult.getMinRefValue(), testParameterResult.getMaxRefValue(), testParameterResult.getParameter());
    }

    public List<TestParameterResultDTO> toTestParameterListDTO(List<TestParameterResult> testParameterResultList) {
        List<TestParameterResultDTO> testParameterResultDTOList = new ArrayList<>();

        for (TestParameterResult testParameterResult : testParameterResultList) {
            testParameterResultDTOList.add(this.toDTO(testParameterResult));
        }
        return testParameterResultDTOList;
    }

    public RecordResultDTO toDTO(RecordResult result) {
        return new RecordResultDTO(result.getTest(),result.getResultList(),result.getCreatedAt());
    }

    public List<RecordResultDTO> toRecordResultListDTO(List<RecordResult> resultList) {
        List<RecordResultDTO> resultDTOList = new ArrayList<>();

        for (RecordResult result : resultList) {
           resultDTOList.add(this.toDTO(result));
        }
        return resultDTOList;
    }
}
