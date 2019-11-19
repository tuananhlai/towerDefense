package game;

import javafx.scene.media.*;
import javafx.util.Duration;

import java.io.File;

public class MediaManager {
    //use when you want to play music with src = "link/to/file"
    public static final Media SNIPER_GUN_SHOT_FX = new Media(MediaManager.class.getResource("/pm_ag_1_2_abstract_guns_281.mp3").toString());
    public static final Media NORMAL_GUN_SHOT_FX = new Media(MediaManager.class.getResource("/explosive_sound.mp3").toString());
    public static final Media MACHINE_GUN_SHOT_FX = new Media(MediaManager.class.getResource("/gatling_gun_loop.mp3").toString());
    public static final Media BACKGROUND_FX = new Media(MediaManager.class.getResource("/Epic-battle-music-grzegorz-majcherczyk-heroica.mp3").toString());
    public static final Media FOOTSTEP_FX = new Media(MediaManager.class.getResource("/zapsplat_foley_footsteps_dirt_road_walking_23331.mp3").toString());
    private MediaPlayer player;
    public MediaManager(Media media, double volume) {
        player = new MediaPlayer(media);
        player.setVolume(volume);
    }

    private void play(boolean repeat) {
        player.play();
        if (repeat) {
            player.setOnEndOfMedia(() -> player.seek(Duration.ZERO));
        }
    }
    public void playOnce() {
        play(false);
    }

    public void playOnRepeat() {
        play(true);
    }

    public void setMedia(Media media) {
        player = new MediaPlayer(media);
    }
}
