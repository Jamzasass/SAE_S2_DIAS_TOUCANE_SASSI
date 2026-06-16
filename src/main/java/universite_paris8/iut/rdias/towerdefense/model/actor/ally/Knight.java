package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Soldier;

public class Knight extends Soldier {
    private Enemy target;

    public Knight(Environnement kEnv, int kId, double kX, double kY) {
        super(kEnv,
                kEnv.getSettings().getKnightHp(),
                kEnv.getSettings().getKnightDmg(),
                kId,
                1,
                kX,
                kY,
                kEnv.getSettings().getKnightSpeed(),
                42,
                40,
                kEnv.getSettings().getKnightSpeedAct()
        );
        target = null;
    }
    public void act() {
        tick();
        this.target = (Enemy) searchTarget();
        if (target != null && target.isLiving()) {
            setxCible((int) target.getX());
            setyCible((int) target.getY());
        }
        this.move();
        if (target != null && calcDistanceFromTargerUsingBFS(target) < this.getRange() && canAct()) {
            target.takeDamage(this.getDmg());
            resetCooldown();
        }
    }
}
