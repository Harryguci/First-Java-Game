package Game;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Game extends JFrame {
    private final GamePanel gamePanel;

    // set up The Game window.
    public Game(String title) {
        super(title);
        this.gamePanel = new GamePanel();
        this.add(gamePanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setLocation(0, 350);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        // Run
        final Game game = new Game("Game");
    }
}
