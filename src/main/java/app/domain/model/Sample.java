package app.domain.model;

import java.io.Serializable;

public class Sample implements Serializable {
    private final String barcode;
    private final int id;

    /**
     * Sample Constructor with its parameters.
     * @param barcode of the sample.
     * @param id position.
     */
    public Sample(String barcode, int id){
        this.barcode = barcode;
        this.id = id;
    }

    /**
     * @return the id of the sample.
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return the barcode of the sample
     */
    public String getBarcode() {
        return barcode;
    }

    @Override
    public String toString() {
        return "Barcode : " + barcode;
    }
}
