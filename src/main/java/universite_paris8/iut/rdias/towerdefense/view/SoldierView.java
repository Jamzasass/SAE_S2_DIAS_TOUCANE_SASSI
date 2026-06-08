package universite_paris8.iut.rdias.towerdefense.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;
import universite_paris8.iut.rdias.towerdefense.model.actor.Soldier;
import universite_paris8.iut.rdias.towerdefense.model.actor.enemy.ArcherViking;
import universite_paris8.iut.rdias.towerdefense.model.actor.enemy.Viking;

public class SoldierView {

    private Soldier soldier;
    private ImageView image;
    private Rectangle pvBar;

    private static Image imageViking1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/vikings_viking/tile_vikingSoldier1.png"));
    private static Image imageViking2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/vikings_viking/tile_vikingSoldier2.png"));

    private static Image imageknight1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/knight/tile_knight1.png"));
    private static Image imageKnight2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/knight/tile_knight2.png"));

    private static Image imageArcherViking1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/vikings_viking/tile_vikingSoldier1.png"));
    private static Image imageArcherViking2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/vikings_viking/tile_vikingSoldier2.png"));


    public SoldierView(Soldier sSoldier) {

        this.soldier = sSoldier;
        this.pvBar = new Rectangle();
        pvBar.setHeight(2);

        if (soldier instanceof Viking) {
            pvBar.widthProperty().bind(sSoldier.getHpPorperty().multiply(0.4));
            pvBar.setFill(Color.GREEN);
            this.image = new ImageView(imageViking1);
            image.setFitWidth(25);
            image.setFitHeight(25);
            this.image.setId("v" + soldier.getId());
            this.pvBar.setId("vP" + soldier.getId());
            this.image.layoutXProperty().bind(this.soldier.getXProperty().multiply(16).add(-16/2));
            this.image.layoutYProperty().bind(this.soldier.getYProperty().multiply(16).add(-16/2));
            pvBar.layoutXProperty().bind(image.layoutXProperty().add(25/2).add(sSoldier.getHpPorperty().multiply(0.4).multiply(-0.5)));
            pvBar.layoutYProperty().bind(image.layoutYProperty());
        }
        else if (soldier instanceof Knight) {
            pvBar.setFill(Color.BLUE);
            pvBar.widthProperty().bind(sSoldier.getHpPorperty().multiply(0.4));
            this.image = new ImageView(imageknight1);
            image.setFitWidth(29);
            image.setFitHeight(29);
            this.image.setId("k" + soldier.getId());
            this.pvBar.setId("kP" + soldier.getId());
            this.image.layoutXProperty().bind(this.soldier.getXProperty().multiply(16).add(-16/2));
            this.image.layoutYProperty().bind(this.soldier.getYProperty().multiply(16).add(-16/2));
            pvBar.layoutXProperty().bind(image.layoutXProperty().add(29/2).add(sSoldier.getHpPorperty().multiply(0.4).multiply(-0.5)));
            pvBar.layoutYProperty().bind(image.layoutYProperty());
        }
        else if (soldier instanceof ArcherViking) {
            pvBar.widthProperty().bind(sSoldier.getHpPorperty().multiply(0.4));
            pvBar.setFill(Color.RED);
            this.image = new ImageView(imageArcherViking1);
            image.setFitWidth(25);
            image.setFitHeight(25);
            this.image.setId("v" + soldier.getId());
            this.pvBar.setId("vP" + soldier.getId());
            this.image.layoutXProperty().bind(this.soldier.getXProperty().multiply(16).add(-16/2));
            this.image.layoutYProperty().bind(this.soldier.getYProperty().multiply(16).add(-16/2));
            pvBar.layoutXProperty().bind(image.layoutXProperty().add(25/2).add(sSoldier.getHpPorperty().multiply(0.4).multiply(-0.5)));
            pvBar.layoutYProperty().bind(image.layoutYProperty());
        }


    }

    public ImageView getImage() {
        return this.image;
    }
    public Rectangle getPvBar() {
        return this.pvBar;
    }

    public void switchImage() {
        if (this.getSoldier() instanceof Viking) {
            if (image.getImage().equals(imageViking1)) {
                image.setImage(imageViking2);
            } else {
                image.setImage(imageViking1);
            }
        }
        else if (this.getSoldier() instanceof ArcherViking) {
            if (image.getImage().equals(imageArcherViking1)) {
                image.setImage(imageArcherViking2);
            } else {
                image.setImage(imageArcherViking1);
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
