package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;

public class Vikings extends Enemy {

    public Vikings(Environnement env, int eId, double eX, double eY) {
        super(env, 70, 20, eId, 0.1, eX, eY, 0.05, 15);
    }


    public void act(){
        this.move();
        if (getEnvironnement().getGround().isCastle((int) this.getY(), (int) this.getX())) {
            getEnvironnement().takeDmgCastle(this.getDmg());
            this.die();
        }
    }


}
