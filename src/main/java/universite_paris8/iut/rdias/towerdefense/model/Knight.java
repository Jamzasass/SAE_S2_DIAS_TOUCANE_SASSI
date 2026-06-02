package universite_paris8.iut.rdias.towerdefense.model;

import universite_paris8.iut.rdias.towerdefense.model.algorithm.AStar;

import java.util.List;

public class Knight extends Soldier {
    private Enemy target;

    public Knight(Environnement env, int kHp, int kDmg, int kId, double kX, double kY) {
        super(env, kHp, kDmg, kId, 1, kX, kY, 0.07, 42, 40);
        target = null;
    }
    public void act() {
        if (target == null || !target.isLiving()) {
            searchtarget();
        }
        this.move();
        if (calculDistance(target) < this.getRange()) {
            target.takeDamage(this.getDmg());
        }
    }

    public void searchtarget() {
        Enemy closeTarget = null;
        double minRange = Double.MAX_VALUE;
        for (Enemy e : getEnvironnement().getEnemies()) {
            if (e.isLiving()) {
                double range = calculDistance(e); // calcul distance via pythagore
                if (range<= minRange) {//(range <= getRange() && range < minRange) {
                    minRange = range;
                    closeTarget = e;
                }
            }
        }
        this.target = closeTarget;
    }

    public void wayChangement() {
        if (((int) target.getX() != getxCible()) || ((int) target.getY() != getyCible())) {
            AStar astar = new AStar(getEnvironnement().getGround(), 0);
            if (target != null && target.isLiving()) {
                this.setxCible((int) target.getX());
                this.setyCible((int) target.getY());
            }
            List<int[]> chemin = astar.trouverChemin((int)this.getY(), (int)this.getX(), getyCible(), getxCible());
            this.setChemin(chemin);
            majDirection();
        }
    }

    public double calculDistance(Enemy e) {
        return Math.hypot(e.getX() - this.getX(), e.getY() - this.getY());
    }
}
