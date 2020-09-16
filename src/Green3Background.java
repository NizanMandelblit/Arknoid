import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Green 3 background.
 */
public class Green3Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.green.darker().darker());
        d.fillRectangle(0, 0, GameLevel.BOARD_WIDTH, GameLevel.BOARD_HEIGHT);


        d.setColor(Color.black);
        d.fillRectangle(70, 550, 100, 170);

        d.setColor(Color.white);
        d.fillRectangle(80, 560, 80, 150);
        d.setColor(Color.BLACK);
        //height
        d.fillRectangle(100, 560, 5, 200);
        d.fillRectangle(120, 560, 5, 200);
        d.fillRectangle(140, 560, 5, 200);
        //width
        d.fillRectangle(70, 580, 100, 5);
        d.fillRectangle(70, 620, 100, 5);
        d.fillRectangle(70, 660, 100, 5);

        d.setColor(Color.gray.darker());
        d.fillRectangle(110, 500, 30, 50);

        d.setColor(Color.gray.darker().darker());
        d.fillRectangle(120, 200, 10, 300);

        d.setColor(Color.orange);
        d.fillCircle(125, 188, 12);
        d.setColor(Color.orange.brighter());
        d.fillCircle(125, 188, 7);

        d.setColor(Color.white.brighter());
        d.fillCircle(125, 188, 3);


    }

    @Override
    public void timePassed() {

    }
}
