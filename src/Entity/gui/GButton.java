package Entity.gui;

import Game.GamePanel;
import Game.MouseInput;
import util.ImageReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.JFrame;

public class GButton extends JPanel implements MouseListener {

    String content;
    GamePanel gamePanel;
    private int _x, _y, _width, _height;
    private int _strX, _strY;
    private Font _font;

    BufferedImage image, imageHover;

    private boolean isHover = false, isPressed = false;
    private int delay = 10;

    public GButton(String str) {
        super();
        content = str;

        _width = 200;
        _height = 60;

        _x = _y = 0;

    _strX = 70;
    _strY = 40;

        _font = new Font("Roboto", Font.BOLD, 30);


        try {
            image = ImageReader.Read("gui/button1.png");
            imageHover = ImageReader.Read("gui/button1Hover.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public GButton(String str, GamePanel gamePanel) {
        super();
        content = str;

        _width = 200;
        _height = 60;

        this.gamePanel = gamePanel;

        _x = (gamePanel.screenWidth - _width) / 2;
        _y = (gamePanel.screenHeight - _height) / 2;


        _strX = 70;
        _strY = 40;

        _font = new Font("Roboto", Font.BOLD, 30);

        try {
            image = ImageReader.Read("gui/button1.png");
            imageHover = ImageReader.Read("gui/button1Hover.png");
        } catch (IOException e) {
            System.out.println("Can not read button images");
            throw new RuntimeException(e);
        }
    }

    public void setContent(String str) {
        content = str;
    }

    public void setStringLocation(int x, int y) {
        _strX = x;
        _strY = y;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void update() {
        Point mouseOffset = gamePanel.mouseInput.ClickLocation();

        if (gamePanel.mouseInput.isClicked && mouseOffset.x >= _x && mouseOffset.x <= _x + _width
                && mouseOffset.y >= _y && mouseOffset.y <= _y + _height) {
            isHover = true;
            delay--;
            if (delay <= 0) {
                gamePanel.mouseInput.isClicked = false;
                delay = 10;
                isPressed = true;
            }
        } else {
            isHover = false;
        }
    }

    public void draw(Graphics2D g2d) {
        if (isHover)
            g2d.drawImage(imageHover, _x, _y, _width, _height, this);
        else
            g2d.drawImage(image, _x, _y, _width, _height, this);

        g2d.setFont(_font);
        g2d.setColor(new Color(50, 50, 150));
        g2d.drawString(content, _x + _strX + 3, _y + _strY);

        g2d.setColor(Color.WHITE);
        g2d.drawString(content, _x + _strX, _y + _strY);

    }

    // SETTER & GETTER

    public void setSize(int w, int h) {
        _width = w;
        _height = h;
    }

    public void setLocation(int x, int y) {
        _x = x;
        _y = y;
    }

    public void setPressed(boolean value) {
        isPressed = value;
    }

    public void setFont(Font font, int x, int y) {
        this._font = font;
        this._strX = x;
        this._strY = y;
    }

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
