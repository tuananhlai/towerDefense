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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WelcomeScreen extends Screen {
    public static boolean isLoadData = false;
    // TODO: Preload assets when in menu
    private Group group;
    public WelcomeScreen() {
        group = new Group();
        addAllElements();
        scene = new Scene(group, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
    }

    private void addAllElements() {
        addBackgroundImg();
        addStartBtn();
        addLoadButton();
        addQuitButton();
    }
    private void addBackgroundImg() {
        Image backgroundImg = null;
        try {
            backgroundImg = new Image(new FileInputStream("assets/Retina/welcome_background_1.png"));
        } catch (FileNotFoundException e) {

        }
        ImageView background = new ImageView(backgroundImg);
        group.getChildren().add(background);
    }

    private void addStartBtn() {
        Button startButton = new Button("Start Game");
        startButton.setPrefSize(200, 50);
        startButton.setLayoutX(400);
        startButton.setLayoutY(200);
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
        loadButton.setLayoutY(250);
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
        quitButton.setLayoutY(300);
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
        group.getChildren().clear();
        scene = null;
        System.gc();
    }
}
