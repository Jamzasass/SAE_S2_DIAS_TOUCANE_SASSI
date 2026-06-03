package universite_paris8.iut.rdias.towerdefense.model;

import universite_paris8.iut.rdias.towerdefense.model.algorithm.AStar;

import java.util.List;

public abstract class Enemy extends Soldier {
    private int deathValue;

    public Enemy(Environnement env, int eHp, int eDmg, int eId, double eRange, double eX, double eY, double eSpeed, int eDeathValue) {
        super(env, eHp, eDmg, eId, eRange, eX, eY, eSpeed, 42, 40);
        this.deathValue = eDeathValue;
    }

    public void act(){
        this.move();
        if (getEnvironnement().getGround().isCastle((int)getY(), (int)getX())) {
            getEnvironnement().castleTakeDmg(this.getDmg());
            this.die();
        }
    }

    public int getDeathValue(){
        return deathValue;
    }

}
