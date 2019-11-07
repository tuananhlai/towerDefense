package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Score {
    private int score = 0;
    private int hp = 20;
    private Vector2D position;
    private Rectangle rect = new Rectangle();
    private Image[] scoreImages = new Image[10];

    public Score(double x, double y){
        position = new Vector2D(x, y);
        rect = new Rectangle(x, y, Settings.TILE_WIDTH, Settings.TILE_HEIGHT);
    }

    public void render(GraphicsContext gc) {
        //print score
        String scoreStr = Integer.toString(score);
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

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
