package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Soldier;

/*La sous-classe Knight qui hérite de Soldier.
 * Le chevalier est spawné par la Barrack et se déplace
 * vers les ennemis pour les attaquer au corps à corps
 * (portée d'attaque de 1). Sa cible par défaut est le château pour le defendre.
 * Un chevalier a comme attribut spécifique:
 * -sa cible (target) qu'il doit attaquer
 */

public class Knight extends Soldier {
    private Enemy target;

    public Knight(Environnement kEnv, int kId, double kX, double kY) {
        super(kEnv,
                kEnv.getSettings().getKnightHp(),
                kEnv.getSettings().getKnightDmg(),
                kId,
                1,
                kX,
                kY,
                kEnv.getSettings().getKnightSpeed(),
                42,
                40);
        target = null;
    }
    public void act() {
        tick();
        searchtarget();
        if (target != null && target.isLiving()) {
            setxCible((int) target.getX());
            setyCible((int) target.getY());
        }
        this.move();
        if (target != null && calculDistanceFromEnemy(target) < this.getRange() && canAct()) {
            target.takeDamage(this.getDmg());
            resetCooldown();
        }
    }

    public void searchtarget() {
        Enemy closeTarget = null;
        double minRange = Double.MAX_VALUE;
        for (Enemy e : getEnvironnement().getEnemies()) {
            if (e.isLiving()) {
                int range = calculDistanceFromEnemy(e); // calcul distance via pythagore
                if (range<= minRange) {//(range <= getRange() && range < minRange) {
                    minRange = range;
                    closeTarget = e;
                }
            }
        }
        this.target = closeTarget;
    }

    public int calculDistanceFromEnemy(Enemy e) {
        return getEnvironnement().getGround().getMapBFS().getDistancesMap()[(int)this.getY()][(int)this.getX()][(int)e.getY()][(int)e.getX()];
    }
}
