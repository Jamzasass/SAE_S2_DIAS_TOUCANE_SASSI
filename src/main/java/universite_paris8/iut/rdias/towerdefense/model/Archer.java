package universite_paris8.iut.rdias.towerdefense.model;

public class Archer extends Tower {

    private static final int hplvl1 = 150;
    private static final int hplvl2 = 225;
    private static final int dmglvl1 = 40;
    private static final int dmglvl2 = 60;
    private static final double speedAttacklvl1 = 1.0;
    private static final double speedAttacklvl2 = 1.8;

    public Archer(int archerId, double archerX, double archerY) {
        super(hplvl1, dmglvl1, archerId, 6.0, archerX, archerY, speedAttacklvl1, 100);
    }

    @Override
    public void act(Environnement env) {
        tick();
        if (!canAttack()) return;
        Enemy target = searchTarget(env);
        if (target != null) {
            target.takeDamage(getDmg());
            resetCooldown(60);
        }
    }

    private Enemy searchTarget(Environnement env) {
        Enemy closeTarget = null;
        double minRange = Double.MAX_VALUE;
        for (Enemy e : env.getEnemies()) {
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
            setSpeedAttack(speedAttacklvl2);
            //lvlUp();
        }
    }

    public void resetCooldown(int val) {
        System.out.println("todo");
    }
}
