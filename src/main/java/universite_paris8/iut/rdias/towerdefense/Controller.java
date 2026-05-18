package universite_paris8.iut.rdias.towerdefense;

import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rdias.towerdefense.view.View;

public class Controller {

    @FXML
    private TilePane mapGrid;

    public void initialize() {
        View.drawMap(mapGrid);
    }
}
