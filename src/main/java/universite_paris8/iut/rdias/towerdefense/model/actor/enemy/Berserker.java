package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;


/*La sous-classe Berserker qui hérite de Enemy.
 * Cet ennemi attaque au corps à corps (portée très faible).
 * Il cible uniquement les chevaliers (Knight) et se déplace
 * vers eux en utilisant le BFS pour les atteindre.
 * Sa portée d'attaque est de 0.1.
 * Un berserker a comme attribut spécifique:
 * -sa cible (target) qu'il doit attaquer
 */

public class Berserker extends MeleeVikings {

    public Berserker(Environnement bEnv, int eId, double eX, double eY) {
        super(bEnv,
                bEnv.getSettings().getBerserkerHp(),
                bEnv.getSettings().getBerserkerDmg(),
                eId,
                0.1,
                eX,
                eY,
                bEnv.getSettings().getBerserkerSpeed(),
                bEnv.getSettings().getBerserkerDeathValue());
    }

}
