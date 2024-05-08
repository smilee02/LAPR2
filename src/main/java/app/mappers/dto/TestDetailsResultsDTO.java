package app.mappers.dto;

import app.domain.model.RecordResult;

public class TestDetailsResultsDTO {
    private String string;
    private RecordResult recordResult;
    private String test;
    private String resultsForATest;

    /**
     * Constructor
     * @param recordResult
     */
    public TestDetailsResultsDTO(RecordResult recordResult){
        this.recordResult = recordResult;
    }

    /**
     * Returns the string of the register test and record results
     * @return
     */
    public String getString() {
        return String();
    }

    /**
     * Returns only the register test in string form
     * @return
     */
    public String getTest() {
        return testString();
    }

    /**
     * Returns only the record results in a string form
     * @return
     */
    public String getResultsForATest() {
        return resultsOnlyString();
    }

    /**
     * Creation of the results string
     * @return
     */
    public String resultsOnlyString(){
        String a = "";
        for (int i = 0; i<recordResult.getResultList().size();i++){
            a = a + recordResult.getResultList().get(i)+"\n";
        }
        return a;
    }

    /**
     * Creation of the register test and record results string
     * @return
     */
    public String String() {
        String categories = categorites();
        String results = "";
        for (int i = 0; i<recordResult.getResultList().size();i++){
            results = results +  " " +recordResult.getResultList().get(i) + "\n";
        }
        return String.format("Test Type: %s%nCategories:%s%nResults:%n%s",recordResult.getTest().getTypeOfTest().getCode(),categories,results);
    }

    /**
     * Creation of the register test in string form
     * @return
     */
    public String testString() {
        return String.format("Test Type: %s%nCategories:%s%n",recordResult.getTest().getTypeOfTest().getCode(),categorites());
    }

    /**
     * Returns a string of all the categories and respective parameters for a type of test
     * @return
     */
    public String categorites()
    {
        String categories = "";
        for (int i = 0; i<recordResult.getTest().getTypeOfTest().getCategoriesList().size();i++){
            categories = categories + "\n -Category: "+ recordResult.getTest().getTypeOfTest().getCategoriesList().get(i).getShortDescription() +"\n";
            for (int j = 0; j<recordResult.getTest().getTypeOfTest().getCategoriesList().get(i).getParameterList().size();j++){
                categories = categories + "  -Parameter: " + recordResult.getTest().getTypeOfTest().getCategoriesList().get(i).getParameterList().get(j).getCode() + " (" + recordResult.getTest().getTypeOfTest().getCategoriesList().get(i).getParameterList().get(j).getDescription()+")\n";
            }
        }
        return categories;
    }

    /**
     * Returns a record result object
     * @return
     */
    public RecordResult getRecordResult() {
        return recordResult;
    }
}
