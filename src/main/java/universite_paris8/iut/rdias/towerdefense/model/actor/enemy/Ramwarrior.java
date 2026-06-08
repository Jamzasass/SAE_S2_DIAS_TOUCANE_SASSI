package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;

public class Ramwarrior extends Enemy {

    public Ramwarrior(Environnement env, int eHp, int eDmg, int eId, double eX, double eY, double eSpeed, int eDeathValue) {
        super(env, eHp, eDmg, eId, 0, eX, eY, eSpeed, eDeathValue);
    }

    public void act(){
        System.out.println("A l'attaque");
    }
}
