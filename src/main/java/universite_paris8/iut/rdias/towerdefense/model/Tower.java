package universite_paris8.iut.rdias.towerdefense.model;

public abstract class Tower extends Actor{

    private int cost;
    private double range;
    private double speedAttack;

    public Tower(int tHp, int tDmg, int tId, double tRange, double tX, double tY, double tSpeedAttack) {
        super(tHp, tDmg, tId, tRange, tX, tY);
        this.speedAttack = speedAttack;
    }


}
