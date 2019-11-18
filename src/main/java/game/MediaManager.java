package game;

import javafx.scene.media.*;
import javafx.util.Duration;

import java.io.File;

public class MediaManager {
    //use when you want to play music with src = "link/to/file"
    public static final Media SNIPER_GUN_SHOT_FX = new Media(MediaManager.class.getResource("/pm_ag_1_2_abstract_guns_281.mp3").toString());
    public static final Media NORMAL_GUN_SHOT_FX = new Media(MediaManager.class.getResource("/explosive_sound.mp3").toString());
    public static final Media MACHINE_GUN_SHOT_FX = new Media(MediaManager.class.getResource("/gatling_gun_loop.mp3").toString());
    public static final Media FOOTSTEP_FX = new Media(MediaManager.class.getResource("/zapsplat_foley_footsteps_dirt_road_walking_23331.mp3").toString());
//    static MediaPlayer mediaPlayer;
//    public MediaManager(Media media) {
//        mediaPlayer = new MediaPlayer(media);
//    }
//
//    public static void playMusic(String url){
//        Media sound = null;
//        try{
//            sound = new Media(new File(url).toURI().toString());
//            mediaPlayer.setVolume(0.5);
//            mediaPlayer.play();
//        } catch (Exception e){
//            System.err.println("music " + e.toString());
//        }
//    }
//
//    public static void playMusic(Media media, boolean repeat) {
//        MediaPlayer mediaManager = new MediaPlayer(media);
//        mediaManager.setVolume(0.5);
//        if (repeat) {
//            mediaManager.setOnEndOfMedia(new Runnable() {
//                @Override
//                public void run() {
//                    mediaManager.seek(Duration.ZERO);
//                }
//            });
//        }
//        mediaManager.play();
//    }
//
//    public static void playMusic(Media media) {
//        MediaManager.playMusic(media, false);
//    }
}
