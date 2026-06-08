package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

public class Berserker extends Enemy {

    private Tower target;

    public Berserker(Environnement env, int eId, double eX, double eY) {
        super(env, 125, 20, eId, 0.1, eX, eY, 0.06, 45);
        target = null;
    }

    @Override
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
