package util;

import javafx.scene.image.Image;

import java.io.File;
//abondoned
public class PlantsMap {
    public static Image getPlants(int i){
        File imageFile;
        Image image;
        switch (i){
            case 0: return null;
            case 1:  imageFile = new File("1.png");
                     image = new Image(imageFile.toURI().toString(),100,100,false,false);
                     return image;
            case 2:  imageFile = new File("2.png");
                     image = new Image(imageFile.toURI().toString(),100,100,false,false);
                      return image;
            case 4:  imageFile = new File("4.png");
                image = new Image(imageFile.toURI().toString(),100,100,false,false);
                return image;
            case 8:  imageFile = new File("8.png");
                image = new Image(imageFile.toURI().toString(),100,100,false,false);
                return image;
            case 16:  imageFile = new File("16.png");
                image = new Image(imageFile.toURI().toString(),100,100,false,false);
                return image;
            case 32:  imageFile = new File("32.png");
                image = new Image(imageFile.toURI().toString(),100,100,false,false);
                return image;
            case 64:  imageFile = new File("64.png");
                image = new Image(imageFile.toURI().toString(),100,100,false,false);
                return image;
            case 128:  imageFile = new File("128.png");
                image = new Image(imageFile.toURI().toString(),100,100,false,false);
                return image;
            case 256:  imageFile = new File("256.png");
                image = new Image(imageFile.toURI().toString(),100,100,false,false);
                return image;
            case 512:  imageFile = new File("512.png");
                image = new Image(imageFile.toURI().toString(),100,100,false,false);
                return image;
            case 1024:  imageFile = new File("1024.png");
                image = new Image(imageFile.toURI().toString(),100,100,false,false);
                return image;
            case 2048:  imageFile = new File("2048.png");
                image = new Image(imageFile.toURI().toString(),100,100,false,false);
                return image;
            case 4096:  imageFile = new File("4096.png");
                image = new Image(imageFile.toURI().toString(),100,100,false,false);
                return image;
            default:  imageFile = new File("4096.png");
                image = new Image(imageFile.toURI().toString(),100,100,false,false);
                return image;


        }
    }
}
