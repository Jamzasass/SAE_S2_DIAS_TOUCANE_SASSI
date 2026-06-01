package universite_paris8.iut.rdias.towerdefense.model;

import javax.xml.stream.events.StartDocument;
import java.util.List;

public class Knight extends Soldier {
    private Enemy target;

    public Knight(Environnement env, int kHp, int kDmg, int kId, double kX, double kY) {
        super(env, kHp, kDmg, kId, 1, kX, kY, 0.05, 42, 40);
        target = null;
    }
    public Knight(Environnement env, int kId, double kX, double kY) {
        super(env, 70, 20, kId, 1, kX, kY, 0.05, 42, 40);
        target = null;
    }
    public void act() {
        if (target == null || !target.isLiving()) {
            searchtarget();
        }
        this.move();
    }

    public void searchtarget() {
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
        target = closeTarget;
    }

    public void wayChangement() {
        AStar astar = new AStar(getEnvironnement().getGround(), 0);
        if (target != null && target.isLiving()) {
            this.setxCible((int) target.getX());
            this.setyCible((int) target.getY());
        }
        List<int[]> chemin = astar.trouverChemin((int)this.getY(), (int)this.getX(), getyCible(), getxCible());
        this.setChemin(chemin);
    }
}
