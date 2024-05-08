package app.domain.shared;

import app.controller.App;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BarcodeBarbecueAdapter implements BarcodeAdapter{
    @Override
    public Barcode createUPCA(String data) throws BarcodeException {
        Barcode barcode = BarcodeFactory.createUPCA(data);
        barcode.setPreferredBarHeight(100);
        try{
            Path path = Paths.get("sampleBarcodes/");
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BarcodeImageHandler.saveJPEG(barcode, new File("sampleBarcodes/"+data + ".jpg"));
        }catch (OutputException | IOException e){
            e.printStackTrace();
        }
        return barcode;
    }
}
