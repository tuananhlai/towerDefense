package game;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;


public class RotateTransitionExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(1000, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        SnapshotParameters snapshotParameters = new SnapshotParameters();
        Image img = new Image("file:assets/towers/towerDefense_tile250.png");
        ImageView imageView = new ImageView(img);
        RotateTransition transition = new RotateTransition();
        transition.setDuration(Duration.millis(1000));
        transition.setNode(imageView);
        transition.setByAngle(90);
        transition.setAutoReverse(false);
        transition.play();
        Button btn = new Button("Turn by 90 degree");
        btn.setLayoutX(100);
        btn.setLayoutY(100);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                transition.setByAngle(90);
                transition.play();
            }
        });
        Group group = new Group(canvas);
        group.getChildren().add(imageView);
        group.getChildren().add(btn);
        Scene scene = new Scene(group, 1000, 800);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}