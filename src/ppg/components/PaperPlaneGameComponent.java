package ppg.components;

import ppg.Direction;
import ppg.Game;
import ppg.Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class PaperPlaneGameComponent extends JComponent {

    public SpaceShip spaceShip;

    private Shape bonanza = null;

    private List<Shape> ellipses;

    private Game game;

    private Color ellipseColor = Color.blue;
    private Color bonanzaColor = Color.blue;


    public PaperPlaneGameComponent(Game game) {
        this.game = game;

        this.spaceShip = new SpaceShip(Launcher.WIDTH / 2, 0);

        ellipses = new ArrayList<>();

        createCircles();

    }

    private void createCircles() {
        ellipses.clear();

        ellipseColor = new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));


        double size = getRandom(20, 40);

        int numOfCircles = (int) (Math.random() * 10) + 1;

        for (int i = 0; i < numOfCircles; i++) {

            double x = getRandom(size, game.getWidth() - size);
            double y = getRandom(size, game.getHeight() - size);

            ellipses.add(new Ellipse2D.Double(x, y, size, size));
        }

        bonanza = new Ellipse2D.Double(getRandom(20, 492), getRandom(20, 748), 20, 20);

    }

    private double getRandom(double min, double max) {
        return min + Math.random() * (max - min);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0x795548));
        g2d.fill(new Rectangle(0, 0, getWidth(), getHeight()));

        fillHorizontalRects(g2d);
        drawHorizontalLines(g2d);

        drawVerticalLines(g2d);

        if(game.getScore() > 0) {

            drawScore(g2d);

            g2d.setColor(ellipseColor);

            for (Shape shape : ellipses) {
                g2d.fill(shape);
            }

            for (Shape shape : ellipses) {
                if (spaceShip.getBounds().intersects(shape.getBounds())) {
                    game.score--;

                    spaceShip.changePosition(game.getWidth() / 2, 0);

                    game.setScore(game.score);

                }
            }

            if(bonanza != null){

                if(Math.round(Math.random()) == 1){
                    bonanzaColor = Color.green;
                }else{
                    bonanzaColor = Color.blue;
                }
                g2d.setColor(bonanzaColor);
                g2d.fill(bonanza);

                if(bonanza.intersects(spaceShip.getBounds())){
                    Game.score += 5;

                    bonanza = null;
                }
            }

            if (game.getKeyManager().isSouth()) {
                spaceShip.setSpeedX(0);
                spaceShip.setDirection(Direction.South);
            } else if (game.getKeyManager().isSouthEast()) {
                spaceShip.setSpeedX(2);
                spaceShip.setDirection(Direction.SouthEast);
            } else if (game.getKeyManager().isSouthWest()) {
                spaceShip.setSpeedX(-2);
                spaceShip.setDirection(Direction.SouthWest);
            } else {
                spaceShip.setSpeedX(0);
            }

            spaceShip.move();

            g2d.setColor(new Color(0xffffff));

            g2d.fill(spaceShip);
//            g2d.fill(spaceShip.getBounds());

        }else{
            showGameOverAndScore(g2d);
        }
    }

    private void drawScore(Graphics2D g2d){
        g2d.setColor(Color.GREEN);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.drawString("Score: " + game.getScore(), 360, 50);
    }

    private void showGameOverAndScore(Graphics2D g2d) {

        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 48));
        g2d.drawString("Game Over", 120, 300);

        g2d.drawString("Your Score is: " + game.getScore(), 30, 372);
    }

    /**
     * Draw the horizontal lines
     */
    private void fillHorizontalRects(Graphics2D g2d) {
        double y = 17.5;
        double width = 22;
        double height = 20;

        double gap = y;


        while (y < game.getHeight()) {
            g2d.setColor(Color.BLACK);
            g2d.fill(new Rectangle2D.Double(0, y, game.getWidth(), height));
            //Draw the red rects
            double x = 17.50;

            while (x < game.getWidth()) {
                g2d.setColor(new Color(0xe67373));
                g2d.fill(new Rectangle2D.Double(x, y, width, height));
                x += width + 13;
            }

            y += gap + height;
        }
    }

    private void drawHorizontalLines(Graphics2D g2d) {
        double y = 17.5;
        double height = 20;

        double gap = y;

        g2d.setColor(new Color(0xffff8d));
        g2d.setStroke(new BasicStroke(2));

        while (y < game.getHeight()) {
            g2d.draw(new Line2D.Double(0, y, game.getWidth(), y));
            y += height;
            g2d.draw(new Line2D.Double(0, y, game.getWidth(), y));
            y += gap;
        }
    }

    /**
     * Drawing the vertical lines
     *
     * @param g2d
     */
    private void drawVerticalLines(Graphics2D g2d) {
        double gap = 35.00;

        double x = 28.50;

        g2d.setStroke(new BasicStroke(2));

        while (x < 512) {
            g2d.setColor(new Color(0xffff8d));
            g2d.draw(new Line2D.Double(x, 0, x, game.getHeight()));

            x += gap;
        }
    }
}
