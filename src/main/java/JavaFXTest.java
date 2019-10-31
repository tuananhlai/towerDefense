import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFXTest extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Path path = new Path();
        MoveTo moveTo = new MoveTo(108, 71);
        LineTo line1 = new LineTo(321, 161);
        LineTo line2 = new LineTo(126, 232);
        LineTo line3 = new LineTo(232, 52);
        LineTo line4 = new LineTo(269, 250);
        LineTo line5 = new LineTo(108, 71);

        path.getElements().add(moveTo);
        path.getElements().addAll(line1, line2, line3, line4, line5);
        Group root = new Group(path);
        Scene scene = new Scene(root, 600, 800);
        primaryStage.setTitle("Drawing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
