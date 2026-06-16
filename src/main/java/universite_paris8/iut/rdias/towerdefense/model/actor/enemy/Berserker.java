package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;

public class Berserker extends Enemy {

    private Knight target;

    public Berserker(Environnement bEnv, int eId, double eX, double eY) {
        super(bEnv,
                bEnv.getSettings().getBerserkerHp(),
                bEnv.getSettings().getBerserkerDmg(),
                eId,
                0.1,
                eX,
                eY,
                bEnv.getSettings().getBerserkerSpeed(),
                bEnv.getSettings().getBerserkerDeathValue(),
                bEnv.getSettings().getBerserkerSpeedAct()
        );
        target = null;
    }

    @Override
    public void act(){
        this.target = (Knight) searchTarget();
        tick();
        if (target != null) {
            setxCible((int) target.getX());
            setyCible((int) target.getY());
            this.move();
            if (calcDistanceFromTargerUsingBFS(target) < this.getRange() && canAct()) {
                target.takeDamage(this.getDmg());
                resetCooldown();
            }
        } else {
            setxCible(42);
            setyCible(40);
            this.move();
        }
        if (getEnvironnement().getGround().isCastle((int)this.getY(), (int)this.getX())) {
            getEnvironnement().getCastle().takeDamage(this.getDmg());
            this.die();
        }
    }
}
