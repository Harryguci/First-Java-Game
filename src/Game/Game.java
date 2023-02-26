package Game;

import javax.swing.JFrame;
import java.awt.*;

public class Game extends JFrame {
    private GamePanel gamePanel;

    // set up The Game window.
    public Game(String title) {
        super(title);
        this.gamePanel = new GamePanel();
        this.add(gamePanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setLocation(0, 500);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        // Run
        Game game = new Game("Game");
    }
}
