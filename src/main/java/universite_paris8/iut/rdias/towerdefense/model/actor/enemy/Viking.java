package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;

/*La sous-classe Viking qui hérite de Enemy.
 * Cet ennemi attaque au corps à corps (portée très faible).
 * Il cible uniquement les chevaliers (Knight) et se déplace
 * vers eux en utilisant le BFS pour les atteindre.
 * Sa portée d'attaque est de 0.1.
 * Un viking a comme attribut spécifique:
 * -sa cible (target) qu'il doit attaquer
 */

public class Viking extends MeleeVikings {
    public Viking(Environnement env,int vId,double vX, double vY) {
        super(env,
                env.getSettings().getVikingHp(),
                env.getSettings().getVikingDmg(),
                vId,
                0.1,
                vX,
                vY,
                env.getSettings().getVikingSpeed(),
                env.getSettings().getVikingDeathValue());
    }
}
