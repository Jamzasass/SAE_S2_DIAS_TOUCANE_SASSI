package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;

public class Berserker extends Enemy {

    private Knight target;

    public Berserker(Environnement env, int eHp, int eDmg, int eId, double eX, double eY, double eSpeed, int eDeathValue) {
        super(env, eHp, eDmg, eId, 0.1, eX, eY, eSpeed, eDeathValue);
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
            setxCible(42);
            setyCible(40);
            this.move();
        }
        if (getEnvironnement().getGround().isCastle((int)this.getY(), (int)this.getY())) {
            getEnvironnement().getCastle().takeDamage(this.getDmg());
            this.die();
        }
    }

    public void searchtarget() {
        Knight closeTarget = null;
        int minRange = Integer.MAX_VALUE;
        for (Knight k : getEnvironnement().getKnights()) {
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
