package universite_paris8.iut.rdias.towerdefense.model;

import universite_paris8.iut.rdias.towerdefense.model.actor.*;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;

/*La sous-classe Projectile qui hérite de Effect.
 * Le projectile se déplace en ligne droite vers sa cible
 * à une vitesse définie. Lorsqu'il atteint sa cible,
 * il lui inflige des dégâts et disparaît.
 * Il met également à jour son angle de rotation
 * pour l'animation visuelle.
 */

public class Projectile extends Effect{

    public Projectile(Environnement pEnv, double pX, double pY, int pDmg, Actor pTarget) {
        super(pEnv, pX, pY, pDmg, pTarget, 0.20);
    }

    @Override
    public void act() {
        if (getTarget() == null || !getTarget().isLiving()) {
            finished();
        }
        double dX = (getTarget().getX()) - getX();
        double dY = (getTarget().getY()) - getY();
        double dist = Math.hypot(dX, dY);

        if (dist < getSpeed() && !isFinished()) {
            getTarget().takeDamage(getDmg());
            finished();
        } else {
            majDirectionEtAngle(dX, dY, dist);
            setX(getX() + (getDirectionX() * getSpeed()));
            setY(getY() + (getDirectionY() * getSpeed()));
        }
    }


    public void majDirectionEtAngle(double dx, double dy, double dist) {
        if (dist > 0) {
            directionXProperty().set(dx / dist);
            directionYProperty().set(dy / dist);
            double angleDegres = Math.toDegrees(Math.atan2(getDirectionY(), getDirectionX()));
            setAngle(angleDegres);
        }
    }
}
