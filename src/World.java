import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class World implements Serializable {

    /** Grid to hold individual Cells */
    private Cell[][] grid;

    /** Width of the world grid */
    private int width;

    /** Height of the world grid */
    private int height;

    /**
     * Creates a world with a specified width and height to hold Cells.
     * 
     * @param width
     *            width of the world
     * @param height
     *            height of the world
     */
    public World(int w, int h) {
        width = w;
        height = h;
        grid = new Cell[width][height];
    }

    /**
     * Initializes the world
     */
    public void initialize() {
        RandomGenerator.reset();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(null, i, j);
                int testNum = RandomGenerator.nextNumber(99);
                if (testNum >= 80) {
                    grid[i][j].setLifeform(new Herbivore(grid[i][j]));
                } else if (testNum >= 60) {
                    grid[i][j].setLifeform(new Plant(grid[i][j]));
                } else if (testNum >= 50) {
                    grid[i][j].setLifeform(new Carnivore(grid[i][j]));
                } else if (testNum >= 45) {
                    grid[i][j].setLifeform(new Omnivore(grid[i][j]));
                } else {
                    grid[i][j].setLifeform(null);
                }
            }
        }
    }

    /**
     * Draws each Cell
     */
    public void draw() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j].draw();
            }
        }
    }

    /**
     * Gets a specified Cell in the grid
     * 
     * @param i
     *            row
     * @param j
     *            col
     * @return Cell in the grid
     */
    public Cell getCellAt(int i, int j) {
        return grid[i][j];
    }

    /**
     * Gets the neighbours of a Cell
     * 
     * @param c
     *            cell to find neighbours
     * @return ArrayList<Cell>
     */
    public List<Cell> getNeighbours(Cell c) {

        // Empty ArrayList to hold neighbouring Cells
        List<Cell> list = new ArrayList<Cell>();

        // if Cell passed in is null, we dont care about neighbours
        if (c == null)
            return null;

        // Gets the x and y of the Cell passed in
        int x = c.getPosX();
        int y = c.getPosY();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                // prevents neighbour being itself
                if(i == 0 && j == 0)
                    continue;
                
                // All the possible neighbours
                int x1 = x + i;
                int y1 = y + j;

                // If not out of bounds, add to ArrayList
                if ((x1 >= 0) && (x1 < width) && (y1 >= 0) && (y1 < height))
                    list.add(getCellAt(x1, y1));
            }
        }
        return list;
    }

    /**
     * Gets a list of ALL lifeforms that exist in this world
     * 
     * @return List of Lifeforms
     */
    public List<Lifeform> getLifeforms() {
        List<Lifeform> zoo = new ArrayList<Lifeform>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid[i][j].getLifeform() != null) {
                    zoo.add(grid[i][j].getLifeform());
                }
            }
        }
        return zoo;
    }

}
