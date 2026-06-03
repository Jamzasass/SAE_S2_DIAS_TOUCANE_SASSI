package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

public class Bramble extends Tower {

    private static final double slowFactor = 0.5;
    private static final int slowDuration =  30;

    public Bramble(Environnement env, int brambleId, double brambleX, double brambleY, double tSpeedAttack, int cost) {
        super(env, 0, 0, brambleId, 0, brambleX, brambleY, 1, 50);
    }

    @Override
    public void act() {
        for (Enemy e : getEnvironnement().getEnemies()) {
            if (onTile(e)) {
                // TODO e.applySlow(slowFactor, slowDuration); dans la classe Vikings
            }
        }
    }

    private boolean onTile(Enemy e){
        int enemyX = (int) e.getX();
        int enemyY = (int) e.getY();
        int brambleX = (int) getX();
        int brambleY = (int) getY();
        return enemyX == brambleX && enemyY == brambleY;
    }

    @Override
    public boolean isLiving(){
        return true;
    }

    @Override
    public void takeDamage(int amount) {
    }

    @Override
    public void upgrade() {
    }
}
