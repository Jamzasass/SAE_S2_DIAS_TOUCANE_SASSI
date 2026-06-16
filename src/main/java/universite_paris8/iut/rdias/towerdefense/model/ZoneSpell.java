package universite_paris8.iut.rdias.towerdefense.model;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;

/*La sous-classe ZoneSpell qui hérite de Effect.
 * Le sort se déplace vers sa cible et, à l'impact,
 * inflige des dégâts à tous les ennemis se trouvant
 * dans le rayon défini (effet de zone).
 * Il est utilisé par la tour SorcererTower.
 * Un zonespell a comme attribut spécifique:
 * -le rayon de la zone d'explosion
 */

public class ZoneSpell extends Effect {
    private double zoneRadius;


    public ZoneSpell(Environnement zEnv, double zX, double zY, int zDmg, Actor zTarget, double zZoneRadius) {
        super(zEnv, zX, zY, zDmg, zTarget, 0.10);
        zoneRadius = zZoneRadius;
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
            damageInZoneAround(getTarget());
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

    private void damageInZoneAround(Actor target) {
        for (Enemy e : getEnvironnement().getEnemies()) {
            if (!e.isLiving()) continue;
            double dist = Math.hypot(e.getX() - target.getX(), e.getY() - target.getY());
            if (dist <= zoneRadius) {
                e.takeDamage(getDmg());
            }
        }
    }

    public double getZoneRadius() {
        return zoneRadius;
    }
}
