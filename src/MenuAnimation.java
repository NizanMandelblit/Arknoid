import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {
    private List<String> keys;
    private List<String> messages;
    private List<T> returnVal;
    private List<Menu<T>> subMenu;
    private List<Boolean> isSubList;
    private boolean isRun;
    private KeyboardSensor sensor;
    private boolean isSub = false;
    private Menu<T> curSub;


    /**
     * Instantiates a new Menu animation.
     */
    public MenuAnimation() {
        this.keys = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.returnVal = new ArrayList<>();
        this.subMenu = new ArrayList<>();
        this.isSubList = new ArrayList<>();
        this.isRun = false;
        this.sensor = Ass7Game.GUI.getKeyboardSensor();
    }

    @Override
    public void addSelection(String key, String message, T returnVall) {
        this.keys.add(key);
        this.messages.add(message);
        this.returnVal.add(returnVall);
        this.isSubList.add(false);
        this.subMenu.add(null);
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenul) {
        this.keys.add(key);
        this.messages.add(message);
        this.returnVal.add(null);
        this.isSubList.add(true);
        this.subMenu.add(subMenul);
    }

    @Override
    public T getStatus() {


        for (String key : this.keys) {
            if (this.sensor.isPressed(key)) {
                this.isRun = true;
                return this.returnVal.get(this.keys.indexOf(key));
            }
        }

        return null;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.blue);
        d.fillRectangle(0, 0, GameLevel.BOARD_WIDTH, GameLevel.BOARD_HEIGHT);
        d.setColor(Color.yellow);
        d.drawText(80, 80, "Arknoid", 50);
        d.setColor(Color.yellow.darker());
        d.drawText(81, 81, "Arknoid", 50);

        d.setColor(Color.green.darker());
        int txtY = 140;
        for (int i = 0; i < messages.size(); i++) {
            d.drawText(170, txtY, this.messages.get(i), 35);
            d.drawText(120, txtY, this.keys.get(i) + " - ", 35);
            txtY += 100;
        }


        //  d.drawText(120, 140, "(S) Start Game", 35);
        //   d.drawText(120, 240, "(h) High Scores", 35);
        //   d.drawText(120, 340, "(q) Quit Game", 35);


        this.getStatus();

    }

    @Override
    public boolean shouldStop() {
        if (this.isRun) {
            this.isRun = false;
            return true;
        } else {
            return false;
        }
    }

}
