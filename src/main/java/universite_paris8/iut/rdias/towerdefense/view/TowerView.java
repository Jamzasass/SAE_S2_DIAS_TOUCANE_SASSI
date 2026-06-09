package universite_paris8.iut.rdias.towerdefense.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.*;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

public class TowerView {
    private Tower tower;
    private ImageView image;
    private static Image spriteArcher = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/archer_tower/sprite_ArcherTowerNiv1.png"));
    private static Image spriteBarrack = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/barrack_tower/sprite_BarrackTower1.png"));
    private static Image spriteBallista = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/tower_ballista/sprite_BallistaTower1.png"));
    private static Image spriteBramble = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/bramble_tower/sprite_BrambleTower4.png"));
    private static Image spritePalissade = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/palissade_tower/sprite_PalissadeTower1.png"));
    public TowerView(Tower tTower) {
        this.tower = tTower;
        if (tTower instanceof Archer) {
            this.image = new ImageView(spriteArcher);
        }
        else if (tTower instanceof Barrack) {
            this.image = new ImageView(spriteBarrack);
        }
        else if (tTower instanceof Ballista){
            this.image = new ImageView(spriteBallista);
        }
        else if (tTower instanceof Bramble){
            this.image = new ImageView(spriteBramble);
        }
        else if (tTower instanceof Palissade){
            this.image = new ImageView(spritePalissade);
        }
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
