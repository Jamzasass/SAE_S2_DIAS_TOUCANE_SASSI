package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;

public class Bowmanviking extends Enemy {

    public Bowmanviking(Environnement env, int eHp, int eDmg, int eId, int eRange, double eX, double eY, double eSpeed, int eDeathValue) {
        super(env, eHp, eDmg, eId, eRange, eX, eY, eSpeed, eDeathValue);
    }

    public void act(){
        this.move();
    }
}
