import biuoop.DrawSurface;

/**
 * The type Ball.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private GameEnvironment gameEnvironment;
    private long time = 0;

    /**
     * Instantiates a new Ball.
     *
     * @param center          the center
     * @param r               the r
     * @param color           the color
     * @param gameEnvironment the game environment
     */
// constructor
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.minX = 0;
        this.minY = 0;
        this.maxX = GameLevel.BOARD_WIDTH;
        this.maxY = GameLevel.BOARD_HEIGHT;
        this.gameEnvironment = gameEnvironment;
        this.velocity = Velocity.fromAngleAndSpeed(Math.random() * 360, 40 / this.r);
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x               the x
     * @param y               the y
     * @param r               the r
     * @param color           the color
     * @param gameEnvironment the game environment
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        Point centerNew = new Point(x, y);
        this.center = centerNew;
        this.r = r;
        this.color = color;
        this.minX = 0;
        this.minY = 0;
        this.maxX = 200;
        this.maxY = 200;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Sets border.
     *
     * @param minLimX the min lim x
     * @param minLimY the min lim y
     * @param maxLimX the max lim x
     * @param maxLimY the max lim y
     */
    public void setBorder(int minLimX, int minLimY, int maxLimX, int maxLimY) {
        this.minX = minLimX;
        this.minY = minLimY;
        this.maxX = maxLimX;
        this.maxY = maxLimY;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        //drawing a circle in the color and size of the given ball
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Time passed.
     */
    public void timePassed() {
        time++;
        this.moveOneStep();
    }

    /**
     * Sets velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        Velocity velocityNew = new Velocity(dx, dy);
        this.velocity = velocityNew;
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }


    /**
     * Change velocity.
     */
    public void changeVelocity() {
        //saving the next x,y values that the ball is headed to
        int moveX = (int) this.velocity.getDX() + (int) this.center.getX();
        int moveY = (int) this.velocity.getDY() + (int) this.center.getY();
        //checking if a direction of the velocity need to be changed
        if ((moveX + this.getSize() > this.maxX - 30 && this.velocity.getDX() > 0)
                || (moveX - this.getSize() < this.minX + 30 && this.velocity.getDX() < 0)) {
            this.velocity = new Velocity(-1 * this.velocity.getDX(), this.velocity.getDY());
        }
        if ((moveY + this.getSize() > this.maxY + 100 && this.velocity.getDY() > 0)
                || (moveY - this.getSize() < this.minY + 30 && this.velocity.getDY() < 0)) {
            this.velocity = new Velocity(this.velocity.getDX(), -1 * this.velocity.getDY());
        }
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {
        Line trajoctory = this.trajctory();
        if (this.gameEnvironment.getClosestCollision(trajoctory) == null) {
            this.changeVelocity();
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            Point collision = this.gameEnvironment.getClosestCollision(trajoctory).collisionPoint();
            Collidable object = this.gameEnvironment.getClosestCollision(trajoctory).collisionObject();
            Velocity newVel = object.hit(this, collision, this.velocity);
            if (newVel != null) {
                this.velocity = newVel;
            }
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }


    /**
     * Trajctory line.
     *
     * @return the line
     */
    public Line trajctory() {
        if (velocity == null) {
            return new Line(this.center, this.center);
        }
        return new Line(this.center, this.getVelocity().applyToPoint(this.center));
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.gameEnvironment.addCollidable(c);
    }

    /**
     * Remove from game.
     *
     * @param g the g
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }


}

