package universite_paris8.iut.rdias.towerdefense.model;

public class Knight extends Soldier {


    public Knight(Environnement env, int kHp, int kDmg, int kId, double kX, double kY) {
        super(env, kHp, kDmg, kId, 1, kX, kY, 0.05);
    }
    public Knight(Environnement env, int kId, double kX, double kY) {
        super(env, 70, 20, kId, 1, kX, kY, 0.05);
    }
    public void act() {
        System.out.println("soldat acting");
    }

    public void searchtarget() {

    }
}
