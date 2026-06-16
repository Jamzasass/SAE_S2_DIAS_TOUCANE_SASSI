package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Soldier;

/*La sous-classe Knight qui hérite de Soldier.
 * Le chevalier est spawné par la Barrack et se déplace
 * vers les ennemis pour les attaquer au corps à corps
 * (portée d'attaque de 1). Sa cible par défaut est le château pour le defendre.
 * Un chevalier a comme attribut spécifique:
 * -sa cible (target) qu'il doit attaquer
 */

public class Knight extends Soldier {
    private Enemy target;
    private Barrack base;

    public Knight(Environnement kEnv, int kId, double kX, double kY, Barrack kBase) {
        super(  kEnv,
                kEnv.getSettings().getKnightHp(),
                kEnv.getSettings().getKnightDmg(),
                kId,
                1,
                kX,
                kY,
                kEnv.getSettings().getKnightSpeed(),
                kBase.getCoordClosePath()[1],
                kBase.getCoordClosePath()[0],
                kEnv.getSettings().getKnightSpeedAct()
        );
        this.base = kBase;
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
        else if (target == null) {
            setxCible(base.getCoordClosePath()[1]);
            setyCible(base.getCoordClosePath()[0]);
        }
    }

    public Barrack getBase() {
        return base;
    }
}
