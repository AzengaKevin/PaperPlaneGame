package ppg.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keyPresssed;

    private boolean southWest, southEast, south;

    public KeyManager() {
        keyPresssed = new boolean[255];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyPresssed[e.getKeyCode()] = true;

        tick();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyPresssed[e.getKeyCode()] = false;
        tick();
    }

    public void tick() {
        southEast = keyPresssed[KeyEvent.VK_RIGHT];
        southWest = keyPresssed[KeyEvent.VK_LEFT];
        south = keyPresssed[KeyEvent.VK_DOWN];
    }

    public boolean isSouthWest() {
        return southWest;
    }

    public boolean isSouthEast() {
        return southEast;
    }

    public boolean isSouth() {
        return south;
    }
}
