import biuoop.DrawSurface;

import java.util.List;
import java.util.ArrayList;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> list;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.list = new ArrayList<Sprite>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.list.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.list.remove(s);
    }

    /**
     * Notify all time passed.
     */
// call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        List<Sprite> listeners = new ArrayList<Sprite>(this.list);
        // Notify all listeners about a hit event:
        for (Sprite s : listeners) {
            s.timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param d the d
     */
// call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.list) {
            s.drawOn(d);
        }
    }
}
