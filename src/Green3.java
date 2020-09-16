import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Green 3.
 */
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballList = new ArrayList<>();
        double k = 0;
        for (int i = 0; i < numberOfBalls(); i++) {
            ballList.add(Velocity.fromAngleAndSpeed(10 + k, 5));
            k += 10;
        }
        return ballList;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 170;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Green3Background();
    }

    @Override
    public List<Block> blocks() {
        //adding blocks
        List<Color> color = new ArrayList<Color>();
        color.add(Color.gray);
        color.add(Color.yellow);
        color.add(Color.orange);
        color.add(Color.red);
        color.add(Color.cyan);
        color.add(Color.magenta);
        List<Block> blockLooop = new ArrayList<>();
        int blocks = 10;
        int x = GameLevel.BOARD_WIDTH - 90;
        int y = 130;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < blocks; j++) {
                blockLooop.add(new Block(new Rectangle(new Point(x, y), 60, 30), color.get(i)));
                x -= 60;
            }
            x = GameLevel.BOARD_WIDTH - 90;
            y += 30;
            blocks--;
        }
        return blockLooop;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
