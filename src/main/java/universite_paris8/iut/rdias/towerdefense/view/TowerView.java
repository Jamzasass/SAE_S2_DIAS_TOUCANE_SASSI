package universite_paris8.iut.rdias.towerdefense.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.rdias.towerdefense.model.Tower;
import universite_paris8.iut.rdias.towerdefense.model.Soldier;

public class TowerView {
    private Tower tower;
    private ImageView image;
    private static Image spriteArcherTower = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower_archer/Sprite_TowerArcherNiv1.pngit "));

    public TowerView(Tower tTower) {
        this.tower = tTower;
        this.image = new ImageView(spriteArcherTower);
        this.image.layoutXProperty().bind(this.tower.getXProperty().multiply(16).add(-16/2));
        this.image.layoutYProperty().bind(this.tower.getYProperty().multiply(16).add(-16/2));
    }

    public ImageView getImage() {
        return this.image;
    }public Tower getTower() {
        return tower;
    }
    public double getX() {
        return image.getX();
    }
    public double getY() {
        return image.getY();
    }
    public int getId() {
        return this.tower.getId();
    }
}
