package app.mappers.dto;

import app.domain.model.RegisterTest;
import app.domain.model.TestParameterResult;
import app.domain.shared.Constants;

import java.util.Date;
import java.util.List;

public class RecordResultDTO {

    private RegisterTest test;
    private List<TestParameterResult> resultList;
    private Date createdAt;

    public RecordResultDTO(RegisterTest test, List<TestParameterResult> resultList, Date createdAt) {
        this.test = test;
        this.resultList = resultList;
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public RegisterTest getTest() {
        return test;
    }

    public List<TestParameterResult> getResultList() {
        return resultList;
    }

    @Override
    public String toString() {
        String results = "";
        for (TestParameterResult x : resultList) {
            results = results + "\n" + x.toString();
        }
        return "\nResults List:\n" + results +
                "\nDate of Test Results Created:" + Constants.SIMPLE_DATE_FORMAT.format(createdAt);
    }

}
