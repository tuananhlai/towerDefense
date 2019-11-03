import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFXTest extends Application {

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("");

        Rectangle rect = new Rectangle(100, 40, 100, 100);
        rect.setArcHeight(50);
        rect.setArcWidth(50);
        rect.setFill(Color.VIOLET);


        PauseTransition pt = new PauseTransition(Duration.millis(1000));
        FadeTransition ft = new FadeTransition(Duration.millis(2000));
        ft.setFromValue(1.0f);
        ft.setToValue(0.3f);
        ft.setAutoReverse(true);
        TranslateTransition tt = new TranslateTransition(Duration.millis(2000));
        tt.setFromX(-100f);
        tt.setToX(100f);
        tt.setAutoReverse(true);
        RotateTransition rt = new RotateTransition(Duration.millis(2000));
        rt.setByAngle(180f);
        rt.setAutoReverse(true);
        ScaleTransition st = new ScaleTransition(Duration.millis(2000));
        st.setByX(1.5f);
        st.setByY(1.5f);
        st.setAutoReverse(true);

        SequentialTransition seqT = new SequentialTransition(rect, pt, ft, tt, rt,
                st);
        seqT.play();

        root.getChildren().add(rect);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}