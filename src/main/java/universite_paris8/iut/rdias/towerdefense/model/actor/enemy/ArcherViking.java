package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;

public class ArcherViking extends Enemy {
    private Actor target;

    public ArcherViking(Environnement env, int eHp, int eDmg, int eId, int eRange, double eX, double eY, double eSpeed, int eDeathValue) {
        super(env, eHp, eDmg, eId, eRange, eX, eY, eSpeed, eDeathValue);
        target = null;
    }
    @Override
    public void act(){
        searchtarget();
        if (target != null) {
            int dist = calculDistanceFromEnemy(target);
            if (dist > this.getRange()) {
                setxCible((int) target.getX());
                setyCible((int) target.getY());
                this.move();
            } else if (canAct()){
                target.takeDamage(this.getDmg());
            }
        } else {
            this.move();
        }
    }

    public void searchtarget() {
        Actor closeTarget = null;
        double minRange = Double.MAX_VALUE;
        for (Tower t : getEnvironnement().getTowers()) {
            if (t.isLiving()) {
                int range = calculDistanceFromEnemy(t); // calcul distance via pythagore
                if (range<= minRange) {//(range <= getRange() && range < minRange) {
                    minRange = range;
                    closeTarget = t;
                }
            }
        }
        for (Knight k : getEnvironnement().getKnights()) {
            if (k.isLiving()) {
                int range = calculDistanceFromEnemy(k); // calcul distance via pythagore
                if (range<= minRange) {//(range <= getRange() && range < minRange) {
                    minRange = range;
                    closeTarget = k;
                }
            }
        }
        this.target = closeTarget;
    }

    public int calculDistanceFromEnemy(Actor a) {
        return getEnvironnement().getGround().getMapBFS().getDistancesMap()[(int)this.getY()][(int)this.getX()][(int)a.getY()][(int)a.getX()];
    }
}
