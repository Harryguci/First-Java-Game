package bin;

import javax.swing.*;

import Game.GamePanel;
import Game.KeyInput;
import util.ImageReader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TestGamePanel extends JPanel implements Runnable {

    final byte FPS = 60;
    final int originalTitleSize = 16;
    final int scale = 4;
    public final int titleSize = scale * originalTitleSize;
    final int maxScreenCol = 20;
    final int maxScreenRow = 12;
    public final int screenWidth = titleSize * maxScreenCol;
    public final int screenHeight = titleSize * maxScreenRow;
    Thread gameThread;

    final String path = "Background/game_background_1.png";
    BufferedImage _image;

    public TestGamePanel() {

        // [ Default Game Project Setting ]
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        // [ END OF : Default Game Project Setting ]

        try {
            _image = ImageReader.Read(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        startGameThread();
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Test");
        TestGamePanel gpl = new TestGamePanel();

        window.add(gpl, BorderLayout.CENTER);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.pack();
        window.setVisible(true);

        window.setLocation(100, 100);
        window.setVisible(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (1e9) / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {

                // 1. Update: information such as character information
                update();

                // 2. Draw: the screen with updated information
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        //
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.drawImage(_image, 0, 0, screenWidth, screenHeight, this);

        g2d.dispose();
    }
}
