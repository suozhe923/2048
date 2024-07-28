package view;

import Saver.User;
import controller.GameController;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class GameScene extends Scene {
    private boolean hasLJ = true;
    private ImageView itemImageView;
    private ImageView itemImageView2;
    private double x_lajiao;
    private double y_lajiao;

    public void setX_lajiao(double x_lajiao) {
        this.x_lajiao = x_lajiao;
    }

    public double getY_lajiao() {
        return y_lajiao;
    }
    private GamePane gamePane;
    private Button restartButton;
    private Button UpButton;
    private Button DownButton;
    private Button LeftButton;
    private Button RightButton;
    private Button Exit;
    private Button Save;
    private Button Stop;
    private GameController controller;
    private Group group;
    private User user;
    private boolean keyEvent = true;
    public GameScene(GamePane gamePane, GameController controller, Group group, Pane root, User user){
        super(root,800,650);
        this.gamePane = gamePane;
        this.user = user;
        this.controller = controller;
        gamePane.requestFocus();
        this.group = group;
        restartButton = new Button("Restart");
        restartButton.setLayoutX(550);
        restartButton.setLayoutY(150);
        restartButton.setMaxWidth(200);
        restartButton.setFont(Font.font("楷体", FontWeight.BLACK, 40));
        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.restartGame();
                gamePane.requestFocus();
                gamePane.reStartTime();
                gamePane.setTimeLabel();
            }
        });
        group.getChildren().add(restartButton);
        Exit = new Button("Exit");
        Exit.setLayoutY(560);
        Exit.setLayoutX(700);
        Exit.setFont(Font.font("楷体",FontWeight.BLACK,20));
        Exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.saveGame(user);
                gamePane.stopTime();
                try {
                    controller.toMode2();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        group.getChildren().add(Exit);
        File up = new File("arrow.png");
        Image up1 = new Image(up.toURI().toString(),75,75,false,false);
        ImageView up2 = new ImageView(up1);
        up2.setRotate(180);
        UpButton = new Button();
        UpButton.setGraphic(up2);
        UpButton.setLayoutX(600);
        UpButton.setLayoutY(265);
        UpButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.moveUp();
                gamePane.requestFocus();
            }
        });
        group.getChildren().add(UpButton);
        File downn = new File("arrow.png");
        Image down11 = new Image(downn.toURI().toString(),75,75,false,false);
        ImageView down22 = new ImageView(down11);
        DownButton = new Button();
        DownButton.setGraphic(down22);
        DownButton.setPrefSize(75,75);
        DownButton.setLayoutX(600);
        DownButton.setLayoutY(350);
        DownButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.moveDown();
                gamePane.requestFocus();
            }
        });
        group.getChildren().add(DownButton);
        File down = new File("arrow.png");
        Image down1 = new Image(down.toURI().toString(),75,75,false,false);
        ImageView down2 = new ImageView(down1);
        down2.setRotate(270);
        RightButton = new Button();
        RightButton.setGraphic(down2);
        RightButton.setPrefSize(75,75);
        RightButton.setLayoutX(680);
        RightButton.setLayoutY(350);

        RightButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.moveRight();
                gamePane.requestFocus();
            }
        });
        group.getChildren().add(RightButton);
        File left = new File("arrow.png");
        Image left1 = new Image(left.toURI().toString(),75,75,false,false);
        ImageView left2 = new ImageView(left1);
        left2.setRotate(90);
        LeftButton = new Button();
        LeftButton.setGraphic(left2);
        LeftButton.setPrefSize(75,75);
        LeftButton.setLayoutX(500);
        LeftButton.setLayoutY(350);
        LeftButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.moveLeft();
                gamePane.requestFocus();
            }
        });
        group.getChildren().add(LeftButton);
        Save = new Button("Save");
        Save.setLayoutX(50);
        Save.setLayoutY(560);
        Save.setMaxWidth(200);
        Save.setFont(Font.font("楷体", FontWeight.BLACK, 40));
        Save.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.saveGame(user);
                gamePane.requestFocus();
            }
        });
        group.getChildren().add(Save);
        this.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            switch (e.getCode().getName()){
                case "Right" -> controller.moveRight();
                case "Left" -> controller.moveLeft();
                case "Up" -> controller.moveUp();
                case "Down" -> controller.moveDown();
            }
        });
    }
    public GameScene(GamePane gamePane, GameController controller, Group group, Pane root, User user,boolean r){
        super(root,950,650);
        File revolution = new File("revolution.png");
        Image revolution1 = new Image(revolution.toURI().toString(),400,100,false,false);
        ImageView revolution2 = new ImageView(revolution1);
        revolution2.toBack();
        revolution2.setX(425);
        revolution2.setY(525);
        group.getChildren().add(revolution2);
        hasLJ = r;
        this.gamePane = gamePane;
        gamePane.setHasLJ(r);
        this.user = user;
        this.controller = controller;
        gamePane.requestFocus();
        this.group = group;
        if (gamePane.getTimeLimit() > 0){
        File imageFile2 = new File("hanBing.png");
        Image image2 = new Image(imageFile2.toURI().toString(),75,95,false,false);
        itemImageView2 = new ImageView(image2);
        itemImageView2.setX(75);
        itemImageView2.setFitWidth(75);
        itemImageView2.setFitHeight(95);
        group.getChildren().add(itemImageView2);
        itemImageView2.setOnMousePressed(event -> {
            gamePane.getTimeline().stop();
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    Platform.runLater(() -> {
                        gamePane.getTimeline().play();
                    });
                }
            };
            timer.schedule(task, 10000);
        });}
        restartButton = new Button("Restart");
        restartButton.setLayoutX(700);
        restartButton.setLayoutY(150);
        restartButton.setMaxWidth(200);
        restartButton.setFont(Font.font("楷体", FontWeight.BLACK, 40));
        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                gamePane.setHasLJ(true);
                hasLJ = true;
                File imageFile = new File("lajiao.png");
                Image image = new Image(imageFile.toURI().toString(), 75, 95, false, false);
                itemImageView = new ImageView(image);
                itemImageView.setFitWidth(75);
                itemImageView.setFitHeight(95);
                group.getChildren().add(itemImageView);
                itemImageView.setOnMousePressed(event2 -> {
                    event2.setDragDetect(true);
                });
                itemImageView.setOnMouseDragged(event2 -> {
                    itemImageView.setLayoutX(event2.getSceneX() - itemImageView.getBoundsInLocal().getWidth() / 2);
                    itemImageView.setLayoutY(event2.getSceneY() - itemImageView.getBoundsInLocal().getHeight() / 2);
                });
                itemImageView.setOnMouseReleased(event2 -> {
                    x_lajiao = event2.getSceneX();
                    y_lajiao = event2.getSceneY();
                    if (lajiaojudge()) {
                        hasLJ = false;
                        gamePane.setHasLJ(false);
                        group.getChildren().remove(itemImageView);
                        File iFile = new File("辣椒爆炸.gif");
                        Image imagelajiao = new Image(iFile.toURI().toString(), 100, 100, false, false);
                        ImageView lajiaoo = new ImageView(imagelajiao);
                        lajiaoo.setX(x_lajiao - 50);
                        lajiaoo.setY(y_lajiao - 50);
                        File iiFile = new File("flame.gif");
                        Image imagefire = new Image(iiFile.toURI().toString(), 100, 100, false, false);
                        ImageView[] fires = new ImageView[8];
                        for (int i = 0; i < 8; i++) {
                            fires[i] = new ImageView(imagefire);
                            fires[i].setY(y_lajiao - 50);
                            fires[i].setX(135 + i * 50);
                        }
                        Timer burn1 = new Timer();
                        TimerTask burnn1 = new TimerTask() {
                            public void run() {
                                Platform.runLater(() -> {
                                    group.getChildren().add(lajiaoo);
                                    Timer burn2 = new Timer();
                                    TimerTask burnn2 = new TimerTask() {
                                        public void run() {
                                            Platform.runLater(() -> {
                                                group.getChildren().remove(lajiaoo);
                                                controller.burn((int) ((2 * y_lajiao - 300) / 210));
                                                gamePane.updateGame();
                                                for (int i = 0; i < 8; i++) {
                                                    group.getChildren().add(fires[i]);
                                                    fires[i].toFront();
                                                }
                                                Timer burn3 = new Timer();
                                                TimerTask burnn3 = new TimerTask() {
                                                    @Override
                                                    public void run() {
                                                        Platform.runLater(() -> {
                                                            for (int i = 0; i < 8; i++) {
                                                                group.getChildren().remove(fires[i]);
                                                            }
                                                        });
                                                    }
                                                };
                                                burn3.schedule(burnn3, 500);
                                            });
                                        }
                                    };
                                    burn2.schedule(burnn2, 500);
                                });
                            }
                        };
                        burn1.schedule(burnn1, 1000);
                    }
                });
                controller.restartGame();
                gamePane.requestFocus();
                gamePane.reStartTime();
                gamePane.setTimeLabel();
            }
        });
        group.getChildren().add(restartButton);
        Exit = new Button("Exit");
        Exit.setLayoutY(560);
        Exit.setLayoutX(850);
        Exit.setFont(Font.font("楷体",FontWeight.BLACK,20));
        Exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.saveGame(user);
                gamePane.stopTime();
                try {
                    controller.toMode2();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        group.getChildren().add(Exit);
        File up = new File("arrow.png");
        Image up1 = new Image(up.toURI().toString(),75,75,false,false);
        ImageView up2 = new ImageView(up1);
        up2.setRotate(180);
        Button UpButton = new Button();
        UpButton.setGraphic(up2);
        UpButton.setLayoutX(750);
        UpButton.setLayoutY(265);
        UpButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.moveUp();
                gamePane.requestFocus();
            }
        });
        group.getChildren().add(UpButton);
        File downn = new File("arrow.png");
        Image down11 = new Image(downn.toURI().toString(),75,75,false,false);
        ImageView down22 = new ImageView(down11);
        DownButton = new Button();
        DownButton.setGraphic(down22);
        DownButton.setPrefSize(75,75);
        DownButton.setLayoutX(750);
        DownButton.setLayoutY(350);
        DownButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.moveDown();
                gamePane.requestFocus();
            }
        });
        group.getChildren().add(DownButton);
        File down = new File("arrow.png");
        Image down1 = new Image(down.toURI().toString(),75,75,false,false);
        ImageView down2 = new ImageView(down1);
        down2.setRotate(270);
        RightButton = new Button();
        RightButton.setGraphic(down2);
        RightButton.setPrefSize(75,75);
        RightButton.setLayoutX(830);
        RightButton.setLayoutY(350);

        RightButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.moveRight();
                gamePane.requestFocus();
            }
        });
        group.getChildren().add(RightButton);
        File left = new File("arrow.png");
        Image left1 = new Image(left.toURI().toString(),75,75,false,false);
        ImageView left2 = new ImageView(left1);
        left2.setRotate(90);
        LeftButton = new Button();
        LeftButton.setGraphic(left2);
        LeftButton.setPrefSize(75,75);
        LeftButton.setLayoutX(650);
        LeftButton.setLayoutY(350);
        LeftButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.moveLeft();
                gamePane.requestFocus();
            }
        });
        group.getChildren().add(LeftButton);
        Save = new Button("Save");
        Save.setLayoutX(50);
        Save.setLayoutY(560);
        Save.setMaxWidth(200);
        Save.setFont(Font.font("楷体", FontWeight.BLACK, 40));
        Save.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.saveGame(user);
                gamePane.requestFocus();
            }
        });
        group.getChildren().add(Save);
        this.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if(keyEvent) {
                switch (e.getCode().getName()) {
                    case "Right" -> controller.moveRight();
                    case "Left" -> controller.moveLeft();
                    case "Up" -> controller.moveUp();
                    case "Down" -> controller.moveDown();
                }
                keyEvent = false;
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    public void run() {
                        Platform.runLater(() -> {
                            keyEvent = true;
                        });
                    }
                };
                timer.schedule(task, 1500);
            }});
        if (hasLJ) {
            File imageFile = new File("lajiao.png");
            Image image = new Image(imageFile.toURI().toString(), 75, 95, false, false);
            itemImageView = new ImageView(image);
            itemImageView.setFitWidth(75);
            itemImageView.setFitHeight(95);
            group.getChildren().add(itemImageView);
            itemImageView.setOnMousePressed(event -> {
                event.setDragDetect(true);
            });
            itemImageView.setOnMouseDragged(event -> {
                itemImageView.setLayoutX(event.getSceneX() - itemImageView.getBoundsInLocal().getWidth() / 2);
                itemImageView.setLayoutY(event.getSceneY() - itemImageView.getBoundsInLocal().getHeight() / 2);
            });
            itemImageView.setOnMouseReleased(event -> {
                x_lajiao = event.getSceneX();
                y_lajiao = event.getSceneY();
                if (lajiaojudge()) {
                    hasLJ = false;
                    gamePane.setHasLJ(false);
                    group.getChildren().remove(itemImageView);
                    File iFile = new File("辣椒爆炸.gif");
                    Image imagelajiao = new Image(iFile.toURI().toString(), 100, 100, false, false);
                    ImageView lajiaoo = new ImageView(imagelajiao);
                    lajiaoo.setX(x_lajiao - 50);
                    lajiaoo.setY(y_lajiao - 50);
                    File iiFile = new File("flame.gif");
                    Image imagefire = new Image(iiFile.toURI().toString(), 100, 100, false, false);
                    ImageView[] fires = new ImageView[8];
                    for (int i = 0; i < 8; i++) {
                        fires[i] = new ImageView(imagefire);
                        fires[i].setY(y_lajiao - 50);
                        fires[i].setX(135 + i * 50);
                    }
                    Timer burn1 = new Timer();
                    TimerTask burnn1 = new TimerTask() {
                        public void run() {
                            Platform.runLater(() -> {
                                group.getChildren().add(lajiaoo);
                                Timer burn2 = new Timer();
                                TimerTask burnn2 = new TimerTask() {
                                    public void run() {
                                        Platform.runLater(() -> {
                                            group.getChildren().remove(lajiaoo);
                                            controller.burn((int) ((2 * y_lajiao - 300) / 210));
                                            gamePane.updateGame();
                                            for (int i = 0; i < 8; i++) {
                                                group.getChildren().add(fires[i]);
                                                fires[i].toFront();
                                            }
                                            Timer burn3 = new Timer();
                                            TimerTask burnn3 = new TimerTask() {
                                                @Override
                                                public void run() {
                                                    Platform.runLater(() -> {
                                                        for (int i = 0; i < 8; i++) {
                                                            group.getChildren().remove(fires[i]);
                                                        }
                                                    });
                                                }
                                            };
                                            burn3.schedule(burnn3, 500);
                                        });
                                    }
                                };
                                burn2.schedule(burnn2, 500);
                            });
                        }
                    };
                    burn1.schedule(burnn1, 1000);
                }
            });
        }
    }

    public GameScene(GamePane gamePane,GameController controller,Group group,Pane root){
        super(root,800,650);
        this.gamePane = gamePane;
        this.controller = controller;
        gamePane.requestFocus();
        this.group = group;
        restartButton = new Button("Restart");
        restartButton.setLayoutX(550);
        restartButton.setLayoutY(150);
        restartButton.setMaxWidth(200);
        restartButton.setFont(Font.font("楷体", FontWeight.BLACK, 40));
        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.restartGame();
                gamePane.requestFocus();
                gamePane.reStartTime();
                gamePane.setTimeLabel();
            }
        });
        group.getChildren().add(restartButton);
        Exit = new Button("Exit");
        Exit.setLayoutY(560);
        Exit.setLayoutX(700);
        Exit.setFont(Font.font("楷体",FontWeight.BLACK,20));
        Exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                gamePane.stopTime();
                try {
                    controller.toMode();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        group.getChildren().add(Exit);
        File up = new File("arrow.png");
        Image up1 = new Image(up.toURI().toString(),75,75,false,false);
        ImageView up2 = new ImageView(up1);
        up2.setRotate(180);
        Button UpButton = new Button();
        UpButton.setGraphic(up2);
        UpButton.setLayoutX(600);
        UpButton.setLayoutY(265);
        UpButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.moveUp();
                gamePane.requestFocus();
            }
        });
        group.getChildren().add(UpButton);
        File downn = new File("arrow.png");
        Image down11 = new Image(downn.toURI().toString(),75,75,false,false);
        ImageView down22 = new ImageView(down11);
        DownButton = new Button();
        DownButton.setGraphic(down22);
        DownButton.setPrefSize(75,75);
        DownButton.setLayoutX(600);
        DownButton.setLayoutY(350);
        DownButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.moveDown();
                gamePane.requestFocus();
            }
        });
        group.getChildren().add(DownButton);
        File down = new File("arrow.png");
        Image down1 = new Image(down.toURI().toString(),75,75,false,false);
        ImageView down2 = new ImageView(down1);
        down2.setRotate(270);
        RightButton = new Button();
        RightButton.setGraphic(down2);
        RightButton.setPrefSize(75,75);
        RightButton.setLayoutX(680);
        RightButton.setLayoutY(350);

        RightButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.moveRight();
                gamePane.requestFocus();
            }
        });
        group.getChildren().add(RightButton);
        File left = new File("arrow.png");
        Image left1 = new Image(left.toURI().toString(),75,75,false,false);
        ImageView left2 = new ImageView(left1);
        left2.setRotate(90);
        LeftButton = new Button();
        LeftButton.setGraphic(left2);
        LeftButton.setPrefSize(75,75);
        LeftButton.setLayoutX(500);
        LeftButton.setLayoutY(350);
        LeftButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.moveLeft();
                gamePane.requestFocus();
            }
        });
        group.getChildren().add(LeftButton);
        this.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            switch (e.getCode().getName()){
                case "Right" -> controller.moveRight();
                case "Left" -> controller.moveLeft();
                case "Up" -> controller.moveUp();
                case "Down" -> controller.moveDown();
            }
        });
    }
    public GameScene(GamePane gamePane,GameController controller,Group group,Pane root,boolean r){
        super(root,950,650);
        File revolution = new File("revolution.png");
        Image revolution1 = new Image(revolution.toURI().toString(),400,100,false,false);
        ImageView revolution2 = new ImageView(revolution1);
        revolution2.setX(425);
        revolution2.setY(525);
        group.getChildren().add(revolution2);
        this.gamePane = gamePane;
        this.controller = controller;
        gamePane.requestFocus();
        this.group = group;
        if (gamePane.getTimeLimit() > 0){
        File imageFile2 = new File("hanBing.png");
        Image image2 = new Image(imageFile2.toURI().toString(),75,95,false,false);
        itemImageView2 = new ImageView(image2);
        itemImageView2.setX(75);
        itemImageView2.setFitWidth(75);
        itemImageView2.setFitHeight(95);
        group.getChildren().add(itemImageView2);
        itemImageView2.setOnMousePressed(event -> {
            gamePane.getTimeline().stop();
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    Platform.runLater(() -> {
                        gamePane.getTimeline().play();
                    });
                }
            };
            timer.schedule(task, 10000);
        });}
        restartButton = new Button("Restart");
        restartButton.setLayoutX(700);
        restartButton.setLayoutY(150);
        restartButton.setMaxWidth(200);
        restartButton.setFont(Font.font("楷体", FontWeight.BLACK, 40));
        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                gamePane.setHasLJ(true);
                hasLJ = true;
                File imageFile = new File("lajiao.png");
                Image image = new Image(imageFile.toURI().toString(), 75, 95, false, false);
                itemImageView = new ImageView(image);
                itemImageView.setFitWidth(75);
                itemImageView.setFitHeight(95);
                group.getChildren().add(itemImageView);
                itemImageView.setOnMousePressed(event2 -> {
                    event2.setDragDetect(true);
                });
                itemImageView.setOnMouseDragged(event2 -> {
                    itemImageView.setLayoutX(event2.getSceneX() - itemImageView.getBoundsInLocal().getWidth() / 2);
                    itemImageView.setLayoutY(event2.getSceneY() - itemImageView.getBoundsInLocal().getHeight() / 2);
                });
                itemImageView.setOnMouseReleased(event2 -> {
                    x_lajiao = event2.getSceneX();
                    y_lajiao = event2.getSceneY();
                    if (lajiaojudge()) {
                        hasLJ = false;
                        gamePane.setHasLJ(false);
                        group.getChildren().remove(itemImageView);
                        File iFile = new File("辣椒爆炸.gif");
                        Image imagelajiao = new Image(iFile.toURI().toString(), 100, 100, false, false);
                        ImageView lajiaoo = new ImageView(imagelajiao);
                        lajiaoo.setX(x_lajiao - 50);
                        lajiaoo.setY(y_lajiao - 50);
                        File iiFile = new File("flame.gif");
                        Image imagefire = new Image(iiFile.toURI().toString(), 100, 100, false, false);
                        ImageView[] fires = new ImageView[8];
                        for (int i = 0; i < 8; i++) {
                            fires[i] = new ImageView(imagefire);
                            fires[i].setY(y_lajiao - 50);
                            fires[i].setX(135 + i * 50);
                        }
                        Timer burn1 = new Timer();
                        TimerTask burnn1 = new TimerTask() {
                            public void run() {
                                Platform.runLater(() -> {
                                    group.getChildren().add(lajiaoo);
                                    Timer burn2 = new Timer();
                                    TimerTask burnn2 = new TimerTask() {
                                        public void run() {
                                            Platform.runLater(() -> {
                                                group.getChildren().remove(lajiaoo);
                                                controller.burn((int) ((2 * y_lajiao - 300) / 210));
                                                gamePane.updateGame();
                                                for (int i = 0; i < 8; i++) {
                                                    group.getChildren().add(fires[i]);
                                                    fires[i].toFront();
                                                }
                                                Timer burn3 = new Timer();
                                                TimerTask burnn3 = new TimerTask() {
                                                    @Override
                                                    public void run() {
                                                        Platform.runLater(() -> {
                                                            for (int i = 0; i < 8; i++) {
                                                                group.getChildren().remove(fires[i]);
                                                            }
                                                        });
                                                    }
                                                };
                                                burn3.schedule(burnn3, 500);
                                            });
                                        }
                                    };
                                    burn2.schedule(burnn2, 500);
                                });
                            }
                        };
                        burn1.schedule(burnn1, 1000);
                    }
                });
                controller.restartGame();
                gamePane.requestFocus();
                gamePane.reStartTime();
                gamePane.setTimeLabel();
            }
        });
        group.getChildren().add(restartButton);
        Exit = new Button("Exit");
        Exit.setLayoutY(560);
        Exit.setLayoutX(850);
        Exit.setFont(Font.font("楷体",FontWeight.BLACK,20));
        Exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                gamePane.stopTime();
                try {
                    controller.toMode();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        group.getChildren().add(Exit);
        File up = new File("arrow.png");
        Image up1 = new Image(up.toURI().toString(),75,75,false,false);
        ImageView up2 = new ImageView(up1);
        up2.setRotate(180);
        UpButton = new Button();
        UpButton.setGraphic(up2);
        UpButton.setLayoutX(750);
        UpButton.setLayoutY(265);
        UpButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.moveUp();
                gamePane.requestFocus();
            }
        });
        group.getChildren().add(UpButton);
        File downn = new File("arrow.png");
        Image down11 = new Image(downn.toURI().toString(),75,75,false,false);
        ImageView down22 = new ImageView(down11);
        DownButton = new Button();
        DownButton.setGraphic(down22);
        DownButton.setPrefSize(75,75);
        DownButton.setLayoutX(750);
        DownButton.setLayoutY(350);
        DownButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.moveDown();
                gamePane.requestFocus();
            }
        });
        group.getChildren().add(DownButton);
        File down = new File("arrow.png");
        Image down1 = new Image(down.toURI().toString(),75,75,false,false);
        ImageView down2 = new ImageView(down1);
        down2.setRotate(270);
        RightButton = new Button();
        RightButton.setGraphic(down2);
        RightButton.setPrefSize(75,75);
        RightButton.setLayoutX(830);
        RightButton.setLayoutY(350);

        RightButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.moveRight();
                gamePane.requestFocus();
            }
        });
        group.getChildren().add(RightButton);
        File left = new File("arrow.png");
        Image left1 = new Image(left.toURI().toString(),75,75,false,false);
        ImageView left2 = new ImageView(left1);
        left2.setRotate(90);
        LeftButton = new Button();
        LeftButton.setGraphic(left2);
        LeftButton.setPrefSize(75,75);
        LeftButton.setLayoutX(650);
        LeftButton.setLayoutY(350);
        LeftButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.moveLeft();
                gamePane.requestFocus();
            }
        });
        group.getChildren().add(LeftButton);

        this.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if(keyEvent) {
                switch (e.getCode().getName()) {
                    case "Right" -> controller.moveRight();
                    case "Left" -> controller.moveLeft();
                    case "Up" -> controller.moveUp();
                    case "Down" -> controller.moveDown();
                }
                keyEvent = false;
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    public void run() {
                        Platform.runLater(() -> {
                            keyEvent = true;
                        });
                    }
                };
                timer.schedule(task, 1500);
            }});
        File imageFile = new File("lajiao.png");
        Image image = new Image(imageFile.toURI().toString(),75,95,false,false);
        itemImageView = new ImageView(image);
        itemImageView.setFitWidth(75);
        itemImageView.setFitHeight(95);
        group.getChildren().add(itemImageView);
        itemImageView.setOnMousePressed(event -> {
            event.setDragDetect(true);
        });
        itemImageView.setOnMouseDragged(event -> {
            itemImageView.setLayoutX(event.getSceneX() - itemImageView.getBoundsInLocal().getWidth() / 2);
            itemImageView.setLayoutY(event.getSceneY() - itemImageView.getBoundsInLocal().getHeight() / 2);
        });
        itemImageView.setOnMouseReleased(event -> {
            x_lajiao = event.getSceneX();
            y_lajiao = event.getSceneY();
            if(lajiaojudge()){
                group.getChildren().remove(itemImageView);
                File iFile = new File("辣椒爆炸.gif");
                Image imagelajiao = new Image(iFile.toURI().toString(),100,100,false,false);
                ImageView lajiaoo = new ImageView(imagelajiao);
                lajiaoo.setX(x_lajiao-50);
                lajiaoo.setY(y_lajiao-50);
                File iiFile = new File("flame.gif");
                Image imagefire = new Image(iiFile.toURI().toString(),100,100,false,false);
                ImageView[] fires = new ImageView[8];
                for (int i = 0; i < 8; i++) {
                    fires[i] = new ImageView(imagefire);
                    fires[i].setY(y_lajiao-50);
                    fires[i].setX(135+i*50);
                }
                Timer burn1 = new Timer();
                TimerTask burnn1 = new TimerTask() {
                    public void run() {
                        Platform.runLater(() -> {
                            group.getChildren().add(lajiaoo);
                            Timer burn2 = new Timer();
                            TimerTask burnn2 = new TimerTask() {
                                public void run() {
                                        Platform.runLater(() -> {
                                            group.getChildren().remove(lajiaoo);
                                            controller.burn((int) ((2*y_lajiao-300)/210));
                                            gamePane.updateGame();
                                            for (int i = 0; i < 8; i++) {
                                                group.getChildren().add(fires[i]);
                                                fires[i].toFront();
                                            }
                                            Timer burn3 = new Timer();
                                            TimerTask burnn3 = new TimerTask() {
                                                @Override
                                                public void run() {
                                                    Platform.runLater(() ->{
                                                        for (int i = 0; i < 8; i++) {
                                                            group.getChildren().remove(fires[i]);
                                                        }
                                                    });
                                                }
                                            };
                                            burn3.schedule(burnn3,500);
                                        });
                                }
                            };
                            burn2.schedule(burnn2, 500);
                        });
                    }
                    };
                 burn1.schedule(burnn1, 1000);
            }
        });

    }
    public GameController getController(){
        return controller;
    }
    public void reStart(){
        controller.restartGame();
        gamePane.requestFocus();
        gamePane.reStartTime();
        gamePane.setTimeLabel();
    }
    public boolean lajiaojudge(){
        boolean judge = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(150+105*i<=x_lajiao&&x_lajiao<=250+105*i&&100+105*j<=y_lajiao&&y_lajiao<=200+105*j){
                    x_lajiao = 200+105*i;
                    y_lajiao = 150+105*j;
                    judge = true;
                    i = 4;
                    j = 4;
                }
            }
        }
        return judge;
    }
}
