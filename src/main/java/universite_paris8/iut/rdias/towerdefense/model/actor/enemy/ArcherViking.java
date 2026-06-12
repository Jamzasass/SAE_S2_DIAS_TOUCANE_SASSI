package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.Projectile;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Bramble;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Palissade;

public class ArcherViking extends Enemy {
    private Actor target;

//TODO speedAttack or speedAttack for ArcherViking

    public ArcherViking(Environnement aEnv, int eId, double eX, double eY) {
        super(aEnv,
                aEnv.getSettings().getArcherVikingHp(),
                aEnv.getSettings().getArcherVikingDmg(),
                eId,
                aEnv.getSettings().getArcherVikingRange(),
                eX,
                eY,
                aEnv.getSettings().getArcherVikingSpeed(),
                aEnv.getSettings().getArcherVikingDeathValue());
        target = null;
    }
    @Override
    public void act(){
        searchtarget();
        tick();
        if (target != null) {
            if (target instanceof Knight) {
                int dist = calculDistanceFromEnemyByBFS(target);
                setxCible((int) target.getX());
                setyCible((int) target.getY());
                this.move();
                if (dist < this.getRange() && canAct()){
                    System.out.println("d " + dist + " - " + this.getRange());
//                    target.takeDamage(getDmg());
                    Projectile p = new Projectile(getEnvironnement(), getX(), getY(), this.getDmg(), target);
                    getEnvironnement().addEffect(p);
                    resetCooldown();
                }
            }
            else if (target instanceof Tower){
                int[] dest = getEnvironnement().getGround().getClosestPath((int)target.getY(), (int)target.getX());
                double dist = calculDistanceFromEnemyByPyth(target);
                setxCible(dest[1]);
                setyCible(dest[0]);
                if (dist < Math.pow((this.getRange()), 2)){
                    if (canAct()) {
//                        target.takeDamage(this.getDmg());
                        Projectile p = new Projectile(getEnvironnement(), getX(), getY(), this.getDmg(), target);
                        getEnvironnement().addEffect(p);
                        resetCooldown();
                    }
                }
                else {
                    this.move();
                }
            }
        } else {
            setxCible(40);
            setyCible(42);
            this.move();
        }
        if (getEnvironnement().getGround().isCastle((int)this.getY(), (int)this.getX())) {
            getEnvironnement().getCastle().takeDamage(this.getDmg());
            this.die();
        }
    }

    public void searchtarget() {
        Actor closeTarget = null;
        double minRange = Double.MAX_VALUE;
        for (Tower t : getEnvironnement().getTowers()) {
            if (!(t instanceof Bramble) && !(t instanceof Palissade) && t.isLiving()) {
                double range = calculDistanceFromEnemyByPyth(t); // calcul distance via pythagore
                if (range <= minRange) {//(range <= getRange() && range < minRange) {
                    minRange = range;
                    closeTarget = t;
                }
            }
        }
        for (Knight k : getEnvironnement().getKnights()) {
            if (k.isLiving()) {
                int range = calculDistanceFromEnemyByBFS(k); // calcul distance via pythagore
                if (range<= minRange) {//(range <= getRange() && range < minRange) {
                    minRange = range;
                    closeTarget = k;
                }
            }
        }
        this.target = closeTarget;
    }

    public double calculDistanceFromEnemyByPyth(Actor a) {
        double dx = getX() - a.getX();
        double dy = getY() - a.getY();
        return dx * dx + dy * dy;
    }
    public int calculDistanceFromEnemyByBFS(Actor a) {
        return getEnvironnement().getGround().getMapBFS().getDistancesMap()[(int)getY()][(int)getX()][(int)a.getY()][(int)a.getX()];
    }


    public int[] closestPath(Tower t) {
        int[] closePath = new int[2];
        for (int i=1; i<6; i++) {
            if (t.getX()+i<getEnvironnement().getGround().width() &&
                    getEnvironnement().getGround().isPath((int)t.getY(), (int)t.getX()+i)) {
                closePath[0] = (int)t.getY();
                closePath[1] = (int)t.getX()+i;
                return closePath;
            }
            else if (t.getX()-i >= 0 &&
                    getEnvironnement().getGround().isPath((int)t.getY(), (int)t.getX()-i)) {
                closePath[0] = (int)t.getY();
                closePath[1] = (int)t.getX()-i;
                return closePath;
            }
            else if (t.getY()+i < getEnvironnement().getGround().heigth() &&
                    getEnvironnement().getGround().isPath((int)t.getY()+i, (int)t.getX())) {
                closePath[0] = (int)t.getY()+i;
                closePath[1] = (int)t.getX();
                return closePath;
            }
            else if (t.getY()-i >= 0 &&
                    getEnvironnement().getGround().isPath((int)t.getY()-i, (int)t.getX())) {
                closePath[0] = (int)t.getY()-i;
                closePath[1] = (int)t.getX();
                return closePath;
            }
        }
        return closePath;
    }
}
