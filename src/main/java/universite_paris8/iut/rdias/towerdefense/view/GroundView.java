package universite_paris8.iut.rdias.towerdefense.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rdias.towerdefense.model.Ground;

public class GroundView {

    private Ground ground;
    private TilePane mapGrid;
    private static Image spriteGrass1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_grass1.png"));
    private static Image spriteGrass2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_grass2.png"));
    private static Image spriteGrass3 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_grass3.png"));
    private static Image spriteGrass4 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_grass4.png"));

    private static Image spriteWater1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_water1.png"));
    private static Image spriteWater2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_water2.png"));
    private static Image spriteWater3 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_water3.png"));
    private static Image spriteWater4 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_water4.png"));

    private static Image spritePath1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_path1.png"));
    private static Image spritePath2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_path2.png"));
    private static Image spritePath3 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_path3.png"));
    private static Image spritePath4 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_path4.png"));

    public GroundView(Ground ground, TilePane mapGrid){
        this.ground = ground;
        this.mapGrid = mapGrid;
    }

    public void drawMap() {

        Image spriteGrass = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_grass.png"));
        Image spritePath = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_path.png"));
        Image spriteCastle = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_castle.png"));

        double widthGround = ground.width() * 16;
        double heigthGround = ground.heigth() * 16;

        mapGrid.setPrefColumns(ground.width());
        mapGrid.setPrefRows(ground.heigth());
        mapGrid.setPrefTileWidth(16);
        mapGrid.setPrefTileHeight(16);
        mapGrid.setMinSize(widthGround, heigthGround);
        mapGrid.setMaxSize(widthGround, heigthGround);
        mapGrid.getChildren().clear();


        for (int ligne = 0; ligne < ground.heigth(); ligne++) {
            for (int col = 0; col < ground.width(); col++) {
                int tileType = ground.codeTuile(ligne,col);
                ImageView sprite= new ImageView();

                if (tileType == 0) {

                    sprite.setImage(grassSelection());
                } else if (tileType == 1) {
                    sprite.setImage(pathSelection());
                } else if (tileType == 2) {
                    sprite.setImage(spriteCastle);
                } else if (tileType == 3) {
                    sprite.setImage(waterSelection());
                } else {
                    sprite.setImage(grassSelection());
                }

                sprite.setFitWidth(16);
                sprite.setFitHeight(16);
                mapGrid.getChildren().add(sprite);
            }
        }
    }

    public Image waterSelection() {
        int random = (int)(Math.random() * 4);
        switch (random) {
            case 0 :
                return spriteWater1;
            case 1 :
                return spriteWater2;
            case 2 :
                return spriteWater3;
            case 3 :
                return spriteWater4;
        }
        return spriteWater1;
    }
    public Image grassSelection() {
        int random = (int) (Math.random() * 4);
        switch (random) {
            case 0:
                return spriteGrass1;
            case 1:
                return spriteGrass2;
            case 2:
                return spriteGrass3;
            case 3:
                return spriteGrass4;
        }
        return spriteGrass4;
    }
    public Image pathSelection() {
        int random = (int) (Math.random() * 4);
        switch (random) {
            case 0:
                return spritePath1;
            case 1:
                return spritePath2;
            case 2:
                return spritePath3;
            case 3:
                return spritePath4;
        }
        return spriteGrass4;
    }
}
