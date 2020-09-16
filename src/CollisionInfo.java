/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Point collision;
    private Collidable object;

    /**
     * Instantiates a new Collision info.
     *
     * @param point  the point
     * @param object the object
     */
    public CollisionInfo(Point point, Collidable object) {
        this.collision = point;
        this.object = object;
    }


    /**
     * Collision point point.
     *
     * @return the point
     */
// the point at which the collision occurs.
    public Point collisionPoint() {
        return this.collision;
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable
     */
// the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.object;
    }
}
