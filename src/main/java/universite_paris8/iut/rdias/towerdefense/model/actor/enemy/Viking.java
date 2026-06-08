package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;

public class Viking extends Enemy {

    private Knight target;

    public Viking(Environnement env, int eId, double eX, double eY) {
        super(env, 70, 20, eId, 0.1, eX, eY, 0.05, 15);
        target = null;
    }

    @Override
    public void act(){
        searchtarget();
        if (target != null) {
            setxCible((int) target.getX());
            setyCible((int) target.getY());
            this.move();

            if (calculDistanceFromEnemy(target) < this.getRange()) {
                target.takeDamage(this.getDmg());
            }
        } else {
            this.move();
        }
    }

    public void searchtarget() {
        Knight closeTarget = null;
        double minRange = Double.MAX_VALUE;
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
    public int calculDistanceFromEnemy(Knight k) {
        return getEnvironnement().getGround().getMapBFS().getDistancesMap()[(int)this.getY()][(int)this.getX()][(int)k.getY()][(int)k.getX()];
    }


}
