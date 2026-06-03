package universite_paris8.iut.rdias.towerdefense.model.actor;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;

public class Knight extends Soldier {
    private Enemy target;

    public Knight(Environnement env, int kHp, int kDmg, int kId, double kX, double kY) {
        super(env, kHp, kDmg, kId, 1, kX, kY, 0.07, 42, 40);
        target = null;
    }
    public void act() {
        searchtarget();
        if (target != null) {
            setxCible((int) target.getX());
            setyCible((int) target.getY());
        }

        this.move();
        if (calculDistanceFromEnemy(target) < this.getRange()) {
            target.takeDamage(this.getDmg());
        }
    }

    public void searchtarget() {
        Enemy closeTarget = null;
        double minRange = Double.MAX_VALUE;
        for (Enemy e : getEnvironnement().getEnemies()) {
            if (e.isLiving()) {
                int range = calculDistanceFromEnemy(e); // calcul distance via pythagore
                if (range<= minRange) {//(range <= getRange() && range < minRange) {
                    minRange = range;
                    closeTarget = e;
                }
            }
        }
        this.target = closeTarget;
    }

    public int calculDistanceFromEnemy(Enemy e) {
        return getEnvironnement().getGround().getMapBFS().getDistancesMap()[(int)this.getY()][(int)this.getX()][(int)e.getY()][(int)e.getX()];
    }
}
