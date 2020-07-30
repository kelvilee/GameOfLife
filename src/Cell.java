import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

/**
 * Cell that has a lifeform and extends GridPane to represent a single cell in
 * the world as a square
 * 
 * @author kelvinlee
 *
 */
public class Cell extends GridPane implements Serializable {

    /** Cells are represented in GUI as a square */
    private transient Rectangle square;

    /** Cell has an lifeform */
    private Lifeform lifeform;

    /** Cell position X */
    private int posX;

    /** Cell position Y */
    private int posY;

    /**
     * Constructs a Cell with a lifeform and position Lifeform
     */
    public Cell(Lifeform life, int x, int y) {
        posX = x;
        posY = y;
        lifeform = life;
    }

    /**
     * Draws the square initially white and calls the lifeform's draw method if
     * lifeform is not null
     */
    public void draw() {
        square = new Rectangle(9, 9);
        square.setStroke(Color.BLACK);
        square.setStrokeWidth(0.5);
        square.setFill(Color.WHITE);
        if (lifeform != null) {
            lifeform.draw();
        }
    }

    /**
     * Gets this square
     * 
     * @return Rectangle
     */
    public Rectangle getSquare() {
        return square;
    }

    /**
     * Gets the X position
     * 
     * @return X as an int
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Gets the Y position
     * 
     * @return Y as an int
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Gets the lifeform
     * 
     * @return Lifeform
     */
    public Lifeform getLifeform() {
        return lifeform;
    }

    /**
     * Sets the lifeform
     * 
     * @param lifeform
     */
    public void setLifeform(Lifeform lifeform) {
        this.lifeform = lifeform;
    }
}
