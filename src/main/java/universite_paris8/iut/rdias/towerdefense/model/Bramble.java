package universite_paris8.iut.rdias.towerdefense.model;



public class Bramble extends Tower{

    public Bramble(Environnement env, int tHp, int tDmg, int tId, double tRange, double tX, double tY, int tSpeedAttack, int cost) {
        super(env, tHp, tDmg, tId, tRange, tX, tY, tSpeedAttack, cost);
    }
    public void act() {
        System.out.println("acting ...");
    }
    public void upgrade() {
        System.out.println("upgrading ...");
    }
}
