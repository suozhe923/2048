package util;

import javafx.scene.image.Image;

import java.io.File;

public class Zombie {
    public static Image getZombies(int i) {
        File imageFile;
        Image image;
        switch (i) {
            case 0:
                return null;
            case 1:
                imageFile = new File("高坚果.gif");
                image = new Image(imageFile.toURI().toString(), 100, 100, false, false);
                return image;
            case 2:
                imageFile = new File("jiangshi.gif");
                image = new Image(imageFile.toURI().toString(), 100, 100, false, false);
                return image;
            case 4:
                imageFile = new File("qizhi.gif");
                image = new Image(imageFile.toURI().toString(), 100, 100, false, false);
                return image;
            case 8:
                imageFile = new File("luzhang.gif");
                image = new Image(imageFile.toURI().toString(), 100, 100, false, false);
                return image;
            case 16:
                imageFile = new File("menban.gif");
                image = new Image(imageFile.toURI().toString(), 100, 100, false, false);
                return image;
            case 32:
                imageFile = new File("tietong.gif");
                image = new Image(imageFile.toURI().toString(), 100, 100, false, false);
                return image;
            case 64:
                imageFile = new File("tietongyazi.gif");
                image = new Image(imageFile.toURI().toString(), 100, 100, false, false);
                return image;
            case 128:
                imageFile = new File("kuanggong.gif");
                image = new Image(imageFile.toURI().toString(), 100, 100, false, false);
                return image;
            case 256:
                imageFile = new File("xueren.gif");
                image = new Image(imageFile.toURI().toString(), 100, 100, false, false);
                return image;
            case 512:
                imageFile = new File("ganlanqiu.gif");
                image = new Image(imageFile.toURI().toString(), 100, 100, false, false);
                return image;
            case 1024:
                imageFile = new File("juren.gif");
                image = new Image(imageFile.toURI().toString(), 100, 100, false, false);
                return image;
            case 2048:
                imageFile = new File("hongjuren.gif");
                image = new Image(imageFile.toURI().toString(), 100, 100, false, false);
                return image;
            case 4096:
                imageFile = new File("qiqiu.png");
                image = new Image(imageFile.toURI().toString(), 100, 100, false, false);
                return image;
            default:
                imageFile = new File("4096.png");
                image = new Image(imageFile.toURI().toString(), 100, 100, false, false);
                return image;


        }
    }
}
