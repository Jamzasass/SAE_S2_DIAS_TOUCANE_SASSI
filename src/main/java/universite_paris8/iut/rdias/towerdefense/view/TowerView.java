package universite_paris8.iut.rdias.towerdefense.view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.*;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

public class TowerView {
    private Tower tower;
    private Pane paneViewContainer;
    private ImageView image;
    private Rectangle hpBarFirst;
    private Rectangle hpBarSecond;

    //Sprite lvl1
    //Archer
    private static Image spriteArcher1 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/archer_tower/sprite_ArcherTowerNiv1_1.png"));
    private static Image spriteArcher2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/archer_tower/sprite_ArcherTowerNiv1_2.png"));
    private static Image spriteArcher3 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/archer_tower/sprite_ArcherTowerNiv1_3.png"));
    private static Image spriteArcher4 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/archer_tower/sprite_ArcherTowerNiv1_4.png"));
    private static Image spriteArcher5 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/archer_tower/sprite_ArcherTowerNiv1_5.png"));
    //Barrack
    private static Image spriteBarrack = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/barrack_tower/sprite_BarrackTower1.png"));
    //Ballista
    private static Image spriteBallista = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/tower_ballista/sprite_BallistaTower1.png"));
    //Bramble
    private static Image spriteBramble = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/bramble_tower/sprite_BrambleTower4.png"));
    //Palissade
    private static Image spritePalissade = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/palissade_tower/sprite_PalissadeTower1.png"));
    //Sorcerer
    private static Image spriteSorcerer = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/wizard_tower/sprite_SorcererTower1.png"));

    //Sprite lvl2

    private static Image spriteArcherlvl2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/archer_tower_lvl2/sprite_ArcherTower2.1.png"));
    private static Image spriteBarracklvl2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/barrack_tower_lvl2/sprite_BarrackTowerNiv2_1.png"));
    private static Image spriteBallistalvl2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/tower_ballista_lvl2/sprite_BallistaTower2.1.png"));
    private static Image spriteSorcererlvl2 = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/tower/wizard_tower_lvl2/sprite_towersorcerer2.1.png"));


    public TowerView(Tower tTower) {
        this.tower = tTower;
        this.paneViewContainer = new Pane();
        this.image = new ImageView();
        updateSprite();

        tower.getLevelProperty().addListener((obs, oldVal, newVal) -> {
            updateSprite();
            if (hpBarFirst != null) {
                double dx = (tower.getLevel() == 2) ? -8 : 0;
                double dy = (tower.getLevel() == 2) ? 1 : 0;
                hpBarFirst.setTranslateX(dx);
                hpBarFirst.setTranslateY(dy);
                hpBarSecond.setTranslateX(dx);
                hpBarSecond.setTranslateY(dy);
            }
        });

        this.image.setId("t" + tTower.getId());
        this.image.layoutXProperty().bind(this.tower.getXProperty().multiply(16).add(-16/2));
        this.image.layoutYProperty().bind(this.tower.getYProperty().multiply(16).add(-16/2));
        this.paneViewContainer.getChildren().add(this.image);
        if (!(tTower instanceof Bramble) && tower.getMaxHp() > 0) {
            createHpBar();
            this.paneViewContainer.getChildren().add(this.hpBarFirst);
            this.paneViewContainer.getChildren().add(this.hpBarSecond);
        }
        this.paneViewContainer.setId("t" + this.tower.getId());
    }

    public void updateSprite() {
        int lvl = tower.getLevel();
        Image newImage = null;
        if (tower instanceof Archer) {
            newImage = (lvl == 2) ? spriteArcherlvl2 : spriteArcher1;
        } else if (tower instanceof Barrack) {
            newImage = (lvl == 2) ? spriteBarracklvl2 : spriteBarrack;
        } else if (tower instanceof Ballista) {
            newImage = (lvl == 2) ? spriteBallistalvl2 : spriteBallista;
        } else if (tower instanceof Bramble) {
            newImage = spriteBramble;
        } else if (tower instanceof Palissade) {
            newImage = spritePalissade;
        } else if (tower instanceof SorcererTower) {
            newImage = (lvl == 2) ? spriteSorcererlvl2 : spriteSorcerer;
        }
        if (newImage != null) image.setImage(newImage);
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

    public void createHpBar() {
        double max = Math.max(1, tower.getMaxHp());
        hpBarFirst  = makeBar(16, Color.rgb(40, 25, 20, 0.9));
        hpBarSecond = makeBar(0,  Color.CYAN);
        hpBarSecond.widthProperty().bind(tower.getHpProperty().multiply(16.0 / max));
    }

    public Rectangle makeBar(double width, Color fill) {
        Rectangle bar = new Rectangle(width, 2, fill);
        bar.layoutXProperty().bind(image.layoutXProperty().add(8));
        bar.layoutYProperty().bind(image.layoutYProperty().subtract(3));
        return bar;
    }

    public Rectangle getHpBarFirst() { return hpBarFirst; }
    public Rectangle getHpBarSecond() { return hpBarSecond; }
    public Pane getPaneViewContainer() {
        return paneViewContainer;
    }

    public void switchImage(){
        if (this.getTower() instanceof Archer){
            if (this.tower.getLevel() == 1 ) {
                if (image.getImage().equals(spriteArcher1)) {
                    image.setImage(spriteArcher2);
                } else if (image.getImage().equals(spriteArcher2)) {
                    image.setImage(spriteArcher3);
                } else if (image.getImage().equals(spriteArcher3)) {
                    image.setImage(spriteArcher4);
                } else if (image.getImage().equals(spriteArcher4)) {
                    image.setImage(spriteArcher5);
                } else if (image.getImage().equals(spriteArcher5)) {
                    image.setImage(spriteArcher1);
                }
            }
        }
    }
}
