package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;

/* Berserker
 * Viking très rapide et plus résistant qu'un simple viking.
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
                bEnv.getSettings().getBerserkerDeathValue(),
                bEnv.getSettings().getBerserkerSpeedAct()
        );
    }
}
