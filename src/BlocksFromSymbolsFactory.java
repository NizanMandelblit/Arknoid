import java.util.Map;

/**
 * The type Blocks from symbols factory.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Instantiates a new Blocks from symbols factory.
     *
     * @param spacerWidths  the spacer widths
     * @param blockCreators the block creators
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * Gets space width.
     *
     * @param s the s
     * @return the space width
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * Gets block.
     *
     * @param s the s
     * @param x the x
     * @param y the y
     * @return the block
     */
// Return a block according to the definitions associated
    // with symbol s. The block will be located at position (xpos, ypos).
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }


    /**
     * Is space symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
// returns true if 's' is a valid space symbol.
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * Is block symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
// returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }


}