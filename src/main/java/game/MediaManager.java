package game;

import javafx.scene.media.*;
import javafx.util.Duration;

import java.io.File;

public class MediaManager {
    //use when you want to play music with src = "link/to/file"
    public static final Media GUN_SHOT_FX = new Media(MediaManager.class.getResource("/pm_ag_1_2_abstract_guns_281.mp3").toString());
    public static final Media FOOTSTEP_FX = new Media(MediaManager.class.getResource("/zapsplat_foley_footsteps_dirt_road_walking_23331.mp3").toString());
    public static void playMusic(String url){
        Media sound = null;
        try{
            sound = new Media(new File(url).toURI().toString());
            MediaPlayer mediaManager = new MediaPlayer(sound);
            mediaManager.setVolume(0.5);
            mediaManager.play();
        } catch (Exception e){
            System.err.println("music " + e.toString());
        }
    }

    public static void playMusic(Media media, boolean repeat) {
        MediaPlayer mediaManager = new MediaPlayer(media);
        mediaManager.setVolume(0.5);
        if (repeat) {
            mediaManager.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    mediaManager.seek(Duration.ZERO);
                }
            });
        }
        mediaManager.play();
    }

    public static void playMusic(Media media) {
        MediaManager.playMusic(media, false);
    }
}
