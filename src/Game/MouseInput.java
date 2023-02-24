package Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    private int _x, _y;
    private Point _lastClicked = new Point(0, 0);

    public boolean isClicked = false;

    public MouseInput() {
        _x = MouseInfo.getPointerInfo().getLocation().x;
        _y = MouseInfo.getPointerInfo().getLocation().y;
    }

    public Point getMouseLocation() {
        return MouseInfo.getPointerInfo().getLocation();
    }

    public Point ClickLocation() {
        return _lastClicked;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        isClicked = true;
        _lastClicked = e.getPoint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isClicked = false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isClicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        isClicked = false;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        isClicked = false;
    }
}
