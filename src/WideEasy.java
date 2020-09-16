import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballList = new ArrayList<>();
        double k = 0;
        for (int i = 0; i < numberOfBalls(); i++) {
            ballList.add(Velocity.fromAngleAndSpeed(-50 + k, 5));
            k += 10;
        }
        return ballList;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 610;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new WideEasyBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Color> colors = new ArrayList<>();
        colors.add(Color.gray);
        colors.add(Color.blue);
        colors.add(Color.yellow);
        colors.add(Color.green);
        colors.add(Color.magenta);
        colors.add(Color.pink);
        colors.add(Color.cyan);
        colors.add(Color.yellow);
        colors.add(Color.green);
        colors.add(Color.magenta);
        colors.add(Color.pink);
        colors.add(Color.cyan);
        colors.add(Color.yellow);
        colors.add(Color.green);
        colors.add(Color.magenta);
        List<Block> blocks = new ArrayList<>();
        //minus the border of the screen-border on the rctRight-width of every block 840/15=56
        int x = GameLevel.BOARD_WIDTH - 56 - 30;
        int y = 300;
        for (int i = 0; i < this.numberOfBlocksToRemove(); i++) {
            blocks.add(new Block(new Rectangle(new Point(x, y), 56, 30), colors.get(i)));
            x -= 56;
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

}
