import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        ColorsParser colorsParser = new ColorsParser();
        ImageParser imageParser = new ImageParser();
        List<LevelInformation> finalLevels = new ArrayList<>();
        LevelInfoAss7 levelTemp = new LevelInfoAss7();
        BufferedReader bufferedReader = new BufferedReader(reader);
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = null;
        //list of things to conigure for the levelTemp
        List<Velocity> ballVelocity = new ArrayList<>();
        int paddleSpeed;
        int paddleWidth;
        String levelName;
        List<Block> blocks = new ArrayList<>();
        int numberOfBlocksToRemove;
        int blockStartX;
        int blockStartY;
        int rowHeight;
        int x, y = 0;
        boolean isInBlocks = false;
        //temp
        String tempLine;

        while (true) {
            try {
                String line = bufferedReader.readLine();
                //comments and eof
                if (line == null) {
                    break;
                } else if (line.startsWith("#")) {
                    continue;
                }
                //START_LEVEL
                if (line.startsWith("START_LEVEL")) {
                    levelTemp = new LevelInfoAss7();
                    ballVelocity.clear();
                }

                //level name
                if (line.startsWith("level_name")) {
                    levelName = line.substring(line.indexOf(":") + 1);
                    levelTemp.setLevelName(levelName);
                }
                //need to complete ball velocities
                if (line.startsWith("ball_velocities")) {
                    tempLine = line.substring(line.indexOf(":") + 1);
                    String[] ballstring = tempLine.split(" ");
                    ballVelocity = new ArrayList<>();
                    for (int i = 0; i < ballstring.length; i++) {
                        double angle = Double.parseDouble(ballstring[i].substring(0, ballstring[i].indexOf(",")));
                        double speed = Double.parseDouble(ballstring[i].substring(ballstring[i].indexOf(",") + 1)) / 10;
                        ballVelocity.add(Velocity.fromAngleAndSpeed(angle, speed));
                    }
                    levelTemp.setBallVelocity(ballVelocity);
                }
                //background
                if (line.startsWith("background")) {
                    tempLine = line.substring(line.indexOf(":") + 1);
                    if (tempLine.startsWith("color")) {
                        levelTemp.setBackground(new BackgroundAss7(colorsParser.colorFromString(tempLine)));
                    } else if (tempLine.startsWith("image")) {
                        levelTemp.setBackground(new BackgroundAss7(imageParser.imageFromString(tempLine)));
                    }
                }
                //paddle speed
                if (line.startsWith("paddle_speed")) {
                    paddleSpeed = Integer.parseInt(line.substring(line.indexOf(":") + 1)) / 10;
                    levelTemp.setPaddleSpeed(paddleSpeed);
                }
                //paddle width
                if (line.startsWith("paddle_width")) {
                    paddleWidth = Integer.parseInt(line.substring(line.indexOf(":") + 1));
                    levelTemp.setPaddleWidth(paddleWidth);
                }
                //BLOCKS
                //block_definitions-NEED TO COMPLETE
                if (line.startsWith("block_definitions")) {
                    line = line.substring(line.indexOf(":") + 1);
                    //using rescources
                    InputStream inputStream =
                            ClassLoader.getSystemClassLoader().getResourceAsStream(
                                    line.substring(line.indexOf(":") + 1));
                    blocksFromSymbolsFactory = BlocksDefinitionReader.fromReader(new InputStreamReader(inputStream));
                }
                //blocks_start_x
                if (line.startsWith("blocks_start_x")) {
                    blockStartX = Integer.parseInt(line.substring(line.indexOf(":") + 1));
                    levelTemp.setBlockStartX(blockStartX);
                }
                //blocks_start_y
                if (line.startsWith("blocks_start_y")) {
                    blockStartY = Integer.parseInt(line.substring(line.indexOf(":") + 1));
                    levelTemp.setBlockStartY(blockStartY);
                }
                //row_height
                if (line.startsWith("row_height")) {
                    rowHeight = Integer.parseInt(line.substring(line.indexOf(":") + 1));
                    levelTemp.setRowHeight(rowHeight);
                }
                //num_blocks
                if (line.startsWith("num_blocks")) {
                    numberOfBlocksToRemove = Integer.parseInt(line.substring(line.indexOf(":") + 1));
                    levelTemp.setNumberOfBlocksToRemove(numberOfBlocksToRemove);
                }

                //START_BLOCKS
                if (line.startsWith("START_BLOCKS")) {
                    isInBlocks = true;
                    continue;
                }
                if (isInBlocks) {
                    x = levelTemp.getBlockStartX();
                    y += levelTemp.getRowHeight();
                    for (int i = 0; i < line.length(); i++) {
                        if (blocksFromSymbolsFactory.isBlockSymbol("" + line.charAt(i))) {
                            Block block = blocksFromSymbolsFactory.getBlock("" + line.charAt(i), x, y);
                            blocks.add(block);
                            x = x + blocksFromSymbolsFactory.getBlock("" + line.charAt(i), x, y).getWidth();
                        } else if (blocksFromSymbolsFactory.isSpaceSymbol(("" + line.charAt(i)))) {
                            x = x + blocksFromSymbolsFactory.getSpaceWidth(("" + line.charAt(i)));
                        }
                    }
                }
                //END_BLOCKS
                if (line.startsWith("END_BLOCKS")) {
                    isInBlocks = false;
                    levelTemp.setBlocks(new ArrayList<>(blocks));

                }

                //END_LEVEL
                if (line.startsWith("END_LEVEL")) {
                    finalLevels.add(levelTemp);
                    levelTemp.setNumberOfBalls(ballVelocity.size());
                    blocks.clear();
                }

            } catch (IOException e) {
                System.out.println("Error");
            }
        }
        return finalLevels;
    }
}