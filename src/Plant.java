import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Plant has a cell inherited from lifeform. Plants do not move but can
 * procreate and spread to neighbouring cells.
 * 
 * @author kelvinlee
 *
 */
public class Plant extends Lifeform implements HerbivoreEdible, OmnivoreEdible {

    /**
     * Constructs a Plant that has a cell
     */
    public Plant(Cell c) {
        super(c);
    }

    /**
     * Sets the color of the cell to GREEN
     */
    public void draw() {
        cell.getSquare().setFill(Color.GREEN);
    }


    /**
     * Plants don't move and will do nothing when called
     */
    @Override
    public void move(List<Cell> neighbours) {
        return;
    }
    
    /*
     * Plants will not eat
     */
    protected boolean edible(Lifeform life) {
        return false;
    }


    /**
     * Plant will create a new Plant in a neighbouring cell given 4 plants and 3
     * empty cells are found in the 3x3 vicinity of this original Plant
     */
    @Override
    void procreate(List<Cell> neighbours) {

        int plantCount = 0;

        // Valid seeding spots
        List<Cell> validSeeds = new ArrayList<Cell>();

        // iterates over neighbours, counting plants and adds valid seeding spots
        for (Cell item : neighbours) {
            if (item.getLifeform() instanceof Plant)
                plantCount++;
            else if(item.getLifeform() == null)
                validSeeds.add(item);
        }

        // if criteria are met, create a new plant in one of the empty spots
        if (plantCount >= 2 && validSeeds.size() >= 3) {
            int num = RandomGenerator.nextNumber(validSeeds.size());
            Cell tempCell = validSeeds.get(num);
            tempCell.setLifeform(new Plant(tempCell));
        }

    }

}
