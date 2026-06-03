package universite_paris8.iut.rdias.towerdefense.model;



public class Archer extends Tower {

    private static final int hplvl1 = 150;
    private static final int hplvl2 = 225;
    private static final int dmglvl1 = 40;
    private static final int dmglvl2 = 60;
    private static final int speedAttacklvl1 = 2;
    private static final int speedAttacklvl2 = 1;

    public Archer(Environnement env, int archerId, double archerX, double archerY) {
        super(env, hplvl1, dmglvl1, archerId, 4.0, archerX, archerY, speedAttacklvl1, 100);
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
        for (Actor a : getEnvironnement().getActors()) {
            if (a instanceof Enemy && a.isLiving()) {
                double range = Math.hypot(a.getX() - getX(), a.getY() - getY()); // calcul distance via pythagore
                if (range <= getRange() && range < minRange) {
                    minRange = range;
                    closeTarget = (Enemy) a;
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
        }
    }
}
