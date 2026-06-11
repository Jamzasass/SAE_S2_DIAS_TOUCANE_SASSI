package universite_paris8.iut.rdias.towerdefense.model.actor.ally;


import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

public class Archer extends Tower {

    public Archer(Environnement aEnv,int archerId, double aX, double aY) {
        super(aEnv,
                aEnv.getSettings().getArcherHp(),
                aEnv.getSettings().getArcherDmg(),
                archerId,
                aEnv.getSettings().getArcherRange(),
                aX,
                aY,
                aEnv.getSettings().getArcherSpeedAttack(),
                aEnv.getSettings().getArcherCost());
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
            if (e!= null && e.isLiving()) {
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
    public int placementRange(){
        return 3;
    }
}
