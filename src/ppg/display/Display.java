package ppg.display;

import javax.swing.*;

public class Display {

    private String title;
    private int width;
    private int height;

    private JFrame frame;

    /**
     * @param title
     * @param width
     * @param height
     */
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createFrame();
    }

    /**
     * Creates the frame of the Game
     */
    private void createFrame(){
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

}
