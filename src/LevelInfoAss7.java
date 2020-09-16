import java.util.ArrayList;
import java.util.List;

/**
 * The type Level info ass 7.
 */
public class LevelInfoAss7 implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> ballVelocity;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    private int blockStartX;
    private int blockStartY;
    private int rowHeight;

    /**
     * Instantiates a new Level info ass 7.
     */
    public LevelInfoAss7() {
        this.numberOfBalls = 0;
        this.ballVelocity = new ArrayList<>();
        this.paddleSpeed = 0;
        this.paddleWidth = 0;
        this.levelName = "null";
        this.background = null;
        this.blocks = new ArrayList<>();
        this.numberOfBlocksToRemove = 0;
    }

    /**
     * Gets row height.
     *
     * @return the row height
     */
    public int getRowHeight() {
        return this.rowHeight;
    }

    /**
     * Sets row height.
     *
     * @param rowHeightl the row height
     */
    public void setRowHeight(int rowHeightl) {
        this.rowHeight = rowHeightl;
    }

    /**
     * Gets block start x.
     *
     * @return the block start x
     */
    public int getBlockStartX() {
        return blockStartX;
    }

    /**
     * Sets block start x.
     *
     * @param blockStartXl the block start x
     */
    public void setBlockStartX(int blockStartXl) {
        this.blockStartX = blockStartXl;
    }

    /**
     * Gets block start y.
     *
     * @return the block start y
     */
    public int getBlockStartY() {
        return blockStartY;
    }

    /**
     * Sets block start y.
     *
     * @param blockStartYl the block start y
     */
    public void setBlockStartY(int blockStartYl) {
        this.blockStartY = blockStartYl;
    }

    /**
     * Sets number of balls.
     *
     * @param numberOfBallsl the number of balls
     */
//setters
    public void setNumberOfBalls(int numberOfBallsl) {
        this.numberOfBalls = numberOfBallsl;
    }

    /**
     * Sets ball velocity.
     *
     * @param ballVelocityl the ball velocity
     */
    public void setBallVelocity(List<Velocity> ballVelocityl) {
        for (int i = 0; i < ballVelocityl.size(); i++) {
            this.ballVelocity.add(ballVelocityl.get(i));
        }
    }

    /**
     * Sets paddle speed.
     *
     * @param paddleSpeedl the paddle speed
     */
    public void setPaddleSpeed(int paddleSpeedl) {
        this.paddleSpeed = paddleSpeedl;
    }

    /**
     * Sets paddle width.
     *
     * @param paddleWidthl the paddle width
     */
    public void setPaddleWidth(int paddleWidthl) {
        this.paddleWidth = paddleWidthl;
    }

    /**
     * Sets level name.
     *
     * @param levelNamel the level name
     */
    public void setLevelName(String levelNamel) {
        this.levelName = levelNamel;
    }

    /**
     * Sets blocks.
     *
     * @param blocksl the blocks
     */
    public void setBlocks(List<Block> blocksl) {
        for (int i = 0; i < blocksl.size(); i++) {
            this.blocks.add(blocksl.get(i));
        }
    }

    /**
     * Sets number of blocks to remove.
     *
     * @param numberOfBlocksToRemovel the number of blocks to remove
     */
    public void setNumberOfBlocksToRemove(int numberOfBlocksToRemovel) {
        this.numberOfBlocksToRemove = numberOfBlocksToRemovel;
    }

    //getters
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocity;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * Sets background.
     *
     * @param backgroundl the background
     */
    public void setBackground(Sprite backgroundl) {
        this.background = backgroundl;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
