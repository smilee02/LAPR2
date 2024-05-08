package app.mappers;

import app.domain.model.Client;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClientMapperTest {

    @Test
    public void toDTO() {
        Client client = new Client("a",1,1,"1",1.0,1.0,"a@g.com","!");
        ClientMapper mapper = new ClientMapper();
        assertNotNull(mapper.toDTO(client));
    }

    @Test
    public void testToDTO() {
        Client client = new Client("a",1,1,"1",1.0,1.0,"a@g.com","!");
        List<Client> clientList = new ArrayList<>();
        clientList.add(client);
        ClientMapper mapper = new ClientMapper();
        assertNotNull(mapper.toDTO(clientList));
    }
}