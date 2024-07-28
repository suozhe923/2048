package Saver;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;



public class User {

    private String username;
    private String password;
    private int[][] numbers;
    private int step;
    private int size = 4;
    private int timeLimit;
    private boolean plants = false;
    public void hasPlants(boolean r){
        plants = r;
    }
    public boolean getPlants(){
        return plants;
    }
    private boolean hasLJ = true;
    public boolean getLJ(){
        return hasLJ;
    }
    public void setHasLJ(boolean r){
        hasLJ = r;
    }

    public User(String username, String password) {
        int X_COUNT = size;
        int Y_COUNT = size;
        Random random = new Random();
        this.username = username;
        this.password = password;
        this.numbers = new int[4][4];
        for (int i = 0; i < X_COUNT; i++) {
            for (int j = 0; j < Y_COUNT; j++) {
                numbers[i][j] = 0;
            }
        }
        int x1 = random.nextInt(X_COUNT);
        int y1 = random.nextInt(Y_COUNT);
        int x2 = random.nextInt(X_COUNT);
        int y2 = random.nextInt(Y_COUNT);
        while (x1 == x2 && y1 == y2) {
            x2 = random.nextInt(X_COUNT);
            y2 = random.nextInt(Y_COUNT);
        }
        numbers[x1][y1] = 2;
        numbers[x2][y2] = 4;
        this.step = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int[][] getNumbers() throws IOException {
        if(numbers.length == size && numbers[0].length == size)
            return numbers;
        else {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Wrong.fxml")));
            Scene wrongScene = new Scene(root);
            Stage wrong = new Stage();
            wrong.setTitle("");
            wrong.setScene(wrongScene);
            wrong.show();
            return null;
        }
    }

    public void setNumbers(int[][] numbers) {
        this.numbers = numbers;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
    public void setTimeLimit(int a){
        timeLimit = a;
    }
    public int getTimeLimit(){
        return timeLimit;
    }
    public void setPlants(boolean r){
        plants = r;
    }
}