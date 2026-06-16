package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;

/*La sous-classe ShieldViking qui hérite de Enemy.
 * Cet ennemi attaque au corps à corps (portée très faible).
 * Il cible uniquement les chevaliers (Knight) et se déplace
 * vers eux en utilisant le BFS pour les atteindre.
 * Sa portée d'attaque est de 0.1.
 * Un shieldviking a comme attribut spécifique:
 * -sa cible (target) qu'il doit attaquer
 */

public class ShieldViking extends Enemy {

    private Knight target;

    public ShieldViking(Environnement sEnv, int eId, double eX, double eY) {
        super(sEnv,
                sEnv.getSettings().getShieldwarriorHp(),
                sEnv.getSettings().getShieldwarriorDmg(),
                eId,
                0.1,
                eX,
                eY,
                sEnv.getSettings().getShieldwarriorSpeed(),
                sEnv.getSettings().getShieldwarriorDeathValue());
        target = null;
    }

    @Override
    public void act(){
        tick();
        searchtarget();
        if (target != null) {
            setxCible((int) target.getX());
            setyCible((int) target.getY());
            this.move();
            if (calculDistanceFromEnemy(target) < this.getRange() && canAct()) {
                target.takeDamage(this.getDmg());
                resetCooldown();
            }
        }
        else {
            setxCible(42);
            setyCible(40);
            this.move();
        }
        if (getEnvironnement().getGround().isCastle((int)this.getY(), (int)this.getX())) {
            getEnvironnement().getCastle().takeDamage(this.getDmg());
            this.die();
        }

    }

    public void searchtarget() {
        Knight closeTarget = null;
        double minRange = Double.MAX_VALUE;
        for (Knight k: getEnvironnement().getKnights()) {
            if (k.isLiving()) {
                int range = calculDistanceFromEnemy(k);
                if (range<= minRange) { //(range <= getRange() && range < minRange) {
                    minRange = range;
                    closeTarget = k;
                }
            }
        }
        this.target = closeTarget;
    }
    public int calculDistanceFromEnemy(Knight k) {
        return getEnvironnement().getGround().getMapBFS().getDistancesMap()[(int)this.getY()][(int)this.getX()][(int)k.getY()][(int)k.getX()];
    }
}
