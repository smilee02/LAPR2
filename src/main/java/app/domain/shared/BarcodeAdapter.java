package app.domain.shared;


import net.sourceforge.barbecue.*;
import net.sourceforge.barbecue.output.OutputException;

import java.io.IOException;

public interface BarcodeAdapter {
    Barcode createUPCA(String data) throws BarcodeException, OutputException, IOException;
}
