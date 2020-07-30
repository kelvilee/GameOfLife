import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class Lifeform that represents a lifeform with basic traits.
 * 
 * @author kelvinlee
 *
 */
abstract class Lifeform implements Serializable {

    /** All lifeforms has a cell */
    protected Cell cell;

    /** All lifeforms have a hunger which is optionally used */
    private int hunger = 0;

    /**
     * Constructs a lifeform that has a cell
     * 
     * @param c
     *            Cell
     */
    public Lifeform(Cell c) {
        cell = c;
    }

    /**
     * All lifeforms must implement a draw
     */
    abstract void draw();

    public void setHunger(int n) {
        hunger = n;
    }

    /**
     * All lifeforms must implement a procreate
     * 
     * @param neighbours
     */
    abstract void procreate(List<Cell> neighbours);

    /**
     * Gets the Cell of this lifeform instance
     * 
     * @return Cell
     */
    public Cell getCell() {
        return cell;
    }

    /**
     * Classes that inherit lifeform must have edible to check if can eat
     * 
     * @param life
     * @return
     */
    protected abstract boolean edible(Lifeform life);

    /**
     * Move method implemented by all lifeforms except plant
     * 
     * @param neighbours
     */
    public void move(List<Cell> neighbours) {


        Cell result = null;
        
        while (neighbours.size() > 0) {
            int num = RandomGenerator.nextNumber(neighbours.size());
            result = neighbours.remove(num);
            if(edible(result.getLifeform()))
                break;
        }

        if (result == null)
            return;

        Lifeform species = result.getLifeform();

        if (cell.getLifeform() != null) {
            if (species != null) {
                hunger = 5;
            } else {
                hunger--;
            }
            if (hunger <= 0) {
                cell.setLifeform(null);

            } else {
                result.setLifeform(this);
                cell.setLifeform(null);
                cell = result;
            }
        }

    }

}
