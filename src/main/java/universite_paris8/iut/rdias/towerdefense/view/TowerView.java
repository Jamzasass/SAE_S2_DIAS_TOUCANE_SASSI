package universite_paris8.iut.rdias.towerdefense.view;

import javafx.beans.binding.Bindings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.*;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

public class TowerView {
    private Tower tower;
    private ImageView image;
    private Rectangle hpBarFirst;
    private Rectangle hpBarSecond;

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
        this.image.setId("t" + tTower.getId());
        this.image.layoutXProperty().bind(this.tower.getXProperty().multiply(16).add(-16/2));
        this.image.layoutYProperty().bind(this.tower.getYProperty().multiply(16).add(-16/2));
        if (tower.getMaxHp() > 0) {
            createHpBar();
        }
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

    private void createHpBar() {
        hpBarFirst = new Rectangle(16, 2);
        hpBarFirst.setFill(Color.rgb(40, 25, 20, 0.9));
        hpBarFirst.layoutXProperty().bind(image.layoutXProperty().add((32-16) / 2));
        hpBarFirst.layoutYProperty().bind(image.layoutYProperty().subtract(3));
        hpBarFirst.setId("tFB" + tower.getId());

        hpBarSecond = new Rectangle(16, 2);
        hpBarSecond.setFill(Color.CYAN);
        hpBarSecond.widthProperty().bind(
                Bindings.createDoubleBinding(
                        () -> 16.0 * tower.getHp() / Math.max(1, tower.getMaxHp()),
                        tower.getHpPorperty()
                )
        );
        hpBarSecond.layoutXProperty().bind(image.layoutXProperty().add((32-16) / 2));
        hpBarSecond.layoutYProperty().bind(image.layoutYProperty().subtract(3));
        hpBarSecond.setId("tSB" + tower.getId());
    }

    public Rectangle getHpBarFirst() { return hpBarFirst; }
    public Rectangle getHpBarSecond() { return hpBarSecond; }
}
