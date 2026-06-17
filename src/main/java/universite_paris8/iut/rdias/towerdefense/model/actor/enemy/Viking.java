package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;

/* Viking Simple
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
