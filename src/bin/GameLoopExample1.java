package bin;

import javax.swing.*;
import java.awt.*;

public class GameLoopExample1 extends JPanel implements Runnable {
    final byte FPS = 60;
    Thread gameThread;

    @Override
    public void run() {
//        boolean isPlay = true;
        double drawInterval = (1e9) / ((double)FPS);
        double nextDrawTime = System.nanoTime() + drawInterval;


        while (gameThread != null) {
            /*
             *  [ Loop Structure ]
             *   1. Update: information such as character information
             *
             *   2. Draw: the screen with updated information
             *
             */

            // [ Update ]
            update();

            // [ Draw ]
            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1e6;
                remainingTime = (remainingTime < 0) ? 0 : remainingTime;

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException("Uncaught", e);
            }
        }
    }
    public void update() {

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // do somethings
    }
}
