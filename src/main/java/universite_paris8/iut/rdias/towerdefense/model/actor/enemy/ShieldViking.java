package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;

/* Viking au bouclier
 * Viking avec beaucoup plus de points de vie mais très lent
 */

public class ShieldViking extends MeleeVikings {

    public ShieldViking(Environnement sEnv, int eId, double eX, double eY) {
        super(  sEnv,
                sEnv.getSettings().getShieldwarriorHp(),
                sEnv.getSettings().getShieldwarriorDmg(),
                eId,
                0.1,
                eX,
                eY,
                sEnv.getSettings().getShieldwarriorSpeed(),
                sEnv.getSettings().getShieldwarriorDeathValue(),
                sEnv.getSettings().getShieldwarriorSpeedAct()
        );
    }
}
