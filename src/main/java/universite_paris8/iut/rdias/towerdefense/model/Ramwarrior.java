package universite_paris8.iut.rdias.towerdefense.model;

public class Ramwarrior extends Enemy{

    public Ramwarrior(Environnement env, int eId, double eX, double eY) {
        super(env, 250, 100, eId, 0, eX, eY, 0.03, 80);
    }

    public void act(){
        System.out.println("A l'attaque");
    }
}
