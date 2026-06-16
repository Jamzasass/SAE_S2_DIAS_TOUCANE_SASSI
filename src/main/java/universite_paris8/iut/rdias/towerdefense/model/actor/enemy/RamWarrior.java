package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Palissade;

import java.util.ArrayList;

public class RamWarrior extends Enemy {
    private Tower target;

    public RamWarrior(Environnement rEnv, int eId, double eX, double eY) {
        super(  rEnv,
                rEnv.getSettings().getRamwarriorHp(),
                rEnv.getSettings().getRamwarriorDmg(),
                eId,
                0.5,
                eX,
                eY,
                rEnv.getSettings().getRamwarriorSpeed(),
                rEnv.getSettings().getRamwarriorDeathValue(),
                rEnv.getSettings().getRamwarriorSpeedAct()
        );
    }

    @Override
    public void act(){
        tick();
        this.target = (Tower) searchTarget();
        if (target != null) {
            if (target instanceof Palissade){
                ArrayList<int[]> dist = getEnvironnement().getGround().getAllClosestPaths((int)target.getY(), (int)target.getX());
                int distClosest = getEnvironnement().getGround().getMapBFS().getDistancesMap()[(int)this.getY()][(int)this.getX()][dist.get(0)[0]][dist.get(0)[1]];
                int[] cible = dist.get(0);
                for (int[] pE : dist) {
                    if (distClosest >= getEnvironnement().getGround().getMapBFS().getDistancesMap()[(int)this.getY()][(int)this.getX()][pE[0]][pE[1]]) {
                        cible = pE;
                        distClosest = getEnvironnement().getGround().getMapBFS().getDistancesMap()[(int)this.getY()][(int)this.getX()][pE[0]][pE[1]];
                    }
                }
                setxCible(cible[1]);
                setyCible(cible[0]);
                this.move();
                if (distClosest < this.getRange() && canAct()) {
                    target.takeDamage(this.getDmg());
                    resetCooldown();
                }
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

    public Actor searchTarget() {
        Tower closeTarget = null;
        double minRange = Double.MAX_VALUE;
        for (Tower t : getEnvironnement().getTowers()) {
            if ((t instanceof Palissade) && t.isLiving()) {
                double range = calcDistanceFromTargerUsingPyth(t); // calcul distance via pythagore
                if (range <= minRange) { //(range <= getRange() && range < minRange) {
                    minRange = range;
                    closeTarget = t;
                }
            }
        }
        return closeTarget;
    }
}
