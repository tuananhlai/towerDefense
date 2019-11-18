package game.test;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class RotateSample extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        Rectangle2D rectangle2D = new Rectangle2D(100, 100, 200 , 100);
        Rotate rotate = new Rotate();
        Rectangle rectangle = new Rectangle(100, 100, 200 , 100);
        Rectangle rectangle2 = new Rectangle(150, 150, 200 , 100);
        rectangle2.setFill(Color.BLUE);
        rotate.setPivotX(rectangle.getX());
        rotate.setPivotY(rectangle.getY());
        rotate.setAngle(0);
        rectangle.getTransforms().add(rotate);
        rectangle.setFill(Color.RED);
        group.getChildren().add(rectangle);
        group.getChildren().add(rectangle2);
        System.out.println(rectangle.intersects(rectangle2.getBoundsInLocal()));
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
