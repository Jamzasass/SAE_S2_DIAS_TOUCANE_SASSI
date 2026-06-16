package universite_paris8.iut.rdias.towerdefense.model.actor;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;

public abstract class Enemy extends Soldier {
    private int deathValue;

    public Enemy(Environnement env, int eHp, int eDmg, int eId, double eRange, double eX, double eY, double eSpeed, int eDeathValue) {
        super(env, eHp, eDmg, eId, eRange, eX, eY, eSpeed, 42, 40);
        this.deathValue = eDeathValue;
    }

    public int getDeathValue(){
        return deathValue;
    }

    @Override
    public Actor searchTarget() {
        Knight closeTarget = null;
        double minRange = 6.0;
        for (Knight k : getEnvironnement().getKnights()) {
            if (k.isLiving()) {
                int range = calcDistanceFromTargerUsingBFS(k);
                if (range<= minRange) {
                    minRange = range;
                    closeTarget = k;
                }
            }
        }
        return closeTarget;
    }

    public boolean isOnCatle() {
        return getEnvironnement().getGround().isCastle((int)this.getY(), (int)this.getX());
    }
}
