package universite_paris8.iut.rdias.towerdefense.view;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Transform;
import javafx.util.Duration;
import universite_paris8.iut.rdias.towerdefense.model.Effect;
import universite_paris8.iut.rdias.towerdefense.model.Projectile;
import universite_paris8.iut.rdias.towerdefense.model.ZoneSpell;

public class EffectView {
    public static Image spriteArrow = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/projectile/sprite_arrow.png"));
    public static Image spriteFireBall = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/projectile/sprite_fireball.png"));
    public static Image spriteFireBallBlowing = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/projectile/sprite_blow.png"));
    public ImageView image;
    public Effect effect;
    public int id;

    public EffectView(Effect eEffect) {
        this.effect = eEffect;
        if (effect instanceof Projectile) {
            this.image = new ImageView(spriteArrow);
        }
        else if (effect instanceof ZoneSpell) {
            this.image = new ImageView(spriteFireBall);
        }
        this.image.setId("e" + effect.getId());
        this.id = effect.getId();
        this.image.layoutXProperty().bind(effect.getXProperty().multiply(16).add(-16/2));
        this.image.layoutYProperty().bind(effect.getYProperty().multiply(16).add(-16/2));
        this.image.rotateProperty().bind(effect.angleProperty().add(0));
    }

    public ImageView getImage() {
        return image;
    }

    public void blow() {
        if (effect instanceof ZoneSpell) {
            this.image.setImage(spriteFireBallBlowing);
            System.out.println(this.image.getTranslateZ());
            this.image.toBack();
            System.out.println(this.image.getOpacity());
            this.image.setOpacity(0.5);
            this.image.setFitHeight((((ZoneSpell)effect).getZoneRadius()*16));
            this.image.setFitWidth(((ZoneSpell)effect).getZoneRadius()*16);
            double x = this.image.getLayoutX();
            double y = this.image.getLayoutY();
            this.image.layoutXProperty().unbind();
            this.image.layoutYProperty().unbind();
            this.image.setLayoutX(x - ((((ZoneSpell)effect).getZoneRadius()*16)/2));
            this.image.setLayoutY(y - ((((ZoneSpell)effect).getZoneRadius()*16)/2));

            FadeTransition fade = new FadeTransition(Duration.seconds(0.5), image);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished(event -> {
                if (image.getParent() != null) {
                    ((javafx.scene.layout.Pane) image.getParent()).getChildren().remove(image);
                }
            });
            fade.play();
        }
    }

    public int getId() {
        return id;
    }
}
