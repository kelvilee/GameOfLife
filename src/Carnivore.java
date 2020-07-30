import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Carnivores can be eatten by omnivores, can move and
 * eat omnivores, herbivores and give birth
 * @author kelvinlee
 *
 */
public class Carnivore extends Lifeform implements OmnivoreEdible {

    /**
     * Constructs a Carnivore
     * @param c
     */
    public Carnivore(Cell c) {
        super(c);
        setHunger(5);
    }

    /*
     * Draws a carnivore
     */
    @Override
    void draw() {
        cell.getSquare().setFill(Color.RED);
    }
    
    /*
     * Checks to see if lifeform can be eatten by carnivore
     */
    protected boolean edible(Lifeform life) {
        if(life instanceof CarnivoreEdible) {
            return true;
        }
        return false;
    }

    /*
     * Carnivores procreate under certain conditions
     */
    @Override
    void procreate(List<Cell> neighbours) {

        int carnivoreCount = 0;
        int foodCount = 0;

        // Valid seeding spots
        List<Cell> validBirth = new ArrayList<Cell>();

        // iterates over neighbours, counting food and adds valid seeding spots
        for (Cell item : neighbours) {
            if (item.getLifeform() instanceof Carnivore)
                carnivoreCount++;
            else if(item.getLifeform() instanceof CarnivoreEdible)
                foodCount++;
            else if(item.getLifeform() == null)
                validBirth.add(item);
        }

        // if criteria are met, create a new carni in one of the empty spots
        if (foodCount == 2 && carnivoreCount >= 1 && validBirth.size() >= 3) {
            int num = RandomGenerator.nextNumber(validBirth.size());
            validBirth.get(num).setLifeform(new Omnivore(validBirth.get(num)));
            //tempCell.setLifeform(new Carnivore(tempCell));
        }

    }
}
