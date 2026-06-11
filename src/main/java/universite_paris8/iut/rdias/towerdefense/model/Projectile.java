package universite_paris8.iut.rdias.towerdefense.model;

import universite_paris8.iut.rdias.towerdefense.model.actor.*;

public class Projectile extends Effect{
    private Enemy target;
    private double speed;
    private double directionX;
    private double directionY;

    public Projectile(double x, double y, int damage, Enemy target, double speed) {
        super(x, y, damage);
        this.target = target;
        this.speed = speed;
    }

    @Override
    public void act() {
        if (target == null || !target.isLiving()) {
            finished();
        }
        double dx = target.getX() - getX();
        double dy = target.getY() - getY();
        double dist = Math.hypot(dx, dy);

        if (dist < speed) {
            target.takeDamage(getDmg());
            finished();
        } else {

            setX(getX() + ((dx / dist) * speed));
            setY(getY() + ((dy / dist) * speed));
        }
    }

    public void majDirection() {
        if (target != null) {
            Math.cos(target.getX() + target.getY());
            directionX = target.getX() - this.getX();
            directionY = target.getY() - this.getY();
        }
    }

}
