package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;

/*La sous-classe ShieldViking qui hérite de Enemy.
 * Cet ennemi attaque au corps à corps (portée très faible).
 * Il cible uniquement les chevaliers (Knight) et se déplace
 * vers eux en utilisant le BFS pour les atteindre.
 * Sa portée d'attaque est de 0.1.
 * Un shieldviking a comme attribut spécifique:
 * -sa cible (target) qu'il doit attaquer
 */

public class ShieldViking extends Enemy {

    private Knight target;

    public ShieldViking(Environnement sEnv, int eId, double eX, double eY) {
        super(sEnv,
                sEnv.getSettings().getShieldwarriorHp(),
                sEnv.getSettings().getShieldwarriorDmg(),
                eId,
                0.1,
                eX,
                eY,
                sEnv.getSettings().getShieldwarriorSpeed(),
                sEnv.getSettings().getShieldwarriorDeathValue(),
                sEnv.getSettings().getShielSpeedAct()
        );
        target = null;
    }

    @Override
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
        }
        else {
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
