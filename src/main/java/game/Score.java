package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Score {
    private Vector2D position;
    private Rectangle rect = new Rectangle();
    private Image[] scoreImages = new Image[10];

    public Score(double x, double y){
        position = new Vector2D(x, y);
        rect = new Rectangle(x, y, Settings.TILE_WIDTH, Settings.TILE_HEIGHT);
    }

    public void render(GraphicsContext gc) {
        //draw background for score and store
        int x = 700;
        int y = 0;
        int width = 100;
        int height = 420;
        gc.setFill(Color.GREENYELLOW);
        gc.fillRect(x, y, width, height);
        gc.setStroke(Color.ANTIQUEWHITE);
        gc.setLineWidth(5);
        gc.strokeLine(x, y, x + width, y);
        gc.strokeLine(x, y, x, y + height);
        gc.strokeLine(x + width, y, x + width, y + height);
        gc.strokeLine(x, y + height, x + width, y + height);
        //print hp
        gc.setFill(Color.RED);
        gc.fillRect(x + 15, y + 350, GameField.HP*4 , 20);
        //print score
        String scoreStr = Integer.toString(GameField.score);
        try{
            scoreImages[0] = new Image(new FileInputStream("assets/Retina/dollarSign.png"));
        }
        catch (Exception e){
            System.err.println(e.toString());
        }
        gc.drawImage(scoreImages[0], rect.getX(), rect.getY(), 50, 50);
        for(int i = 0; i < scoreStr.length(); i++){
            char number = scoreStr.charAt(i);
            try{
                scoreImages[i] = new Image(new FileInputStream("assets/Retina/towerDefense_tile" + number + ".png"));
                gc.drawImage(scoreImages[i], rect.getX() + 50 + i*30, rect.getY(), 30, 50);
            }
            catch (Exception e){
                System.err.println(e.toString());
            }
        }
    }
}
