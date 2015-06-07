/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Sound // Holds one audio file
{

    private AudioClip song; // Sound player
    private URL songPath; // Sound path

    public Sound(String filename) {
        try {
            //songPath = new URL(getCodeBase(),filename); // Get the Sound URL
            //songPath = new URL(filename); // Get the Sound URL
            //song = Applet.newAudioClip(songPath); // Load the Sound
            //song = Applet.newAudioClip(songPath);
            song = Applet.newAudioClip(Sound.class.getResource(filename));
        } catch (Exception e) {
        } // Satisfy the catch
    }

    public void playSound() {
        song.loop(); // Play 
    }

    public void stopSound() {
        song.stop(); // Stop
    }

    public void playSoundOnce() {
        song.play(); // Play only once
    }
}
