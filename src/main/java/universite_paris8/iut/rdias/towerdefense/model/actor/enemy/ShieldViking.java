package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;

/*La sous-classe ShieldViking qui hérite de Enemy.
 * Cet ennemi attaque au corps à corps (portée très faible).
 * Il cible uniquement les chevaliers (Knight) et se déplace
 * vers eux en utilisant le BFS pour les atteindre.
 * Sa portée d'attaque est de 0.1.
 * Un shieldviking a comme attribut spécifique:
 * -sa cible (target) qu'il doit attaquer
 */

public class ShieldViking extends MeleeVikings {

    public ShieldViking(Environnement sEnv, int eId, double eX, double eY) {
        super(sEnv,
                sEnv.getSettings().getShieldwarriorHp(),
                sEnv.getSettings().getShieldwarriorDmg(),
                eId,
                0.1,
                eX,
                eY,
                sEnv.getSettings().getShieldwarriorSpeed(),
                sEnv.getSettings().getShieldwarriorDeathValue());
    }
}
