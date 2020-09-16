import java.io.IOException;
import java.util.List;

/**
 * The type Play game task.
 */
public class PlayGameTask implements Task {
    private GameFlow game;
    private List<LevelInformation> levels;

    /**
     * Instantiates a new Play game task.
     *
     * @param game   the game
     * @param levels the levels
     */
    public PlayGameTask(GameFlow game, List<LevelInformation> levels) {
        this.game = game;
        this.levels = levels;
    }

    @Override
    public Object run() throws IOException {
        this.game.runLevels(this.levels);

        return null;
    }
}
