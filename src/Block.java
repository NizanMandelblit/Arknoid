import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle block;
    private java.awt.Color color;
    private Color stroke;

    private List<Color> colorList;
    private List<Image> imageList;
    private int hitPoint;
    private int width;

    private long time;


    /**
     * Sets stroke.
     *
     * @param strokel the stroke
     */
    public void setStroke(Color strokel) {
        this.stroke = strokel;
    }

    /**
     * Sets color list.
     *
     * @param colorListL the color list
     */
    public void setColorList(List<Color> colorListL) {
        this.colorList = colorListL;
    }

    /**
     * Sets image list.
     *
     * @param imageListl the image list
     */
    public void setImageList(List<Image> imageListl) {
        this.imageList = imageListl;
    }

    /**
     * Instantiates a new Block.
     *
     * @param rct the rct
     */
    public Block(Rectangle rct) {
        this.block = rct;
        this.color = Color.white;
        this.width = (int) block.getWidth();

    }

    /**
     * Instantiates a new Block.
     *
     * @param rct       the rct
     * @param colorList the color list
     */
    public Block(Rectangle rct, List<Color> colorList) {
        this.block = rct;
        this.colorList = colorList;
    }

    /**
     * Sets block.
     *
     * @param blockl the block
     */
    public void setBlock(Rectangle blockl) {
        this.block = blockl;
    }

    /**
     * Sets color.
     *
     * @param colorl the color
     */
    public void setColor(Color colorl) {
        this.color = colorl;
    }


    /**
     * Sets border flag.
     *
     * @param borderFlagl the border flag
     */
    public void setBorderFlag(int borderFlagl) {
        this.borderFlag = borderFlagl;
    }

    /**
     * Sets hit listeners.
     *
     * @param hitListenersl the hit listeners
     */
    public void setHitListeners(List<HitListener> hitListenersl) {
        this.hitListeners = hitListenersl;
    }


    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param widthl the width
     */
    public void setWidth(int widthl) {
        this.width = widthl;
    }

    /**
     * Sets hit point.
     *
     * @param hitPointl the hit point
     */
    public void setHitPoint(int hitPointl) {
        this.hitPoint = hitPointl;
    }

    private int borderFlag = 0;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Instantiates a new Block.
     *
     * @param rct   the rct
     * @param color the color
     */
    public Block(Rectangle rct, Color color) {
        this.block = rct;
        this.color = color;
        this.time = 0;
    }


    /**
     * getCollisionRectangle.
     *
     * @return Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * Sets border flag.
     */
    public void setBorderFlag() {
        this.borderFlag = 1;
    }

    /**
     * First row.
     */
    public void firstRow() {
        this.hitPoint++;
    }

    /**
     * Decrese life.
     */
    public void decreseLife() {
        this.hitPoint--;
    }

    /**
     * hit.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter          the hitter
     * @return the velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        if (collisionPoint == null || currentVelocity == null) {
            return null;
        }
        this.hitPoint--;
        if (this.block.getUpLine().checkPointOnLine(collisionPoint)
                || this.block.getDownLine().checkPointOnLine(collisionPoint)) {
            Velocity newVelocity = new Velocity(currentVelocity.getDX(), (-1) * currentVelocity.getDY());
            return newVelocity;
        }

        if (this.block.getLeftLine().checkPointOnLine(collisionPoint)
                || this.block.getRightLine().checkPointOnLine(collisionPoint)) {
            Velocity newVelocity = new Velocity((-1) * currentVelocity.getDX(), currentVelocity.getDY());
            return newVelocity;
        }
        return null;
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    // draw the sprite to the screen
    public void drawOn(DrawSurface d) {
        if (this.borderFlag == 1) {
            d.setColor(this.color);
            Point point = this.block.getUpperLeft();
            d.fillRectangle((int) point.getX(), (int) point.getY(), (int) this.block.getWidth(),
                    (int) this.block.getHeight());
            d.setColor(Color.black);
            d.drawRectangle((int) point.getX(), (int) point.getY(), (int) this.block.getWidth(),
                    (int) this.block.getHeight());

        }
        if (this.stroke != null) {
            Point point = this.block.getUpperLeft();
            d.setColor(stroke);
            d.drawRectangle((int) point.getX(), (int) point.getY(), (int) this.block.getWidth(),
                    (int) this.block.getHeight());
            d.setColor(Color.orange);
            d.fillRectangle((int) point.getX(), (int) point.getY(), (int) this.block.getWidth(),
                    (int) this.block.getHeight());
        }

        if (colorList != null && colorList.get(0) != null && hitPoint != 0) {
            d.setColor(colorList.get(hitPoint - 1));
            d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX()
                    , (int) this.getCollisionRectangle().getUpperLeft().getY()
                    , this.width, (int) this.getCollisionRectangle().getHeight());
        }

        if (imageList != null && imageList.get(0) != null && hitPoint != 0) {
            d.drawImage((int) this.getCollisionRectangle().getUpperLeft().getX()
                    , (int) this.getCollisionRectangle().getUpperLeft().getY(), imageList.get(this.hitPoint - 1));
        }

        if (borderFlag == 1) {
            d.setColor(Color.white);
            int textX = (int) ((this.getCollisionRectangle().getUpLine().middle().getX()
                    + this.getCollisionRectangle().getDownLine().middle().getX()) / 2);
            int textY = (int) ((this.getCollisionRectangle().getRightLine().middle().getY()
                    + this.getCollisionRectangle().getLeftLine().middle().getY()) / 2);


            if (this.hitPoint <= 0 || this.borderFlag == 1) {
                d.drawText(textX, textY, "X", 15);
            }
        }
    }

    /**
     * Time passed.
     */
    // notify the sprite that time has passed
    public void timePassed() {
        this.time++;
    }


    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Sets location.
     *
     * @param xpos the xpos
     * @param ypos the ypos
     */
    public void setLocation(int xpos, int ypos) {
        this.block.setUpperLeft(new Point(xpos, ypos));
    }


    /**
     * Remove from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * notifyHit.
     *
     * @param hitter the hitter
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Gets hit points.
     *
     * @return the hit points
     */
    public int getHitPoints() {
        return this.hitPoint;
    }


}
