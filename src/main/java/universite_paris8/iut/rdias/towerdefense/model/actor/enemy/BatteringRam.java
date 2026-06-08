package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Palissade;

public class BatteringRam extends Enemy {

    private Tower target;

    public BatteringRam(Environnement env, int eId, double eX, double eY) {
        super(env, 250, 100, eId, 0.1, eX, eY, 0.03, 80);
    }

    @Override
    public void act(){
        searchtarget();
        if (target != null) {
            setxCible((int) target.getX());
            setyCible((int) target.getY());
            this.move();

            if (calculDistanceFromEnemy(target) < this.getRange() && canAct()) {
                ((Palissade) target).takeBatteringDamage(this.getDmg());
                resetCooldown(180);
            }
        } else {
            this.move();
        }
    }

    public void searchtarget() {
        Tower closeTarget = null;
        double minRange = Double.MAX_VALUE;
        for (Tower p : getEnvironnement().getTowers()) {
            if (p.isLiving() && (p instanceof Palissade)) {
                int range = calculDistanceFromEnemy(p); // calcul distance via pythagore
                if (range<= minRange) { //(range <= getRange() && range < minRange) {
                    minRange = range;
                    closeTarget = p;
                }
            }
        }
        this.target = closeTarget;
    }
    public int calculDistanceFromEnemy(Tower t) {
        return getEnvironnement().getGround().getMapBFS().getDistancesMap()[(int)this.getY()][(int)this.getX()][(int)t.getY()][(int)t.getX()];
    }
}
