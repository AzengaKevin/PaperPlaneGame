package ppg.components;

import ppg.Direction;
import ppg.Game;
import ppg.Launcher;

import java.awt.*;

public class SpaceShip extends Polygon {

    private Direction direction = Direction.South;

    public static int[] southXpoly = {0, 15, -15};
    public static int[] southYpoly = {15, -15, -15};

    public static int[] southWestXpoly = {-9, -9, 21};
    public static int[] southWestYpoly = {-12, 21, 9};

    public static int[] southEastXpoly = {9, 9, -21};
    public static int[] southEastYpoly = {12, -21, 9};

    private int speedX = 0;
    private int speedY = 3;
    private double centerX;
    private double centerY;

    /**
     * The Starting Center points of the Spaceship are passed
     * @param centerX
     * @param centerY
     */
    public SpaceShip(double centerX, double centerY) {
        super(SpaceShip.genXPoints(centerX), SpaceShip.genYPoints(centerY), 3);

        this.centerX = centerX;
        this.centerY = centerY;
    }

    /**
     *
     * Generates an int array for xpoints of the polygon based on the passed center X point
     *
     * @param x
     * @return
     */
    public static int[] genXPoints(double x) {
        int shift = (int) x;
        int[] tempX = southEastXpoly.clone();

        for (int i = 0; i < tempX.length; i++) {
            tempX[i] += shift;
        }

        return tempX;
    }

    /**
     * Generates new ypoints for the polygon based on the center point
     * @param y
     * @return
     */
    public static int[] genYPoints(double y) {
        int shift = (int) y;
        int[] tempY = southEastYpoly.clone();

        for (int i = 0; i < tempY.length; i++) {
            tempY[i] += shift;
        }

        return tempY;
    }

    /**
     * Changing the position of the Spaceship on the screen instantly
     *
     * @param x
     * @param y
     */
    public void changePosition(int x, int y){
        this.centerY = x;
        this.centerY = y;

        updatePoints();
    }

    /**
     * Returns the Pane Paper Bounding Box
     *
     * @return Rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle(super.xpoints[0] - 20, super.ypoints[0] - 30, 40, 40);
    }

    /**
     * Changes the speed of the Plane Paper Accordingly
     *
     * @param speedX
     */
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }


    /**
     * Changes the Direction of the Spaceship
     *
     * @param direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Moves the Plane Paper aroud the Frame Accordingly while checking the available constraints and responding accordingly
     */
    public void move() {
        this.centerY += speedY;
        this.centerX += speedX;

        if (this.centerY >= Launcher.HEIGHT) {
            this.centerY = 0;

            Game.score++;
        }

        updatePoints();

    }

    /**
     * Updates the points after the center point is moved
     */
    private void updatePoints() {

        int[] tempX = southXpoly.clone();
        int[] tempY = southYpoly.clone();

        if (direction.equals(Direction.SouthWest)) {
            tempX = southWestXpoly.clone();
            tempY = southWestYpoly.clone();
        } else if (direction.equals(Direction.SouthEast)) {
            tempX = southEastXpoly.clone();
            tempY = southEastYpoly.clone();
        } else {
            tempX = southXpoly.clone();
            tempY = southYpoly.clone();
        }


        for (int i = 0; i < super.xpoints.length; i++) {
            super.xpoints[i] = (int) this.centerX + tempX[i];
        }

        for (int i = 0; i < super.ypoints.length; i++) {
            super.ypoints[i] = tempY[i] + (int) this.centerY;
        }

    }
}
