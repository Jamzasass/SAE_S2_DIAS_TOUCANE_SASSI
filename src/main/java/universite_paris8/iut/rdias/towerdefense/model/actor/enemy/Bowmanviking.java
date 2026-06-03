package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;

public class Bowmanviking extends Enemy {

    public Bowmanviking(Environnement env, int eId, double eX, double eY) {
        super(env, 50, 20, eId, 4, eX, eY, 0.10, 25);
    }

    public void act(){
        System.out.println("A l'attaque");
    }
}
