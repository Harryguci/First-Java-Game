package Game;

import Entity.Player;
import Entity.Zombie;
import Entity.gui.GButton;
import util.RandomLocation;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.util.Scanner;

public class GamePanel extends JPanel implements Runnable {

    private final byte FPS = 60;
    private final int originalTitleSize = 16;
    private final int scale = 3;
    public final int titleSize = scale * originalTitleSize;
    private final int maxScreenCol = 20;
    private final int maxScreenRow = 12;
    public final int screenWidth = titleSize * maxScreenCol;
    public final int screenHeight = titleSize * maxScreenRow;

    private enum StatusGame {
        START, PLAYING, PAUSE, OVER, TRANSITION,
    }

    private StatusGame statusGame = StatusGame.START;
    public boolean[][] walls;
    private final BufferedImage[] _backgroundImg = new BufferedImage[5];
    public int numBackground = 0;
    public String[] pathBackground =
            {"Background/game_background_1.png", "Background/game_background_2.png", "Background/game_background_3.png", "Background/game_background_4.png"};
    private Thread gameThread;
    private final KeyInput keyInput;
    public MouseInput mouseInput;
    public Player player;
    public Zombie[] zombies = new Zombie[100];
    public Zombie zombieBoss;
    private int countZombie;
    public String[] mapName = {"map3.txt"};
    public int numMap = 0;
    private int scores = 0;
    private final GButton[] _buttons;
    private int delayTransition = 500; // about 5 seconds

