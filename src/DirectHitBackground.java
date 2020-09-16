import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Direct hit background.
 */
public class DirectHitBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, GameLevel.BOARD_WIDTH, GameLevel.BOARD_HEIGHT);
        d.setColor(new Color(0x1E2DFF));
        d.drawCircle(400, 200, 100);
        d.setColor(new Color(0x3578FF));
        d.drawCircle(400, 200, 75);
        d.setColor(new Color(0x28B7FF));
        d.drawCircle(400, 200, 50);

        d.drawLine(270, 200, 370, 200);
        d.drawLine(430, 200, 530, 200);
        d.drawLine(400, 70, 400, 170);
        d.drawLine(400, 230, 400, 330);
    }

    @Override
    public void timePassed() {

    }
}
