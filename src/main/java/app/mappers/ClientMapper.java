package app.mappers;

import app.domain.model.Client;
import app.domain.model.TestParameterResult;
import app.mappers.dto.ClientDTO;
import app.mappers.dto.TestParameterResultDTO;

import java.util.ArrayList;
import java.util.List;

public class ClientMapper {

    public ClientDTO toDTO(Client client) {
        return new ClientDTO((client));
    }

    public List<ClientDTO> toDTO(List<Client> clientList) {
        List<ClientDTO> clientDTOList = new ArrayList<>();

        for (Client client: clientList) {
            clientDTOList.add(this.toDTO(client));
        }
        return clientDTOList;
    }
}
