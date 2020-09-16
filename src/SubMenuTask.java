import java.io.IOException;

/**
 * The type Sub menu task.
 */
public class SubMenuTask implements Task {

    private Menu<Task> animation;
    private AnimationRunner animationRunner;


    /**
     * Instantiates a new Sub menu task.
     *
     * @param animationl the animationl
     */
    public SubMenuTask(Menu<Task> animationl) {
        this.animation = animationl;
        this.animationRunner = new AnimationRunner(Ass7Game.GUI, 60);
    }


    @Override
    public Object run() throws IOException {
        while (true) {
            animationRunner.run(animation);
            // A menu with the selections is displayed.
            // Runs until user presses "s", "h"  or "q"

            Task result = animation.getStatus();
            if (result != null) {
                // result will contain "option a", "option b"
                // or "option c"
                result.run();
            }
            return null;
        }
    }
}
