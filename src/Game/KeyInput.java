package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
    private final int MAX_SIZE = 120;
    public boolean[] key;
    private boolean isPressed = false;

    public KeyInput() {
        super();
        key = new boolean[MAX_SIZE];
        key[KeyEvent.VK_UP] = key[KeyEvent.VK_DOWN] = key[KeyEvent.VK_LEFT] = key[KeyEvent.VK_RIGHT] = key[KeyEvent.VK_SPACE] = key[KeyEvent.VK_ESCAPE] = false;
    }

    public boolean isPressed() {
        return this.isPressed;
    }

    public boolean isUp() {
        return key[KeyEvent.VK_UP];
    }

    public boolean isDown() {
        return key[KeyEvent.VK_DOWN];
    }

    public boolean isLeft() {
        return key[KeyEvent.VK_LEFT];
    }

    public boolean isRight() {
        return key[KeyEvent.VK_RIGHT];
    }

    public boolean isESC() {
        return key[KeyEvent.VK_ESCAPE];
    }

    public boolean isSpace() {
        return key[KeyEvent.VK_SPACE];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        isPressed = true;
        if (e.getKeyCode() > MAX_SIZE) return;
        key[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        isPressed = false;
        if (e.getKeyCode() > MAX_SIZE) return;
        key[e.getKeyCode()] = false;
    }
}
