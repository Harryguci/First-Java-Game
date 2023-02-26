package Entity;

import Game.GamePanel;
import Game.KeyInput;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.Rectangle;

public abstract class Entity extends JPanel {
    protected GamePanel gamePanel;
    protected KeyInput keyInput;

    protected Rectangle collisionArea;

    protected int _x, _y, _speed, _width, _height;

    protected boolean isCollision = false;

    public Entity(GamePanel gamePanel, KeyInput keyInput) {
        this.gamePanel = gamePanel;
        this.keyInput = keyInput;

        _x = _y = 100;
        _width = _height = gamePanel.titleSize;
        _speed = 5;
    }

    // [GETTER]
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

    public Rectangle getCollisionArea() {
        return this.collisionArea;
    }

    // [SETTER]
    public void setSize(Dimension s) {
        _width = s.width;
        _height = s.height;
    }

    public void setValues(int x, int y, int speed) {
        _x = x;
        _y = y;
        _speed = speed;
    }

    public void setCollisionArea(Rectangle rect) {
        this.collisionArea = rect;
    }

    public void setLocation(int x, int y) {
        _x = x;
        _y = y;
    }

    public void setSpeed(int speed) {
        _speed = speed;
    }

    public void setCollision(boolean value) {
        this.isCollision = value;
    }

    public boolean isCollide(Entity entity) {
        Rectangle rect1 = this.collisionArea;
        Rectangle rect2 = entity.collisionArea;

        return (
                rect1.x < rect2.x + rect2.width &&
                        rect1.x + rect1.width > rect2.x &&
                        rect1.y < rect2.y + rect2.height &&
                        rect1.height + rect1.y > rect2.y
        );
    }

    abstract public void update();

    abstract public void draw(Graphics2D g2d);
}
