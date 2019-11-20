package game.screen;

import game.GameStage;
import game.Settings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GameWinningScreen extends Screen {
    private Group group;
    public GameWinningScreen() {
        group = new Group();
        addAllElements();
        scene = new Scene(group, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
        scene.setFill(Color.BLACK);
        scene.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scene.setCursor(Cursor.HAND);
            }
        });
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GameStage.signNewScreen(new WelcomeScreen());
            }
        });
    }

    private void addAllElements() {
        addGameOverImg();
    }

    private void addGameOverImg() {
        Image gameOver = Settings.loadImage("assets/Retina/win_image.jpg");
        ImageView gameOverView = new ImageView(gameOver);
        gameOverView.setLayoutX((1000 - gameOverView.getImage().getWidth())/2);
        gameOverView.setLayoutY((600 - gameOverView.getImage().getHeight())/2);
        group.getChildren().add(gameOverView);
    }

    private void addMenuButton() {
        Button menuBtn = new Button("Menu");
        menuBtn.setPrefSize(200, 50);
        menuBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameStage.signNewScreen(new WelcomeScreen());
            }
        });
        group.getChildren().add(menuBtn);
    }

    @Override
    public void clear() {
        group.getChildren().clear();
        scene = null;
        System.gc();
    }
}
