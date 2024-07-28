package Saver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserManager {
    private Map<String,User> usersMap;
    public Map<String, User> getUsersMap() {
        return usersMap;
    }

    public UserManager() throws IOException {
        usersMap = new HashMap<>();
        loadUserData();
    }

    private void loadUserData() throws IOException {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader("usersMap.json");
            Type userType = new TypeToken<HashMap<String, User>>(){}.getType();
            this.usersMap = gson.fromJson(reader, userType);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setUsersMap(Map<String, User> usersMap) {
        this.usersMap = usersMap;
    }

    public void saveUserData() {
        Gson gson = new Gson();
        String json = gson.toJson(usersMap);
        try (PrintWriter writer = new PrintWriter("usersMap.json")) {
            writer.write(json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public boolean registerUser(String username, String password, String confirm) {
        for (String key : usersMap.keySet()) {
            if (usersMap.get(key).getUsername().equals(username)) {
                return false;
            }
        }
        if (!password.equals(confirm))
            return false;
        usersMap.put(username,new User(username, password));
        saveUserData();
        return true;
    }
    public boolean ifAccount(String username){
        for (String key : usersMap.keySet()) {
            if (usersMap.get(key).getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public boolean authenticateUser(String username, String password) throws IOException {
        for (String key : usersMap.keySet()) {
            if (usersMap.get(key).getUsername().equals(username) && usersMap.get(key).getPassword().equals(password)){
                return true;
            }
        }

        return false;
    }
}
