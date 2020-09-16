import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Image;

/**
 * The type Background ass 7.
 */
public class BackgroundAss7 implements Sprite {
    private Color backgroundColor;
    private Image image;

    /**
     * Instantiates a new Background ass 7.
     *
     * @param backgroundColor the background color
     */
    public BackgroundAss7(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Instantiates a new Background ass 7.
     *
     * @param image the image
     */
    public BackgroundAss7(Image image) {
        this.image = image;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.backgroundColor != null) {
            d.setColor(backgroundColor);
            d.fillRectangle(0, 0, GameLevel.BOARD_WIDTH, GameLevel.BOARD_HEIGHT);
        } else {
            d.drawImage(0, 0, this.image);
        }


    }

    @Override
    public void timePassed() {

    }
}
