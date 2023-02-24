package Game;

import java.awt.*;

public class Fruit {
    private int _x, _y, _R;
    private Color color;

    public Fruit(int x, int y, int R) {
        _x = x;
        _y = y;
        _R = R;
        color = new Color(255, 0, 0);
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public void setLocation(int x, int y) {
        _x = x;
        _y = y;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(color);
        g2d.fillOval(_x, _y, _R, _R);

        g2d.dispose();
    }
}
