import java.nio.ByteBuffer;

import com.sun.webkit.dom.CSSFontFaceRuleImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JavaFXTest extends Application
{
    private static final int RECT_WIDTH = 25;
    private static final int RECT_HEIGHT = 25;

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        Canvas canvas = new Canvas(400, 200);
        // Get the graphics context of the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Set line width
        gc.setLineWidth(2.0);

        // Write custom pixels to create a pattern
        writePixels(gc);

        // Create the Pane
        Pane root = new Pane();
        // Set the Style-properties of the Pane
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        // Add the Canvas to the Pane
        root.getChildren().add(canvas);
        // Create the Scene
        Scene scene = new Scene(root);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("Writing Pixels on a Canvas");
        // Display the Stage
        stage.show();
    }

    private void writePixels(GraphicsContext gc)
    {
        // Define properties of the Image
        int spacing = 5;
        int imageWidth = 300;
        int imageHeight = 100;
        int rows = imageHeight/(RECT_HEIGHT + spacing);
        int columns = imageWidth/(RECT_WIDTH + spacing);

        // Get the Pixels
        byte[] pixels = this.getPixelsData();

        // Create the PixelWriter
        PixelWriter pixelWriter = gc.getPixelWriter();

        // Define the PixelFormat
        PixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteRgbInstance();

        // Write the pixels to the canvas
        for (int y = 0; y < rows; y++)
        {
            for (int x = 0; x < columns; x++)
            {
                int xPos = 50 + x * (RECT_WIDTH + spacing);
                int yPos = 50 + y * (RECT_HEIGHT + spacing);
                pixelWriter.setPixels(xPos, yPos, RECT_WIDTH, RECT_HEIGHT,
                        pixelFormat, pixels, 0, RECT_WIDTH * 3);
            }
        }
    }

    private byte[] getPixelsData()
    {
        // Create the Array
        byte[] pixels = new byte[RECT_WIDTH * RECT_HEIGHT * 3];
        // Set the ration
        double ratio = 1.0 * RECT_HEIGHT/RECT_WIDTH;
        // Generate pixel data
        for (int y = 0; y < RECT_HEIGHT; y++)
        {
            for (int x = 0; x < RECT_WIDTH; x++)
            {
                int i = y * RECT_WIDTH * 3 + x * 3;
                if (x <= y/ratio)
                {
                    pixels[i] = -1;
                    pixels[i+1] = 1;
                    pixels[i+2] = 0;
                }
                else
                {
                    pixels[i] = 1;
                    pixels[i+1] = 1;
                    pixels[i+2] = 0;
                }
            }
        }
        for (int i = 0; i < RECT_HEIGHT; i++) {
            System.out.print(pixels[i] + " ");
        }

        // Return the Pixels
        return pixels;
    }
}