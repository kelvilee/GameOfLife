import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Omnivores implement carnivore edible, and can eat all other lifeforms
 * and give birth
 * @author kelvinlee
 *
 */
public class Omnivore extends Lifeform implements CarnivoreEdible {

    /**
     * Constructs an omnivore
     * @param c
     */
    public Omnivore(Cell c) {
        super(c);
        setHunger(5);
    }

    /*
     * Draws an omnivore
     */
    @Override
    void draw() {
        cell.getSquare().setFill(Color.BLUE);
    }
    
    /*
     * Checks to see if lifeform is omnivore edible
     */
    protected boolean edible(Lifeform life) {
        if(life instanceof OmnivoreEdible) {
            return true;
        }
        return false;
    }

    /*
     * Omnivores can give birth under certain conditions
     */
    @Override
    void procreate(List<Cell> neighbours) {

        int omnivoreCount = 0;
        int foodCount = 0;

        // Valid seeding spots
        List<Cell> validBirth = new ArrayList<Cell>();

        // iterates over neighbours, counting food and adds valid seeding spots
        for (Cell item : neighbours) {
            if (item.getLifeform() instanceof Omnivore)
                omnivoreCount++;
            else if(item.getLifeform() instanceof OmnivoreEdible)
                foodCount++;
            else if(item.getLifeform() == null)
                validBirth.add(item);
        }

        // if criteria are met, create a new omni in one of the empty spots
        if (foodCount == 1 && omnivoreCount >= 1 && validBirth.size() >= 3) {
            int num = RandomGenerator.nextNumber(validBirth.size());
            validBirth.get(num).setLifeform(new Omnivore(validBirth.get(num)));
            //tempCell.setLifeform(new Omnivore(tempCell));
        }

    }
}
