package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;

public class ArcherViking extends Enemy {
    private Actor target;

    public ArcherViking(Environnement env, int eId, double eX, double eY) {
        super(env, 50, 20, eId, 3, eX, eY, 0.10, 25);
        target = null;
    }
    @Override
    public void act(){
//        searchtarget();
//        if (target != null) {
//            setxCible((int) target.getX());
//            setyCible((int) target.getY());
//            this.move();
//
//            if (calculDistanceFromEnemy(target) < this.getRange()) {
//                target.takeDamage(this.getDmg());
//            }
//        } else {
//            this.move();
//        }
        searchtarget();
        if (target != null) {
            int dist = calculDistanceFromEnemy(target);

            if (dist > this.getRange()) {
                setxCible((int) target.getX());
                setyCible((int) target.getY());
                this.move();
            } else {
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
