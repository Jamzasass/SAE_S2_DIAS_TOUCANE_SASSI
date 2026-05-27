package universite_paris8.iut.rdias.towerdefense.model;

public class Vikings extends Enemy{

    public Vikings(int eId, double eX, double eY) {
        super(70, 20, eId, 0.1, eX, eY, 0.05, 15);
    }


    public void act(){
        System.out.println("A l'attaque");
    }


}
