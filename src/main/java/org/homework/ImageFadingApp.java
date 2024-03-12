package org.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;

public class ImageFadingApp {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Image Fading App");
            frame.setSize(WIDTH, HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ImagePanel imagePanel1 = new ImagePanel(loadImage("https://upload.wikimedia.org/wikipedia/ru/c/ce/Rocket_in_Avengers_Endgame.jpeg?20210607123343"));
            ImagePanel imagePanel2 = new ImagePanel(loadImage("https://73online.ru/pic/upld_80797.png"));

            frame.setLayout(new GridLayout(1, 2));
            frame.add(imagePanel1);
            frame.add(imagePanel2);

            frame.setVisible(true);

            fadeImages(imagePanel1, imagePanel2);
        });
    }

    private static void fadeImages(ImagePanel imagePanel1, ImagePanel imagePanel2) {
        Thread thread1 = new Thread(() -> fadeImage(imagePanel1));
        Thread thread2 = new Thread(() -> fadeImage(imagePanel2));

        thread1.start();
        thread2.start();
    }

    private static void fadeImage(ImagePanel imagePanel) {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int x = random.nextInt(imagePanel.getWidth());
            int y = random.nextInt(imagePanel.getHeight());

            Color backgroundColor = imagePanel.getBackground();
            imagePanel.setPixelColor(x, y, backgroundColor);
            imagePanel.repaint();
        }
    }

    private static BufferedImage loadImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

