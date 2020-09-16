import java.util.List;

/**
 * The type Line.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Instantiates a new Line.
     *
     * @param start the start
     * @param end   the end
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Length double.
     *
     * @return the double
     */
// Return the length of the line
    public double length() {
        return end.distance(start);
    }

    /**
     * Middle point.
     *
     * @return the point
     */
// Returns the middle point of the line
    public Point middle() {
        //calculating the middle point of the line
        double middleX = (this.start.getX() + end.getX()) / 2;
        double middleY = (this.start.getY() + end.getY()) / 2;
        Point middle = new Point(middleX, middleY);
        return middle;
    }

    /**
     * Start point.
     *
     * @return the point
     */
// Returns the start point of the line
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the point
     */
// Returns the end point of the line
    public Point end() {
        return this.end;
    }

    /**
     * Is intersecting boolean.
     *
     * @param other the other
     * @return the boolean
     */
// Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        // first line is represented as c1 = a1x + b1y
        double a1 = this.end.getY() - this.start.getY();
        double b1 = this.start.getX() - this.end.getX();
        double c1 = a1 * (this.start.getX()) + b1 * (this.start.getY());
        // second line is represented as c2 = a2x + b2y
        double a2 = other.end.getY() - other.start.getY();
        double b2 = other.start.getX() - other.end.getX();
        double c2 = a2 * (other.start.getX()) + b2 * (other.start.getY());
        //calculating the determinant of the lines
        double determinant = a1 * b2 - a2 * b1;
        //if the determinant=0 the lines do not intersect
        if (determinant == 0) {
            if (other.checkPointOnLine(this.start) || other.checkPointOnLine(this.end)
                    || this.checkPointOnLine(other.start) || this.checkPointOnLine(other.end)) {
                return true;
            }
            return false;
        } else {
            //calculating the point of intersection
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            Point intersection = new Point(x, y);
            //checking if where the lines intersect
            if ((int) this.length() == (int) (intersection.distance(this.start) + intersection.distance(this.end))) {
                if ((int) other.length() == ((int) (intersection.distance(other.start)
                        + intersection.distance(other.end)))) {
                    return true;
                }
            }
            //if the lines do intersect but not in the lines segment
            return false;
        }
    }

    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the point
     */
// Returns the intersection point if the lines intersect, and null otherwise.
    public Point intersectionWith(Line other) {

        if (!this.isIntersecting(other)) {
            return null;
        }
        // first line is represented as c1 = a1x + b1y
        double a1 = this.end.getY() - this.start.getY();
        double b1 = this.start.getX() - this.end.getX();
        double c1 = a1 * (this.start.getX()) + b1 * (this.start.getY());
        // second line is represented as c2 = a2x + b2y
        double a2 = other.end.getY() - other.start.getY();
        double b2 = other.start.getX() - other.end.getX();
        double c2 = a2 * (other.start.getX()) + b2 * (other.start.getY());
        //calculating the point of intersection
        double determinant = a1 * b2 - a2 * b1;
        if (determinant != 0) {
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            Point intersection = new Point(x, y);
            return intersection;
        } else {
            if (other.checkPointOnLine(this.start)) {
                return this.start;
            }
            if (this.checkPointOnLine(other.start)) {
                return other.start;
            }
            if (this.checkPointOnLine(other.end)) {
                return other.end;
            }
            return this.end;
        }

    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if (this.start.getX() == other.start.getX() && this.start.getY() == other.start.getY()) {
            if (this.end.getX() == other.end.getX() && this.end.getY() == other.end.getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the point
     */
// If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        }
        List<Point> intersection = rect.intersectionPoints(this);
        Point i1 = intersection.get(0);
        if (intersection.size() == 2) {
            Point i2 = intersection.get(1);
            double distance1 = start.distance(i1);
            double distance2 = start.distance(i2);
            double min = Math.min(distance1, distance2);
            if (min == distance1) {
                return i1;
            }
            return i2;
        }
        return i1;
    }


    /**
     * Check point on line boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean checkPointOnLine(Point point) {
        if (point == null) {
            return false;
        }
        //calculate the max and min value of x,y of the line.
        double maxX = Math.max(this.start.getX(), this.end.getX());
        double minX = Math.min(this.start.getX(), this.end.getX());
        double maxY = Math.max(this.start.getY(), this.end.getY());
        double minY = Math.min(this.start.getY(), this.end.getY());
        if (point.getX() >= minX - 0.05 && point.getX() <= maxX + 0.05 && point.getY() >= minY - 0.05 && point.getY()
                <= maxY + 0.05) {
            return true;
        }
        return false;
    }
}