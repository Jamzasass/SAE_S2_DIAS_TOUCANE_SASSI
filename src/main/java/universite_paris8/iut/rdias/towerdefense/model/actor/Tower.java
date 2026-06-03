package universite_paris8.iut.rdias.towerdefense.model.actor;

import universite_paris8.iut.rdias.towerdefense.model.Environnement;

public abstract class Tower extends Actor{

    private final int cost;
    private int speedAct; //en demi seconde (si 1 = une attaque possible toutes le 1/2 secondes
    private int level;
    private int cooldown;


    public Tower(Environnement env, int tHp, int tDmg, int tId, double tRange, double tX, double tY, int speedAct, int tCost) {
        super(env, tHp, tDmg, tId, tRange, tX, tY);
        this.cost = tCost;
        this.speedAct = speedAct;
        this.cooldown = speedAct*30;
        this.level = 1;
    }

    public int getCost() {
        return cost;
    }

    public double getSpeedAct() {
        return speedAct;
    }

    public int getLevel() {
        return level;
    }
    public int getCooldown() {
        return cooldown;
    }

    public abstract void upgrade();

    public void tick(){
        cooldown++;
        if (cooldown > speedAct*30) {
            cooldown = speedAct*30;
        }
    }

    public boolean canAct(){
        return cooldown >= speedAct*30;
    }

    public void resetCooldown() {
        this.cooldown = 0; //jcapte pas
    }

    public void setSpeedAct(int speedAct){this.speedAct = speedAct;}

    public void lvlUp() {
        this.level++;
    }


}
