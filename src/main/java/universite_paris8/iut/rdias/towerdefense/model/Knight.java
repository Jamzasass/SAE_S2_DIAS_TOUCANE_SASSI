package universite_paris8.iut.rdias.towerdefense.model;

public class Knight extends Soldier {


    public Knight(int kHp, int kDmg, int kId, double kX, double kY) {
        super(kHp, kDmg, kId, 1, kX, kY, 0.05);
    }
    public Knight(int kId, double kX, double kY) {
        super(10, 10, kId, 1, kX, kY, 0.05);
    }
    public void act(Environnement env) {
        System.out.println("soldat acting");
    }

    public void searchtarget() {

    }
}
