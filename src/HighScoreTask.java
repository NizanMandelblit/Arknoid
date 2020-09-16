import biuoop.KeyboardSensor;

/**
 * The type High score task.
 */
public class HighScoreTask implements Task {
    private HighScoresAnimation animation;
    private AnimationRunner animationRunner;

    /**
     * Instantiates a new High score task.
     *
     * @param animation the animation
     */
    public HighScoreTask(HighScoresAnimation animation) {

        this.animation = animation;
        this.animationRunner = new AnimationRunner(Ass7Game.GUI, 60);
    }

    @Override
    public Void run() {
        animationRunner.run(new KeyPressStoppableAnimation(Ass7Game.GUI.getKeyboardSensor(), KeyboardSensor.SPACE_KEY
                , this.animation));
        return null;
    }
}
