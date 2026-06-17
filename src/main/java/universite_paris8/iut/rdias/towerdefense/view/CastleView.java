package universite_paris8.iut.rdias.towerdefense.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.rdias.towerdefense.model.Castle;

/*
 * CastleView - Gère l'affichage et l'animation du château
 *
 * Cette classe est permet de :
 * - Afficher le château sur l'écran de jeu
 * - Animer le château en basculant entre 4 sprites (tile_castle1 à tile_castle4)
 * - Fournir accès à l'ImageView du château pour les bindings JavaFX.
 */

public class CastleView {
    private ImageView image;
    private static String pathBegin = "/universite_paris8/iut/rdias/towerdefense/sprite/ground/tile_castle";
    private static Image castleSprite1 = new Image(GroundView.class.getResourceAsStream(pathBegin + "/tile_castle1.png"));
    private static Image castleSprite2 = new Image(GroundView.class.getResourceAsStream(pathBegin + "/tile_castle2.png"));
    private static Image castleSprite3 = new Image(GroundView.class.getResourceAsStream(pathBegin + "/tile_castle3.png"));
    private static Image castleSprite4 = new Image(GroundView.class.getResourceAsStream(pathBegin + "/tile_castle4.png"));

    public CastleView(Castle c) {
        this.image = new ImageView(castleSprite1);
        this.image.setFitWidth(1000);
        this.image.setFitHeight(1000);
        this.image.setLayoutX(40*16);
        this.image.setLayoutY(41*16);
        this.image.setId("Chateau");
    }


    public ImageView getCastleImage() {
        return image;
    }

    public void switchImage() {
        if (getCastleImage().imageProperty().getValue().equals(castleSprite1)) {
            getCastleImage().imageProperty().setValue(castleSprite2);
        }
        else if (getCastleImage().imageProperty().getValue().equals(castleSprite2)) {
            getCastleImage().imageProperty().setValue(castleSprite3);
        }
        else if (getCastleImage().imageProperty().getValue().equals(castleSprite3)) {
            getCastleImage().imageProperty().setValue(castleSprite4);
        }
        else {
            getCastleImage().imageProperty().setValue(castleSprite1);
        }

    }
}
