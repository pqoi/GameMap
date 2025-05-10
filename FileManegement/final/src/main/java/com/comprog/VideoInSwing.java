package com.comprog;

import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;

public class VideoInSwing {
    public static void main(String[] args) {
        // Set VLC native library path (required for Windows)
        System.setProperty("jna.library.path", "C:\\Program Files\\VideoLAN\\VLC");

        // Optional: Discover native libraries
        new NativeDiscovery().discover();

        JFrame frame = new JFrame("VLCJ Video Player");
        EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

        frame.setContentPane(mediaPlayerComponent);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Play video
        mediaPlayerComponent.mediaPlayer().media().play(
            "C:\\Users\\Asus\\Downloads\\LoadingScreen.mp4"
        );
        
    }
}
