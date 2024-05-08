package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.RecordResult;
import app.domain.model.Validate;
import app.domain.shared.Constants;
import app.domain.store.ClientStore;
import app.mappers.dto.TestDetailsResultsDTO;

import java.util.ArrayList;
import java.util.List;

public class CheckingTestsController {
    Company company = App.getInstance().getCompany();
    private List<Client> clientList;

    public CheckingTestsController() {
        // Constructor doesn't need parameters
    }

    /**
     * Returns a list of all the validated clients, using a sorting algorithm
     * depending on their execution time
     * 
     * @return
     */
    public List<Client> getOrderedClients(boolean tin) {
        clientList = new ArrayList<>();
        List<Client> orderedClients;
        getClientsValidated();
        orderedClients = Constants.SORTING_ALGORITHM.orderClients(clientList, tin);
        clientList = new ArrayList<>();
        return orderedClients;
    }

    /**
     * Returns a list of all the results for a client received as a parameter
     * 
     * @param client
     * @return
     */
    public List<TestDetailsResultsDTO> getResultsOfClient(Client client) {
        List<TestDetailsResultsDTO> recordResult = company.getCheckingTestsStore().getResultsOfClient(client.getTin());
        return recordResult;
    }

    /**
     * Fills a list with the all the clients that have validated tests
     */
    public void getClientsValidated() {
        List<Validate> validateList = company.getValidateStore().getValidateList();
        List<Client> clients = ClientStore.getClients();
        for (Validate x : validateList) {
            for (Client y : clients) {
                if (x.getTestRegistered().getCCN() == y.getTin()) {
                    clientList.add(y);
                }
            }
        }
    }
}
