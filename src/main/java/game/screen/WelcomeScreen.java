package game.screen;

import game.GameManager;
import game.GameStage;
import game.MediaManager;
import game.Settings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WelcomeScreen extends Screen {
    public static boolean isLoadData = false;
    // TODO: Preload assets when in menu
    private Group group;
    private MediaPlayer mediaPlayer;
    private final Font brushUp = Font.loadFont(getClass().getResourceAsStream("/iCielBrushUp.otf"), 20);

    public WelcomeScreen() {
        setAndPlayMedia();
        group = new Group();
        addAllElements();
        scene = new Scene(group, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
    }

    private void addAllElements() {
        addBackgroundImg();
        addLogo();
        addStartBtn();
        addLoadButton();
        addQuitButton();
    }

    private void addLogo() {
        Image logoImg = null;
        logoImg = Settings.loadImage("assets/tower_defense_logo.png", 400, 0.0D, true, false);
        ImageView logo = new ImageView(logoImg);
        logo.setLayoutX(300);
        logo.setLayoutY(290 - logoImg.getHeight());
        group.getChildren().add(logo);
    }

    private void addBackgroundImg() {
        Image backgroundImg = null;
        backgroundImg = Settings.loadImage("assets/welcome_background.png");
        ImageView background = new ImageView(backgroundImg);
        group.getChildren().add(background);
    }

    private void addStartBtn() {
        Button startButton = new Button("Start Game");
        startButton.setPrefSize(200, 50);
        startButton.setLayoutX(400);
        startButton.setLayoutY(350);
        startButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                startButton.setTextFill(Color.BLUE);
            }
        });
        startButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                startButton.setTextFill(Color.BLACK);
            }
        });
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameStage.signNewScreen(new PlayScreen()); }
        });
        group.getChildren().add(startButton);
    }

    private void addLoadButton() {
        Button loadButton = new Button("Load Game");
        loadButton.setPrefSize(200, 50);
        loadButton.setLayoutX(400);
        loadButton.setLayoutY(400);
        loadButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                loadButton.setTextFill(Color.BLUE);
            }
        });
        loadButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                loadButton.setTextFill(Color.BLACK);
            }
        });
        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                new GameManager().loadData();
                isLoadData = true;
                GameStage.signNewScreen(new PlayScreen());
            }
        });
        group.getChildren().add(loadButton);
    }

    private void addQuitButton() {
        Button quitButton = new Button("Quit");
        quitButton.setPrefSize(200, 50);
        quitButton.setLayoutX(400);
        quitButton.setLayoutY(450);
        quitButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                quitButton.setTextFill(Color.BLUE);
            }
        });
        quitButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                quitButton.setTextFill(Color.BLACK);
            }
        });
        quitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage s = (Stage) group.getScene().getWindow();
                s.close();
            }
        });
        group.getChildren().add(quitButton);
    }
    @Override
    public void clear() {
        mediaPlayer.stop();
        group.getChildren().clear();
        scene = null;
        System.gc();
    }

    private void setAndPlayMedia() {
        mediaPlayer = new MediaPlayer(MediaManager.BACKGROUND_FX);
        mediaPlayer.setVolume(0.05);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
    }
}
