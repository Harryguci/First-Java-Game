package Entity;

import Game.GamePanel;
import Game.KeyInput;

import javax.swing.JPanel;
import java.awt.*;

public abstract class Entity extends JPanel {
    protected GamePanel gamePanel;
    protected KeyInput keyInput;
    protected int _x, _y, _speed, _width, _height;

    public Entity(GamePanel gamePanel, KeyInput keyInput) {
        this.gamePanel = gamePanel;
        this.keyInput = keyInput;

        _x = _y = 100;
        _width = _height = gamePanel.titleSize;
        _speed = 5;
    }

    // getter
    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public Point getLocation() {
        return new Point(_x, _y);
    }

    public int getSpeed() {
        return _speed;
    }

    public Dimension getSize() {
        return new Dimension(_width, _height);
    }

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    // setter
    public void setSize(Dimension s) {
        _width = s.width;
        _height = s.height;
    }

    public void setValues(int x, int y, int speed) {
        _x = x;
        _y = y;
        _speed = speed;
    }

    public void setLocation(int x, int y) {
        _x = x;
        _y = y;
    }

    public void setSpeed(int speed) {
        _speed = speed;
    }

    abstract public void update();

    abstract public void draw(Graphics2D g2d);
}
