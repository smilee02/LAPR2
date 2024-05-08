package app.domain.shared;

import app.domain.model.Client;

import java.util.List;

public interface SortingAlgorithm {
    List<Client> orderClients(List<Client> clients,boolean tin);

}
