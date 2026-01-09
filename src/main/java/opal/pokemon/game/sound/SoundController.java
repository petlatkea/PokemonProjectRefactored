package main.java.opal.pokemon.game.sound;

import main.java.opal.pokemon.game.screens.overworld.characters.player.Player;
import main.java.opal.pokemon.game.GameController;

/**
 * The SoundController is the ONLY way for other parts of the application to play sounds and music
 *
 * For now, it just delegates everything to Sound, but acts as a wrapper that should make
 * it easier to redesign the Sound-system.
 */
public class SoundController {
    private GameController gameController;

    private Sound sound;

    public SoundController(GameController gameController) {
        this.gameController = gameController;
        this.sound = new Sound(gameController);
    }

    public void playCollisionSound() {
        sound.PlayCollisionSound();
    }

    public void playButtonSound() {
        sound.playButtonSound();
    }

    public void playGrassStep() {
        sound.playGrassStep();
    }

    public void playSound(int i) {
        sound.playSound(i);
    }

    public void play() {
        sound.play();
    }

    public void setFile() {
        sound.setFile();
    }

    public void stopSound() {
        sound.stopSound();
    }

    public void updateFade() {
        sound.updateFade();
    }

    public void updateMusic(Player player) {
        sound.updateMusic(player);
    }

    public void updateMusic() {
        sound.updateMusic();
    }

    public void stopMusic() {
        sound.stopMusic();
    }

    public void machopSound() {
        sound.machopSound();
    }
}
