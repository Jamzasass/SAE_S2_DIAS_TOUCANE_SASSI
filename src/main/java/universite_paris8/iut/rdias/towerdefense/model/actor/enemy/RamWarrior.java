package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Palissade;

public class RamWarrior extends Enemy {

    private Tower target;

    public RamWarrior(Environnement rEnv, int eId, double eX, double eY) {
        super(rEnv,
                rEnv.getSettings().getRamwarriorHp(),
                rEnv.getSettings().getRamwarriorDmg(),
                eId,
                0.1,
                eX,
                eY,
                rEnv.getSettings().getRamwarriorSpeed(),
                rEnv.getSettings().getRamwarriorDeathValue());
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
        } else {
            this.move();
        }
    }

    public void searchtarget() {
        Tower closeTarget = null;
        double minRange = Double.MAX_VALUE;
        for (Tower t : getEnvironnement().getTowers()) {
            if (t.isLiving() && (t instanceof Palissade)) {
                int range = calculDistanceFromEnemy(t); // calcul distance via pythagore
                if (range<= minRange) { //(range <= getRange() && range < minRange) {
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
