package universite_paris8.iut.rdias.towerdefense.model;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;

public class ZoneSpell extends Effect {
    private int zoneRadius;


    public ZoneSpell(Environnement pEnv, int pId, double pX, double pY, int pDmg, Actor pTarget) {
        super(pEnv, pX, pY, pDmg, pId);


    }

    @Override
    public void act() {

    }

    private void damageInZoneAround(Enemy target) {
        for (Enemy e : getEnvironnement().getEnemies()) {
            if (!e.isLiving()) continue;
            double dist = Math.hypot(e.getX() - target.getX(), e.getY() - target.getY());
            if (dist <= zoneRadius) {
                e.takeDamage(getDmg());
            }
        }
    }
}
