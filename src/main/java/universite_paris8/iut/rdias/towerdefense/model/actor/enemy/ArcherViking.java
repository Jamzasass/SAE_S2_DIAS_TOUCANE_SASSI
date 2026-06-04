package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

public class ArcherViking extends Enemy {
    private Tower target;

    public ArcherViking(Environnement env, int eId, double eX, double eY) {
        super(env, 50, 20, eId, 4, eX, eY, 0.10, 25);
    }

    public void act(){
        searchtarget();
        if (target != null) {
            setxCible((int) target.getX());
            setyCible((int) target.getY());
        }

        this.move();
        if (calculDistanceFromEnemy(target) < this.getRange()) {
            target.takeDamage(this.getDmg());
        }
    }

    public void searchtarget() {
        Tower closeTarget = null;
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
        this.target = closeTarget;
    }
    public int calculDistanceFromEnemy(Tower t) {
        return getEnvironnement().getGround().getMapBFS().getDistancesMap()[(int)this.getY()][(int)this.getX()][(int)t.getY()][(int)t.getX()];
    }
}
