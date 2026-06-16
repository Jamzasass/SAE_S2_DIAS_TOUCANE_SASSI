package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

public class Barrack extends Tower {
    private int[] coordClosePath;

    public Barrack (Environnement bEnv, int barrackId, double barrackX, double barrackY) {
        super(  bEnv,
                bEnv.getSettings().getBarrackHp(),
                bEnv.getSettings().getBallistaDmg(),
                barrackId,
                0,
                barrackX,
                barrackY,
                bEnv.getSettings().getBarrackSpeedProduction(),
                bEnv.getSettings().getBarrackCost()
        );
        coordClosePath = getEnvironnement().getGround().getClosestPath((int) getY(), (int) getX());
    }

    @Override
    public void act() {
        tick();
        if (canAct() && hasEnemies(getEnvironnement())) {
            Knight k = new Knight(getEnvironnement(), getEnvironnement().getId(), coordClosePath[1], coordClosePath[0]);
            getEnvironnement().addKnight(k);
            resetCooldown();
        }
    }

    public boolean hasEnemies(Environnement env) {
        for (Enemy e : env.getEnemies()){
            if (e.isLiving()) {
                return true;
            }
        }
        return false;
    }
}
