package universite_paris8.iut.rdias.towerdefense;

import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rdias.towerdefense.model.Terrain;
import universite_paris8.iut.rdias.towerdefense.view.TerrainView;

public class Controller {

    @FXML
    private TilePane mapGrid;

    public void initialize() {

        Terrain terrain = new Terrain();
        TerrainView terrainView = new TerrainView(terrain, mapGrid);
        terrainView.drawMap();

        //définition et démarrage d'un gameloop qui fait env.unTour()

    }
}
