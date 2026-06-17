package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.ZoneSpell;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.Settings;

/*La sous-classe SorcererTower qui hérite de Tower.
 * Cette tour attaque à distance avec des boules de feu
 * qui explosent à l'impact, infligeant des dégâts à tous
 * les ennemis se trouvant dans le rayon d'explosion.
 * Une tour sorcier a comme attribut spécifique:
 * -le rayon de l'explosion de ses boules de feu
 */

public class SorcererTower extends Tower {
    private double radiusBlow;

    public SorcererTower(Environnement sEnv, int sorcererId, double sorcererX, double sorcererY) {
        super(  sEnv,
                sEnv.getSettings().getSorcererTowerHp(),
                sEnv.getSettings().getSorcererTowerDmg(),
                sorcererId,
                sEnv.getSettings().getSorcererRange(),
                sorcererX,
                sorcererY,
                sEnv.getSettings().getSorcererTowerSpeedAttack(),
                sEnv.getSettings().getSorcererTowerCost()
        );
        radiusBlow = sEnv.getSettings().getSorcererRadiusBlow();
    }

    @Override
    public void act(){
        tick();
        if (canAct()){
            Enemy target = (Enemy) searchTarget();
            if (target != null){
                ZoneSpell zp = new ZoneSpell(getEnvironnement(), getX(), getY(), getDmg(), target, radiusBlow);
                getEnvironnement().addEffect(zp);
                resetCooldown();
            }
        }
    }

    @Override
    public int placementRange(){
        return 4;
    }
}
