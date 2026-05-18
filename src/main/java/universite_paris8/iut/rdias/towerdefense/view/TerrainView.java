package universite_paris8.iut.rdias.towerdefense.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rdias.towerdefense.model.Terrain;

public class TerrainView {

    private Terrain terrain;
    private TilePane mapGrid;

    public TerrainView(Terrain terrain, TilePane mapGrid) {
        this.terrain = terrain;
        this.mapGrid = mapGrid;
    }

    public void drawMap() {

        Image spriteGrass = new Image(TerrainView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_grass.png"));
        Image spritePath = new Image(TerrainView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_path.png"));
        Image spriteCastle = new Image(TerrainView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_castle.png"));
        Image spriteWater = new Image(TerrainView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_water.png"));

        for (int ligne = 0; ligne < terrain.hauteur(); ligne++) {
            for (int col = 0; col < terrain.largeur(); col++) {
                int tileType = terrain.codeTuile(ligne,col);
                ImageView sprite= new ImageView();

                if (tileType == 0) {
                    sprite.setImage(spriteGrass);
                } else if (tileType == 1) {
                    sprite.setImage(spritePath);
                } else if (tileType == 2) {
                    sprite.setImage(spriteCastle);
                } else if (tileType == 3) {
                    sprite.setImage(spriteWater);
                } else {
                    sprite.setImage(spriteGrass);
                }

                sprite.setFitWidth(32);
                sprite.setFitHeight(32);
                mapGrid.getChildren().add(sprite);
            }
        }
    }
}
