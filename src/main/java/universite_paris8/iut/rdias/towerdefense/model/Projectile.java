package universite_paris8.iut.rdias.towerdefense.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.rdias.towerdefense.model.actor.*;

public class Projectile extends Effect{


    public Projectile(Environnement pEnv, double pX, double pY, int pDmg, Actor pTarget) {
        super(pEnv, pX, pY, pDmg, pTarget, 0.35);
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
