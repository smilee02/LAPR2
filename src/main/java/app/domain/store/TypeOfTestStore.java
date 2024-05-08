package app.domain.store;

import app.domain.model.Sample;
import app.domain.model.TestAndSamples;
import app.domain.model.TypeOfTest;
import app.domain.shared.Files;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TypeOfTestStore implements Serializable, Files {
    private static List<TypeOfTest> listOfTypeOfTest = new ArrayList<>();
    public TypeOfTestStore(){
        try{
            listOfTypeOfTest = (List<TypeOfTest>) Files.decrypt("TypeOfTestStore.ser");
        }catch(Exception e){

        }

    }



    public List<TypeOfTest> getListOfTypeOfTest() {
        return listOfTypeOfTest;
    }

    public boolean saveTypeTest(TypeOfTest typeOfTest) {
        listOfTypeOfTest.add(typeOfTest);
        Files.encrypt("TypeOfTestStore.ser",listOfTypeOfTest);
        return true;
    }
}
