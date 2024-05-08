package app.mappers.dto;

import app.domain.model.Client;

public class ClientDTO {
    public String string;
    private Client client;

    /**
     * Constructor for a Data Transfer Object
     * @param client
     */
    public ClientDTO(Client client){
        this.client = client;
        this.string = getMeClientString();
    }

    /**
     * Returns the toString of the client
     * @return
     */
    public String getString() {
        return string;
    }

    /**
     * Returns the client
     * @return
     */
    public Client getClient() {
        return client;
    }

    public String getMeClientString(){
        String checked[] = convertDouble(client.getCitizenCardNumber()).split("\\.0");
        if (client.getSex() != null) {
            return String.format("%nClient:%nName: %s%nSex: %s%nCitizen Card Number: %s%nNHS Number: %.0f%nBirth Date: %s%nTIN: %.0f%nPhone Number: %.0f%nEmail: %s", client.getName(), client.getSex(), checked[0], client.getNhsNumber(), client.getBirthDate(), client.getTin(), client.getPhoneNumber(), client.getEmail());
        } else {
            return String.format("%nClient:%nName: %s%nCitizen Card Number: %s%nNHS Number: %.0f%nBirth Date: %s%nTIN: %.0f%nPhone Number: %.0f%nEmail: %s", client.getName(), checked[0], client.getNhsNumber(), client.getBirthDate(), client.getTin(), client.getPhoneNumber(), client.getEmail());
        }
    }

    public String convertDouble(double ccn){
        String check = String.valueOf(ccn);
        for(int i=check.length();i<18;i++){
            check = 0+""+check;
        }
        return check;
    }
}
