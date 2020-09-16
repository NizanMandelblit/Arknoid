import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The type GameLevel.
 */
public class GameLevel implements Animation {
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle;
    private GUI gui;
    private Counter countBlocks;
    private Counter countBalls = new Counter(0);
    private Counter currentScore;
    private Counter lives;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    /**
     * The constant BOARD_WIDTH.
     */
    public static final int BOARD_WIDTH = 800;
    /**
     * The constant BOARD_HEIGHT.
     */
    public static final int BOARD_HEIGHT = 600;


    /**
     * Instantiates a new GameLevel.
     *
     * @param levelInformation the level information
     * @param ks               the ks
     * @param ar               the ar
     * @param score            the score
     * @param lives            the lives
     * @param remainBlocks     the remain blocks
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor ks, AnimationRunner ar, Counter score
            , Counter lives, Counter remainBlocks) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();

        //this.runner = new AnimationRunner(this.GUI, 60);
        // this.keyboard = this.GUI.getKeyboardSensor();
        this.runner = ar;
        this.keyboard = ks;
        this.levelInformation = levelInformation;
        this.countBlocks = remainBlocks;
        this.lives = lives;
        this.currentScore = score;
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }


    /**
     * Initialize.
     */
// Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        //adding level background
        sprites.addSprite(this.levelInformation.getBackground());

        PrintingHitListener print = new PrintingHitListener();
        BlockRemover remover = new BlockRemover(this, this.countBlocks);
        BallRemover removeBall = new BallRemover(this, this.countBalls);
        ScoreTrackingListener score = new ScoreTrackingListener(this.currentScore);
        ScoreIndicator scoreDisplay = new ScoreIndicator(this.currentScore);
        LivesIndicator livesDisplay = new LivesIndicator(this.lives);
        LevelName levelName = new LevelName(this.levelInformation.levelName());


        //adding blocks borders of the screen
        Rectangle rctUp = new Rectangle(new Point(0, 0), GameLevel.BOARD_WIDTH, 30);
        Block up = new Block(rctUp, Color.GRAY);
        //rctDown will be the block remover that very ball that hits it will kill the ball
        Rectangle rctDown = new Rectangle(new Point(0, GameLevel.BOARD_HEIGHT + 50), GameLevel.BOARD_WIDTH, 30);
        Block down = new Block(rctDown, Color.GRAY);
        down.addHitListener(removeBall);
        Rectangle rctLeft = new Rectangle(new Point(0, 30), 30, GameLevel.BOARD_WIDTH);
        Block left = new Block(rctLeft, Color.GRAY);
        Rectangle rctRight = new Rectangle(new Point(770, 30), 30, GameLevel.BOARD_WIDTH);
        Block right = new Block(rctRight, Color.GRAY);
        up.setBorderFlag();
        down.setBorderFlag();
        right.setBorderFlag();
        left.setBorderFlag();
        up.addToGame(this);
        down.addToGame(this);
        left.addToGame(this);
        right.addToGame(this);

        //adding blocks of the level
        for (Block block : this.levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(remover);
            block.addHitListener(score);
            this.sprites.addSprite(scoreDisplay);
            this.sprites.addSprite(livesDisplay);
        }


        this.addSprite(scoreDisplay);
        this.addSprite(livesDisplay);
        this.addSprite(levelName);
    }


    /**
     * Run.
     */
// Run the game -- start the animation loop.
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle(); // or a similar method
        //adding paddle
        // biuoop.KeyboardSensor keyboard = this.GUI.getKeyboardSensor();
        Rectangle shape = new Rectangle(new Point(250, 560), this.levelInformation.paddleWidth(), 30);
        Paddle paddle1 = new Paddle(this.keyboard, shape, this.levelInformation.paddleSpeed());
        this.paddle = paddle1;
        this.paddle.addToGame(this);

        this.runner.run(new CountdownAnimation(2, 3, this.sprites)); // countdown before turn starts.
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * createBallsOnTopOfPaddle.
     */
    private void createBallsOnTopOfPaddle() {
        //adding  balls
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(400 + i * 20, 550), 7, Color.YELLOW, this.environment);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            ball.addToGame(this);
            this.countBalls.increase(1);
        }
    }

    /**
     * Run.
     */
    public void run() {
        while (this.lives.getValue() != 0) {
            if (this.lives.getValue() == 0) {
                gui.close();
                return;
            } else {
                this.playOneTurn();
            }
        }
        gui.close();
        return;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous playOneTurn method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;


        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        long startTime = System.currentTimeMillis(); // timing
        this.sprites.drawAllOn(d);
        // GUI.show(d);
        this.sprites.notifyAllTimePassed();
        // timing
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
        if (milliSecondLeftToSleep > 0) {
            sleeper.sleepFor(milliSecondLeftToSleep);
        }
        if (this.keyboard.isPressed("p")) {
            Animation pause = new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY
                    , new PauseScreen(this.keyboard));
            runner.run(pause);
        }
        if (this.countBlocks.getValue() == 0) {
            this.currentScore.increase(100);
            this.running = false;
        }
        if (this.countBalls.getValue() == 0) {
            this.lives.decrease(1);
            removeSprite(paddle);
            removeCollidable(paddle);
            this.running = false;
        }

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
