package app.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Object Imported Test
 *
 * 
 */
public class importedTest implements Serializable {
    private final Client client;
    private final RecordResult recordResult;

    /**
     * Constructor for a Imported Test
     * 
     * @param client       Client object
     * @param recordResult RecordResult object
     */
    public importedTest(Client client, RecordResult recordResult) {
        this.client = client;
        this.recordResult = recordResult;
    }

    /**
     *
     * @return Client object
     */
    public Client getClient() {
        return client;
    }

    /**
     *
     * @param ccn receives a double
     * @return a string with length 18 filled with original ccn and until 18 length
     *         "0"
     */
    public String convertDouble(double ccn) {
        String check = String.valueOf(ccn);
        for (int i = check.length(); i < 18; i++) {
            check = 0 + "" + check;
        }
        return check;
    }

    /**
     *
     * @return RecordResult object
     */
    public RecordResult getRecordResult() {
        return recordResult;
    }

    /**
     *
     * @return String with importedTest client object organized parameters
     */
    public String toStringClient() {
        String checked[] = convertDouble(client.getCitizenCardNumber()).split("\\.0");
        return String.format("Name : %s %nCCN : %s %nTIN : %.0f %nNHS : %.0f %nAddress : %s", client.getName(),
                checked[0], client.getTin(), client.getNhsNumber(), client.getAddress());
    }

    /**
     *
     * @return String with importedTest record result object organized parameters
     */
    public String toStringRecordResult() {
        return recordResult.toString();
    }
}