    public GamePanel() {
        // [ Default Game Project Setting ]
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        keyInput = new KeyInput();
        mouseInput = new MouseInput();
        _buttons = new GButton[10];

        initButtons(); // set value for each button.

        // [ END OF : Default Game Project Setting ]

        //  Declare Entities
        getBackgroundImg();

        player = new Player(this, keyInput);
        player.setLocation((screenWidth - player.getWidth()) / 2, (screenHeight - player.getHeight()) / 2);

        zombies[0] = new Zombie(this, keyInput, 0);

        for (int i = 1; i <= 5; i++) {
            zombies[i] = new Zombie(zombies[0]);
        }
        resetZombies();

        zombieBoss = new Zombie(this, keyInput, 1);
        zombieBoss.setSize(new Dimension((int) (100 * 1.47) * 3, 300));
        zombieBoss.setDame(20);
        countZombie = 10;

        walls = new boolean[screenHeight][screenWidth];
        //  [END OF] : Declare Entities

        if (!setMap(mapName[numMap])) System.out.println("Map is null");

        addKeyListener(keyInput);
        addMouseListener(mouseInput);

        startGameThread();
        setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void initButtons() {

        _buttons[ButtonConstant.PLAY.ordinal()] = new GButton("PLAY", this);
        _buttons[ButtonConstant.PAUSE.ordinal()] = new GButton("PAUSE", this);

        _buttons[ButtonConstant.PAUSE.ordinal()].setSize(100, 50);
        _buttons[ButtonConstant.PAUSE.ordinal()].setLocation(10, 10);
        _buttons[ButtonConstant.PAUSE.ordinal()].setFont(new Font("Arial", Font.BOLD, 15), 25, 30);

        // UNPAUSE
        _buttons[ButtonConstant.UNPAUSE.ordinal()] = new GButton("Continue", this);
        _buttons[ButtonConstant.UNPAUSE.ordinal()].setSize(100, 50);
        _buttons[ButtonConstant.UNPAUSE.ordinal()].setLocation((screenWidth - _buttons[ButtonConstant.UNPAUSE.ordinal()].getWidth()) / 2,
                (screenHeight - _buttons[ButtonConstant.UNPAUSE.ordinal()].getHeight()) / 2);
        _buttons[ButtonConstant.UNPAUSE.ordinal()].setFont(new Font("Arial", Font.BOLD, 15), 15, 30);
    }

    public void restartGame() {
        player = new Player(this, keyInput);
        player.setLocation((screenWidth - player.getWidth()) / 2, (screenHeight - player.getHeight()) / 2);
        resetZombies();
        scores = 0;
    }

    public void getBackgroundImg() {
        try {
            _backgroundImg[0] = ImageIO.read(new File(System.getProperty("user.dir") + "/src/resources/" + pathBackground[0]));
            _backgroundImg[1] = ImageIO.read(new File(System.getProperty("user.dir") + "/src/resources/" + pathBackground[1]));
            _backgroundImg[2] = ImageIO.read(new File(System.getProperty("user.dir") + "/src/resources/" + pathBackground[2]));
            _backgroundImg[3] = ImageIO.read(new File(System.getProperty("user.dir") + "/src/resources/" + pathBackground[3]));
        } catch (IOException e) {
            System.out.println("Could not read background image files");
        }
    }

    public static void main(String[] args) {

        // Test Read map file.
        GamePanel game = new GamePanel();
        game.setMap("map3.txt");
        for (int i = 0; i < game.maxScreenRow; i++) {
            for (int j = 0; j < game.maxScreenCol; j++) {
                String t = (game.walls[i][j] ? "1" : "0");
                System.out.print(t);
            }
            System.out.println();
        }
    }

    // Increase your score when killed a zomibes
    public void incScore() {
        scores++;

        if (countZombie == 0) {

            numBackground++;
            scores++;

            countZombie = numBackground * 2;

            statusGame = StatusGame.TRANSITION;

            if (numBackground >= 4) numBackground = 0;
            resetZombies();
        }
    }


    @Override
    public void run() {
        // Run game
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

    public void resetZombies() {
        // Create more zombies
        if (countZombie <= 0)
            return;

        for (int i = 0; i <= 5; i++) {
            if (countZombie <= 0)
                return;

            int d = (i % 2 == 0) ? -100 : screenWidth + 50;
            zombies[i].setLocation(d, new RandomLocation(0, screenHeight).rand());
            countZombie--;
        }
    }

    public void updateBackground(int index) {
        numBackground = index;
    }

    public void update() {
        if (statusGame == StatusGame.START) {
            _buttons[ButtonConstant.PLAY.ordinal()].update();
            if (_buttons[ButtonConstant.PLAY.ordinal()].isPressed()) {
                statusGame = StatusGame.PLAYING;
                _buttons[ButtonConstant.PLAY.ordinal()].setPressed(false);
            }
        } else if (statusGame == StatusGame.PLAYING) {
            player.update();

            if (player.isDied()) {
                delayTransition -= 5;

                if (delayTransition <= 0) {
                    delayTransition = 500;
                    statusGame = StatusGame.OVER;
                }
                return;
            }

            if (numBackground == 3) {
                zombieBoss.update();
            } else {
                for (int i = 0; i < 5; i++) {
                    zombies[i].update();
                }
            }

            _buttons[ButtonConstant.PAUSE.ordinal()].update();

            if (keyInput.isESC() || _buttons[ButtonConstant.PAUSE.ordinal()].isPressed()) {
                // [ PAUSE GAME ]
                statusGame = StatusGame.PAUSE;
                _buttons[ButtonConstant.PAUSE.ordinal()].setPressed(false);
            }
        } else if (statusGame == StatusGame.PAUSE) {
            _buttons[ButtonConstant.UNPAUSE.ordinal()].update();
            if (keyInput.isESC() || _buttons[ButtonConstant.UNPAUSE.ordinal()].isPressed()) {
                statusGame = StatusGame.PLAYING;
                _buttons[ButtonConstant.UNPAUSE.ordinal()].setPressed(false);
            }
        } else if (statusGame == StatusGame.TRANSITION) {
            delayTransition--;
            if (delayTransition <= 0
                    || keyInput.isESC()) {
                delayTransition = 500;
                statusGame = StatusGame.PLAYING;
            }
        } else if (statusGame == StatusGame.OVER) {
            _buttons[ButtonConstant.PLAY.ordinal()].update();

            if (_buttons[ButtonConstant.PLAY.ordinal()].isPressed()) {
                statusGame = StatusGame.TRANSITION;
                _buttons[ButtonConstant.PLAY.ordinal()].setPressed(false);
                restartGame();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.drawImage(_backgroundImg[numBackground], 0, 0, screenWidth, screenHeight, this);

        if (statusGame == StatusGame.START) {
            paintStartScreen(g2d);
        } else if (statusGame == StatusGame.PLAYING) {
            paintPlayScreen(g2d);
        } else if (statusGame == StatusGame.PAUSE) {
            paintPauseScreen(g2d);
        } else if (statusGame == StatusGame.TRANSITION) {
            paintMapTransition(g2d);
        } else if (statusGame == StatusGame.OVER) {
            paintGameOver(g2d);
        }


        g2d.dispose();
    }

    public void paintGrid(Graphics2D g2d) {
        Color color = new Color(0, 0, 0, 50);

        for (int y = 0; y < maxScreenRow * titleSize; y += titleSize) {
            for (int x = 0; x < maxScreenCol * titleSize; x += titleSize) {
                g2d.setColor(color);
                g2d.drawRect(x, y, titleSize, titleSize);
            }
        }
    }

    public void paintMap(Graphics2D g2d) {
        if (walls == null) return;
        g2d.setColor(new Color(50, 50, 50, 50));
        for (int r = 0; r < maxScreenRow; r++) {
            for (int cl = 0; cl < maxScreenCol; cl++) {

                if (!walls[r][cl]) continue;
                int x = (cl * titleSize);
                int y = (r * titleSize);
                g2d.fillRect(x, y, titleSize, titleSize);

            }
        }
    }

    public void paintInformation(Graphics2D g2d) {

        // Scores
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRoundRect(screenWidth - 280, 10, 250, 50, 30, 30);

        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setColor(Color.WHITE);
        String str = scores + " Star(s)";
        g2d.drawString(str, screenWidth - 130, 40);

        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        str = player.getHP() + " HP";
        g2d.drawString(str, screenWidth - 250, 40);

        // PAUSE button
        _buttons[ButtonConstant.PAUSE.ordinal()].draw(g2d);
    }

    public void paintStartScreen(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, screenWidth, screenHeight);

        g2d.drawImage(_backgroundImg[numBackground], 0, 0, screenWidth, screenHeight, this);
        g2d.setColor(new Color(0, 0, 0, 100));
        g2d.fillRect(0, 0, screenWidth, screenHeight);

        _buttons[ButtonConstant.PLAY.ordinal()].setContent("PLAY");
        _buttons[ButtonConstant.PLAY.ordinal()].setStringLocation(70, 40);
        _buttons[ButtonConstant.PLAY.ordinal()].draw(g2d);
    }

    public void paintPlayScreen(Graphics2D g2d) {

        // [For development]

        // paintMap(g2d);

        // paintGrid(g2d);

        // END OF [For development]
        paintInformation(g2d);


        if (numBackground == 3) {
            zombieBoss.draw(g2d);
        } else {
            for (int i = 0; i < 5; i++)
                zombies[i].draw(g2d);
        }
        // Draw player
        player.draw(g2d);
    }

    public void paintPauseScreen(Graphics2D g2d) {
        g2d.setColor(new Color(0, 0, 0, 100));
        g2d.fillRect(0, 0, screenWidth, screenHeight);
        _buttons[ButtonConstant.UNPAUSE.ordinal()].draw(g2d);
    }

    public void paintMapTransition(Graphics2D g2d) {

        g2d.setColor(new Color(0, 0, 0, 100));
        g2d.fillRect(0, 0, screenWidth, screenHeight);

        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.RED);
        g2d.drawString("Continue after " + (delayTransition / 50) + "s", (screenWidth - 200) / 2, (screenHeight - 30) / 2);
    }

    public void paintGameOver(Graphics2D g2d) {
        g2d.setColor(new Color(0, 0, 0, 100));
        g2d.fillRect(0, 0, screenWidth, screenHeight);

        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.RED);
        g2d.drawString("Game Over", (screenWidth - 160) / 2, (screenHeight - 30) / 2 - 40);
        g2d.drawString("YOUR SCORES: " + scores, (screenWidth - 250) / 2, (screenHeight - 30) / 2 + 100);
        _buttons[ButtonConstant.PLAY.ordinal()].setContent("AGAIN");
        _buttons[ButtonConstant.PLAY.ordinal()].setStringLocation(55, 40);
        _buttons[ButtonConstant.PLAY.ordinal()].draw(g2d);
    }

    public boolean setMap(String mapName) {
        try {
            File myObj = new File(System.getProperty("user.dir") + "/src/resources/map/" + mapName);
            Scanner myReader = new Scanner(myObj);

            int col, row;
            col = row = 0;
            if (myReader.hasNextLine())
                row = Integer.parseInt(myReader.nextLine());
            if (myReader.hasNextLine())
                col = Integer.parseInt(myReader.nextLine());

            for (int i = 0; i < row; i++) {
                if (!myReader.hasNextLine())
                    return false;

                String data = myReader.nextLine();
                for (int j = 0; j < col; j++) {
                    walls[i][j] = (data.charAt(j) == '#');
                }
            }

            myReader.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }
}
