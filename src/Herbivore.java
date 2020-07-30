import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Herbivore has a cell that is inherited from lifeform. Herbivores do not
 * procreate but can move into specific neighbouring cells.
 * 
 * @author kelvinlee
 *
 */
public class Herbivore extends Lifeform implements CarnivoreEdible, OmnivoreEdible {

    /**
     * Constructs a Herbivore. Inherits cell from parent and sets initial hunger to
     * 5.
     */
    public Herbivore(Cell c) {
        super(c);
        setHunger(5);
    }

    /**
     * Draws the Herbivore and colors it YELLOW
     */
    public void draw() {
        cell.getSquare().setFill(Color.YELLOW);
    }
    
    protected boolean edible(Lifeform life) {
        if(life instanceof HerbivoreEdible) {
            return true;
        }
        return false;
    }

    /**
     * Herbivores procreate
     */
    @Override
    void procreate(List<Cell> neighbours) {

        int foodCount = 0;
        int herbCount = 0;

        // Valid seeding spots
        List<Cell> validBirth = new ArrayList<Cell>();

        // iterates over neighbours, counting plants and adds valid seeding spots
        for (Cell item : neighbours) {
            if (item.getLifeform() instanceof HerbivoreEdible)
                foodCount++;
            else if(item.getLifeform() instanceof Herbivore)
                herbCount++;
            else if(item.getLifeform() == null)
                validBirth.add(item);
        }

        // if criteria are met, create a new plant in one of the empty spots
        if (foodCount >= 2 && herbCount >= 1 && validBirth.size() >= 2) {
            int num = RandomGenerator.nextNumber(validBirth.size());
            Cell tempCell = validBirth.get(num);
            tempCell.setLifeform(new Herbivore(tempCell));
        }

    }

}
