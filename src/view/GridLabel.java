package view;

import controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GridLabel extends Label {
    private int number;
    public int getNumber() {
        return number;
    }
    private final int gap = 5;

    public GridLabel(int i,int j,double labelSize){
        this.setText(null);
        this.setAlignment(Pos.CENTER);
        this.setPrefHeight(labelSize);
        this.setPrefWidth(labelSize);
        this.setLayoutX(j * (labelSize + gap)+50);
        this.setLayoutY(i * (labelSize + gap)+50);
        this.setFont(Font.font("楷体", FontWeight.BLACK, 40));
        this.setTextFill(Color.WHITE);
    }
    public GridLabel(int i,int j,double labelSize,boolean r){
        this.setText(null);
        this.setAlignment(Pos.CENTER);
        this.setPrefHeight(labelSize);
        this.setPrefWidth(labelSize);
        this.setLayoutX(j * (labelSize + gap)+150);
        this.setLayoutY(i * (labelSize + gap)+100);
        this.setFont(Font.font("楷体", FontWeight.BLACK, 40));
        this.setTextFill(Color.WHITE);
    }
    public void setNumber(int n){
        number = n;
    }
}
