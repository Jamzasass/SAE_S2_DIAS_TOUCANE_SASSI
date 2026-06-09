package universite_paris8.iut.rdias.towerdefense.model.actor.ally;


import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

public class Archer extends Tower {

    private static final int hplvl1 = 150;
    private static final int hplvl2 = 225;
    private static final int dmglvl1 = 40;
    private static final int dmglvl2 = 60;
    private static final int speedAttacklvl1 = 2;
    private static final int speedAttacklvl2 = 1;

    public Archer(Environnement aEnv, int aHp, int aDmg, int archerId, double aX, double aY, int aSpeedAttack, int aCost) {
        super(aEnv, aHp, aDmg, archerId, 4.0, aX, aY, aSpeedAttack, aCost);
    }

    @Override
    public void act() {
        tick();
        if (canAct()){
            Enemy target = searchTarget();
            if (target != null) {
                target.takeDamage(getDmg());
                resetCooldown();
            }
        }
    }

    private Enemy searchTarget() {
        Enemy closeTarget = null;
        double minRange = Double.MAX_VALUE;
        for (Enemy e : getEnvironnement().getEnemies()) {
            if (e.isLiving()) {
                double range = Math.hypot(e.getX() - getX(), e.getY() - getY()); // calcul distance via pythagore
                if (range <= getRange() && range < minRange) {
                    minRange = range;
                    closeTarget = e;
                }
            }
        }
        return closeTarget;
    }

    @Override
    public void upgrade() {
        if (getLevel() < 2) {
            setHp(hplvl2);
            setDmg(dmglvl2);
            setSpeedAct(speedAttacklvl2);
            lvlUp();
            System.out.println("cacacacacacacacacaca");
        }
    }
}
