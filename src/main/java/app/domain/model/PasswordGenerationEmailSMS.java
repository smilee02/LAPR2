package app.domain.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

public class PasswordGenerationEmailSMS implements Serializable {
    private static final Random random = new Random();
    private final static int length = 10;
    public static String emailClientRegistration(String address){
        String password = passwordGeneration();
        try{
            FileWriter write = new FileWriter("./emailAndSMSMessages.txt", true);
            write.write("\nFrom: noreply@manylabs.pt");
            write.write("\nTo: " + address + "\n\n\n");
            write.write("                       Welcome To ManyLabs   \n\n ");
            write.write("Your registration has been complete. Below are the login credentials.");
            write.write("\n\n\nEmail:  " + address);
            write.write("\nPassword: " + password);
            write.write("\n\n\nThank you for joining the ManyLabs family :)\n\n");
            write.write("---------------------------------------------------------------------");
            write.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return password;

    }

    public static String passwordGeneration() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String password = "";

        for (int i = 0; i < length; i++) {
            int position = random.nextInt(characters.length());
            password = password + characters.charAt(position);
        }
        return password;
    }

    public static boolean notificationSending(String email){
        try{
            FileWriter write = new FileWriter("./emailAndSMSMessages.txt", true);
            write.write("\nFrom: noreply@manylabs.pt");
            write.write("\nTo: " + email + "\n\n\n");
            write.write("The results for the test you realized are already available in the application.\n");
            write.write("-----------------------------------------------------------------------------------\n");
            write.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            System.out.println("Finalized the email sending");
        }
    }
}
