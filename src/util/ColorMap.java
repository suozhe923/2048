package util;

import javafx.scene.paint.Color;

public class ColorMap {
    public static Color getColor(int i){
        return switch (i) {
            case 0 -> Color.WHITE;
            case 1 -> Color.BLACK;
            case 2 -> Color.color(0.1,0.8,0.9);
            case 4 -> Color.color(0.1,0.5,0.6);
            case 8 -> Color.color(0.3,0.4,0.3);
            case 16 -> Color.color(0.3,0.2,0.8);
            case 32 -> Color.color(0.1,0.2,0.8);
            case 64 -> Color.color(0.5,0.2,0.6);
            case 128 -> Color.color(0.5,0.8,0.6);
            case 256 -> Color.color(0.4,0.4,0.5);
            case 512 -> Color.color(0.3,0.5,0.1);
            case 1024 -> Color.color(0.6,0.2,0.8);
            case 2048 -> Color.color(0.9,0.4,0.6);
            default -> Color.BLACK;
        };
    }
}
