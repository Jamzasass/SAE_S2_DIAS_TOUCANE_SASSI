package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

public class Barrack extends Tower {

    private static final int hplvl1 = 250;
    private static final int hplvl2 = 325;
    private static final int speedProductionlvl1 = 10;
    private static final int speedProductionlvl2 = 20;
    private static final int knightHPlvl1 = 70;
    private static final int knightHPlvl2 = 105;
    private static final int knightDmglvl1 = 10;
    private static final int knightDmglvl2 = 20;
    private static int nextKnightId = 0;
    private int[] coordClosePath;


    public Barrack (Environnement env, int bHp, int barrackId, double barrackX, double barrackY, int bSpeedProduction, int bCost) {
        super(env, bHp, 0, barrackId, 0, barrackX, barrackY, bSpeedProduction, bCost);
        coordClosePath = closestPath();
    }

    @Override
    public void act(){
        tick();
        if (canAct() && hasEnemies(getEnvironnement())){
            int hpKnight = getLevel() == 2 ? knightHPlvl2 : knightHPlvl1;
            int dmgKnight = getLevel() == 2 ? knightDmglvl2 : knightDmglvl1;
            Knight k = new Knight(getEnvironnement(), hpKnight, dmgKnight, nextKnightId++, coordClosePath[1], coordClosePath[0]);
            getEnvironnement().addKnight(k);
            resetCooldown();
        }
    }

    @Override
    public void upgrade(){
        if (getLevel() < 2){
            setHp(hplvl2);
            setSpeedAct(speedProductionlvl2);
            lvlUp();
            System.out.println("caca2");
        }
    }

    private boolean hasEnemies(Environnement env){
        for (Enemy e : env.getEnemies()){
            if (e.isLiving()) {
                return true;
            }
        }
        return false;
    }
    public int[] closestPath() {
        int[] closePath = new int[2];
        for (int i=1; i<6; i++) {
            if (this.getX()+i<getEnvironnement().getGround().width() &&
                    getEnvironnement().getGround().isPath((int)this.getY(), (int)this.getX()+i)) {
                closePath[0] = (int)this.getY();
                closePath[1] = (int)this.getX()+i;
                return closePath;
            }
            else if (this.getX()-i >= 0 &&
                    getEnvironnement().getGround().isPath((int)this.getY(), (int)this.getX()-i)) {
                closePath[0] = (int)this.getY();
                closePath[1] = (int)this.getX()-i;
                return closePath;
            }
            else if (this.getY()+i < getEnvironnement().getGround().heigth() &&
                    getEnvironnement().getGround().isPath((int)this.getY()+i, (int)this.getX())) {
                closePath[0] = (int)this.getY()+i;
                closePath[1] = (int)this.getX();
                return closePath;
            }
            else if (this.getY()-i >= 0 &&
                    getEnvironnement().getGround().isPath((int)this.getY()-i, (int)this.getX())) {
                closePath[0] = (int)this.getY()-i;
                closePath[1] = (int)this.getX();
                return closePath;
            }
        }
        return closePath;
    }
}
