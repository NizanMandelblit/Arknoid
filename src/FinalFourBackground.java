import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Final four background.
 */
public class FinalFourBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, GameLevel.BOARD_WIDTH, GameLevel.BOARD_HEIGHT);


        d.setColor(Color.lightGray);
        d.fillCircle(200, 500, 25);
        d.fillCircle(220, 520, 25);
        d.fillCircle(220, 480, 25);
        d.setColor(Color.GRAY.brighter());
        d.fillCircle(248, 480, 25);
        d.fillCircle(258, 520, 25);
        d.fillCircle(275, 500, 25);


        d.setColor(Color.gray.brighter());
        for (int i = 0; i < 10; i++) {
            d.drawLine(200 + i * 10, 500, 70 + i * 10, 700);
        }


        d.setColor(Color.lightGray);
        d.fillCircle(200 + 400, 500, 25);
        d.fillCircle(220 + 400, 520, 25);
        d.fillCircle(220 + 400, 480, 25);
        d.setColor(Color.GRAY.brighter());
        d.fillCircle(248 + 400, 480, 25);
        d.fillCircle(258 + 400, 520, 25);
        d.fillCircle(275 + 400, 500, 25);


        d.setColor(Color.gray.brighter());
        for (int i = 0; i < 10; i++) {
            d.drawLine(200 + 400 + i * 10, 500, 200 + i * 10, 900);
        }

    }

    @Override
    public void timePassed() {

    }
}
