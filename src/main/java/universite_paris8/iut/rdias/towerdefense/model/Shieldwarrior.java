package universite_paris8.iut.rdias.towerdefense.model;

public class Shieldwarrior extends Enemy{

    public Shieldwarrior(Environnement env, int eId, double eX, double eY) {
        super(env, 250, 20, eId, 0, eX, eY, 0.03, 70);
    }

    public void act(){
        System.out.println("A l'attaque");
    }
}
