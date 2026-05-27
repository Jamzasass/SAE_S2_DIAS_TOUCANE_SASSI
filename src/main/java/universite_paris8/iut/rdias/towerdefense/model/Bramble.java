package universite_paris8.iut.rdias.towerdefense.model;



public class Bramble extends Tower{

    public Bramble(int tHp, int tDmg, int tId, double tRange, double tX, double tY, double tSpeedAttack, int cost) {
        super(tHp, tDmg, tId, tRange, tX, tY, tSpeedAttack, cost);
    }
    public void act(Environnement env) {
        System.out.println("acting ...");
    }
    public void upgrade() {
        System.out.println("upgrading ...");
    }
}
