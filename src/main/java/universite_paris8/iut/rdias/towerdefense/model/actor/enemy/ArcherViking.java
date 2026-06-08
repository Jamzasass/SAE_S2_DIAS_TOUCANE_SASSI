package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;

public class ArcherViking extends Enemy {
    private Actor target;

    public ArcherViking(Environnement env, int eHp, int eDmg, int eId, int eRange, double eX, double eY, double eSpeed, int eDeathValue) {
        super(env, eHp, eDmg, eId, eRange, eX, eY, eSpeed, eDeathValue);
        target = null;
    }
    @Override
    public void act(){
        searchtarget();
        if (target != null) {
            if (target instanceof Knight) {
                int dist = calculDistanceFromEnemyByBFS(target);
                if (dist > this.getRange()) {
                    setxCible((int) target.getX());
                    setyCible((int) target.getY());
                    this.move();
                } else if (canAct()){
                    target.takeDamage(this.getDmg());
                }
            }
            else if (target instanceof Tower){
                int[] dest = closestPath((Tower) target);
                double dist = calculDistanceFromEnemyByPyth(target);
                if (dist > this.getRange()) {
                    setxCible(dest[1]);
                    setyCible(dest[0]);
                    this.move();
                } else if (canAct()){
                    target.takeDamage(this.getDmg());
                }
            }
        } else {
            this.move();
        }
    }

    public void searchtarget() {
        Actor closeTarget = null;
        double minRange = Double.MAX_VALUE;
        for (Tower t : getEnvironnement().getTowers()) {
            if (t.isLiving()) {
                double range = calculDistanceFromEnemyByPyth(t); // calcul distance via pythagore
                if (range<= minRange) {//(range <= getRange() && range < minRange) {
                    minRange = range;
                    closeTarget = t;
                }
            }
        }
        for (Knight k : getEnvironnement().getKnights()) {
            if (k.isLiving()) {
                int range = calculDistanceFromEnemyByBFS(k); // calcul distance via pythagore
                if (range<= minRange) {//(range <= getRange() && range < minRange) {
                    minRange = range;
                    closeTarget = k;
                }
            }
        }
        this.target = closeTarget;
    }

    public double calculDistanceFromEnemyByPyth(Actor a) {
        double dx = getX() - a.getX();
        double dy = getY() - a.getY();
        return dx * dx + dy * dy;
    }
    public int calculDistanceFromEnemyByBFS(Actor a) {
        return getEnvironnement().getGround().getMapBFS().getDistancesMap()[(int)getY()][(int)getX()][(int)a.getY()][(int)a.getY()];
    }


    public int[] closestPath(Tower t) {
        int[] closePath = new int[2];
        for (int i=1; i<6; i++) {
            if (t.getX()+i<getEnvironnement().getGround().width() &&
                    getEnvironnement().getGround().isPath((int)t.getY(), (int)t.getX()+i)) {
                closePath[0] = (int)t.getY();
                closePath[1] = (int)t.getX()+i;
                return closePath;
            }
            else if (t.getX()-i >= 0 &&
                    getEnvironnement().getGround().isPath((int)t.getY(), (int)t.getX()-i)) {
                closePath[0] = (int)t.getY();
                closePath[1] = (int)t.getX()-i;
                return closePath;
            }
            else if (t.getY()+i < getEnvironnement().getGround().heigth() &&
                    getEnvironnement().getGround().isPath((int)t.getY()+i, (int)t.getX())) {
                closePath[0] = (int)t.getY()+i;
                closePath[1] = (int)t.getX();
                return closePath;
            }
            else if (t.getY()-i >= 0 &&
                    getEnvironnement().getGround().isPath((int)t.getY()-i, (int)t.getX())) {
                closePath[0] = (int)t.getY()-i;
                closePath[1] = (int)t.getX();
                return closePath;
            }
        }
        return closePath;
    }
}
