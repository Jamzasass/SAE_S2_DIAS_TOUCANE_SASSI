package universite_paris8.iut.rdias.towerdefense.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rdias.towerdefense.model.*;

public class GroundView {
    //temporaire
    private Environnement env;

    private Ground ground;
    private TilePane mapGrid;
    private Pane actorsArea;

    private static Image spriteGrass1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_grass/tile_grass1.png"));
    private static Image spriteGrass2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_grass/tile_grass2.png"));
    private static Image spriteGrass3 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_grass/tile_grass3.png"));
    private static Image spriteGrass4 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_grass/tile_grass4.png"));

    private static Image spriteWater1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_water/tile_water1.png"));
    private static Image spriteWater2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_water/tile_water2.png"));
    private static Image spriteWater3 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_water/tile_water3.png"));
    private static Image spriteWater4 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_water/tile_water4.png"));
    //Straight paths
    private static Image spritePath1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_path/tile_path1.png"));
    private static Image spriteStraightPathVR = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_path/tile_StraightPathVR.png"));
    private static Image spriteStraightPathVL = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_path/tile_StraightPathVL.png"));
    private static Image spriteStraightPathHT = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_path/tile_StraightPathHT.png"));
    private static Image spriteStraightPathHB = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_path/tile_StraightPathHB.png"));
    // Corner paths
    private static Image spriteBottomCornerR = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_path/tile_BottomCornerR.png"));
    private static Image spriteBottomCornerL = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_path/tile_BottomCornerL.png"));
    private static Image spriteBottomTopR = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_path/tile_TopCornerR.png"));
    private static Image spriteBottomTopL = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_path/tile_TopCornerL.png"));
    private static Image spriteBridge = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_path/tile_Bridge.png"));


    public GroundView(Ground ground, TilePane mapGrid, Pane actorsArea, Environnement env){
        this.env = env;

        this.ground = ground;
        this.mapGrid = mapGrid;
        this.actorsArea = actorsArea;

    }

    public void drawMap() {
        Image spriteCastle = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_groundcastle/tile_center.png"));

        double widthGround = ground.width() * 16;
        double heigthGround = ground.heigth() * 16;

        mapGrid.setPrefColumns(ground.width());
        mapGrid.setPrefRows(ground.heigth());
        mapGrid.setPrefTileWidth(16);
        mapGrid.setPrefTileHeight(16);
        mapGrid.setMinSize(widthGround, heigthGround);
        mapGrid.setMaxSize(widthGround, heigthGround);
        mapGrid.getChildren().clear();


        for (int line = 0; line < ground.heigth(); line++) {
            for (int col = 0; col < ground.width(); col++) {
                int tileType = ground.idTuile(line, col);
                ImageView sprite = new ImageView();
                sprite.setId(col + "," + line);
                if (tileType == 0) {
                    sprite.setImage(grassSelection());
                } else if (tileType == 1) {
                    sprite.setImage(pathSelection(line, col));
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


    public Image pathSelection(int l, int col) {
        //For auto path
        boolean above = isPath(l - 1, col);
        boolean below = isPath(l+1, col);
        boolean left = isPath(l, col-1);
        boolean right = isPath(l, col+1);


        boolean belowW = isWater(l+1, col);
        boolean belowWPlus = isWater(l+2, col);
        boolean leftW = isWater(l, col-1);
        boolean rightW = isWater(l, col+1);
        boolean leftWPlus = isWater(l, col-2);
        boolean rightWPlus = isWater(l, col+2);

        if ((belowWPlus && below && left && right) || (above && belowW && left && right) || (above && below && leftW && right && rightWPlus) || (above && below && rightW && left && leftWPlus))
            return spriteBridge;
        if (above && below && left && !right)
            return spriteStraightPathVR;
        if (above && below && !left && right)
            return spriteStraightPathVL;
        if (left && right && !above && below)
            return spriteStraightPathHT;
        if (left && right && above && !below)
            return spriteStraightPathHB;
        if (above && left && !below && !right)
            return spriteBottomCornerR;
        if (above && right && !below && !left)
            return spriteBottomCornerL;
        if (below && left && !above && !right)
            return spriteBottomTopR;

        if (below && right && !above && !left)
            return spriteBottomTopL;

        return spritePath1;
    }

    private boolean isPath(int l, int col) {
        if (l < 0 || col < 0 || l >= this.ground.heigth() || col >= this.ground.width()) {
            return false;
        }
        return this.ground.idTuile(l, col) == 1;
    }

    private boolean isWater(int l, int col) {
        if (l < 0 || col < 0 || l >= this.ground.heigth() || col >= this.ground.width()) {
            return false;
        }
        return this.ground.idTuile(l, col) == 3;
    }


}
