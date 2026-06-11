package universite_paris8.iut.rdias.towerdefense.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.rdias.towerdefense.model.Projectile;

public class ProjectileView {
    public static Image spriteArrow = new Image(GroundView.class.getResourceAsStream("/universite_paris8/iut/rdias/towerdefense/sprite/projectile/sprite_arrow.png"));
    public ImageView image;
    public Projectile projectile;

    public ProjectileView(Projectile pProjectile) {
        this.projectile = pProjectile;
        this.image = new ImageView(spriteArrow);
        this.image.setId("e" + projectile.getId());
        this.image.layoutXProperty().bind(projectile.getXProperty().multiply(16).add(-16/2));
        this.image.layoutYProperty().bind(projectile.getYProperty().multiply(16).add(-16/2));
        this.image.rotateProperty().bind(projectile.getAnglePorperty());
    }

    public ImageView getImage() {
        return image;
    }
}
