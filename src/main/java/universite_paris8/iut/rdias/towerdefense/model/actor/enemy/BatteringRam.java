package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Palissade;

import java.util.ArrayList;

/*La sous-classe RamWarrior qui hérite de Enemy.
 * Cet ennemi attaque au corps à corps et cible
 * uniquement les Palissade. Il est le seul ennemi
 * capable de détruire les palissades. Sa portée
 * d'attaque est de 0.5. Il utilise le BFS pour
 * trouver le chemin le plus proche vers sa cible.
 * Un ramwarrior a comme attribut spécifique:
 * -sa cible (target) qu'il doit attaquer
 */

public class BatteringRam extends Enemy {

    private Tower target;

    public BatteringRam(Environnement rEnv, int eId, double eX, double eY) {
        super(rEnv,
                rEnv.getSettings().getRamwarriorHp(),
                rEnv.getSettings().getRamwarriorDmg(),
                eId,
                0.5,
                eX,
                eY,
                rEnv.getSettings().getRamwarriorSpeed(),
                rEnv.getSettings().getRamwarriorDeathValue());
    }

    @Override
    public void act(){
        tick();
        searchtarget();
        if (target != null) {
            if (target instanceof Palissade){
                ArrayList<int[]> dist = getEnvironnement().getGround().getAllClosestPaths((int)target.getY(), (int)target.getX());
                int distClosest = getEnvironnement().getGround().getMapBFS().getDistancesMap()[(int)this.getY()][(int)this.getX()][dist.get(0)[0]][dist.get(0)[1]];
                int[] cible = dist.get(0);
                for (int[] pE : dist) {
                    if (distClosest >= getEnvironnement().getGround().getMapBFS().getDistancesMap()[(int)this.getY()][(int)this.getX()][pE[0]][pE[1]]) {
                        cible = pE;
                        distClosest = getEnvironnement().getGround().getMapBFS().getDistancesMap()[(int)this.getY()][(int)this.getX()][pE[0]][pE[1]];
                    }
                }
                setxCible(cible[1]);
                setyCible(cible[0]);
                this.move();
                if (distClosest < this.getRange() && canAct()) {
                    target.takeDamage(this.getDmg());
                    System.out.println("a l'attaque" + distClosest);
                    resetCooldown();
                }
            }
        } else {
            setxCible(42);
            setyCible(40);
            this.move();
        }
        if (getEnvironnement().getGround().isCastle((int)this.getY(), (int)this.getX())) {
            getEnvironnement().getCastle().takeDamage(this.getDmg());
            this.die();
        }
    }

    public void searchtarget() {
        Tower closeTarget = null;
        double minRange = Double.MAX_VALUE;
        for (Tower t : getEnvironnement().getTowers()) {
            if (t.isLiving() && (t instanceof Palissade)) {
                double range = calculDistanceFromEnemyByPyth(t); // calcul distance via pythagore
                if (range <= minRange) { //(range <= getRange() && range < minRange) {
                    minRange = range;
                    closeTarget = t;
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
}
