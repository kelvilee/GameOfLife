import java.io.Serializable;
import java.util.List;

/**
 * Game that constructs a world with specific dimensions.
 * 
 * @author kelvinlee
 *
 */
public class Game implements Serializable {

    /** Dimensions of this world */
    private final int size = 50;

    /** The games world */
    private World world;

    /**
     * Constructs a Game. Initializes the world and draws it.
     *
     */
    public Game() {
        world = new World(size, size);
        world.initialize();
        world.draw();
    }

    /**
     * Takes a turn and activates lifeform functions. Redraws the world with new
     * positions.
     */
    void takeTurn() {
        // gets all lifeforms from this world
        List<Lifeform> list = world.getLifeforms();

        // iterate over each lifeform and call their move
        for (Lifeform life : list) {
            life.move(world.getNeighbours(life.getCell()));
            life.procreate(world.getNeighbours(life.getCell()));
        }
        
        // Always draw at the end
        world.draw();
    }

    /**
     * Gets the game's world
     * 
     * @return World
     */
    World getWorld() {
        return world;
    }

    /**
     * Gets the size of this game
     * 
     * @return int
     */
    int getSize() {
        return size;
    }
}
