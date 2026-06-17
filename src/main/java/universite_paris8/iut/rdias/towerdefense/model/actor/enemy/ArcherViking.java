package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.Projectile;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Bramble;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Palissade;

/*La sous-classe ArcherViking qui hérite de Enemy.
 * Cet ennemi attaque à distance avec des flèches.
 * Il peut cibler les chevaliers (Knight) ainsi que
 * les tours, à l'exception des Palissade et Bramble.
 * Il utilise le BFS pour se déplacer vers les chevaliers
 * et le calcul de distance de pythagore pour les tours.
 * Un archer viking a comme attribut spécifique:
 * -sa cible (target) qu'il doit attaquer
 */

public class ArcherViking extends Enemy {
    private Actor target;

    public ArcherViking(Environnement aEnv, int eId, double eX, double eY) {
        super(  aEnv,
                aEnv.getSettings().getArcherVikingHp(),
                aEnv.getSettings().getArcherVikingDmg(),
                eId,
                aEnv.getSettings().getArcherVikingRange(),
                eX,
                eY,
                aEnv.getSettings().getArcherVikingSpeed(),
                aEnv.getSettings().getArcherVikingDeathValue(),
                aEnv.getSettings().getArcherSpeedAct()
        );
        target = null;
    }
    @Override
    public void act(){
        tick();
        this.target = searchTarget();
        if (target != null) {
            if (target instanceof Knight) {
                int dist = calcDistanceFromTargerUsingBFS(target);
                setxCible((int) target.getX());
                setyCible((int) target.getY());
                this.move();
                if (dist < this.getRange() && canAct()){
                    Projectile p = new Projectile(getEnvironnement(), getX(), getY(), this.getDmg(), target);
                    getEnvironnement().addEffect(p);
                    resetCooldown();
                }
            }
            else if (target instanceof Tower){
                int[] dest = getEnvironnement().getGround().getClosestPath((int)target.getY(), (int)target.getX());
                double dist = calcDistanceFromTargerUsingPyth(target);
                setxCible(dest[1]);
                setyCible(dest[0]);
                this.move();
                if (dist < Math.pow((this.getRange()), 2)){
                    if (canAct()) {
                        Projectile p = new Projectile(getEnvironnement(), getX(), getY(), this.getDmg(), target);
                        getEnvironnement().addEffect(p);
                        resetCooldown();
                    }
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

    public Actor searchTarget() {
        Actor closeTarget = null;
        double minRange = Double.MAX_VALUE;
        for (Tower t : getEnvironnement().getTowers()) {
            if (!(t instanceof Bramble) && !(t instanceof Palissade) && t.isLiving()) {
                double range = calcDistanceFromTargerUsingPyth(t); // calcul distance via pythagore
                if (range <= minRange) {//(range <= getRange() && range < minRange) {
                    minRange = range;
                    closeTarget = t;
                }
            }
        }
        for (Knight k : getEnvironnement().getKnights()) {
            if (k.isLiving()) {
                int range = calcDistanceFromTargerUsingBFS(k); // calcul distance via pythagore
                if (range<= minRange) {//(range <= getRange() && range < minRange) {
                    minRange = range;
                    closeTarget = k;
                }
            }
        }
        return closeTarget;
    }
}
