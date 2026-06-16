package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.controller.Controller;
import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;

/*La sous-classe Viking qui hérite de Enemy.
 * Cet ennemi attaque au corps à corps (portée très faible).
 * Il cible uniquement les chevaliers (Knight) et se déplace
 * vers eux en utilisant le BFS pour les atteindre.
 * Sa portée d'attaque est de 0.1.
 * Un viking a comme attribut spécifique:
 * -sa cible (target) qu'il doit attaquer
 */

public class Viking extends Enemy {
    private Knight target;

    public Viking(Environnement env,int vId,double vX, double vY) {
        super(  env,
                env.getSettings().getVikingHp(),
                env.getSettings().getVikingDmg(),
                vId,
                0.1,
                vX,
                vY,
                env.getSettings().getVikingSpeed(),
                env.getSettings().getVikingDeathValue(),
                env.getSettings().getVikingSpeedAct()
        );
        target = null;
    }

    public void act(){
        tick();
        this.target = (Knight) searchTarget();
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
