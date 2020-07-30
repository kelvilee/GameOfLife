import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

/**
 * Main method where the program starts
 *
 * @author kelvinlee
 */
public class Main extends Application {

    Game game = new Game();
    
    World world = game.getWorld();

    GridPane grid = new GridPane();
    
    /**
     * JavaFX GUI construction
     */
    public void start(Stage stage) {
        
        Button save = new Button("Save");
        Button open = new Button("Open");

        int size = game.getSize();

        // Handles Save mouseclick
        save.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Save File");
                    fileChooser.setInitialFileName("GOL.ser");
                    File savedFile = fileChooser.showSaveDialog(stage);
                    
                    FileOutputStream fileOut = new FileOutputStream(savedFile);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(game);
                    out.close();
                    fileOut.close();
                    System.out.println("saved");
                } catch (Exception e) {
                    System.out.println("no file saved");
                    //e.printStackTrace();
                }

            }
        });
        
        // Handles open mouseclick
        open.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    
                    FileChooser fileChooser = new FileChooser();
                    File selectedFile = fileChooser.showOpenDialog(stage);
                    
                    FileInputStream fileIn = new FileInputStream(selectedFile);
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    game = (Game) in.readObject();
                    world = game.getWorld();
                    
                    grid.getChildren().clear();
                    for (int row = 0; row < size; row++) {
                        for (int col = 0; col < size; col++) {
                            world.getCellAt(col, row).draw();
                            grid.add(world.getCellAt(col, row).getSquare(), col, row);
                        }
                    }
                    
                    in.close();
                    fileIn.close();
                    System.out.println("loaded");
                    
                } catch (Exception e) {
                    System.out.println("class not found/nothing selected");
                    //e.printStackTrace();
                }
            }
        });
        
        VBox box = new VBox(grid, save, open);


        // initial display
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid.add(world.getCellAt(col, row).getSquare(), col, row);
            }
        }

        // handles clicks
        grid.setOnMouseClicked(new EventHandler<Event>() {

            public void handle(Event arg0) {
                game.takeTurn();
                // refresh display after every click
                grid.getChildren().clear();
                for (int row = 0; row < size; row++) {
                    for (int col = 0; col < size; col++) {
                        grid.add(world.getCellAt(col, row).getSquare(), col, row);
                    }
                }

            }
        });


        Scene scene = new Scene(box, 600, 600);
        stage.setScene(scene);
        stage.setTitle("Game of Life");
        stage.show();
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
