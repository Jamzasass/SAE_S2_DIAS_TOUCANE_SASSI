package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

import java.util.ArrayList;

public class Ballista extends Tower {

    private Enemy[] targets;

    public Ballista(Environnement bEnv, int ballistaId, double ballistaX, double ballistaY) {
        super(bEnv,
                bEnv.getSettings().getBallistaHp(),
                bEnv.getSettings().getBallistaDmg(),
                ballistaId,
                bEnv.getSettings().getBallistaRange(),
                ballistaX,
                ballistaY,
                bEnv.getSettings().getBallistaSpeedAttack(),
                bEnv.getSettings().getBallistaCost());
        targets = new Enemy[2];
    }

    @Override
    public void act(){
        tick();
        if (canAct()) {
            targets = searchTargets();
            if (targets[0]!=null){
                targets[0].takeDamage(this.getDmg());
                resetCooldown();
            }
            if (targets[1]!=null) {
                targets[1].takeDamage(this.getDmg());
            }
        }
    }

    private Enemy[] searchTargets() {
        Enemy firstTarget = null;
        Enemy secondTarget = null;
        double firstDist = Double.MAX_VALUE;
        double secondDist = Double.MAX_VALUE;

        for (Enemy e : getEnvironnement().getEnemies()) {
            if (e.isLiving()) {
                double dist = Math.hypot(e.getX() - getX(), e.getY() - getY()); //calcul distance
                if (dist <= getRange()) {
                    if (dist < firstDist) {
                        secondTarget = firstTarget;
                        secondDist = firstDist;
                        firstTarget = e;
                        firstDist = dist;
                    } else if (dist < secondDist) {
                        secondTarget = e;
                        secondDist = dist;
                    }
                }
            }
        }
        Enemy[] result = new Enemy[2];
        result[0] = firstTarget;
        result[1] = secondTarget;
        return result;
    }

    @Override
    public int placementRange(){
        return 3;
    }
}
