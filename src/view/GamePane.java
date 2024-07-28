package view;

import controller.LoseController;
import controller.VictoryController;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import model.GridNumber;
import util.ColorMap;
import util.Zombie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class GamePane extends Pane {
    private boolean hasLJ;
    public void setHasLJ(boolean r){
        hasLJ = r;
    }
    public boolean getLJ(){
        return hasLJ;
    }
    private static int goal = 2048;

    public static void setGoal(int g) {
        goal = g;
    }

    private double gap = 5;
    private static int size = 4;

    public static void setSize(int s) {
        size = s;
    }
    public static int getSize(){return size;}

    private double labelSize = 400.0 / size;
    private int steps;
    private final Label stepLabel;
    private Label timeLabel;
    private int timeLimitOrigin = -1;
    private int timeLimit;
    private GridNumber model;
    private final int time = 150;
    private GridLabel[][] labels;
    private static boolean barrier = false;
    public static boolean getBarrier(){
        return barrier;
    }
    public static void setBarrier(boolean r){
        barrier = r;
    }
    private boolean plants = false;
    public void setPlants(boolean r){
        plants = r;
    }
    public boolean getPlants(){return plants;}
    public GridLabel[][] getLabels() {
        return labels;
    }

    private Timeline timeline;
    public Timeline getTimeline(){
        return timeline;
    }
    private Rectangle blow = new Rectangle(200,200);

    public GamePane() {
        this.model = new GridNumber(size);
        setGap(gap);
        setPadding(new Insets(50, 100, 100, 50));
        labels = new GridLabel[size][size];
        if (barrier){
            model.setBarrier();
        }
        timeLabel = new Label("");
        timeLabel.setLayoutX(550);
        timeLabel.setLayoutY(25);
        timeLabel.setFont(Font.font("楷体", FontWeight.BLACK, 30));
        getChildren().add(timeLabel);
        stepLabel = new Label("Start");
        stepLabel.setLayoutX(610);
        stepLabel.setLayoutY(75);
        stepLabel.setFont(Font.font("楷体", FontWeight.BLACK, 40));
        getChildren().add(stepLabel);
    }
    public void startTimeLine(){
        if (timeLimitOrigin != -1) {
            timeLimit = timeLimitOrigin;
            if (timeLimit>0){
                setTimeLabel();
            }
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                timeLimit--;
                if (timeLimit >= 0) {
                    setTimeLabel();
                } else {
                    timeline.stop();
                    if (timeLimit == 0){
                    LoseController l = new LoseController();
                    try {
                        l.getLose();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }}
                }
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }

    public void initialGame() {
        this.steps = 0;
        if (!plants) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    Rectangle rectangle = new Rectangle(labelSize, labelSize);
                    rectangle.setX(j * (labelSize + gap) + 50);
                    rectangle.setY(i * (labelSize + gap) + 50);
                    rectangle.setStroke(Color.BLUE);
                    rectangle.setFill(Color.WHITE);
                    rectangle.toBack();
                    this.getChildren().add(rectangle);
                    labels[i][j] = new GridLabel(i, j, labelSize);
                    this.getChildren().add(labels[i][j]);
                }
            }
        }
        else {
            Rectangle back = new Rectangle(415,415);
            back.setX(150);
            back.setY(100);
            Image imageFile = null;
            try {
                imageFile = new Image(new FileInputStream("屏幕截图 2024-05-26 215723.png"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            ImagePattern Image_Gradient = new ImagePattern(imageFile,150,100,415,415,false);
            back.setFill(Image_Gradient);
            this.getChildren().add(back);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    labels[i][j] = new GridLabel(i, j, 100,true);
                    this.getChildren().add(labels[i][j]);
                    labels[i][j].toFront();
                }
            }

        }
    }
    public void updateGame() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                labels[i][j].setNumber(model.getNumber(i, j));
                if (plants){
                    labels[i][j].setText(null);
                    labels[i][j].setGraphic(new ImageView(Zombie.getZombies(labels[i][j].getNumber())));}
                else {
                    labels[i][j].setGraphic(null);
                if (labels[i][j].getNumber() == 0 ||  labels[i][j].getNumber() == 1){
                    labels[i][j].setText(null);
                } else {
                    labels[i][j].setText(String.valueOf(labels[i][j].getNumber()));
                }
                labels[i][j].setBackground(new Background(new BackgroundFill(ColorMap.getColor(labels[i][j].getNumber()), null, null)));
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (model.getBig()[i][j] == 1) {
                    int x = model.getPlace()[i][j][0];
                    int y = model.getPlace()[i][j][1];
                    ScaleTransition bigger = new ScaleTransition(Duration.millis(200), labels[x][y]);
                    ScaleTransition smaller = new ScaleTransition(Duration.millis(100), labels[x][y]);
                    bigger.setToX(1.2);
                    bigger.setToY(1.2);
                    smaller.setToX(1);
                    smaller.setToY(1);
                    bigger.play();
                    bigger.setOnFinished(e -> {
                        smaller.play();
                    });

                }
            }
        }
    }

    public void moveAni() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int finalI = i;
                int finalJ = j;
                if (model.getPlace()[i][j][0] != -1) {
                    labels[i][j].toFront();
                    TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(time / 1000.0), labels[i][j]);
                    translateTransition.setByX((model.getPlace()[i][j][1] - j) * (labelSize + gap));
                    translateTransition.setByY((model.getPlace()[i][j][0] - i) * (labelSize + gap));
                    translateTransition.setOnFinished(e -> {
                        labels[finalI][finalJ].setTranslateX(0);
                        labels[finalI][finalJ].setTranslateY(0);
                    });
                    translateTransition.play();
                }
            }
        }
    }
    public void doMoveRight() {
        boolean a = this.model.moveRight();
        blow = new Rectangle(200,200);
        blow.setX(0);
        blow.setY(200);
        Image blowfile = null;
        try {
            blowfile = new Image(new FileInputStream("blow.gif"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ImagePattern Image_Gradient = new ImagePattern(blowfile,0,200,200,200,false);
        blow.setFill(Image_Gradient);
        if (a) {
            if(getPlants()) {
                getChildren().add(blow);
                Timer timer1 = new Timer();
                TimerTask task1 = new TimerTask() {
                    public void run() {
                        Platform.runLater(() -> {
                            moveAni2();
                        });
                    }
                };
                timer1.schedule(task1, 10 * time);
            }
            else moveAni2();
        }
    }
    public void doMoveLeft() {
        boolean a = this.model.moveLeft();
        blow = new Rectangle(200,200);
        blow.setX(495);
        blow.setY(200);
        Image blowfile = null;
        try {
            blowfile = new Image(new FileInputStream("blow.gif"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ImagePattern Image_Gradient = new ImagePattern(blowfile,495,200,200,200,false);
        blow.setFill(Image_Gradient);
        blow.setScaleX(-1);
        if (a) {
            if(getPlants()) {
                getChildren().add(blow);
                Timer timer1 = new Timer();
                TimerTask task1 = new TimerTask() {
                    public void run() {
                        Platform.runLater(() -> {
                            moveAni2();
                        });
                    }
                };
                timer1.schedule(task1, 10 * time);
            }
            else moveAni2();
        }

    }

    public void doMoveUp() {
        boolean a = this.model.moveUp();
        blow = new Rectangle(200,200);
        blow.setX(250);
        blow.setY(450);
        Image blowfile = null;
        try {
            blowfile = new Image(new FileInputStream("blow.gif"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ImagePattern Image_Gradient = new ImagePattern(blowfile, 250, 450, 200, 200, false);
        blow.setFill(Image_Gradient);
        blow.setRotate(315);
        if (a) {
            if (getPlants()) {
                getChildren().add(blow);
                Timer timer1 = new Timer();
                TimerTask task1 = new TimerTask() {
                    public void run() {
                        Platform.runLater(() -> {
                            moveAni2();
                        });
                    }
                };
                timer1.schedule(task1, 10 * time);
            } else moveAni2();
        }
    }

    public void doMoveDown() {
        boolean a = this.model.moveDown();
        blow = new Rectangle(200,200);
        blow.setX(250);
        blow.setY(0);
        Image blowfile = null;
        try {
            blowfile = new Image(new FileInputStream("blow.gif"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ImagePattern Image_Gradient = new ImagePattern(blowfile,250,0,200,200,false);
        blow.setFill(Image_Gradient);
        blow.setRotate(135);
        if (a) {
            if(getPlants()) {
                getChildren().add(blow);
                Timer timer1 = new Timer();
                TimerTask task1 = new TimerTask() {
                    public void run() {
                        Platform.runLater(() -> {
                            moveAni2();
                        });
                    }
                };
                timer1.schedule(task1, 10 * time);
            }
            else moveAni2();
        }
    }
    public void afterMove() throws IOException {
        this.steps++;
        setStepLabel();
        int[] r =  model.addSticker();
        int x = r[0];
        int y = r[1];
        labels[x][y].setScaleX(0);
        labels[x][y].setScaleY(0);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.3), labels[x][y]);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
        if (!model.ifLR() && !model.ifUD()) {
            LoseController l = new LoseController();
            l.getLose();
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (model.getNumber(i, j) == goal) {
                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                        public void run() {
                            Platform.runLater(() -> {
                                VictoryController v = new VictoryController();
                                try {
                                    v.getVictory();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                if (timeLimitOrigin > 0){timeline.stop();}
                            });
                        }
                    };
                    timer.schedule(task, time);
                }
            }
        }
    }
    public void setGap(double gap) {
        this.gap = gap;
    }

    public GridNumber getModel() {
        return model;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public void setStepLabel() {
        if (steps == 0) {
            stepLabel.setText("Start");
        } else stepLabel.setText(String.format("Step: %d", this.steps));
    }
    public void reStep() {
        steps = 0;
        setStepLabel();
    }
    public void setTimeLabel() {
        if (timeLimitOrigin>0){
        timeLabel.setText("Countdown: " + timeLimit + "s");}
    }
    public int getTimeLimit() {
        return timeLimit;
    }
    public void setTimeLimitOrigin(int timeLimit){timeLimitOrigin = timeLimit;}
    public void stopTime() {
        if (timeLimitOrigin > 0){
        timeline.stop();}
    }
    public void resetTimeLimit() {
        timeLimit = timeLimitOrigin;
    }
    public void setTimeLimit(int limit) {
        timeLimit = limit;
    }
    public void reStartTime() {
        if (timeLimitOrigin > 0) {
            timeLimit = timeLimitOrigin;
        }
    }
    public void moveAni2(){
        moveAni();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                Platform.runLater(() -> {
                    try {
                        afterMove();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    updateGame();
                    if(getPlants())
                    {getChildren().remove(blow);}
                });
            }
        };
        timer.schedule(task, time);
    }
}