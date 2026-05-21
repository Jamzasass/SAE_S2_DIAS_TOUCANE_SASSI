package universite_paris8.iut.rdias.towerdefense.view;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.rdias.towerdefense.model.Actor;
import universite_paris8.iut.rdias.towerdefense.model.Soldier;

public class SoldierView {
    private Soldier soldier;
    private int id;
    private ImageView image;
    private static Image imageViking1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_vikingSoldier1.png"));
    private static Image imageViking2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_vikingSoldier2.png"));

    public SoldierView(Soldier sSoldier, int sId) {
        this.soldier = sSoldier;
        this.id = sId;
        this.image = new ImageView(imageViking1);
        image.setFitWidth(25);
        image.setFitHeight(25);
        this.image.setSmooth(false);
        this.image.setId(sId + "");
        this.image.layoutXProperty().bind(this.soldier.getXProperty().multiply(16).add(16/2.0));
        this.image.layoutYProperty().bind(this.soldier.getYProperty().multiply(16).add(16/2.0));
    }

    public ImageView getImage() {
        return this.image;
    }

    public void switchImage() {
        if (image.getImage().equals(imageViking1)) {
            image.setImage(imageViking2);
        }
        else {
            image.setImage(imageViking1);
        }
    }


}
