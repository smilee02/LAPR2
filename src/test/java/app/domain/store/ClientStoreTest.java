package app.domain.store;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Employee;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientStoreTest {
    private static final ClientStore store = new ClientStore();
    static Client client1 = new Client("Andre","Rua",Long.parseLong("1234123412341234"),Long.parseLong("12341234123412"),"23/6/1998",Long.parseLong("1234123412"),Long.parseLong("12341234123"),"r@g.com","a");
    static Client client2 = new Client(null,null,1,1,null,1,1,null,null);

    @Test
    public void createEmployee(){
        Client client = store.createClient("Andre","Rua",Long.parseLong("1234123412341234"),Long.parseLong("12341234123412"),"23/6/1998",Long.parseLong("1234123412"),Long.parseLong("12341234123"),"r@g.com","a");
        assertNotEquals(store.createClient("Andre","Rua",Long.parseLong("1234123412341234"),Long.parseLong("12341234123412"),"23/6/1998",Long.parseLong("1234123412"),Long.parseLong("12341234123"),"r@g.com","a"),client);
        Client client1 = store.createClient("Andre",Long.parseLong("1234123412341234"),Long.parseLong("12341234123412"),"23/6/1998",Long.parseLong("1234123412"),Long.parseLong("12341234123"),"r@g.com","a");
        assertNotEquals(store.createClient("Andre",Long.parseLong("1234123412341234"),Long.parseLong("12341234123412"),"23/6/1998",Long.parseLong("1234123412"),Long.parseLong("12341234123"),"r@g.com","a"),client1);

    }


    @Test
    public void validateClient() {
        Client client  = null;
        assertFalse(store.validateClient(client));
        ClientStore.getClients().add(client2);
        assertTrue(store.validateClient(client1));
        ClientStore.getClients().remove(client2);
    }
    @Test
    public void validateClient2(){
        assertNotNull(client2);
    }
    @Test
    public void saveClient(){
        Client client = null;
        assertFalse(store.saveClient(client));
        assertTrue(store.saveClient(client1));
    }
}
