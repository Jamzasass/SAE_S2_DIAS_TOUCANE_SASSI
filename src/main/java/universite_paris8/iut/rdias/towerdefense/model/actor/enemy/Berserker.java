package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;

public class Berserker extends Enemy {


    public Berserker(Environnement env, int eId, double eX, double eY) {
        super(env, 125, 20, eId, 0, eX, eY, 0.06, 45);
    }


    public void act(){
        System.out.println("A l'attaque");
    }
}
