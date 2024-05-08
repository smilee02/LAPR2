package app.domain.shared;

import net.sourceforge.barbecue.BarcodeException;
import org.junit.Test;

import static org.junit.Assert.*;

public class BarcodeBarbecueAdapterTest {

    @Test
    public void createUPCA() {
        BarcodeBarbecueAdapter barcodeBarbecueAdapter = new BarcodeBarbecueAdapter();
        try {
            barcodeBarbecueAdapter.createUPCA("11111111111");
        } catch (BarcodeException e) {
            e.printStackTrace();
        }
    }
}