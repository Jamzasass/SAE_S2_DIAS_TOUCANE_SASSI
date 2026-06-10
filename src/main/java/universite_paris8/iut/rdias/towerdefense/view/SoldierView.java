package universite_paris8.iut.rdias.towerdefense.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;
import universite_paris8.iut.rdias.towerdefense.model.actor.Soldier;
import universite_paris8.iut.rdias.towerdefense.model.actor.enemy.*;

public class SoldierView {

    private Soldier soldier;
    private ImageView image;
    private Rectangle pvBar;

    private static Image imageViking1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/vikings_viking/tile_vikingSoldier1.png"));
    private static Image imageViking2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/vikings_viking/tile_vikingSoldier2.png"));

    private static Image imageknight1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/knight/tile_knight1.png"));
    private static Image imageKnight2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/knight/tile_knight2.png"));

    private static Image imageArcherViking1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/vikings_archerer/tile_vikingarcherer1.png"));
    private static Image imageArcherViking2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/vikings_archerer/tile_vikingarcherer2.png"));

    private static Image imageBerserker1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/vikings_berserker/tile_vikingberserker1.png"));
    private static Image imageBerserker2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/vikings_berserker/tile_vikingberserker2.png"));

    private static Image imageShiedlViking1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/vikings_bouclier/tile_bouclierSoldier1.png"));
    private static Image imageShiedlViking2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/vikings_bouclier/tile_bouclierSoldier2.png"));

    private static Image imageBatteringRam1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/battering_ram/sprite_BatteringRam1.png"));
    private static Image imageBatteringRam2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/battering_ram/sprite_BatteringRam2.png"));

    public SoldierView(Soldier sSoldier) {
        this.soldier = sSoldier;
        this.pvBar = new Rectangle();
        pvBar.setHeight(2);
        pvBar.setFill(Color.RED);
        int imageSize = 25;
        if (soldier instanceof Viking) {
            this.image = new ImageView(imageViking1);
            this.image.setId("v" + soldier.getId());
            this.pvBar.setId("vP" + soldier.getId());
        }
        else if (soldier instanceof Knight) {
            pvBar.setFill(Color.BLUE);
            this.image = new ImageView(imageknight1);
            imageSize = 29;
            this.image.setId("k" + soldier.getId());
            this.pvBar.setId("kP" + soldier.getId());;
        }
        else if (soldier instanceof ArcherViking) {
            this.image = new ImageView(imageArcherViking1);
            this.image.setId("v" + soldier.getId());
            this.pvBar.setId("vP" + soldier.getId());
        }
        else if (soldier instanceof Berserker) {
            this.image = new ImageView(imageBerserker1);
            this.image.setId("v" + soldier.getId());
            this.pvBar.setId("vP" + soldier.getId());
        }
        else if (soldier instanceof RamWarrior) {
            this.image = new ImageView(imageBatteringRam1);
            this.image.setId("v" + soldier.getId());
            this.pvBar.setId("vP" + soldier.getId());
        }
        else if (soldier instanceof ShieldViking) {
            this.image = new ImageView(imageShiedlViking1);
            this.image.setId("v" + soldier.getId());
            this.pvBar.setId("vP" + soldier.getId());
        }
        pvBar.widthProperty().bind(sSoldier.getHpProperty().multiply(0.4));
        image.setFitWidth(imageSize);
        image.setFitHeight(imageSize);
        this.image.layoutXProperty().bind(this.soldier.getXProperty().multiply(16).add(-16/2));
        this.image.layoutYProperty().bind(this.soldier.getYProperty().multiply(16).add(-16/2));
        pvBar.layoutXProperty().bind(image.layoutXProperty().add(imageSize/2).add(sSoldier.getHpProperty().multiply(0.4).multiply(-0.5)));
        pvBar.layoutYProperty().bind(image.layoutYProperty());

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
        else if (soldier instanceof Knight){
            if (image.getImage().equals(imageknight1)) {
                image.setImage(imageKnight2);
            } else {
                image.setImage(imageknight1);
            }
        }
        else if (soldier instanceof Berserker) {
            if (image.getImage().equals(imageBerserker1)) {
                image.setImage(imageBerserker2);
            }
            else {
                image.setImage(imageBerserker1);
            }
        }
        else if (soldier instanceof RamWarrior) {
            if (image.getImage().equals(imageBatteringRam1)) {
                image.setImage(imageBatteringRam2);
            }
            else {
                image.setImage(imageBatteringRam1);
            }
        }
        else if (soldier instanceof ShieldViking) {
            if (image.getImage().equals(imageShiedlViking1)) {
                image.setImage(imageShiedlViking2);
            }
            else {
                image.setImage(imageShiedlViking1);
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
