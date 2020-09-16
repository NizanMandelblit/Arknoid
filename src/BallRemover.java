/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter balls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel the gameLevel
     * @param ball the ball
     */
    public BallRemover(GameLevel gameLevel, Counter ball) {
        this.gameLevel = gameLevel;
        this.balls = ball;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.balls.decrease(1);
        hitter.removeFromGame(this.gameLevel);

    }
}
