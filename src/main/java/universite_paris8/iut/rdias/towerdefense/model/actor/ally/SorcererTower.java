package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.ZoneSpell;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.Settings;

/*La sous-classe SorcererTower qui hérite de Tower.
 * Cette tour attaque à distance avec des boules de feu
 * qui explosent à l'impact, infligeant des dégâts à tous
 * les ennemis se trouvant dans le rayon d'explosion.
 * Une tour sorcier a comme attribut spécifique:
 * -le rayon de l'explosion de ses boules de feu
 */

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
                ZoneSpell zp = new ZoneSpell(getEnvironnement(), getX(), getY(), getDmg(), target, radiusBlow);
                getEnvironnement().addEffect(zp);
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
}
