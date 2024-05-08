package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ClinicalChemistryTechnologistUI implements Runnable{
    public ClinicalChemistryTechnologistUI()
    {
        /**
         * We dont need any parameter
         */
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Record results of a given test ", new RecordResultsUI()));
        options.add(new MenuItem("List all recorded results for tests",new ListRecordedResultsUI()));


        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nClinical Chemistry Technologist Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}