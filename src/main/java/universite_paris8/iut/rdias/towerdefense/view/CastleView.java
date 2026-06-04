package universite_paris8.iut.rdias.towerdefense.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.rdias.towerdefense.model.Castle;

public class CastleView {
    private Castle player;
    private ImageView image;
    private static Image castleSprite = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/ground/castle_32x32.png"));


    public CastleView(Castle c) {
        this.image = new ImageView(castleSprite);
        this.image.setFitWidth(32);
        this.image.setFitHeight(32);
        this.image.setLayoutX(12);
        this.image.setLayoutY(12);
        this.image.setId("Chateau");
        System.out.println(" " + this.image.getLayoutX() + " " + this.image.getLayoutY());
        System.out.println("sprite error=" + castleSprite.isError()
                + " w=" + castleSprite.getWidth() + " h=" + castleSprite.getHeight());
    }


    public ImageView getCastleImage() {
        return image;
    }
}
