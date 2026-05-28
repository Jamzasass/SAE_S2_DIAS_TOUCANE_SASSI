package universite_paris8.iut.rdias.towerdefense.model;

public abstract class Enemy extends Soldier {
    private int deathValue;

    public Enemy(Environnement env, int eHp, int eDmg, int eId, double eRange, double eX, double eY, double eSpeed, int eDeathValue) {
        super(env, eHp, eDmg, eId, eRange, eX, eY, eSpeed);
        this.deathValue = eDeathValue;
    }

    public void act(Environnement env){
        this.move(env);
    }

    public int getDeathValue(){
        return deathValue;
    }

}
