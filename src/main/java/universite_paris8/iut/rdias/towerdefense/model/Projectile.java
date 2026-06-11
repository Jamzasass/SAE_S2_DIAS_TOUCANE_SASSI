package universite_paris8.iut.rdias.towerdefense.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.rdias.towerdefense.model.actor.*;

public class Projectile extends Effect{
    private Actor target;
    private double speed;
    private DoubleProperty directionX;
    private DoubleProperty directionY;
    private DoubleProperty angle;

    public Projectile(Environnement pEnv, int pId, double pX, double pY, int pDmg, Actor pTarget) {
        super(pEnv, pX, pY, pDmg, pId);
        this.target = pTarget;
        this.speed = 0.35;
        directionX = new SimpleDoubleProperty(0);
        directionY = new SimpleDoubleProperty(0);
        angle = new SimpleDoubleProperty(0);
    }

    @Override
    public void act() {
        if (target == null || !target.isLiving()) {
            finished();
        }
        double dX = (target.getX()) - getX();
        double dY = (target.getY()) - getY();
        double dist = Math.hypot(dX, dY);

        if (dist < speed && !isFinished()) {
            target.takeDamage(getDmg());
            finished();
        } else {
            majDirectionEtAngle(dX, dY, dist);
            setX(getX() + (directionX.getValue() * speed));
            setY(getY() + (directionY.getValue() * speed));
        }
    }


    public void majDirectionEtAngle(double dx, double dy, double dist) {
        if (dist > 0) {
            directionX.set(dx / dist);
            directionY.set(dy / dist);
            double angleDegres = Math.toDegrees(Math.atan2(directionY.get(), directionX.get()));
            angle.set(angleDegres);
        }
    }
    public DoubleProperty getAnglePorperty() {
        return angle;
    }
}
