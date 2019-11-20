package game.screen;

import game.GameField;
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
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MapSelectScreen extends Screen {
    private Group group;
    private MediaPlayer mediaPlayer;
    private int numOfMaps = 3;
    private final Font brushUp = Font.loadFont(getClass().getResourceAsStream("/iCielBrushUp.otf"), 20);
    public MapSelectScreen() {
        setAndPlayMedia();
        group = new Group();
        addAllElements();
        scene = new Scene(group, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
    }

    private void addAllElements() {
        addBackgroundImg();
        addMap("assets/map_1.png", 0);
        addMap("assets/map_2.png", 1);
        addMap("assets/map_3.png", 2);
    }

    private void addBackgroundImg() {
        Image backgroundImg = null;
        backgroundImg = Settings.loadImage("assets/welcome_background.png");
        ImageView background = new ImageView(backgroundImg);
        group.getChildren().add(background);
    }

    private void addMap(String URL, int x) { //x la map thu x
        Image image = null;
        try{
            image = new Image(new FileInputStream(URL));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert image != null;
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(600/numOfMaps, 400/numOfMaps, false, false, false, false ));
        Background background = new Background(backgroundImage);
        Button map = new Button();
        map.setPrefSize(600/numOfMaps, (400/numOfMaps));
        map.setLayoutX(200 +  x*(610/numOfMaps));
        map.setLayoutY(100 + 40*numOfMaps);
        map.setBackground(background);
        //event
        map.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                map.setBorder(new Border(new BorderStroke(Color.LIGHTGREEN,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        map.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                map.setBorder(null);
            }
        });
        map.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameField.mapURL = "assets/tiles/map_" + Integer.toString(x+1) + ".txt";
                GameStage.signNewScreen(new WelcomeScreen()); }
        });
        group.getChildren().add(map);
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
