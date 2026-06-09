package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.controller.Controller;
import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;

public class Viking extends Enemy {
    private Knight target;
    public Viking(Environnement env, int vHp, int vDmg, int vId, double vSpeed, double vX, double vY) {
        super(env, vHp, vDmg, vId, 0.1, vX, vY, vSpeed, 15);
        target = null;
    }

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
        double minRange = 6.0; //a changer
        for (Knight k : getEnvironnement().getKnights()) {
            if (k.isLiving()) {
                int range = calculDistanceFromEnemy(k); // calcul distance via pythagore
                if (range <= minRange) {//(range <= getRange() && range < minRange) {
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
