package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

public class Barrack extends Tower {
    private int[] coordClosePath;


    public Barrack (Environnement bEnv, int barrackId, double barrackX, double barrackY) {
        super(bEnv,
                bEnv.getSettings().getBarrackHp(),
                bEnv.getSettings().getBallistaDmg(),
                barrackId,
                0,
                barrackX,
                barrackY,
                bEnv.getSettings().getBarrackSpeedProduction(),
                bEnv.getSettings().getBarrackCost());
        coordClosePath = closestPath();
    }

    @Override
    public void act(){
        tick();
        if (canAct() && hasEnemies(getEnvironnement())){
            Knight k = new Knight(getEnvironnement(), getEnvironnement().getId(), coordClosePath[1], coordClosePath[0]);
            getEnvironnement().addKnight(k);
            resetCooldown();
        }
    }

    @Override
    public void upgrade(){
        if (getLevel() < 2){
//            setHp(hplvl2);
//            setSpeedAct(speedProductionlvl2);
//            lvlUp();
//            System.out.println("caca2");
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
