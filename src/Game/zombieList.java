package Game;

import Entity.Zombie;
import util.RandomLocation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class zombieList {
    public List<Zombie> list;
    private GamePanel gamePanel;
    private KeyInput keyInput;

    public zombieList(GamePanel gamePanel, KeyInput keyInput) {
        this.gamePanel = gamePanel;
        this.keyInput = keyInput;
        list = new ArrayList<>();
    }

    public void create() {
        Zombie t = new Zombie(gamePanel, keyInput);
        int valueX = new RandomLocation(0, gamePanel.screenWidth).rand();
        int valueY = new RandomLocation(0, gamePanel.screenHeight).rand();
        t.setLocation(valueX, valueY);
    }

    public void kill(int index) {
        if (index >= list.size()
        || list.get(index).isDied()) return;

        list.get(index).kill();
    }

    public void update() {
        for (Zombie x : list) {
            x.update();
        }
    }

    public void draw(Graphics2D g2d) {
        for (Zombie x : list) {
            x.draw(g2d);
        }
    }
}
