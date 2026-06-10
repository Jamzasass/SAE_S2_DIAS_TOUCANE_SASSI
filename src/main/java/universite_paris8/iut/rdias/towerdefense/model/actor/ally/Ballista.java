package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

import java.util.ArrayList;

public class Ballista extends Tower {

    public Ballista(Environnement env, int ballistaHP, int ballistaDmg, int ballistaId, int ballistaRange, double ballistaX, double ballistaY, int ballistaSpeedAttack, int ballistaCost) {
        super(env, ballistaHP , ballistaDmg, ballistaId, ballistaRange, ballistaX, ballistaY, ballistaSpeedAttack, ballistaCost);
    }

    @Override
    public void act(){
        tick();
        if (canAct()) {
            ArrayList<Enemy> targets = searchTargets();
            if (!targets.isEmpty()){
                for (Enemy e : targets) {
                    e.takeDamage(getDmg());
                }
                resetCooldown();
            }
        }
    }

    private ArrayList<Enemy> searchTargets() {
        Enemy first = null, second = null;
        double firstDist = Double.MAX_VALUE, secondDist = Double.MAX_VALUE;

        for (Enemy e : getEnvironnement().getEnemies()) {
            if (e.isLiving()) {
                double dist = Math.hypot(e.getX() - getX(), e.getY() - getY()); //calcul distance
                if (dist <= getRange()) {
                    if (dist < firstDist) {
                        second = first;
                        secondDist = firstDist;
                        first = e;
                        firstDist = dist;
                    } else if (dist < secondDist) {
                        second = e;
                        secondDist = dist;
                    }
                }
            }
        }
        ArrayList<Enemy> result = new ArrayList<>();
        if (first != null) {
            result.add(first);
        }
        if (second != null) {
            result.add(second);
        }
        return result;
    }

    @Override
    public void upgrade() {
        if (getLevel() < 2) {
//            setHp(hplvl2);
//            setDmg(dmglvl2);
//            setSpeedAct(speedAttacklvl2);
//            lvlUp();
        }
    }
}
