import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private int speed;

    /**
     * Instantiates a new Paddle.
     *
     * @param keyboard  the keyboard
     * @param rectangle the rectangle
     * @param speed     the speed
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle, int speed) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.speed = speed;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        double x = this.rectangle.getUpperLeft().getX();
        double y = this.rectangle.getUpperLeft().getY();
        if (x - 5 >= 40) {
            this.rectangle = new Rectangle(new Point(x - this.speed, y), this.rectangle.getWidth()
                    , this.rectangle.getHeight());
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        double x = this.rectangle.getUpperLeft().getX();
        double y = this.rectangle.getUpperLeft().getY();
        if (x + 5 + this.rectangle.getWidth() <= GameLevel.BOARD_WIDTH - 40) {
            this.rectangle = new Rectangle(new Point(x + this.speed, y), this.rectangle.getWidth()
                    , this.rectangle.getHeight());

        }
    }


    /**
     * Time passed.
     */
    // Sprite
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.YELLOW);
        Point point = this.rectangle.getUpperLeft();
        d.fillRectangle((int) point.getX(), (int) point.getY(), (int) this.rectangle.getWidth()
                , (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) point.getX(), (int) point.getY()
                , (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    // Collidable
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Hit velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter          the hitter
     * @return the velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double section = this.rectangle.getUpperLeft().getX() + (this.rectangle.getWidth() / 5);
        double section2 = section + (this.rectangle.getWidth() / 5);
        double section3 = section2 + (this.rectangle.getWidth() / 5);
        double section4 = section3 + (this.rectangle.getWidth() / 5);
        if (this.rectangle.getUpLine().checkPointOnLine(collisionPoint)) {
            //region 1
            if (collisionPoint.getX() <= section) {
                Velocity newVelocity = Velocity.fromAngleAndSpeed(-60, 40 / 7);
                return newVelocity;
                //region 2
            } else if (collisionPoint.getX() <= section2) {
                Velocity newVelocity = Velocity.fromAngleAndSpeed(-30, 40 / 7);
                return newVelocity;

                //region 3
            } else if (collisionPoint.getX() <= section3) {
                Velocity newVelocity = Velocity.fromAngleAndSpeed(60, 40 / 7);
                return newVelocity;

                //region 4
            } else if (collisionPoint.getX() <= section4) {
                Velocity newVelocity = Velocity.fromAngleAndSpeed(30, 40 / 7);
                return newVelocity;

                //region 5
            } else {
                Velocity newVelocity = Velocity.fromAngleAndSpeed(60, 40 / 7);
                return newVelocity;
            }
            //if there is a hit in the sides of the paddle
        } else {
            Velocity newVelocity = new Velocity((-1) * currentVelocity.getDX(), currentVelocity.getDY());
            return newVelocity;
        }
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
// Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}