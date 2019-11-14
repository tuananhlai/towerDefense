package game;

import javafx.scene.media.*;

import java.io.File;

public class MediaManager {
    public void playMusic(String url){
        Media sound = null;
        try{
            sound = new Media(new File(url).toURI().toString());
            MediaPlayer mediaManager = new MediaPlayer(sound);
            mediaManager.setVolume(0.5);
            mediaManager.play();
        }catch (Exception e){
            System.err.println("music " + e.toString());
        }
    }
}
