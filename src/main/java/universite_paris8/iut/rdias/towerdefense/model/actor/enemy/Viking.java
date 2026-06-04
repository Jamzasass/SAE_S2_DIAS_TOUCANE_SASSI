package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;

public class Viking extends Enemy {

    public Viking(Environnement env, int vHp, int vDmg, int vId, double vSpeed, double vX, double vY) {
        super(env, vHp, vDmg, vId, 0.1, vX, vY, vSpeed, 15);
    }


    public void act(){
        this.move();
        if (getEnvironnement().getGround().isCastle((int) this.getY(), (int) this.getX())) {
            getEnvironnement().takeDmgCastle(this.getDmg());
            this.die();
        }
    }


}
