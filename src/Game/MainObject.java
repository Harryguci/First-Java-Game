package Game;

import com.sun.tools.javac.Main;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.color.ICC_ColorSpace;

public class MainObject {
    private int _x, _y, _width, _height, _speed;
    private Color color;
    private int _length;
    Point[] _cells;
    private boolean[][] _map;
    private int SCREEN_WIDTH, SCREEN_HEIGHT;

    public MainObject() {

        _speed = 5;
        _width = _height = 20;
        _cells = new Point[100];
        for (int i = 0; i < 100; i++) {
            _cells[i] = new Point(0, 0);
        }


        _length = 1;
        color = new Color(150, 120, 255);
    }

    public MainObject(int x, int y, int width, int height) {
        _speed = 8;
        _width = width;
        _height = height;
        _cells = new Point[100];
        for (int i = 0; i < 100; i++) {
            _cells[i] = new Point(x, y);
        }

        _length = 1;
        color = new Color(150, 120, 255);
    }

    public void setScreen(int width, int height) {
        SCREEN_HEIGHT = height;
        SCREEN_WIDTH = width;
    }

    public void growUp() {
        _length++;
        handle();
    }

    public void setLocation(int x, int y) {
        _cells[0] = new Point(x, y);
    }

    public int getSpeed() {
        return _speed;
    }

    public int getX() {
        return _cells[0].x;
    }

    public int getY() {
        return _cells[0].y;
    }

    public int getWidth() {
        return _width;
    }

    public void setX(int x) {
        _cells[0].x = x;
    }

    public void setY(int y) {
        _cells[0].y = y;
    }

    public void setSize(int width, int height) {
        _width = width;
        _height = height;
    }

    public void setMap(boolean[][] map) {
        _map = map;
    }

    public boolean canMove(int titleSize, int x, int y) {

        int row = (int) Math.floor(((double) y) / titleSize);
        int col = (int) Math.floor(((double) x) / titleSize);
        int row2 = (int) Math.floor((double) (y + _height) / (double) (titleSize));
        int col2 = (int) Math.floor((double) (x + _width) / (double) (titleSize));

        boolean is = !_map[row][col] && !_map[row2][col2];
        return is;
    }

    public void move(int x, int y) {
        if (canMove(_width, x, y)) {
            _cells[0].x = x;
            _cells[0].y = y;
            if (_cells[0].x <= 0) _cells[0].x = SCREEN_WIDTH;
            else if (_cells[0].x >= SCREEN_WIDTH) _cells[0].x = 0;
            if (_cells[0].y <= 0) _cells[0].y = SCREEN_HEIGHT;
            else if (_cells[0].y >= SCREEN_HEIGHT) _cells[0].y = 0;
        }
    }

    public void handle() {
        for (int i = 1; i < 100; i++) {
            _cells[i] = _cells[i - 1];
        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        for (int i = 0; i < _length; i++) {
            g2d.setColor(color);
            g2d.fillRect(_cells[i].x, _cells[i].y, _width, _height);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(_cells[i].x, _cells[i].y, _width, _height);
        }

        g2d.dispose();
    }
}
