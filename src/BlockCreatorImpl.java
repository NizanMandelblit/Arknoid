import java.awt.Color;
import java.awt.Image;
import java.util.List;

/**
 * The type Block creator.
 */
public class BlockCreatorImpl implements BlockCreator {

    private int blockWidth;
    private int blockHeight;
    private int hitPoint;
    private Color stroke;
    private List<Color> colorList;
    private List<Image> imageList;


    /**
     * Instantiates a new Block creator.
     *
     * @param blockWidth  the block width
     * @param blockHeight the block height
     * @param hitPoint    the hit point
     * @param stroke      the stroke
     * @param colorList   the color list
     * @param imageList   the image list
     */
    public BlockCreatorImpl(int blockWidth, int blockHeight, int hitPoint, Color stroke, List<Color> colorList
            , List<Image> imageList) {
        // super(new Rectangle(new Point(200, 200), blockWidth, blockHeight), Color.yellow);

        this.blockWidth = blockWidth;
        this.blockHeight = blockHeight;
        this.hitPoint = hitPoint;
        this.stroke = stroke;
        this.colorList = colorList;
        this.imageList = imageList;
        // super.setLocation(this.xpos, this.ypos);
        //  super.setWidth(this.blockWidth);
        //    super.setHitPoint(this.hitPoint);
        //  super.setBlock(new Rectangle(new Point(this.xpos, this.ypos), this.blockWidth, this.blockHeight));
    }

    @Override
    public Block create(int xpos, int ypos) {
        Block block = new Block(new Rectangle(new Point(xpos, ypos), this.blockWidth, this.blockHeight));
        //set xpos and ypos
        block.setHitPoint(this.hitPoint);
        block.setImageList(this.imageList);
        block.setColorList(this.colorList);
        block.setStroke(stroke);
        return block;
    }

}
