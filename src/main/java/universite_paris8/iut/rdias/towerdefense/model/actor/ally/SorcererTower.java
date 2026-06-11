package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.Settings;

public class SorcererTower extends Tower {
    private double radiusBlow = 8.0;

    public SorcererTower(Environnement sEnv, int sorcererId, double sorcererX, double sorcererY) {
        super(sEnv,
                sEnv.getSettings().getSorcererTowerHp(),
                sEnv.getSettings().getSorcererTowerDmg(),
                sorcererId,
                sEnv.getSettings().getSorcererRange(),
                sorcererX,
                sorcererY,
                sEnv.getSettings().getSorcererTowerSpeedAttack(),
                sEnv.getSettings().getSorcererTowerCost());
    }

    @Override
    public void act(){
        tick();
        if (canAct()){
            Enemy target = searchTarget();
            if (target != null){
                damageInZoneAround(target);
                resetCooldown();
            }
        }
    }

    private void damageInZoneAround(Enemy target) {
        for (Enemy e : getEnvironnement().getEnemies()) {
            if (!e.isLiving()) continue;
            double dist = Math.hypot(e.getX() - target.getX(), e.getY() - target.getY());
            if (dist <= radiusBlow) {
                e.takeDamage(getDmg());
            }
        }
    }

    private Enemy searchTarget() {
        Enemy closest = null;
        double minDist = Double.MAX_VALUE;
        for (Enemy e : getEnvironnement().getEnemies()) {
            if (!e.isLiving()) continue;
            double dist = Math.hypot(e.getX() - getX(), e.getY() - getY());
            if (dist <= getRange() && dist < minDist) {
                minDist = dist;
                closest = e;
            }
        }
        return closest;
    }

    @Override
    public int placementRange(){
        return 4;
    }

    @Override
    public void upgrade() {
//        if (getLevel() < 2) {
//            setHp(hplvl2);
//            setSpeedAct(speedAttacklvl2);
//            dmgPerSec = dmgPerSeclvl2;
//            zoneDuration = zoneDurationlvl2;
//            lvlUp();
//        }
    }
}
