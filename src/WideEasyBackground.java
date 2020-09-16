import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Wide easy background.
 */
public class WideEasyBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, GameLevel.BOARD_WIDTH, GameLevel.BOARD_HEIGHT);
        d.setColor(Color.yellow);
        for (int i = 0; i < 60; i++) {
            d.drawLine(150, 150, 60 * i, 300);
        }
        d.setColor(Color.yellow.darker());
        d.fillCircle(150, 150, 70);
        d.setColor(Color.yellow);
        d.fillCircle(150, 150, 60);


    }

    @Override
    public void timePassed() {

    }
}
