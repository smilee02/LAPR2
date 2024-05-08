package app.domain.store;

import app.domain.model.Client;
import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.Company;
import app.domain.shared.Files;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores all Clients
 * 
 */
public class ClientStore implements Serializable, Files {
    /**
     * Client Store Constructor but we dont need any parameters
     */

    private static List<Client> clients = new ArrayList<>();

    public ClientStore() {
        try {
            clients = (List<Client>) Files.decrypt("ClientStore.ser");
        } catch (Exception e) {

        }
        /**
         * We dont need any parameter
         */
    }

    /**
     * Create a Client
     * 
     * @param name              Client name
     * @param sex               Client sex
     * @param citizenCardNumber Client citizen card number
     * @param nhsNumber         Client National Healthcare Service number
     * @param birthDate         Client birth date
     * @param tin               Client tax identification number
     * @param phoneNumber       Client phone number
     * @param email             Client email
     * @return
     */
    public Client createClient(String name, String sex, long citizenCardNumber, long nhsNumber, String birthDate,
            long tin, long phoneNumber, String email, String address) {
        Company.getPhoneNumbers().add(phoneNumber);
        return new Client(name, sex, citizenCardNumber, nhsNumber, birthDate, tin, phoneNumber, email, address);
    }

    /**
     * Create a Client
     * 
     * @param name              Client name
     * @param citizenCardNumber Client citizen card number
     * @param nhsNumber         Client National Healthcare Service number
     * @param birthDate         Client birth date
     * @param tin               Client tax identification number
     * @param phoneNumber       Client phone number
     * @param email             Client email
     * @return
     */
    public Client createClient(String name, long citizenCardNumber, long nhsNumber, String birthDate, long tin,
            long phoneNumber, String email, String address) {
        Company.getPhoneNumbers().add(phoneNumber);
        return new Client(name, citizenCardNumber, nhsNumber, birthDate, tin, phoneNumber, email, address);
    }

    public static List<Client> getClients() {
        return clients;
    }

    /**
     * Validates a client if its new or repeated
     * 
     * @param c
     * @return boolean if its new or not the client registered
     */
    public static boolean validateClient(Client c) {
        if (c == null)
            return false;
        return !clients.contains(c);
    }

    /**
     * Save Client if validate true
     * 
     * @param c Client parameter
     * @return
     */
    public static boolean saveClient(Client c) {
        if (!validateClient(c))
            return false;
        else {
            clients.add(c);
            Files.encrypt("ClientStore.ser", clients);
            return true;
        }

    }

    /* Parte Toni */
    public static Client getSpecificClient(int pos) {
        return clients.get(pos);
    }
}
