import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Final four.
 */
public class FinalFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
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
        return 150;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new FinalFourBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Color> color = new ArrayList<Color>();
        color.add(Color.gray);
        color.add(Color.yellow);
        color.add(Color.orange);
        color.add(Color.red);
        color.add(Color.cyan);
        color.add(Color.magenta);
        int blocks = 15;
        List<Block> blockList = new ArrayList<>();
        int x = GameLevel.BOARD_WIDTH - 56 - 30;
        int y = 130;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < blocks; j++) {
                blockList.add(new Block(new Rectangle(new Point(x, y), 56, 30), color.get(i)));
                x -= 56;
            }
            x = GameLevel.BOARD_WIDTH - 56 - 30;
            y += 30;
        }
        return blockList;
    }


    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
