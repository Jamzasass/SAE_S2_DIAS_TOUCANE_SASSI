package universite_paris8.iut.rdias.towerdefense.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class View {

    public static void drawMap(TilePane mapGrid) {
        int[][] map = {
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2}
        };
        Image spriteGrass = new Image(View.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_grass.png"));
        Image spritePath = new Image(View.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_path.png"));
        Image spriteCastle = new Image(View.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_castle.png"));
        Image spriteWater = new Image(View.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_water.png"));

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                int tileType = map[y][x];
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
