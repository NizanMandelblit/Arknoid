import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Level name.
 */
public class LevelName implements Sprite {
    private String levelName;

    /**
     * Instantiates a new Level name.
     *
     * @param levelname the levelname
     */
    public LevelName(String levelname) {
        this.levelName = levelname;
    }

    @Override
    public void drawOn(DrawSurface d) {
        //level name
        d.setColor(Color.BLACK);
        d.drawText(550, 17, "Level Name: " + this.levelName, 15);
    }

    @Override
    public void timePassed() {

    }
}
