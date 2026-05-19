package universite_paris8.iut.rdias.towerdefense.model;

public class Enemy extends Soldier {
    private int deathValue;

    public Enemy(int eHp, int eDmg, int eId, double eRange, double eX, double eY, double eSpeed, int eDeathValue) {
        super(eHp, eDmg, eId, eRange, eX, eY, eSpeed);
        this.deathValue = eDeathValue;
    }

    @Override
    public void act(){

    }

    public int getDeathValue(){
        return deathValue;
    }

}
