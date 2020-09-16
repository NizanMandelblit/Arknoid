import java.util.ArrayList;
import java.util.List;

/**
 * The type Rectangle.
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeft;
    private Line up;
    private Line down;
    private Line right;
    private Line left;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.up = this.getUpLine();
        this.down = this.getDownLine();
        this.right = this.getRightLine();
        this.left = this.getLeftLine();
    }

    /**
     * Gets up line.
     *
     * @return the up line
     */
    public Line getUpLine() {
        double x = upperLeft.getX();
        double y = upperLeft.getY();
        Line upEq = new Line(x, y, x + this.width, y);
        return upEq;
    }

    /**
     * Gets down line.
     *
     * @return the down line
     */
    public Line getDownLine() {
        double x = upperLeft.getX();
        double y = upperLeft.getY();
        Line downEq = new Line(x, y + this.height, x + this.width, y + this.height);
        return downEq;
    }

    /**
     * Gets right line.
     *
     * @return the right line
     */
    public Line getRightLine() {
        double x = upperLeft.getX();
        double y = upperLeft.getY();
        Line rightEq = new Line(x + this.width, y, x + this.width, y + this.height);
        return rightEq;
    }


    /**
     * Gets left line.
     *
     * @return the left line
     */
    public Line getLeftLine() {
        double x = upperLeft.getX();
        double y = upperLeft.getY();
        Line leftEq = new Line(x, y, x, y + this.height);
        return leftEq;
    }

    /**
     * Intersection points java . util . list.
     *
     * @param line the line
     * @return the java . util . list
     */
// Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List intersectionPoints(Line line) {
        List<Point> list = new ArrayList<Point>();
        if (this.up.isIntersecting(line)) {
            list.add(this.up.intersectionWith(line));
        }
        if (this.down.isIntersecting(line)) {
            list.add(this.down.intersectionWith(line));
        }
        if (this.left.isIntersecting(line)) {
            list.add(this.left.intersectionWith(line));
        }
        if (this.right.isIntersecting(line)) {
            list.add(this.right.intersectionWith(line));
        }
        return list;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Sets upper left.
     *
     * @param point the point
     */
    public void setUpperLeft(Point point) {
        this.upperLeft = point;
    }
}