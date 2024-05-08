package app.ui.gui;

public class User {
    private String colClient;
    private String colTest;
    public User(String colClient,String colTest){
        this.colClient=colClient;
        this.colTest=colTest;
    }

    public String getColClient() {
        return colClient;
    }

    public String getColTest() {
        return colTest;
    }

    public void setColClient(String colClient) {
        this.colClient = colClient;
    }

    public void setColTest(String colTest) {
        this.colTest = colTest;
    }
}
