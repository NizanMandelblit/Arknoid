import java.util.ArrayList;
import java.util.List;

/**
 * The type GameLevel environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables;


    /**
     * Instantiates a new GameLevel environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }


    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
// Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (trajectory == null) {
            return null;
        }
        Point min = null;
        Collidable shape = null;
        for (Collidable collidable : this.collidables) {
            Point impact = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            //if its the first time the point was computed
            if (min == null) {
                min = impact;
                shape = collidable;
            } else {
                if (impact != null) {
                    if (impact.distance(trajectory.start()) <= min.distance(trajectory.start())) {
                        min = impact;
                        shape = collidable;
                    }
                }
            }
        }
        if (min != null && shape != null) {
            CollisionInfo result = new CollisionInfo(min, shape);
            return result;
        }
        return null;
    }
}
