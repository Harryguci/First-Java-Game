package Game;

import java.awt.*;

public class Wall {
    private int _width, _height;
    private int _x, _y;
    private Color color;

    public Wall(int x, int y) {
        _x = x;
        _y = y;
        color = Color.GRAY;
    }

    // getter
    public Point getLocation() {
        return new Point(_x, _y);
    }

    // setter
    public void setSize(int w, int h) {
        this._width = w;
        this._height = h;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
