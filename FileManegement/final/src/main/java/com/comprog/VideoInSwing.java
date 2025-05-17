package com.comprog;
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventListener;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import java.awt.event.WindowAdapter;

import javax.swing.*;

public class VideoInSwing {
    VideoInSwing() {
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
            "FileManegement\\final\\src\\main\\resources\\LoadingScreen.mp4"
        );

        // Close the frame when playback finishes
        mediaPlayerComponent.mediaPlayer().events().addMediaPlayerEventListener((MediaPlayerEventListener) new uk.co.caprica.vlcj.player.base.events.MediaPlayerEventAdapter() {
            @Override
            public void finished(uk.co.caprica.vlcj.player.base.MediaPlayer mediaPlayer) {
            SwingUtilities.invokeLater(() -> frame.dispose());
            }
            @Override
            public void error(uk.co.caprica.vlcj.player.base.MediaPlayer mediaPlayer) {
            SwingUtilities.invokeLater(() -> frame.dispose());
            }
        });
         mediaPlayerComponent.addComponentListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                frame.dispose(); // Close the video player frame
            new FileLogin();
            }
        });
    }
    public static void main(String[] args) {
       new VideoInSwing();
       
    }
    public void addWindowListener(WindowAdapter windowAdapter) {
       
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addWindowListener'");
    }
}