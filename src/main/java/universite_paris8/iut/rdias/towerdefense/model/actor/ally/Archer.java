package universite_paris8.iut.rdias.towerdefense.model.actor.ally;


import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.Projectile;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

public class Archer extends Tower {
    public Archer(Environnement aEnv,int archerId, double aX, double aY) {
        super(  aEnv,
                aEnv.getSettings().getArcherHp(),
                aEnv.getSettings().getArcherDmg(),
                archerId,
                aEnv.getSettings().getArcherRange(),
                aX,
                aY,
                aEnv.getSettings().getArcherSpeedAttack(),
                aEnv.getSettings().getArcherCost()
        );
    }

    @Override
    public void act() {
        tick();
        if (canAct()){
            Enemy target = (Enemy) searchTarget();
            if (target != null) {
                Projectile proj = new Projectile(getEnvironnement(), getX(), getY(), this.getDmg(), target);
                getEnvironnement().addEffect(proj);
                resetCooldown();
            }
        }
    }

    @Override
    public int placementRange(){
        return 3;
    }
}
