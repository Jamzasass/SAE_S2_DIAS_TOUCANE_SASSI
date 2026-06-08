package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

public class Berserker extends Enemy {

    private Enemy target;

    public Berserker(Environnement env, int eHp, int eDmg, int eId, double eX, double eY, double eSpeed, int eDeathValue) {
        super(env, eHp, eDmg, eId, 0.1, eX, eY, eSpeed, eDeathValue);
        target = null;
    }

    @Override
    public void act(){
        searchtarget();
        if (target != null) {
            setxCible((int) target.getX());
            setyCible((int) target.getY());
            this.move();
            if (calculDistanceFromEnemy(target) < this.getRange()) {
                target.takeDamage(this.getDmg());
            }
        } else {
            setxCible(42);
            setyCible(40);
            this.move();
        }
        if (getEnvironnement().getGround().isCastle((int)this.getY(), (int)this.getY())) {
            getEnvironnement().getCastle().takeDamage(this.getDmg());
            this.die();
        }
    }

    public void searchtarget() {
        Enemy closeTarget = null;
        int minRange = Integer.MAX_VALUE;
        for (Enemy e : getEnvironnement().getEnemies()) {
            if (e.isLiving()) {
                int range = calculDistanceFromEnemy(e); // calcul distance via pythagore
                if (range<= minRange) { //(range <= getRange() && range < minRange) {
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
