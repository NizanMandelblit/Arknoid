import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean isDone;
    private boolean wasPushed;


    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.keyboardSensor = sensor;
        this.isDone = false;
        this.wasPushed = true;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.keyboardSensor.isPressed(this.key)) {
            this.wasPushed = false;
        }

        this.animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(this.key) && !this.wasPushed) {
            this.isDone = true;
        }


    }

    @Override
    public boolean shouldStop() {

        return this.isDone;
    }
}
