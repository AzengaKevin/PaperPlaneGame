package ppg;

import ppg.components.PaperPlaneGameComponent;
import ppg.display.Display;
import ppg.input.KeyManager;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game {

    public static final int DEFAULT_SCORE = 5;

    public static int score = DEFAULT_SCORE;

    private String title;
    private int width;
    private int height;

    private Display display;
    private PaperPlaneGameComponent component;
    private KeyManager keyManager;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        initComponents();

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        executor.scheduleAtFixedRate(new RepaintFrame(this), 0L, 20L, TimeUnit.MILLISECONDS);
    }

    private void initComponents() {
        display = new Display(title, width, height);
        keyManager = new KeyManager();

        component = new PaperPlaneGameComponent(this);

        component.addKeyListener(keyManager);
        display.getFrame().addKeyListener(keyManager);

        display.getFrame().add(component, BorderLayout.CENTER);

    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public KeyManager getKeyManager(){
        return this.keyManager;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Display getDisplay() {
        return display;
    }
}

class RepaintFrame implements Runnable {

    private Game game;

    public RepaintFrame(Game game) {

        this.game = game;
    }

    @Override
    public void run() {
        game.getDisplay().getFrame().repaint();
    }
}
