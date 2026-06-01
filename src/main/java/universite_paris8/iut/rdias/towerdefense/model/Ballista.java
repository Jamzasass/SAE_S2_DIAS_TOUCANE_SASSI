package universite_paris8.iut.rdias.towerdefense.model;

import java.util.ArrayList;
import java.util.List;

public class Ballista extends Tower{

    private static final int hplvl1 = 300;
    private static final int hplvl2 = 375;
    private static final int dmglvl1 = 30;
    private static final int dmglvl2 = 55;
    private static final int speedAttacklvl1 = 3;
    private static final int speedAttacklvl2 = 1;

    public Ballista(Environnement env, int ballistaId, double ballistaX, double ballistaY) {
        super(env, hplvl1, dmglvl1, ballistaId, 4.0, ballistaX, ballistaY, speedAttacklvl1, 350);
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
            setHp(hplvl2);
            setDmg(dmglvl2);
            setSpeedAct(speedAttacklvl2);
            lvlUp();
        }
    }
}
