package universite_paris8.iut.rdias.towerdefense.view;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.rdias.towerdefense.model.Actor;
import universite_paris8.iut.rdias.towerdefense.model.Knight;
import universite_paris8.iut.rdias.towerdefense.model.Soldier;
import universite_paris8.iut.rdias.towerdefense.model.Vikings;

public class SoldierView {

    private Soldier soldier;
    private ImageView image;
    private static Image imageViking1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_vikingSoldier1.png"));
    private static Image imageViking2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_vikingSoldier2.png"));

    private static Image imageknight1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_knight1.png"));
    private static Image imageKnight2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tile_knight2.png"));


    public SoldierView(Soldier sSoldier) {
        this.soldier = sSoldier;
        if (soldier instanceof Vikings) {
            this.image = new ImageView(imageViking1);
            image.setFitWidth(25);
            image.setFitHeight(25);
            this.image.setId(soldier.getId() + "");
            this.image.layoutXProperty().bind(this.soldier.getXProperty().multiply(16).add(-16/2));
            this.image.layoutYProperty().bind(this.soldier.getYProperty().multiply(16).add(-16/2));
        }
        else if (soldier instanceof Knight) {
            this.image = new ImageView(imageknight1);
            image.setFitWidth(29);
            image.setFitHeight(29);
            this.image.setId(soldier.getId() + "");
            this.image.layoutXProperty().bind(this.soldier.getXProperty().multiply(16).add(-16/2));
            this.image.layoutYProperty().bind(this.soldier.getYProperty().multiply(16).add(-16/2));
        }

    }

    public ImageView getImage() {
        return this.image;
    }

    public void switchImage() {
        if (this.getSoldier() instanceof Vikings) {
            if (image.getImage().equals(imageViking1)) {
                image.setImage(imageViking2);
            } else {
                image.setImage(imageViking1);
            }
        }
        else {
            if (image.getImage().equals(imageknight1)) {
                image.setImage(imageKnight2);
            } else {
                image.setImage(imageknight1);
            }
        }
    }


    public Soldier getSoldier() {
        return soldier;
    }
    public double getX() {
        return image.getX();
    }
    public double getY() {
        return image.getY();
    }
    public int getId() {
        return this.soldier.getId();
    }


    public double getTuileLine() {
        double x = this.getImage().getLayoutX() + 20/2;
        double posTuileX = (x - x%16);
        posTuileX = posTuileX/16;
        return posTuileX;
    }

    public double getTuileColumn() {
        double y = this.getImage().getLayoutY() + 20/2;
        double posTuileY = (y - y%16);
        posTuileY = posTuileY/16;
        return posTuileY;
    }
}
