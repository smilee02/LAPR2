package app.domain.shared;

import auth.domain.model.User;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface Files {
    static boolean createFolder() {
        try {
            Path path = Paths.get("Database/");
            java.nio.file.Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static boolean file(String fileName) {
        File file = new File("Database/" + fileName);
        try {
            FileOutputStream fos = new FileOutputStream("Database/" + fileName,true);
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    static boolean encrypt(String fileName, Object o) {
        boolean flagValue = createFolder();
        if (flagValue) {
            flagValue = file(fileName);
            if (flagValue) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream("Database/" + fileName);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(o);
                    objectOutputStream.close();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return flagValue;
    }

    static List<?> decrypt(String fileName) {
        List<?> list = new ArrayList<>();
        File file = new File("Database/" + fileName);
        if(file.exists()){
            boolean flagValue = file(fileName);
            if (flagValue) {
                try {
                    FileInputStream fileInputStream = new FileInputStream("Database/" + fileName);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    list = (List<?>) objectInputStream.readObject();
                    objectInputStream.close();
                    fileInputStream.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
    static Set<User> decryptSet(String fileName) {
        Set<User> list = new HashSet<User>();
        File file = new File("Database/" + fileName);
        if(file.exists()){
            boolean flagValue = file(fileName);
            if (flagValue) {
                try {
                    FileInputStream fileInputStream = new FileInputStream("Database/" + fileName);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    list = (Set<User>) objectInputStream.readObject();
                    objectInputStream.close();
                    fileInputStream.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}